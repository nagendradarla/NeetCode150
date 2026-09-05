package LeetCode242;

import java.util.HashMap;
import java.util.Map;

/**
 * NeetCode #2 | LeetCode 242: Valid Anagram
 * Pattern: Arrays & Hashing | Difficulty: Easy
 *
 * Approach hint:
 * Count character frequencies (array of size 26 or HashMap). Two strings are anagrams iff counts match.
 *
 * Similar problems:
 *   - Group Anagrams (LC 49)
 *   - Find All Anagrams in a String (LC 438)
 *
 * Problem:
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 *
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 *
 * Output: false
 *
 * Constraints:
 *
 * - 1 <= s.length, t.length <= 5 * 10^4
 *
 * - s and t consist of lowercase English letters.
 *
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 *
 * Link: https://leetcode.com/problems/valid-anagram/
 */
class Solution {
    /*public boolean isAnagram(String s, String t) {
        // If lengths differ, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return false;
    }*/

    // For inputs containing unicode chars, we have to use HashMap
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        if (s.length() != t.length()) return false;
        for(int i=0; i<s.length(); i++) {
            charMap.merge(s.charAt(i), 1, Integer::sum);
            charMap.merge(t.charAt(i), -1, Integer::sum);
        }
        return !charMap.values().stream().anyMatch(x -> x != 0);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.isAnagram("anagram", "nagaram")); // true
        System.out.println(sol.isAnagram("rat", "car")); // false
    }
}
