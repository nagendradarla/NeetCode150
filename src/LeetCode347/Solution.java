package LeetCode347;

/**
 * NeetCode #5 | LeetCode 347: Top K Frequent Elements
 * Pattern: Arrays & Hashing | Difficulty: Medium
 *
 * Approach hint:
 * Count frequencies, then bucket-sort by count (or min-heap of size k). Avoid full sort when possible.
 *
 * Similar problems:
 *   - Top K Frequent Words (LC 692)
 *   - Kth Largest Element (LC 215)
 *
 * Problem:
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 *
 * Output: [1,2]
 *
 * Example 2:
 *
 * Input: nums = [1], k = 1
 *
 * Output: [1]
 *
 * Example 3:
 *
 * Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 *
 * Output: [1,2]
 *
 * Constraints:
 *
 * - 1 <= nums.length <= 10^5
 *
 * - -10^4 <= nums[i] <= 10^4
 *
 * - k is in the range [1, the number of unique elements in the array].
 *
 * - It is guaranteed that the answer is unique.
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *
 * Link: https://leetcode.com/problems/top-k-frequent-elements/
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // TODO
        return new int[]{};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // TODO: add sample calls for Top K Frequent Elements
        // Signature: public int[] topKFrequent(int[] nums, int k) {
        }
}
