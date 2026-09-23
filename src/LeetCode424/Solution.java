package LeetCode424;

/**
 * NeetCode #17 | LeetCode 424: Longest Repeating Character Replacement
 * Pattern: Sliding Window | Difficulty: Medium
 *
 * Approach hint:
 * Window valid if windowLen - maxFreq <= k. Expand right; shrink left when invalid.
 *
 * Similar problems:
 *   - Max Consecutive Ones III (LC 1004)
 *   - Longest Substring with At Most K Distinct (LC 340)
 *
 * Problem:
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 *
 * Example 1:
 *
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 *
 * Example 2:
 *
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achieve this answer too.
 *
 * Constraints:
 *
 * - 1 <= s.length <= 10^5
 *
 * - s consists of only uppercase English letters.
 *
 * - 0 <= k <= s.length
 *
 * Link: https://leetcode.com/problems/longest-repeating-character-replacement/
 * https://youtu.be/gqXU1UyA8pk?si=mK7NOfLOWdGDuxRa
 */
class Solution {
    public int characterReplacement(String s, int k) {
        int l = 0, r = 0, n = s.length();
        int maxFreq = 0;
        int maxLen = 0;
        int[] count = new int[26];
        while (r < n) {
            char ch = s.charAt(r);
            count[ch - 'A']++;
            maxFreq = Math.max(maxFreq, count[ch - 'A']);
            while (r - l + 1 - maxFreq > k) {
                ch = s.charAt(l);
                count[ch - 'A']--;
                l++;
                // Recalculate true maxFreq directly from the count array
                // This recalculation can be avoid and redendant.
                // This only increases the Time complexity to O(26*N)
                maxFreq = 0;
                for (int c : count) {
                    maxFreq = Math.max(maxFreq, c);
                }
            }
            maxLen = Math.max(maxLen, r - l + 1);
            r++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("LC 424: Longest Repeating Character Replacement — implement Solution then add sample calls here.");
        System.out.println(sol.characterReplacement("ABAB", 2) == 4 ? "PASS" : "FAIL"); // Expected output: 4
        System.out.println(sol.characterReplacement("AABABBA", 1) == 4 ? "PASS" : "FAIL"); // Expected output: 4
        System.out.println(sol.characterReplacement("AAAA", 2) == 4 ? "PASS" : "FAIL"); // Expected output: 4
        System.out.println(sol.characterReplacement("ABCDE", 1) == 2 ? "PASS" : "FAIL"); // Expected output: 2
        System.out.println(sol.characterReplacement("AABABBA", 2) == 5 ? "PASS" : "FAIL"); // Expected output: 5
    }
}
