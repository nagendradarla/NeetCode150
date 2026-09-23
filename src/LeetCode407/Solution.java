package LeetCode407;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode 407: Trapping Rain Water II
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Two Pointers
 *
 * Problem:
 * Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.
 *
 * Example 1:
 *
 * Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * Output: 4
 * Explanation: After the rain, water is trapped between the blocks.
 * We have two small ponds 1 and 3 units trapped.
 * The total volume of water trapped is 4.
 *
 * Example 2:
 *
 * Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * Output: 10
 *
 * Constraints:
 *
 * - m == heightMap.length
 *
 * - n == heightMap[i].length
 *
 * - 1 <= m, n <= 200
 *
 * - 0 <= heightMap[i][j] <= 2 * 10^4
 *
 * Link: https://leetcode.com/problems/trapping-rain-water-ii/
 * https://www.youtube.com/watch?v=M12EsHAkgsw
 */
class Solution {
    public static class Cell implements Comparable<Cell> {
        int height, r, c;

        public Cell(int h, int r, int c) {
            this.height = h;
            this.r = r;
            this.c = c;
        }

        public int compareTo(Cell other) {
            return Integer.compare(this.height, other.height);
        }
    }

    public int trapRainWater(int[][] heightMap) {
        // 1. Iterate through boarders and mark them as visited and add them to minHeap
        int rows = heightMap.length;
        int cols = heightMap[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<Cell> minHeap = new PriorityQueue<Cell>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                    visited[i][j] = true;
                    minHeap.offer(new Cell(heightMap[i][j], i, j));
                }
            }
        }

        //2. use BFS to find the trapped water
        int res = 0;
        int[] row = new int[] { -1, 0, 1, 0 };
        int[] col = new int[] { 0, -1, 0, 1 };
        while (!minHeap.isEmpty()) {
            Cell current = minHeap.poll();
            for (int i = 0; i < 4; i++) {
                int r = current.r + row[i];
                int c = current.c + col[i];
                if (r >= 0 && c >= 0 && r < rows && c < cols && !visited[r][c]) {
                    // If the neighboring cell is of shorter than current, only then it can trap watter
                    if (heightMap[r][c] < current.height)
                        res += current.height - heightMap[r][c];
                    // While adding to minHeap you need to add the Cell with the resultant height of current and new cell
                    // This is because the next neighbor will trapp trap water if it is less the Math.max(heightMap[r][c], current.height)
                    minHeap.offer(new Cell(Math.max(heightMap[r][c], current.height), r, c));
                    visited[r][c] = true;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.trapRainWater(new int[][] { { 1, 4, 3, 1, 3, 2 }, { 3, 2, 1, 3, 2, 4 }, { 2, 3, 3, 2, 3, 1 } }) == 4 ? "PASS" : "FAIL"); // Expected output: 4
        System.out.println(sol.trapRainWater(new int[][] { { 3, 3, 3, 3, 3 }, { 3, 2, 2, 2, 3 }, { 3, 2, 1, 2, 3 }, { 3, 2, 2, 2, 3 }, { 3, 3, 3, 3, 3 } })== 10 ? "PASS" : "FAIL"); // Expected output: 10
    }
}
