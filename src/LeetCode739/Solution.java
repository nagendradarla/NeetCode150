package LeetCode739;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * NeetCode #25 | LeetCode 739: Daily Temperatures
 * Pattern: Stack | Difficulty: Medium
 *
 * Approach hint:
 * Monotonic decreasing stack of indices. When warmer day found, pop and set wait days.
 *
 * Similar problems:
 *   - Next Greater Element I (LC 496)
 *   - Next Greater Element II (LC 503)
 *
 * Problem:
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the i^th day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * Example 1:
 *
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 *
 * Example 2:
 *
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 *
 * Example 3:
 *
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 *
 * Constraints:
 *
 * - 1 <= temperatures.length <= 10^5
 *
 * - 30 <= temperatures[i] <= 100
 *
 * Link: https://leetcode.com/problems/daily-temperatures/
 */
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            while (stack.peek() != null && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        runTestCase(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 }, new int[] { 1, 1, 4, 2, 1, 1, 0, 0 });
        runTestCase(new int[] { 30, 40, 50, 60 }, new int[] { 1, 1, 1, 0 });
        runTestCase(new int[] { 30, 60, 90 }, new int[] { 1, 1, 0 });
    }

    private static void runTestCase(int[] temperatures, int[] expected) {
        Solution sol = new Solution();
        int[] result = sol.dailyTemperatures(temperatures);
        boolean pass = Arrays.equals(result, expected);
        System.out.println(Arrays.toString(temperatures) + " -> " + Arrays.toString(result)
                + " | Expected: " + Arrays.toString(expected)
                + " | " + (pass ? "PASS" : "FAIL"));
    }
}
