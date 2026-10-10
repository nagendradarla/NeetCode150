package LeetCode84;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * NeetCode #27 | LeetCode 84: Largest Rectangle in Histogram
 * Pattern: Stack | Difficulty: Hard
 *
 * Approach hint:
 * Monotonic increasing stack of indices. When height drops, pop and compute width×height areas.
 *
 * Similar problems:
 *   - Maximal Rectangle (LC 85)
 *   - Largest Rectangle in Histogram variants (LC 1793)
 *
 * Problem:
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
 *
 * Example 1:
 *
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 *
 * Example 2:
 *
 * Input: heights = [2,4]
 * Output: 4
 *
 * Constraints:
 *
 * - 1 <= heights.length <= 10^5
 *
 * - 0 <= heights[i] <= 10^4
 *
 * Link: https://leetcode.com/problems/largest-rectangle-in-histogram/
 */

class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0, n = heights.length;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i <= n; i++) {
            int currheight = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && currheight < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] heights = { 2, 1, 5, 6, 2, 3 };
        System.out.println(sol.largestRectangleArea(heights) == 10 ? "Test 1 passed" : "Test 1 failed");
        heights = new int[] { 2, 4 };
        System.out.println(sol.largestRectangleArea(heights) == 4 ? "Test 2 passed" : "Test 2 failed");
        heights = new int[] { 2, 1, 2 };
        System.out.println(sol.largestRectangleArea(heights) == 3 ? "Test 3 passed" : "Test 3 failed");
        heights = new int[] { 2, 4, 2 };
        System.out.println(sol.largestRectangleArea(heights) == 4 ? "Test 4 passed" : "Test 4 failed");
        heights = new int[] { 2, 4, 2, 1, 2 };
        System.out.println(sol.largestRectangleArea(heights) == 6 ? "Test 5 passed" : "Test 5 failed");
    }
}
