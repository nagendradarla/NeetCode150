package LeetCode240;

/**
 * LeetCode 240: Search a 2D Matrix II
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Binary Search
 *
 * Problem:
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 *
 * - Integers in each row are sorted in ascending from left to right.
 *
 * - Integers in each column are sorted in ascending from top to bottom.
 *
 * Example 1:
 *
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 *
 * Example 2:
 *
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 *
 * Constraints:
 *
 * - m == matrix.length
 *
 * - n == matrix[i].length
 *
 * - 1 <= n, m <= 300
 *
 * - -10^9 <= matrix[i][j] <= 10^9
 *
 * - All the integers in each row are sorted in ascending order.
 *
 * - All the integers in each column are sorted in ascending order.
 *
 * - -10^9 <= target <= 10^9
 *
 * Link: https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // start from top right corner cell
        int rows = matrix.length;
        int cols = matrix[0].length;
        int i = 0, j = cols - 1;
        while (i <= rows - 1 && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else if (matrix[i][j] > target) {
                j--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 5) ? "PASS" : "FAIL"); // true
        System.out.println(sol.searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 20) ? "FAIL" : "PASS"); // false
    }
}
