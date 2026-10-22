# NeetCode 150 Practice

Structured practice for the [NeetCode 150](https://neetcode.io/practice) list: day-by-day schedule, Java solution stubs, pattern notes, and a small daily practice agent.

**Pace:** ~12–13 weeks · **2 NeetCode problems/day** + **1 similar LeetCode problem** · weekly revision day

---

## Repo layout

```
.
├── PRACTICE_PLAN.md          # Full study plan and checklist
├── list.txt                  # NeetCode 150 problem list
├── curriculum/
│   ├── problems.json         # All 150 + hints + similar problems
│   ├── schedule.json         # Day-by-day plan (~87 days)
│   └── progress.json         # Current day / completed problems
├── practice_agent/           # Curriculum builder, stubs, optional Telegram
└── src/
    └── LeetCode{num}/
        └── Solution.java     # Per-problem Java stubs / solutions
```

---

## Quick start

```bash
# Optional: local practice agent venv
cd practice_agent
python3 -m venv venv
./venv/bin/pip install -r requirements.txt

# Rebuild curriculum / schedule if needed
./venv/bin/python build_curriculum.py

# See where you are
./venv/bin/python daily_practice.py --status

# Preview today's plan (no Telegram)
./venv/bin/python daily_practice.py --dry-run

# After finishing a day
./venv/bin/python daily_practice.py --advance

# Generate stubs for specific LeetCode numbers
./venv/bin/python generate_stubs.py --lc 217 242
```

Solutions live under `src/LeetCode{number}/Solution.java` (same package style as LeetCode submissions). Existing files are not overwritten by the stub generator.

---

## Daily routine (~60–90 min)

1. Pattern warm-up (5–10 min)
2. Core problem A (Easy 20 / Medium 35 / Hard 45 min)
3. Core problem B (same limits)
4. Similar problem or blind re-solve
5. Short notes: approach, complexity, one pitfall
6. Mark day done with `--advance`

Every 7th day is **revision** (re-solve recent problems blind; no new topics).

Details and pattern roadmap: [PRACTICE_PLAN.md](./PRACTICE_PLAN.md)

---

## Patterns covered

Arrays & Hashing · Two Pointers · Sliding Window · Stack · Binary Search · Greedy · Intervals · Math & Geometry · Bit Manipulation · Linked List · Trees · Tries · Heap / Priority Queue · Backtracking · Graphs · Advanced Graphs · 1-D DP · 2-D DP

Active order: `list_reordered.txt` (original: `list.txt`).

---

## Telegram / job-agent

This repo can feed a morning Telegram alert via [Job-search-agent](https://github.com/nagendradarla/Job-search-agent): the daily job-scan message appends today's NeetCode problems, hints, and similar links, and can generate stubs.

Standalone Telegram from this repo is optional (`practice_agent/daily_practice.py`); when job-agent already runs daily, a separate LaunchAgent is not required.

---

## Useful commands

| Command | Purpose |
|---------|---------|
| `daily_practice.py --status` | Current day and progress |
| `daily_practice.py --day N` | Preview / run a specific schedule day |
| `daily_practice.py --advance` | Mark today complete → next day |
| `daily_practice.py --dry-run` | Print plan without sending Telegram |
| `generate_stubs.py --lc …` | Create Java placeholders |
| `build_curriculum.py` | Regenerate `curriculum/*.json` |

---

## License / notes

Personal interview prep workspace. LeetCode problem statements remain the property of LeetCode; this repo stores your schedule, stubs, and solutions.
