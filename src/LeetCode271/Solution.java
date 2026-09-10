package LeetCode271;

import java.util.*;

/**
 * NeetCode #8 | LeetCode 271: Encode and Decode Strings
 * Pattern: Arrays & Hashing | Difficulty: Medium
 *
 * Approach hint:
 * Premium. Encode with length#string (or delimiter escaping) so decode can split unambiguously.
 *
 * Similar problems:
 *   - Serialize and Deserialize Binary Tree (LC 297)
 *   - String Compression (LC 443)
 *
 * Problem:
 * (description unavailable)
 *
 * Link: https://leetcode.com/problems/encode-and-decode-strings/
 */
class Solution {
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append("#").append(str);
        }
        return sb.toString();
    }

    public List<String> decode(String s) {
        if (s == null || s.isEmpty()) {
            return Collections.emptyList();
        }
        int i=0;
        List<String> result = new ArrayList<>();
        while (i < s.length()) {
            int j = s.indexOf("#", i);
            int length = Integer.parseInt(s.substring(i, j));
            result.add(s.substring(j + 1, j + 1 + length));
            i = j + 1 + length;
        }
        return result;
    }

    private static void assertRoundTrip(Solution sol, List<String> input, String label) {
        List<String> decoded = sol.decode(sol.encode(input));
        boolean pass = input.equals(decoded);
        System.out.println(label + ": " + (pass ? "PASS" : "FAIL"));
        if (!pass) {
            System.out.println("  expected: " + input);
            System.out.println("  got:      " + decoded);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // 1. Basic multiple strings
        assertRoundTrip(sol, Arrays.asList("hello", "world"), "basic multiple strings");

        // 2. Empty list
        assertRoundTrip(sol, Collections.emptyList(), "empty list");

        // 3. Single string
        assertRoundTrip(sol, Arrays.asList("abc"), "single string");

        // 4. Multiple empty strings
        assertRoundTrip(sol, Arrays.asList("", "", ""), "multiple empty strings");

        // 5. Mixed empty and non-empty
        assertRoundTrip(sol, Arrays.asList("", "a", "", "bb"), "mixed empty and non-empty");

        // 6. Strings that look like length-delimiter encoding (e.g. "5#hello")
        assertRoundTrip(sol, Arrays.asList("#", "##", "5#hello", "12#oops"), "delimiter-like strings");

        // 7. Strings with commas, pipes, and spaces
        assertRoundTrip(sol, Arrays.asList("hello,world", "foo|bar", " spaced out "), "special characters");

        // 8. Longer list with repeated values
        assertRoundTrip(sol, Arrays.asList("neet", "code", "neet", "love", "you"), "repeated values");
    }
}
