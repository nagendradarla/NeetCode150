package LeetCode85;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 85: Maximal Rectangle
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Stack
 *
 * Problem:
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 * Example 1:
 *
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above picture.
 *
 * Example 2:
 *
 * Input: matrix = [["0"]]
 * Output: 0
 *
 * Example 3:
 *
 * Input: matrix = [["1"]]
 * Output: 1
 *
 * Constraints:
 *
 * - rows == matrix.length
 *
 * - cols == matrix[i].length
 *
 * - 1 <= rows, cols <= 200
 *
 * - matrix[i][j] is '0' or '1'.
 *
 * Link: https://leetcode.com/problems/maximal-rectangle/
 */
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int maxArea = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length, maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i <= n; i++) {
            int currHeight = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && currHeight < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] matrix = { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
                { '1', '0', '0', '1', '0' } };
        System.out.println(sol.maximalRectangle(matrix) == 6 ? "Test 1 passed" : "Test 1 failed");
        matrix = new char[][] { { '0' } };
        System.out.println(sol.maximalRectangle(matrix) == 0 ? "Test 2 passed" : "Test 2 failed");
        matrix = new char[][] { { '1' } };
        System.out.println(sol.maximalRectangle(matrix) == 1 ? "Test 3 passed" : "Test 3 failed");
    }
}
