package LeetCode53;

/**
 * NeetCode #122 | LeetCode 53: Maximum Subarray
 * Pattern: Greedy | Difficulty: Medium
 *
 * Approach hint:
 * Kadane: cur=max(x, cur+x); track global max. Greedy local decision.
 *
 * Similar problems:
 *   - Maximum Sum Circular Subarray (LC 918)
 *   - Maximum Product Subarray (LC 152)
 *
 * Problem:
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 *
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 *
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 * Constraints:
 *
 * - 1 <= nums.length <= 10^5
 *
 * - -10^4 <= nums[i] <= 10^4
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 *
 * Link: https://leetcode.com/problems/maximum-subarray/
 */
class Solution {
    public int maxSubArray(int[] nums) {
        int currMax = 0;
        int maxSum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            //currMax will have the hold the max, either by continuing the previous sum or starting a new summ altogether
            currMax = Math.max(nums[i], currMax + nums[i]);
            //get the max of previous maxSum and currMax
            maxSum = Math.max(maxSum, currMax);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(sol.maxSubArray(nums) == 6 ? "Test 1 passed" : "Test 1 failed");
        int[] nums2 = { 1 };
        System.out.println(sol.maxSubArray(nums2) == 1 ? "Test 2 passed" : "Test 2 failed");
        int[] nums3 = { 5, 4, -1, 7, 8 };
        System.out.println(sol.maxSubArray(nums3) == 23 ? "Test 3 passed" : "Test 3 failed");
    }
}
