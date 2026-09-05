package LeetCode155;

import java.util.*;

/**
 * NeetCode #22 | LeetCode 155: Min Stack
 * Pattern: Stack | Difficulty: Medium
 *
 * Approach hint:
 * Two stacks (or pairs): values + current mins. Push/pop keep min in O(1).
 *
 * Similar problems:
 *   - Max Stack (LC 716)
 *   - Sliding Window Maximum (LC 239)
 *
 * Link: https://leetcode.com/problems/min-stack/
 */
class Solution {
    private Deque<Integer> stack = new ArrayDeque<>();
    private Deque<Integer> minStack = new ArrayDeque<>();

    public void push(int val) {
        // TODO
    }

    public void pop() {
        // TODO
    }

    public int top() {
        // TODO
        return 0;
    }

    public int getMin() {
        // TODO
        return 0;
    }
}
