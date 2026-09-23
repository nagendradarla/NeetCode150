package LeetCode3;

import java.util.HashSet;
import java.util.Set;

/**
 * NeetCode #16 | LeetCode 3: Longest Substring Without Repeating Characters
 * Pattern: Sliding Window | Difficulty: Medium
 *
 * Approach hint:
 * Expand right; shrink left when duplicate appears (map last index or set). Track max window length.
 *
 * Similar problems:
 *   - Longest Repeating Character Replacement (LC 424)
 *   - Fruit Into Baskets (LC 904)
 *
 * Problem:
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
 *
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Constraints:
 *
 * - 0 <= s.length <= 10^5
 *
 * - s consists of English letters, digits, symbols and spaces.
 *
 * Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * https://youtu.be/wiGpQwVHdE0?si=qEkh07MP351O4ntl
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int i = 0, j = 0, res = 0, l = s.length();
        Set<Character> charSet = new HashSet<Character>();
        while (i < l && j < l) {
            char ch = s.charAt(j);
            while (charSet.contains(ch)) {
                charSet.remove(s.charAt(i));
                i++;
            }
            res = Math.max(res, j - i + 1);
            j++;
            charSet.add(ch);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("LC 3: Longest Substring Without Repeating Characters — implement Solution then add sample calls here.");
        System.out.println(sol.lengthOfLongestSubstring("abcabcbb") == 3 ? "PASS" : "FAIL"); // Expected output: 3
        System.out.println(sol.lengthOfLongestSubstring("bbbbb") == 1 ? "PASS" : "FAIL"); // Expected output: 1
        System.out.println(sol.lengthOfLongestSubstring("pwwkew") == 3 ? "PASS" : "FAIL"); // Expected output: 3
        System.out.println(sol.lengthOfLongestSubstring("") == 0 ? "PASS" : "FAIL"); // Expected output: 0
        System.out.println(sol.lengthOfLongestSubstring(" ")== 1 ? "PASS" : "FAIL"); // Expected output: 1
        System.out.println(sol.lengthOfLongestSubstring("au") == 2 ? "PASS" : "FAIL"); // Expected output: 2
        System.out.println(sol.lengthOfLongestSubstring("dvdf") == 3 ? "PASS" : "FAIL"); // Expected output: 3
    }
}
