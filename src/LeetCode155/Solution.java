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
 * Problem:
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * - MinStack() initializes the stack object.
 *
 * - void push(int value) pushes the element value onto the stack.
 *
 * - void pop() removes the element on the top of the stack.
 *
 * - int top() gets the top element of the stack.
 *
 * - int getMin() retrieves the minimum element in the stack.
 *
 * You must implement a solution with O(1) time complexity for each function.
 *
 * Example 1:
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top(); // return 0
 * minStack.getMin(); // return -2
 *
 * Constraints:
 *
 * - -2^31 <= val <= 2^31 - 1
 *
 * - Methods pop, top and getMin operations will always be called on non-empty stacks.
 *
 * - At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
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
