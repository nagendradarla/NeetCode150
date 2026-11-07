# Day 24 MiniMax coach

### NeetCode Day 24 — Greedy

**Focus:** Greedy works when we maintain the **most restrictive valid interpretation** and widen only when necessary.

#### 1. LC 763 — Partition Labels
**Pattern reminder**
- Record the last occurrence of each character.
- Scan, extending the current partition’s right boundary.
- Cut when the current index reaches that boundary.

**Complexity target:** `O(n)` time, `O(k)` space for the last-index map.

**Edge cases**
- Repeated characters spanning candidate cut points.
- Single-character string.

#### 2. LC 678 — Valid Parenthesis String
**Pattern reminder**
- Track:
  - `low`: minimum possible unmatched `'('`
  - `high`: maximum possible unmatched `'('`
- For `'*'`, update the range as possible `')'`, `'('`, or empty.
- If `low < 0`, the prefix is already invalid.
- At the end, `low == 0` means a valid interpretation exists.

**Complexity target:** `O(n)` time, `O(1)` space.

**Edge case**
- A `'*'` cannot rescue a prefix where ordinary `')'` makes `low` negative.

#### Similar: LC 1239 — Maximum Length of a Concatenated String
**Pattern reminder**
- Try each character as the allowed repeating character.
- For each candidate, greedily form the longest valid string using available letters.
- Track the maximum length across candidates.

**Complexity target:** `O(n²)` time, `O(n)` space.

**Quick coaching:** Ask, “What is the tightest state I need to keep?” For parentheses, that range (`low`/`high`) is the key.
