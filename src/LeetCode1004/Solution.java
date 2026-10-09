package LeetCode1004;

/**
 * LeetCode 1004: Max Consecutive Ones III
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Sliding Window
 *
 * Problem:
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 * Example 2:
 *
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 * Constraints:
 *
 * - 1 <= nums.length <= 10^5
 *
 * - nums[i] is either 0 or 1.
 *
 * - 0 <= k <= nums.length
 *
 * Link: https://leetcode.com/problems/max-consecutive-ones-iii/
 * https://youtu.be/HsGKI02yw6M?si=1jMpYoNo7M1og8rP
 */
class Solution { // O(2n) time, O(1) space
    public int longestOnes(int[] nums, int k) {
        int l = 0, r = 0;
        int maxLen = 0;
        int numZeros = 0;
        while (r<nums.length) {
            if (nums[r] == 0) numZeros++;
            while (numZeros > k) {
                if (nums[l] == 0) numZeros--;
                l++;
            }
            maxLen = Math.max(maxLen, r-l+1);
            r++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2)==6 ? "PASS" : "FAIL"); // 6
        System.out.println(sol.longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3)==10 ? "PASS" : "FAIL"); // 10
    }
}
