package LeetCode239;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * NeetCode #20 | LeetCode 239: Sliding Window Maximum
 * Pattern: Sliding Window | Difficulty: Hard
 *
 * Approach hint:
 * Monotonic decreasing deque of indices. Front is always max of current window.
 *
 * Similar problems:
 *   - Sliding Window Median (LC 480)
 *   - Jump Game VI (LC 1696)
 *
 * Problem:
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position Max
 * --------------- -----
 * [1 3 -1] -3 5 3 6 7 3
 * 1 [3 -1 -3] 5 3 6 7 3
 * 1 3 [-1 -3 5] 3 6 7 5
 * 1 3 -1 [-3 5 3] 6 7 5
 * 1 3 -1 -3 [5 3 6] 7 6
 * 1 3 -1 -3 5 [3 6 7] 7
 *
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Constraints:
 *
 * - 1 <= nums.length <= 10^5
 *
 * - -10^4 <= nums[i] <= 10^4
 *
 * - 1 <= k <= nums.length
 *
 * Link: https://leetcode.com/problems/sliding-window-maximum/
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0)
            return new int[0];
        int l = 0, n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<Integer>();
        int i = 0;
        for (int r = 0; r < n; r++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[r]) {
                deque.removeLast();
            }
            while (!deque.isEmpty() && deque.peekFirst() < r - k + 1) {
                deque.removeFirst();
            }
            deque.addLast(r);
            if (r >= k - 1) {
                res[i] = nums[deque.peekFirst()];
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k = 3;
        int[] expected = { 3, 3, 5, 5, 6, 7 };
        runTestCase(nums, k, expected);
        nums = new int[] { 1 };
        k = 1;
        expected = new int[] { 1 };
        runTestCase(nums, k, expected);
    }

    private static void runTestCase(int[] nums, int k, int[] expected) {
        Solution sol = new Solution();
        int[] res = sol.maxSlidingWindow(nums, k);
        assert Arrays.equals(res, expected)
                : "Expected " + Arrays.toString(expected) + " but got " + Arrays.toString(res);
    }
}
