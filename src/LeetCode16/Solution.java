package LeetCode16;

import java.util.Arrays;

/**
 * LeetCode 16: 3Sum Closest
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Two Pointers
 *
 * Problem:
 * You are given an integer array nums of length n and an integer target.
 *
 * Find three integers at distinct indices in nums such that the sum is closest to target.
 *
 * Return the sum of the three integers.
 *
 * You may assume that each input would have exactly one solution.
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * Example 2:
 *
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 * Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
 *
 * Constraints:
 *
 * - 3 <= nums.length <= 500
 *
 * - -1000 <= nums[i] <= 1000
 *
 * - -10^4 <= target <= 10^4
 *
 * Link: https://leetcode.com/problems/3sum-closest/
 */
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int res = 0;
        Arrays.sort(nums);
        for (int i=0; i<nums.length-2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int tar = target - nums[i];
            int l=i+1,r=nums.length-1;
            while (l < r) {
                int sum = nums[l] + nums[r];
                int currentDiff = Math.abs(sum-tar);
                if (sum == tar) {
                    return target;
                }

                if (currentDiff < diff) {
                    res = nums[i] + sum;
                    diff = currentDiff;
                }

                if (sum < tar) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("LC 16: 3Sum Closest — implement Solution then add sample calls here.");
        System.out.println(sol.threeSumClosest(new int[]{-1,2,1,-4}, 1) == 2 ? "PASS" : "FAIL"); // Expected output: 2
        System.out.println(sol.threeSumClosest(new int[]{0,0,0}, 1) == 0 ? "PASS" : "FAIL"); // Expected output: 0
        System.out.println(sol.threeSumClosest(new int[]{1,1,1,0}, -100) == 2 ? "PASS" : "FAIL"); // Expected output: 2
        System.out.println(sol.threeSumClosest(new int[]{1,1,1,1}, 0) == 3 ? "PASS" : "FAIL"); // Expected output: 3
        System.out.println(sol.threeSumClosest(new int[]{1,1,-1,-1,3}, -1) == -1 ? "PASS" : "FAIL"); // Expected output: -1
        System.out.println(sol.threeSumClosest(new int[]{0,2,1,-3}, 1) == 0 ? "PASS" : "FAIL");
    }
}
