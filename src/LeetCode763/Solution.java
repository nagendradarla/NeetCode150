package LeetCode763;

import java.util.*;

/**
 * NeetCode #128 | LeetCode 763: Partition Labels
 * Pattern: Greedy | Difficulty: Medium
 *
 * Approach hint:
 * Last index of each char. Extend partition end; cut when i==end.
 *
 * Similar problems:
 *   - Merge Intervals (LC 56)
 *   - Maximum Length of a Concatenated String (LC 1239)
 *
 * Problem:
 * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part. For example, the string "ababcc" can be partitioned into ["abab", "cc"], but partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.
 *
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 *
 * Return a list of integers representing the size of these parts.
 *
 * Example 1:
 *
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 *
 * Example 2:
 *
 * Input: s = "eccbbbbdec"
 * Output: [10]
 *
 * Constraints:
 *
 * - 1 <= s.length <= 500
 *
 * - s consists of lowercase English letters.
 *
 * Link: https://leetcode.com/problems/partition-labels/
 */
class Solution {
    public List<Integer> partitionLabels(String s) {
        // TODO
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // TODO: add sample calls for Partition Labels
        // Signature: public List<Integer> partitionLabels(String s) {
        }
}
