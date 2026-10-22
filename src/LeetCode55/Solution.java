package LeetCode55;

/**
 * NeetCode #123 | LeetCode 55: Jump Game
 * Pattern: Greedy | Difficulty: Medium
 *
 * Approach hint:
 * Track farthest reachable. Fail if i > farthest. Success if farthest >= last.
 *
 * Similar problems:
 *   - Jump Game II (LC 45)
 *   - Jump Game III (LC 1306)
 *
 * Problem:
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 *
 * Constraints:
 *
 * - 1 <= nums.length <= 10^4
 *
 * - 0 <= nums[i] <= 10^5
 *
 * Link: https://leetcode.com/problems/jump-game/
 */
class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            //System.out.println("i= " + i);
            //System.out.println("maxReach= " + maxReach);
            if (i > maxReach) { // maxReach is less than the current index, them we cannot reach this index 'i'
                return false;
            }
            // Calculate the next maxReach
            maxReach = Math.max(maxReach, i + nums[i]);

            //maxReach is already greater than last index, then return true. 
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = { 2, 3, 1, 1, 4 };
        System.out.println(sol.canJump(nums) == true ? "Test 1 passed" : "Test 1 failed");
        int[] nums2 = { 3, 2, 1, 0, 4 };
        System.out.println(sol.canJump(nums2) == false ? "Test 2 passed" : "Test 2 failed");
        int[] nums3 = { 0 };
        System.out.println(sol.canJump(nums3) == true ? "Test 3 passed" : "Test 3 failed");
        int[] nums4 = { 1, 0, 1, 0 };
        System.out.println(sol.canJump(nums4) == false ? "Test 4 passed" : "Test 4 failed");
        int[] nums5 = { 1, 1, 1, 1, 1 };
        System.out.println(sol.canJump(nums5) == true ? "Test 5 passed" : "Test 5 failed");
        int[] nums6 = { 1, 0, 0, 0, 0 };
        System.out.println(sol.canJump(nums6) == false ? "Test 6 passed" : "Test 6 failed");
        int[] nums7 = { 1, 2, 3, 4, 5 };
        System.out.println(sol.canJump(nums7) == true ? "Test 7 passed" : "Test 7 failed");
        int[] nums8 = { 5, 4, 3, 2, 1 };
        System.out.println(sol.canJump(nums8) == true ? "Test 8 passed" : "Test 8 failed");
    }
}
