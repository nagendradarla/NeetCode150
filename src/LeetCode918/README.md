# LeetCode 918: Maximum Sum Circular Subarray

## Problem Overview
Given a circular integer array `nums` of length `n`, return the maximum possible sum of a non-empty subarray of `nums`. A circular array means the end of the array wraps around to connect to the beginning.

## The Core Intuition: The Wrapping Insight
Any subarray in a circular array takes one of two forms:
1. **Non-Wrapping Subarray:** Contained entirely inside the array bounds (solved via standard Kadane's max sum).
2. **Wrapping Subarray:** Wraps around the ends, taking elements from the tail and the head. 

**The Key Formula:**
When a subarray wraps around, the elements left out in the middle form a contiguous subarray with the **minimum possible sum**. Therefore:
$$\text{Max Wrapped Sum} = \text{Total Sum} - \text{Minimum Subarray Sum}$$

---

## Running Example: `nums = [3, -1, 2, -1]`

| Step | Current Num | `totalSum` | `currMax` | `maxSum` | `currMin` | `minSum` |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| **Start** | — | `0` | `0` | `3` | `0` | `3` |
| **1** | **`3`** | `3` | `3` | `3` | `3` | `3` |
| **2** | **`-1`** | `2` | `2` | `3` | `-1` | `-1` |
| **3** | **`2`** | `4` | `4` | `4` | `1` | `-1` |
| **4** | **`-1`** | `3` | `3` | `4` | `-1` | `-1` |

### Final Calculation:
* **`totalSum`** = `3`
* **`maxSum`** (Non-wrapping) = `4`
* **`minSum`** (Minimum subarray) = `-1`
* **Wrapped Sum** = $\text{Total Sum} - \text{Min Sum} = 3 - (-1) = 4$
* **Result:** $\max(\text{maxSum}, \text{Wrapped Sum}) = \max(4, 4) = \mathbf{4}$

---

## Complexity Analysis
* **Time Complexity:** $\mathcal{O}(n)$ — Single pass through the array calculating max and min simultaneously.
* **Space Complexity:** $\mathcal{O}(1)$ — Constant space using running accumulators.