# Day 7 MiniMax coach

Day 7 Revision — Arrays & Hashing + Two Pointers

**Pattern triggers:**
- "Consecutive" / O(1) lookup → HashSet, start only from sequence heads (no n-1).
- "Sorted + pair/triple" → Two pointers beat nested loops; skip dupes at every level.
- "Palindrome / in-place" → Opposing pointers + skip invalid chars.

**Complexity targets:**
- 128: O(n) time, O(n) space (single HashSet pass).
- 125: O(n) time, O(1) space.
- 167: O(n) time, O(1) space.
- 15: O(n²) time, O(1) extra (sorting dominates).

**Edge cases to rehearse:**
- 128: duplicates, single element, negatives, already-sequential input.
- 125: empty string, all punctuation, mixed case, single char.
- 167: exactly one solution guaranteed; pointers must not cross; sum == target.
- 15: all zeros (-1,0,0,1), duplicates after fix and inside two-pointer window, positive-only/negative-only arrays.

**Coaching tip:** For 15, the classic bug is forgetting to skip `nums[i] == nums[i-1]` *and* both pointer-side dupes after a found triplet — practice writing that boundary logic cold.

Move on to Day 8?
