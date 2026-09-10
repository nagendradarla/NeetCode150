package LeetCode125;

/**
 * NeetCode #10 | LeetCode 125: Valid Palindrome
 * Pattern: Two Pointers | Difficulty: Easy
 *
 * Approach hint:
 * Left/right pointers; skip non-alphanumeric; compare lowercased chars until they meet.
 *
 * Similar problems:
 *   - Palindrome Linked List (LC 234)
 *   - Valid Palindrome II (LC 680)
 *
 * Problem:
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * Example 1:
 *
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * Example 2:
 *
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 *
 * Example 3:
 *
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 *
 * Constraints:
 *
 * - 1 <= s.length <= 2 * 10^5
 *
 * - s consists only of printable ASCII characters.
 *
 * Link: https://leetcode.com/problems/valid-palindrome/
 */
class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty())
            return true;
        int i = 0, j = s.length() - 1;
        while (i <= j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        runTestCase(sol, "A man, a plan, a canal: Panama", true);
        runTestCase(sol, "race a car", false);
        runTestCase(sol, " ", true);
        runTestCase(sol, "0P", false);
        runTestCase(sol, "ab2a", false);
        runTestCase(sol, "abba", true);
        runTestCase(sol, "abc", false);
    }

    private static void runTestCase(Solution sol, String input, boolean expected) {
        boolean result = sol.isPalindrome(input);
        System.out.println(input + " -> " + result + " | Expected: " + expected + " | " + (result == expected ? "PASS" : "FAIL"));
    }
}
