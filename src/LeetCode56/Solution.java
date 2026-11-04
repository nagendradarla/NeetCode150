package LeetCode56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * NeetCode #131 | LeetCode 56: Merge Intervals
 * Pattern: Intervals | Difficulty: Medium
 *
 * Approach hint:
 * Sort by start; merge if next.start <= cur.end.
 *
 * Similar problems:
 *   - Insert Interval (LC 57)
 *   - Meeting Rooms II (LC 253)
 *
 * Problem:
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * Example 3:
 *
 * Input: intervals = [[4,7],[1,4]]
 * Output: [[1,7]]
 * Explanation: Intervals [1,4] and [4,7] are considered overlapping.
 *
 * Constraints:
 *
 * - 1 <= intervals.length <= 10^4
 *
 * - intervals[i].length == 2
 *
 * - 0 <= starti <= endi <= 10^4
 *
 * Link: https://leetcode.com/problems/merge-intervals/
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> res = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (res.isEmpty() || res.get(res.size() - 1)[1] < interval[0]) {
                res.add(interval);
            } else {
                int[] last = res.get(res.size() - 1);
                last[1] = Math.max(last[1], interval[1]);
            }
        }
        /*int[][] result = new int[res.size()][2];
        int i=0;
        for (int[] intvl : res) {
            result[i++] = intvl;
        }
        return result;*/
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        runTestCase(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } },
                new int[][] { { 1, 6 }, { 8, 10 }, { 15, 18 } });
        runTestCase(new int[][] { { 1, 4 }, { 4, 5 } }, new int[][] { { 1, 5 } });
        runTestCase(new int[][] { { 4, 7 }, { 1, 4 } }, new int[][] { { 1, 7 } });
    }

    private static void runTestCase(int[][] intervals, int[][] expected) {
        Solution sol = new Solution();
        int[][] result = sol.merge(intervals);
        boolean pass = Arrays.deepEquals(result, expected);
        System.out.println(Arrays.deepToString(intervals) + " -> " + Arrays.deepToString(result)
                + " | Expected: " + Arrays.deepToString(expected)
                + " | " + (pass ? "PASS" : "FAIL"));
    }
}
