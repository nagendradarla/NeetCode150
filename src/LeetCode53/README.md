# LeetCode 53: Maximum Subarray

## Problem Overview
Given an integer array `nums`, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

## The Core Intuition: Kadane's Algorithm
Instead of checking every possible subarray (which takes $\mathcal{O}(n^2)$ or $\mathcal{O}(n^3)$ time), Kadane's algorithm uses a greedy approach in a single pass ($\mathcal{O}(n)$ time).

At every element, you face a binary choice:
1. **Extend the existing subarray:** Add the current element to your running sum.
2. **Start a fresh subarray:** If your running sum has dropped below zero, carrying it forward will only drag down future sums. Abandon it and start fresh at the current element.

Mathematically: `currentSum = max(num, currentSum + num)`

---

## Running Example: `nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]`

| Step | Current Num | `currentSum` Calculation | `currentSum` Value | `maxSum` Update |
| :---: | :---: | :--- | :---: | :---: |
| **Start** | — | — | `-2` | `-2` |
| **1** | **`1`** | $\max(1, -2 + 1) = \max(1, -1)$ | `1` | $\max(-2, 1) = \mathbf{1}$ |
| **2** | **`-3`** | $\max(-3, 1 + (-3)) = \max(-3, -2)$ | `-2` | $\max(1, -2) = \mathbf{1}$ |
| **3** | **`4`** | $\max(4, -2 + 4) = \max(4, 2)$ | `4` | $\max(1, 4) = \mathbf{4}$ |
| **4** | **`-1`** | $\max(-1, 4 + (-1)) = \max(-1, 3)$ | `3` | $\max(4, 3) = \mathbf{4}$ |
| **5** | **`2`** | $\max(2, 3 + 2) = \max(2, 5)$ | `5` | $\max(4, 5) = \mathbf{5}$ |
| **6** | **`1`** | $\max(1, 5 + 1) = \max(1, 6)$ | `6` | $\max(5, 6) = \mathbf{6}$ |
| **7** | **`-5`** | $\max(-5, 6 + (-5)) = \max(-5, 1)$ | `1` | $\max(6, 1) = \mathbf{6}$ |
| **8** | **`4`** | $\max(4, 1 + 4) = \max(4, 5)$ | `5` | $\max(6, 5) = \mathbf{6}$ |

**Result:** Maximum Subarray Sum = **`6`** (Subarray: `[4, -1, 2, 1]`).

---

## Complexity Analysis
* **Time Complexity:** $\mathcal{O}(n)$ — Single pass through the array.
* **Space Complexity:** $\mathcal{O}(1)$ — Constant space using only tracking variables.