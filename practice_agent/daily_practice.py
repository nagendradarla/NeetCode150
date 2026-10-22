#!/usr/bin/env python3
"""Daily NeetCode practice runner: stubs + Telegram alert (job-agent style).

Usage:
  python daily_practice.py              # send today's plan + generate stubs
  python daily_practice.py --dry-run    # print message, no Telegram
  python daily_practice.py --no-telegram  # stubs only (job-agent digest owns the alert)
  python daily_practice.py --no-coach   # skip MiniMax coach
  python daily_practice.py --day 12     # force a specific schedule day
  python daily_practice.py --advance    # mark current day complete, move to next
  python daily_practice.py --status     # show progress
  python daily_practice.py --coach      # MiniMax coaching note (job-agent .env)
  python daily_practice.py --verify     # MiniMax review of today's Solution.java
"""

from __future__ import annotations

import argparse
import json
import os
import sys
from datetime import date, datetime
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
AGENT = Path(__file__).resolve().parent
CURRICULUM = ROOT / "curriculum"
PROGRESS_PATH = CURRICULUM / "progress.json"
SCHEDULE_PATH = CURRICULUM / "schedule.json"

sys.path.insert(0, str(AGENT))

from generate_stubs import write_stubs_for_lcs  # noqa: E402

JOB_AGENT = Path.home() / "job-agent"
if str(JOB_AGENT) not in sys.path:
    sys.path.insert(0, str(JOB_AGENT))


def load_json(path: Path) -> dict:
    return json.loads(path.read_text())


def save_json(path: Path, data: dict) -> None:
    path.write_text(json.dumps(data, indent=2) + "\n")


def load_progress() -> dict:
    if not PROGRESS_PATH.exists():
        raise SystemExit("Missing curriculum/progress.json — run: python build_curriculum.py")
    return load_json(PROGRESS_PATH)


def ensure_start_date(progress: dict) -> dict:
    if not progress.get("start_date"):
        progress["start_date"] = date.today().isoformat()
        save_json(PROGRESS_PATH, progress)
    return progress


def day_from_calendar(progress: dict, schedule: dict) -> int:
    """Map calendar date → schedule day, skipping nothing (1:1 with schedule days)."""
    start = date.fromisoformat(progress["start_date"])
    today = date.today()
    offset = (today - start).days
    total = schedule["meta"]["total_calendar_days"]
    day = min(max(1 + offset, 1), total)
    # Prefer explicit current_day if user manually advanced
    return int(progress.get("current_day") or day)


def get_day_plan(schedule: dict, day: int) -> dict | None:
    for d in schedule["days"]:
        if d["day"] == day:
            return d
    return None


def format_message(day_plan: dict, progress: dict, meta: dict) -> str:
    from practice_progress import (
        completed_problem_ids,
        filter_open_problems,
        plan_has_open_problems,
        problem_lc_ids,
    )

    day = day_plan["day"]
    dtype = day_plan["type"]
    patterns = ", ".join(day_plan["patterns"]) or "—"
    total = meta["total_calendar_days"]
    week = (day - 1) // 7 + 1
    completed = completed_problem_ids(progress)
    open_plan = filter_open_problems(day_plan, completed)

    lines = [
        f"📚 NeetCode 150 — Day {day}/{total} (Week {week})",
        f"Type: {dtype.upper()} | Patterns: {patterns}",
        f"Pace: {meta['pace_note']}",
        "",
    ]

    if not plan_has_open_problems(day_plan, completed):
        lines.append("All problems on this day are already marked done.")
        lines.append("Run: python daily_practice.py --advance")
        done = len(progress.get("completed_problems", []))
        lines.append("")
        lines.append(f"Progress: {done}/150 NeetCode problems marked done")
        return "\n".join(lines)

    skipped = len(problem_lc_ids(day_plan)) - len(problem_lc_ids(open_plan))
    if skipped > 0:
        lines.append(f"Skipped {skipped} already-completed problem(s).")
        lines.append("")

    if dtype == "practice":
        lines.append("🎯 Today's core problems")
        index = 1
        for p in open_plan.get("problems") or []:
            prem = " [Premium]" if p.get("premium") else ""
            lines.append(
                f"{index}. LC {p['lc']} — {p['title']} ({p['difficulty']}){prem}\n"
                f"   Pattern: {p['pattern']}\n"
                f"   {p['url']}\n"
                f"   Hint: {p['explanation']}"
            )
            lines.append("")
            index += 1

        for s in open_plan.get("similar") or []:
            prem = " [Premium]" if s.get("premium") else ""
            diff = str(s.get("difficulty") or "").strip()
            diff_bit = f" ({diff})" if diff else ""
            lines.append(f"{index}. LC {s['lc']} — {s['title']}{diff_bit}{prem}")
            if s.get("url"):
                lines.append(f"   {s['url']}")
            note = str(s.get("note") or "").strip()
            if note:
                lines.append(f"   Hint: {note}")
            lines.append("")
            index += 1
    else:
        lines.append("🔄 Revision day — remaining unfinished only")
        for p in open_plan.get("problems") or []:
            lines.append(
                f"• LC {p['lc']} — {p['title']}\n"
                f"  {p['url']}\n"
                f"  Recall: {p['explanation']}"
            )
            lines.append("")

    lines.append("✅ Daily checklist")
    for t in day_plan.get("tasks", []):
        lines.append(f"• {t}")

    done = len(progress.get("completed_problems", []))
    lines.append("")
    lines.append(f"Progress: {done}/150 NeetCode problems marked done")
    return "\n".join(lines)


def _collect_solution_sources(lcs: list[int]) -> dict[int, str]:
    sources: dict[int, str] = {}
    for lc in lcs:
        path = ROOT / "src" / f"LeetCode{lc}" / "Solution.java"
        if path.is_file():
            sources[lc] = path.read_text(encoding="utf-8")
    return sources


def _problem_lcs(day_plan: dict) -> list[int]:
    numbers: list[int] = []
    seen: set[int] = set()
    for group in (day_plan.get("problems") or [], day_plan.get("similar") or []):
        for item in group:
            try:
                lc = int(item.get("lc"))
            except (TypeError, ValueError, AttributeError):
                continue
            if lc > 0 and lc not in seen:
                seen.add(lc)
                numbers.append(lc)
    return numbers


def run_coach(day_plan: dict, *, force: bool = False) -> str:
    try:
        from practice_llm import generate_leetcode_coach
    except ImportError:
        print("MiniMax helper missing (job-agent/practice_llm.py).")
        return ""
    text = generate_leetcode_coach(day_plan, repo=ROOT, force=force)
    if text:
        print(text)
    else:
        print("MiniMax coach unavailable (check MINIMAX_API_KEY in ~/job-agent/.env).")
    return text


def run_verify(day_plan: dict, *, force: bool = False) -> str:
    try:
        from practice_llm import verify_leetcode_solutions
    except ImportError:
        print("MiniMax helper missing (job-agent/practice_llm.py).")
        return ""
    sources = _collect_solution_sources(_problem_lcs(day_plan))
    if not sources:
        print("No Solution.java files found for today.")
        return ""
    text = verify_leetcode_solutions(day_plan, repo=ROOT, sources=sources, force=force)
    if text:
        print(text)
    else:
        print("MiniMax verify unavailable (check MINIMAX_API_KEY in ~/job-agent/.env).")
    return text


def _generate_stubs(day_plan: dict) -> list[Path]:
    """Core NeetCode stubs + similar fallback (via job-agent leetcode_practice)."""
    try:
        from leetcode_practice import generate_stubs_for_plan

        paths_str = generate_stubs_for_plan(day_plan, repo=ROOT)
        return [Path(p) for p in paths_str]
    except ImportError:
        lcs = [p["lc"] for p in day_plan.get("problems", [])]
        return write_stubs_for_lcs(lcs, overwrite=False)


def run_daily(
    *,
    day: int | None,
    dry_run: bool,
    no_stubs: bool,
    advance: bool,
    no_telegram: bool = False,
    no_coach: bool = False,
) -> None:
    if not SCHEDULE_PATH.exists():
        raise SystemExit("Run: python practice_agent/build_curriculum.py first")

    schedule = load_json(SCHEDULE_PATH)
    progress = ensure_start_date(load_progress())

    if advance:
        from practice_progress import mark_leetcode_day_complete

        cur = int(progress.get("current_day", 1))
        mark_leetcode_day_complete(schedule, progress, cur)
        save_json(PROGRESS_PATH, progress)
        print(
            f"Advanced to day {progress['current_day']}. "
            f"Completed problems: {len(progress['completed_problems'])}"
        )
        return

    target_day = day if day is not None else int(progress.get("current_day") or day_from_calendar(progress, schedule))
    # Keep current_day in sync when using calendar
    if day is None and not progress.get("current_day"):
        progress["current_day"] = target_day
        save_json(PROGRESS_PATH, progress)

    day_plan = get_day_plan(schedule, target_day)
    if not day_plan:
        raise SystemExit(f"No plan for day {target_day}")

    # Generate Java stubs for today's problems (core + similar; do not overwrite existing)
    if not no_stubs:
        paths = _generate_stubs(day_plan)
        print("Stubs ready:")
        for p in paths:
            print(f"  {p}")

    if not no_coach:
        try:
            run_coach(day_plan)
        except Exception as exc:  # noqa: BLE001
            print(f"MiniMax coach skipped: {exc}")

    msg = format_message(day_plan, progress, schedule["meta"])
    print("=" * 60)
    print(msg)
    print("=" * 60)

    if dry_run:
        print("(dry-run: Telegram not sent)")
        return
    if no_telegram:
        print("(Telegram skipped — included in job-agent digest)")
        return

    # Lazy import so --advance / --status work without Telegram deps (certifi/urllib3).
    from telegram_notify import send_telegram_long

    send_telegram_long(msg)
    try:
        from practice_digest import send_practice_email
    except ImportError as exc:
        print(f"Practice email skipped (cannot import job-agent/practice_digest.py: {exc}).")
        return
    send_practice_email(msg, subject=f"LeetCode practice {date.today().isoformat()}")


def status() -> None:
    progress = load_progress()
    schedule = load_json(SCHEDULE_PATH)
    cur = progress.get("current_day", 1)
    plan = get_day_plan(schedule, cur)
    print(f"Start date: {progress.get('start_date')}")
    print(f"Current day: {cur}/{schedule['meta']['total_calendar_days']}")
    print(f"Completed days: {len(progress.get('completed_days', []))}")
    print(f"Completed problems: {len(progress.get('completed_problems', []))}/150")
    if plan:
        print(f"Today type: {plan['type']} | patterns: {', '.join(plan['patterns'])}")
        for p in plan["problems"]:
            print(f"  - LC {p['lc']} {p['title']}")


def main() -> None:
    # Load .env from practice_agent or fall back to job-agent
    for env_path in (AGENT / ".env", Path.home() / "job-agent" / ".env"):
        if env_path.exists():
            for line in env_path.read_text().splitlines():
                line = line.strip()
                if not line or line.startswith("#") or "=" not in line:
                    continue
                k, v = line.split("=", 1)
                os.environ.setdefault(k.strip(), v.strip().strip('"').strip("'"))
            break

    parser = argparse.ArgumentParser(description="NeetCode 150 daily practice agent")
    parser.add_argument("--day", type=int, help="Schedule day number")
    parser.add_argument("--dry-run", action="store_true")
    parser.add_argument(
        "--no-telegram",
        action="store_true",
        help="Generate stubs without sending Telegram (job-agent digest owns the alert)",
    )
    parser.add_argument(
        "--no-coach",
        action="store_true",
        help="Skip MiniMax coach (job-agent daily run keeps quota for tailoring)",
    )
    parser.add_argument("--no-stubs", action="store_true")
    parser.add_argument("--advance", action="store_true", help="Mark day done and move forward")
    parser.add_argument("--status", action="store_true")
    parser.add_argument("--coach", action="store_true", help="Generate MiniMax coaching note for today")
    parser.add_argument("--verify", action="store_true", help="Review today's Solution.java with MiniMax")
    parser.add_argument("--force-llm", action="store_true", help="Regenerate MiniMax coach/verify even if cached")
    args = parser.parse_args()

    if args.status:
        status()
        return

    if args.coach or args.verify:
        schedule = load_json(SCHEDULE_PATH)
        progress = ensure_start_date(load_progress())
        target_day = args.day if args.day is not None else int(progress.get("current_day") or 1)
        plan = get_day_plan(schedule, target_day)
        if not plan:
            raise SystemExit(f"No plan for day {target_day}")
        if args.coach:
            run_coach(plan, force=args.force_llm)
        if args.verify:
            run_verify(plan, force=args.force_llm)
        return

    run_daily(
        day=args.day,
        dry_run=args.dry_run,
        no_stubs=args.no_stubs,
        advance=args.advance,
        no_telegram=args.no_telegram,
        no_coach=args.no_coach,
    )


if __name__ == "__main__":
    main()
