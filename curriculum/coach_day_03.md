# Day 3 MiniMax coach

# Day 3 — Arrays & Hashing (NeetCode)

## 🎯 Focus Patterns
1. **Frequency counting** → HashMap then bucket by count.
2. **Prefix/suffix products** → avoid division, O(n), O(1) extra.

---

## LC 347 — Top K Frequent Elements
**Reminder of the slick approach:**
- Count with `Map<Integer, Integer>`.
- **Bucket sort by frequency**: `List<Integer>[] buckets = new List[nums.length + 1]`.
- Walk buckets from high → low, collect until result size == k.
- Why it's better: O(n), no heap overhead, no sorting comparator traps.

**Edge cases to keep in your head:**
- All elements the same → `k=1`, single bucket fills.
- `k == nums.length` → return all (order doesn't matter, but bucket walk naturally handles it).
- Ties in frequency → any order is acceptable (LC doesn't specify). If asked for stable order, switch to min-heap with tie-break.

**Heap variant check:** `PriorityQueue<int[]> minHeap` of size k, comparator on count. O(n log k). Use this if memory pressure is fine but you want a 1-liner mental model.

---

## LC 238 — Product of Array Except Self
**Two-pass trick:**
- `left[i]` = product of everything left of i.
- `right[i]` = product of everything right of i.
- Answer = `left[i] * right[i]`. Optimize by filling answer array with left pass (multiply by running suffix on the fly).

**Edge cases:**
- One zero in array → that position holds full product, others are 0.
- Multiple zeros → entire array is 0. Verify your code doesn't accidentally compute non-zero elsewhere.
- `nums = [0,0]` → all zeros.

**Complexity target:** O(n) time, O(1) extra (excluding output array).

---

## 🔗 Stretch: LC 692 Top K Frequent Words
Same bucket idea, but buckets become `List<String>[]` and you must **sort each bucket lexicographically** before walking. Tie-break: higher freq wins, then lexicographically smaller wins.

---

## 💡 Coaching nudge for tomorrow
When you see "top k", ask: *do I need ordering within ties?* That decides bucket+sort vs. heap vs. quick-select.

— *Coach* �
