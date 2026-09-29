package LeetCode76;

/**
 * NeetCode #19 | LeetCode 76: Minimum Window Substring
 * Pattern: Sliding Window | Difficulty: Hard
 *
 * Approach hint:
 * Expand until t is covered; shrink to minimize. Track need/have counts of required chars.
 *
 * Similar problems:
 *   - Smallest Range Covering Elements from K Lists (LC 632)
 *   - Minimum Window Subsequence (LC 727)
 *
 * Problem:
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 *
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 *
 * Example 3:
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 *
 * Constraints:
 *
 * - m == s.length
 *
 * - n == t.length
 *
 * - 1 <= m, n <= 10^5
 *
 * - s and t consist of uppercase and lowercase English letters.
 *
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 *
 * Link: https://leetcode.com/problems/minimum-window-substring/
 */
class Solution {
    public String minWindow(String s, String t) {
        int l = 0, r = 0, need=0, have=0, minLen=Integer.MAX_VALUE, startIdx=0, lastIdx=0;
        int[] sCount = new int[128];
        int[] tCount = new int[128];
        for (int i=0; i<t.length(); i++) {
            if (tCount[t.charAt(i)-'A'] == 0) {
                // "need" keeps track of unique characters matching,
                // it does not consider same character repeating multiple times
                need++;
            }
            tCount[t.charAt(i)-'A']++;
        }
        while (r < s.length()) {
            char ch = s.charAt(r);
            sCount[ch-'A']++;
            if (tCount[ch-'A'] > 0 && sCount[ch-'A'] == tCount[ch-'A']) {
                // "need" keeps track of unique characters matching,
                // It should be updated only when the character with required count is reached
                have++;
            }
            while (need == have) {
                //System.out.println("l = " + l);
                //System.out.println("r = " + r);
                if (r-l+1 < minLen) {
                    minLen = Math.min(minLen, r-l+1);
                    startIdx = l;
                    lastIdx = r+1;
                }
                ch = s.charAt(l);
                sCount[ch-'A']--;
                if (tCount[ch-'A'] > 0 && sCount[ch-'A'] < tCount[ch-'A']) {
                    have--;
                }
                l++;
            }
            r++;
        }
        return s.substring(startIdx, lastIdx);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("LC 76: Minimum Window Substring — implement Solution then add sample calls here.");
        System.out.println(sol.minWindow("ADOBECODEBANC", "ABC").equals("BANC") ? "PASS" : "FAIL"); // Expected output: "BANC"
        System.out.println(sol.minWindow("a", "a").equals("a") ? "PASS" : "FAIL"); // Expected output: "a"
        System.out.println(sol.minWindow("a", "aa").equals("") ? "PASS" : "FAIL"); // Expected output: ""
    }
}
