package LeetCode128;

/**
 * NeetCode #9 | LeetCode 128: Longest Consecutive Sequence
 * Pattern: Arrays & Hashing | Difficulty: Medium
 *
 * Approach hint:
 * Put nums in a HashSet. Only start counting from numbers with no left neighbor (n-1 missing).
 *
 * Similar problems:
 *   - Longest Consecutive Sequence II (tree) (LC 549)
 *   - Maximum Consecutive Floors (LC 2274)
 *
 * Problem:
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 * Example 3:
 *
 * Input: nums = [1,0,1,2]
 * Output: 3
 *
 * Constraints:
 *
 * - 0 <= nums.length <= 10^5
 *
 * - -10^9 <= nums[i] <= 10^9
 *
 * Link: https://leetcode.com/problems/longest-consecutive-sequence/
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // TODO: add sample calls for Longest Consecutive Sequence
        // Signature: public int longestConsecutive(int[] nums) {
        }
}
