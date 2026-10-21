# Day 20 MiniMax coach

# Day 20 — Linked List Foundations 🎯

## Mental Model
Linked lists = **rearranging pointers in place**. Draw it out. Always check `null` before `next.next`.

---

## LC 206 Reverse Linked List
**Pattern:** Three pointers (`prev`, `curr`, `next`) walk once. Save `next` → flip `curr.next` → advance both.

**Complexity:** O(n) time, O(1) space.

**Edge cases:**
- Empty list / single node → return head as-is.
- Two nodes → confirm `prev` ends pointing to the new head.

**Recursive trap:** Reverse the rest first, *then* `head.next.next = head; head.next = null`. Forgetting the last step creates a cycle.

---

## LC 21 Merge Two Sorted Lists
**Pattern:** Dummy sentinel + tail pointer. Compare heads, attach the smaller, advance that list. At end, attach whichever list is non-null.

**Complexity:** O(n+m) time, O(1) space (dummy trick avoids building a new list).

**Edge cases:**
- One or both lists empty.
- Equal values → attach `list1` (or either), no infinite loop.
- Watch the final `tail.next = list1 ?? list2` line — easy to drop.

---

## LC 92 Reverse Linked List II (the twist)
Reverse **only positions `left..right`**. Pattern: walk to `left-1`, then reverse exactly `right-left+1` nodes using the LC 206 technique. Edge case: `left == 1` → no "before" node; use a dummy head to keep code uniform.

---

## Today's Drill (15 min)
1. LC 206 iterative → recursive.
2. LC 21 with dummy.
3. Peek LC 92, identify where you'd splice.

**Spot check:** Can you reverse a sublist in one pass without losing the pointer to `left-1`?
