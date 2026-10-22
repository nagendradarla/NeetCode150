# LeetCode 55: Jump Game

## Problem Overview
You are given an integer array `nums`. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position. Return `true` if you can reach the last index, or `false` otherwise.

## The Core Intuition: Greedy Horizon Tracking
Instead of trying every possible sequence of jumps (exponential backtracking), track a single variable: **`maxReach`** (the farthest index you can possibly reach at any given moment).

As you iterate through the array:
1. **Check if stuck:** If your current index `i` is greater than your `maxReach`, you cannot cross this point. Return `false`.
2. **Expand horizon:** Update `maxReach` to be the maximum of its current value and `i + nums[i]`.
3. **Early exit:** If `maxReach` ever equals or exceeds the last index, return `true` immediately.

---

## Running Example: `nums = [2, 3, 1, 1, 4]`

| Index (`i`) | Value (`nums[i]`) | Check: `i > maxReach`? | `maxReach` Calculation | Horizon Reached Last Index? |
| :---: | :---: | :---: | :--- | :---: |
| **0** | **`2`** | No ($0 > 0$ is false) | $\max(0, 0 + 2) = \mathbf{2}$ | No ($2 < 4$) |
| **1** | **`3`** | No ($1 > 2$ is false) | $\max(2, 1 + 3) = \mathbf{4}$ | **Yes** ($4 \ge 4$) $\rightarrow$ Return `true` |

**Result:** Returns **`true`**.

---

## Complexity Analysis
* **Time Complexity:** $\mathcal{O}(n)$ — Traverses the array at most once (with potential early termination).
* **Space Complexity:** $\mathcal{O}(1)$ — Uses a single scalar tracking variable.