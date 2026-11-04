package LeetCode45;

/**
 * NeetCode #124 | LeetCode 45: Jump Game II
 * Pattern: Greedy | Difficulty: Medium
 *
 * Approach hint:
 * BFS-like levels: within current end, track nextEnd; jump++ when i==end.
 *
 * Similar problems:
 *   - Jump Game (LC 55)
 *   - Video Stitching (LC 1024)
 *
 * Problem:
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at index i, you can jump to any index (i + j) where:
 *
 * - 0 <= j <= nums[i] and
 *
 * - i + j < n
 *
 * Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 * Constraints:
 *
 * - 1 <= nums.length <= 10^4
 *
 * - 0 <= nums[i] <= 1000
 *
 * - It's guaranteed that you can reach nums[n - 1].
 *
 * Link: https://leetcode.com/problems/jump-game-ii/
 */
class Solution {
    public int jump(int[] nums) {
        /*if (nums.length == 1) {
            return 0;
        }*/
        int last = 0, max = 0, jumps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == last) { // if current position is the last jump
                jumps++;
                last = max;
                // If the distance from last jump is already greater than or equal to array size(n-1)
                if (last >= nums.length - 1)
                    return jumps;
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = { 2, 3, 1, 1, 4 };
        System.out.println(sol.jump(nums) == 2 ? "Test Case 1 Passed" : "Test Case 1 Failed");
        int[] nums2 = { 2, 3, 0, 1, 4 };
        System.out.println(sol.jump(nums2) == 2 ? "Test Case 2 Passed" : "Test Case 2 Failed");
        int[] nums3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println(sol.jump(nums3) == 3 ? "Test Case 3 Passed" : "Test Case 3 Failed");
        int[] nums4 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println(sol.jump(nums4) == 4 ? "Test Case 4 Passed" : "Test Case 4 Failed");
        int[] nums5 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println(sol.jump(nums5) == 5 ? "Test Case 5 Passed" : "Test Case 5 Failed");
    }
}
