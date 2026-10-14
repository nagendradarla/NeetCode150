package LeetCode74;

/**
 * NeetCode #29 | LeetCode 74: Search a 2D Matrix
 * Pattern: Binary Search | Difficulty: Medium
 *
 * Approach hint:
 * Treat matrix as sorted 1D array: mid → row=mid/cols, col=mid%cols. Or binary search row then col.
 *
 * Similar problems:
 *   - Search a 2D Matrix II (LC 240)
 *   - Kth Smallest Element in a Sorted Matrix (LC 378)
 *
 * Problem:
 * You are given an m x n integer matrix matrix with the following two properties:
 *
 * - Each row is sorted in non-decreasing order.
 *
 * - The first integer of each row is greater than the last integer of the previous row.
 *
 * Given an integer target, return true if target is in matrix or false otherwise.
 *
 * You must write a solution in O(log(m * n)) time complexity.
 *
 * Example 1:
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 *
 * Example 2:
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 *
 * Constraints:
 *
 * - m == matrix.length
 *
 * - n == matrix[i].length
 *
 * - 1 <= m, n <= 100
 *
 * - -10^4 <= matrix[i][j], target <= 10^4
 *
 * Link: https://leetcode.com/problems/search-a-2d-matrix/
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int i = 0, j = rows * cols - 1;
        while (i <= j) {
            int mid = (i + j) >>> 1;
            int midVal = matrix[mid / cols][mid % cols]; // Divide my cols, think of it like you dividing the number into chunks of size 'cols'
            if (midVal == target) {
                return true;
            } else if (midVal < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3) ? "PASS" : "FAIL"); // true
        System.out.println(sol.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13) ? "FAIL" : "PASS"); // false
    }
}
