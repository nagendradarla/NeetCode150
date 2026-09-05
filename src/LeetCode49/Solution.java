package LeetCode49;

import java.util.*;
import java.util.stream.Collectors;

/**
 * NeetCode #4 | LeetCode 49: Group Anagrams
 * Pattern: Arrays & Hashing | Difficulty: Medium
 *
 * Approach hint:
 * Key each word by sorted chars or by a frequency signature; group into a Map<String, List>.
 *
 * Similar problems:
 *   - Valid Anagram (LC 242)
 *   - Group Shifted Strings (LC 249)
 *
 * Problem:
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 *
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Explanation:
 *
 * - There is no string in strs that can be rearranged to form "bat".
 *
 * - The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
 *
 * - The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
 *
 * Example 2:
 *
 * Input: strs = [""]
 *
 * Output: [[""]]
 *
 * Example 3:
 *
 * Input: strs = ["a"]
 *
 * Output: [["a"]]
 *
 * Constraints:
 *
 * - 1 <= strs.length <= 10^4
 *
 * - 0 <= strs[i].length <= 100
 *
 * - strs[i] consists of lowercase English letters.
 *
 * Link: https://leetcode.com/problems/group-anagrams/
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] charArray = new int[26];
            for (int i = 0; i < str.length(); i++) {
                charArray[str.charAt(i) - 'a']++;
            }
            StringBuilder key = new StringBuilder();
            for (int i=0; i<charArray.length; i++) {
                key.append(charArray[i]);
                key.append("$");
            }
            map.computeIfAbsent(key.toString(), K -> new ArrayList<String>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        // expected groups like [eat, tea, ate], [tan, nat], [bat]
        System.out.println(sol.groupAnagrams(new String[]{""}));
        System.out.println(sol.groupAnagrams(new String[]{"a"}));
    }
}
