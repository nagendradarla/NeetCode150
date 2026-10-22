# NeetCode 150 — Practice Plan

**Full documentation (job-agent hub):** `~/job-agent/docs/PRACTICE.md`

- LeetCode daily usage + all CLI flags: `~/job-agent/docs/practice/leetcode.md`
- Command cheat sheet: `~/job-agent/docs/practice/commands.md`
- MiniMax `--coach` / `--verify`: `~/job-agent/docs/practice/minimax.md`

Optimal pace: **~12–13 weeks**, **2 NeetCode problems/day** + **1 similar LeetCode problem**, with a **weekly revision day**.

This is intentionally not “2 full patterns per day” (that would rush Trees/DP). Instead you go **pattern-by-pattern**, and on transition days you may touch **two patterns** when finishing one and starting the next.

---

## How each day works (~60–90 min)

| Step | Time | What |
|------|------|------|
| 1. Pattern warm-up | 5–10 min | Skim the pattern’s intuition (hashing, two pointers, window, etc.) |
| 2. Core problem A | 20–45 min | Timed: Easy 20 / Medium 35 / Hard 45 |
| 3. Core problem B | 20–45 min | Same limits |
| 4. Similar problem | 15–25 min | Same pattern reinforcement (or blind re-solve of the harder core) |
| 5. Notes | 5 min | Approach, complexity, one pitfall |

**Revision days** (every 7th day): re-solve the last ~4 problems blind. No new topics.

---

## Pace rationale

| Option | Problems/day | Duration | Verdict |
|--------|--------------|----------|---------|
| Aggressive | 4–5 | ~5 weeks | Burnout + weak retention |
| **This plan** | **2 + similar** | **~12–13 weeks** | **Best for interviews while job hunting** |
| Slow | 1 | ~5 months | Too slow if you have upcoming interviews |

150 ÷ 2 = **75 practice days** + **~12 revision days** ≈ **87 calendar days**.

---

## Pattern roadmap

Active order is in `list_reordered.txt` (and `PATTERN_ORDER` in `practice_agent/curriculum_data.py`).
Original NeetCode list remains in `list.txt`.

| # | Pattern | Problems | Notes |
|---|---------|----------|-------|
| 1 | Arrays & Hashing | 9 | Done / early |
| 2 | Two Pointers | 5 | Done / early |
| 3 | Sliding Window | 6 | Done / early |
| 4 | Stack | 7 | Done / early |
| 5 | Binary Search | 7 | Done / early |
| 6 | Greedy | 8 | **Next** (moved up) |
| 7 | Intervals | 6 | Moved up |
| 8 | Math & Geometry | 8 | Moved up |
| 9 | Bit Manipulation | 7 | Moved up |
| 10 | Linked List | 11 | Resume after Bit Manipulation |
| 11 | Trees | 15 | |
| 12 | Tries | 3 | |
| 13 | Heap / Priority Queue | 7 | |
| 14 | Backtracking | 9 | |
| 15 | Graphs | 13 | |
| 16 | Advanced Graphs | 6 | |
| 17 | 1-D DP | 12 | |
| 18 | 2-D DP | 11 | |

Exact day numbers live in `curriculum/schedule.json` (generated).

---

## Daily checklist (copy/paste)

```
[ ] Pattern notes (5–10 min)
[ ] LC ___ — solved + complexity noted
[ ] LC ___ — solved + complexity noted
[ ] Similar LC ___ — attempted
[ ] 3 bullets written (approach / complexity / pitfall)
[ ] Solution saved under src/LeetCode{num}/Solution.java
[ ] Mark day done: python practice_agent/daily_practice.py --advance
```

---

## Telegram daily alert (via job-agent)

NeetCode practice is **bundled into the existing job-agent daily Telegram alert**
(the morning job-scan message from `~/job-agent`). No separate LaunchAgent needed.

Each day the job scan alert includes:

- Day number + patterns
- LeetCode numbers + titles + links
- Short explanation / approach hint
- Similar problem for the same pattern
- Path to Java stubs

### Config (`~/job-agent/config.yaml`)

```yaml
practice:
  enabled: true
  leetcode_root: "/Users/nagendradarla/github/LeetCode"
  python_root: "/Users/nagendradarla/github/python-practice"
  include_in_telegram: true
  ensure_materials: true
  run_coach: false          # MiniMax coach uses tailoring quota; run --coach standalone
```

### One-time curriculum setup (LeetCode repo)

```bash
cd ~/github/LeetCode/practice_agent
python3 -m venv venv
./venv/bin/pip install -r requirements.txt
./venv/bin/python build_curriculum.py
```

### Preview / advance day

```bash
# Preview the LeetCode + Python block job-agent will append
cd ~/job-agent && ./venv/bin/python practice_digest.py

# After you finish today's problems
~/github/LeetCode/practice_agent/venv/bin/python \
  ~/github/LeetCode/practice_agent/daily_practice.py --advance
```

### Optional: standalone send (not required if job-agent runs daily)

```bash
cd ~/github/LeetCode/practice_agent
./venv/bin/python daily_practice.py --dry-run
```

A separate `com.nagendradarla.leetcode` LaunchAgent is **not needed** when job-agent
already runs every morning — keep only `com.nagendradarla.jobagent`.

### Useful commands

Complete list: `~/job-agent/docs/practice/commands.md`.

```bash
python daily_practice.py --status
python daily_practice.py --day 5 --dry-run
python daily_practice.py --coach
python daily_practice.py --verify
python daily_practice.py --advance
python generate_stubs.py --lc 217 242
```

---

## Java placeholders

Each morning (or on demand) stubs are created at:

```
src/LeetCode{number}/Solution.java
```

Same package style as your existing `LeetCode17`, `LeetCode51`, `LeetCode200`.  
**Existing files are never overwritten** (your solutions stay safe).

---

## Learning tips

1. **Speak the pattern** before coding (“sliding window with need/have counts”).
2. **One brute force → optimize** on Medium/Hard if stuck after 10 minutes.
3. **Spaced revision** beats grinding new problems only — protect revision days.
4. **Premium problems** (271, 286, 323, 261, 269, 252, 253): use NeetCode.io / YouTube if you lack Premium.
5. After finishing all 150, do a **2-week mixed random review** (2–3/day from weak patterns).

---

## Files

| Path | Purpose |
|------|---------|
| `list.txt` | Original NeetCode 150 list |
| `list_reordered.txt` | Active practice order (Greedy→…→Bit, then Linked List→…) |
| `curriculum/problems.json` | All 150 + explanations + similar + stubs |
| `curriculum/schedule.json` | Day-by-day plan |
| `curriculum/progress.json` | Your current day / completed set |
| `practice_agent/` | Stub generator + optional standalone runner |
| `~/job-agent/practice_digest.py` | Appends LeetCode + Python block to daily job Telegram |
| `PRACTICE_PLAN.md` | This document |
