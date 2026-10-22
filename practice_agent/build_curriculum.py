"""Build a ~10-week NeetCode 150 daily schedule.

Pace (optimal, not aggressive):
  - 2 new NeetCode problems per practice day
  - 1 similar LeetCode problem for reinforcement
  - Every 7th calendar day = revision (revisit earlier patterns)
  - Harder patterns (Trees, Graphs, DP) keep the same 2/day pace with more review baked in

150 problems / 2 per day = 75 practice days + ~12 revision days ≈ 87 calendar days (~12.5 weeks).
"""

from __future__ import annotations

import json
from pathlib import Path

from curriculum_data import PROBLEMS, PATTERN_ORDER

ROOT = Path(__file__).resolve().parent.parent
CURRICULUM_DIR = ROOT / "curriculum"


def build_schedule() -> dict:
    by_nc = {p["nc"]: p for p in PROBLEMS}
    practice_days: list[dict] = []
    pattern_rank = {name: idx for idx, name in enumerate(PATTERN_ORDER)}
    ordered = sorted(
        PROBLEMS,
        key=lambda p: (pattern_rank.get(p["pattern"], 999), p["nc"]),
    )

    # Chunk into pairs of 2 NeetCode problems
    pairs: list[list[dict]] = []
    i = 0
    while i < len(ordered):
        chunk = ordered[i : i + 2]
        pairs.append(chunk)
        i += 2

    day_num = 0
    practice_idx = 0
    calendar_day = 0
    revision_every = 7  # every 7th calendar day is revision

    while practice_idx < len(pairs):
        calendar_day += 1
        if calendar_day % revision_every == 0:
            day_num += 1
            # Revise last 4 completed NeetCode problems (or fewer at start)
            done_ncs = []
            for d in practice_days:
                if d["type"] == "practice":
                    done_ncs.extend(p["nc"] for p in d["problems"])
            revise_ncs = done_ncs[-4:] if done_ncs else [1, 2]
            revise_probs = [by_nc[n] for n in revise_ncs if n in by_nc]
            patterns = sorted({p["pattern"] for p in revise_probs})
            practice_days.append(
                {
                    "day": day_num,
                    "type": "revision",
                    "patterns": patterns,
                    "problems": [
                        {
                            "nc": p["nc"],
                            "lc": p["lc"],
                            "title": p["title"],
                            "pattern": p["pattern"],
                            "difficulty": p["difficulty"],
                            "explanation": p["explanation"],
                            "url": f"https://leetcode.com/problems/{_slug(p)}/",
                        }
                        for p in revise_probs
                    ],
                    "similar": [],
                    "tasks": [
                        "Re-solve each revision problem from scratch (no peeking at old code for 20+ min).",
                        "Write the pattern intuition in 3–5 bullet points.",
                        "Note time/space complexity and one common pitfall.",
                        "If stuck >25 min, review notes then re-attempt blind.",
                    ],
                }
            )
            continue

        day_num += 1
        chunk = pairs[practice_idx]
        practice_idx += 1
        patterns = sorted({p["pattern"] for p in chunk})

        # Pick one similar problem from the first problem (prefer not already in NeetCode set)
        nc_lcs = {p["lc"] for p in PROBLEMS}
        similar_pick = None
        for p in chunk:
            for title, lc in p.get("similar", []):
                if lc not in nc_lcs:
                    similar_pick = {"title": title, "lc": lc, "pattern": p["pattern"]}
                    break
            if similar_pick:
                break
        if not similar_pick and chunk[0].get("similar"):
            title, lc = chunk[0]["similar"][0]
            similar_pick = {"title": title, "lc": lc, "pattern": chunk[0]["pattern"]}

        practice_days.append(
            {
                "day": day_num,
                "type": "practice",
                "patterns": patterns,
                "problems": [
                    {
                        "nc": p["nc"],
                        "lc": p["lc"],
                        "title": p["title"],
                        "pattern": p["pattern"],
                        "difficulty": p["difficulty"],
                        "explanation": p["explanation"],
                        "url": f"https://leetcode.com/problems/{_slug(p)}/",
                        "premium": p["lc"] in {271, 286, 323, 261, 269, 252, 253},
                    }
                    for p in chunk
                ],
                "similar": (
                    [
                        {
                            **similar_pick,
                            "url": f"https://leetcode.com/problems/{_title_to_slug(similar_pick['title'])}/",
                            "note": "Reinforcement — same pattern, attempt after core problems",
                        }
                    ]
                    if similar_pick
                    else []
                ),
                "tasks": [
                    "Read pattern notes (5–10 min) before coding.",
                    "Solve Problem A timed (Easy 20 / Medium 35 / Hard 45 min).",
                    "Solve Problem B timed with the same limits.",
                    "Attempt the similar problem OR re-solve the harder of A/B without notes.",
                    "Write 3 bullets: approach, complexity, one mistake to avoid.",
                    "Commit / save Solution.java under src/LeetCode{lc}/.",
                ],
            }
        )

    # Pattern overview for the plan doc
    pattern_days: dict[str, list[int]] = {p: [] for p in PATTERN_ORDER}
    for d in practice_days:
        if d["type"] != "practice":
            continue
        for pat in d["patterns"]:
            pattern_days.setdefault(pat, []).append(d["day"])

    return {
        "meta": {
            "name": "NeetCode 150 Daily Practice",
            "practice_problems_per_day": 2,
            "similar_per_day": 1,
            "revision_every_n_calendar_days": revision_every,
            "total_calendar_days": day_num,
            "total_practice_days": sum(1 for d in practice_days if d["type"] == "practice"),
            "total_revision_days": sum(1 for d in practice_days if d["type"] == "revision"),
            "estimated_weeks": round(day_num / 7, 1),
            "pace_note": (
                "2 NeetCode problems + 1 similar per practice day; "
                "1 revision day every week. ~12–13 weeks to finish all 150."
            ),
        },
        "pattern_day_ranges": {
            pat: {"days": days, "first": days[0] if days else None, "last": days[-1] if days else None}
            for pat, days in pattern_days.items()
        },
        "days": practice_days,
    }


def _slug(p: dict) -> str:
    # Best-effort slug from title; LeetCode URLs usually match kebab-case title
    return _title_to_slug(p["title"])


def _title_to_slug(title: str) -> str:
    s = title.lower()
    for ch in "()[],'":
        s = s.replace(ch, "")
    s = s.replace("/", " ")
    return "-".join(s.split())


def write_curriculum() -> None:
    CURRICULUM_DIR.mkdir(parents=True, exist_ok=True)

    problems_out = []
    for p in PROBLEMS:
        problems_out.append(
            {
                "nc": p["nc"],
                "title": p["title"],
                "lc": p["lc"],
                "pattern": p["pattern"],
                "difficulty": p["difficulty"],
                "explanation": p["explanation"],
                "similar": [{"title": t, "lc": lc} for t, lc in p.get("similar", [])],
                "url": f"https://leetcode.com/problems/{_slug(p)}/",
                "java_stub": p["java"],
            }
        )

    (CURRICULUM_DIR / "problems.json").write_text(
        json.dumps({"patterns": PATTERN_ORDER, "problems": problems_out}, indent=2) + "\n"
    )

    schedule = build_schedule()
    (CURRICULUM_DIR / "schedule.json").write_text(json.dumps(schedule, indent=2) + "\n")

    progress_path = CURRICULUM_DIR / "progress.json"
    if not progress_path.exists():
        progress_path.write_text(
            json.dumps(
                {
                    "start_date": None,  # set on first run, YYYY-MM-DD
                    "current_day": 1,
                    "completed_days": [],
                    "completed_problems": [],  # lc numbers
                    "timezone_note": "Schedule day advances when daily_practice.py --advance runs or after successful morning send.",
                },
                indent=2,
            )
            + "\n"
        )

    print(f"Wrote {CURRICULUM_DIR / 'problems.json'}")
    print(f"Wrote {CURRICULUM_DIR / 'schedule.json'} ({schedule['meta']['total_calendar_days']} days)")


if __name__ == "__main__":
    write_curriculum()
