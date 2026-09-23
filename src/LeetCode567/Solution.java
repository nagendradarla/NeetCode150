package LeetCode567;

/**
 * NeetCode #18 | LeetCode 567: Permutation in String
 * Pattern: Sliding Window | Difficulty: Medium
 * <p>
 * Approach hint:
 * Fixed window of len(s1): maintain frequency match count. Slide across s2.
 * <p>
 * Similar problems:
 * - Find All Anagrams in a String (LC 438)
 * - Minimum Window Substring (LC 76)
 * <p>
 * Problem:
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * <p>
 * In other words, return true if one of s1's permutations is the substring of s2.
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * <p>
 * Example 2:
 * <p>
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * - 1 <= s1.length, s2.length <= 10^4
 * <p>
 * - s1 and s2 consist of lowercase English letters.
 * <p>
 * Link: https://leetcode.com/problems/permutation-in-string/
 * https://www.youtube.com/watch?v=UbyhOgBN834
 */
class Solution { //O(26*n) time | O(1) space
/*    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] s1Count = new int[26];
        int[] s2Count = new int[26];
        for (int i=0; i<s1.length(); i++) {
            s1Count[s1.charAt(i)-'a']++;
            s2Count[s2.charAt(i)-'a']++;
        }
        if (matches(s1Count, s2Count)) return true;
        int l1=s1.length(), l2=s2.length();
        for (int i = l1; i< l2; i++) {
            s2Count[s2.charAt(i)-'a']++;
            s2Count[s2.charAt(i-l1)-'a']--;
            if (matches(s1Count, s2Count)) return true;
        }
        return false;
    }

    public boolean matches(int[] s1, int[] s2) {
        for (int i = 0; i < 26; i++) {
            if (s1[i] != s2[i])
                return false;
        }
        return true;
    }*/

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("LC 567: Permutation in String — implement Solution then add sample calls here.");
        System.out.println(sol.checkInclusion("ab", "eidbaooo") == true ? "PASS" : "FAIL"); // Expected output: true
        System.out.println(sol.checkInclusion("ab", "eidboaoo") == false ? "PASS" : "FAIL"); // Expected output: false
    }

    // O(n) time | O(1) space
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] s1Count = new int[26];
        int[] s2Count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
            s2Count[s2.charAt(i) - 'a']++;
        }
        //Calculate Matches
        int matches = 0;
        for (int i = 0; i < 26; i++) {
            if (s1Count[i] == s2Count[i]) matches++;
        }
        if (matches == 26) return true;
        int l1 = s1.length(), l2 = s2.length();
        for (int i = l1; i < l2; i++) {
            char ch = s2.charAt(i);
            s2Count[ch - 'a']++;
            if (s1Count[ch - 'a'] == s2Count[ch - 'a']) {
                matches++;
            } else if (s1Count[ch - 'a'] + 1 == s2Count[ch - 'a']) { // only decrement matches if it was previously equal and now is not
                matches--;
            }
            ch = s2.charAt(i - l1);
            s2Count[ch - 'a']--;
            if (s1Count[ch - 'a'] == s2Count[ch - 'a']) {
                matches++;
            } else if (s1Count[ch - 'a'] - 1 == s2Count[ch - 'a']) { // only decrement matches if it was previously equal and now is not
                matches--;
            }
            if (matches == 26) return true; // return true is matches is 26, whenever the counts are equal for all characters
        }
        return false;
    }
}
