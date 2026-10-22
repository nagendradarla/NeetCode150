package LeetCode918;

/**
 * LeetCode 918: Maximum Sum Circular Subarray
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Greedy
 *
 * Problem:
 * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
 *
 * A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
 *
 * A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 *
 * Example 1:
 *
 * Input: nums = [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3.
 *
 * Example 2:
 *
 * Input: nums = [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
 *
 * Example 3:
 *
 * Input: nums = [-3,-2,-3]
 * Output: -2
 * Explanation: Subarray [-2] has maximum sum -2.
 *
 * Constraints:
 *
 * - n == nums.length
 *
 * - 1 <= n <= 3 * 10^4
 *
 * - -3 * 10^4 <= nums[i] <= 3 * 10^4
 *
 * Link: https://leetcode.com/problems/maximum-sum-circular-subarray/
 */
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;
        int currMax = 0, maxSum = nums[0];
        int currMin = 0, minSum = nums[0];

        for (int num : nums) {
            totalSum += num;

            // 1. Standard Kadane's for Maximum Subarray
            currMax = Math.max(num, currMax + num);
            maxSum = Math.max(maxSum, currMax);

            // 2. Flipped Kadane's for Minimum Subarray
            currMin = Math.min(num, currMin + num);
            minSum = Math.min(minSum, currMin);
        }

        // Edge Case: If all numbers are negative, maxSum will be negative.
        // In this case, wrapping doesn't apply (it would result in an empty array sum of 0).
        if (maxSum < 0) {
            return maxSum;
        }

        // The answer is the maximum between normal max and wrapped max (Total - Min)
        return Math.max(maxSum, totalSum - minSum);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = { 1, -2, 3, -2 };
        System.out.println(sol.maxSubarraySumCircular(nums) == 3 ? "Test 1 passed" : "Test 1 failed");
        int[] nums2 = { 5, -3, 5 };
        System.out.println(sol.maxSubarraySumCircular(nums2) == 10 ? "Test 2 passed" : "Test 2 failed");
        int[] nums3 = { -3, -2, -3 };
        System.out.println(sol.maxSubarraySumCircular(nums3) == -2 ? "Test 3 passed" : "Test 3 failed");
        int[] nums4 = { 3, -1, 2, -1 };
        System.out.println(sol.maxSubarraySumCircular(nums4) == 4 ? "Test 4 passed" : "Test 4 failed");
        int[] nums5 = { 3, -2, 2, -3 };
        System.out.println(sol.maxSubarraySumCircular(nums5) == 3 ? "Test 5 passed" : "Test 5 failed");
        int[] nums6 = { 5, -3, 5 };
        System.out.println(sol.maxSubarraySumCircular(nums6) == 10 ? "Test 6 passed" : "Test 6 failed");
        int[] nums7 = { -3, -2, -3 };
        System.out.println(sol.maxSubarraySumCircular(nums7) == -2 ? "Test 7 passed" : "Test 7 failed");
    }
}
