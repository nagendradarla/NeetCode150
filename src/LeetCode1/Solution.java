package LeetCode1;

import java.util.HashMap;
import java.util.Map;

/**
 * NeetCode #3 | LeetCode 1: Two Sum
 * Pattern: Arrays & Hashing | Difficulty: Easy
 *
 * Approach hint:
 * One-pass HashMap: for each num, check if target-num exists. Store value→index as you go.
 *
 * Similar problems:
 *   - Two Sum II (LC 167)
 *   - 3Sum (LC 15)
 *   - 4Sum (LC 18)
 *
 * Problem:
 * You are given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Example 3:
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 * Constraints:
 *
 * - 2 <= nums.length <= 10^4
 *
 * - -10^9 <= nums[i] <= 10^9
 *
 * - -10^9 <= target <= 10^9
 *
 * - Only one valid answer exists.
 *
 * Follow-up: Can you come up with an algorithm that is less than O(n^2) time complexity?
 *
 * Link: https://leetcode.com/problems/two-sum/
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i=0; i<nums.length; i++) {
            if (map.get(target - nums[i]) != null) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(java.util.Arrays.toString(sol.twoSum(new int[]{2, 7, 11, 15}, 9))); // [0, 1]
        System.out.println(java.util.Arrays.toString(sol.twoSum(new int[]{3, 2, 4}, 6))); // [1, 2]
        System.out.println(java.util.Arrays.toString(sol.twoSum(new int[]{3, 3}, 6))); // [0, 1]
    }
}
