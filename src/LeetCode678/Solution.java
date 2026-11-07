package LeetCode678;

/**
 * NeetCode #129 | LeetCode 678: Valid Parenthesis String
 * Pattern: Greedy | Difficulty: Medium
 *
 * Approach hint:
 * Track low/high open counts; '*' as )/( /empty. low never <0; end low==0.
 *
 * Similar problems:
 *   - Valid Parentheses (LC 20)
 *   - Minimum Remove to Make Valid Parentheses (LC 1249)
 *
 * Problem:
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 *
 * The following rules define a valid string:
 *
 * - Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 *
 * - Any right parenthesis ')' must have a corresponding left parenthesis '('.
 *
 * - Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 *
 * - '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "(*)"
 * Output: true
 *
 * Example 3:
 *
 * Input: s = "(*))"
 * Output: true
 *
 * Example 4:
 *
 * Input: s = "("
 * Output: false
 *
 * Constraints:
 *
 * - 1 <= s.length <= 100
 *
 * - s[i] is '(', ')' or '*'.
 *
 * Link: https://leetcode.com/problems/valid-parenthesis-string/
 */
class Solution {
    public boolean checkValidString(String s) {
        // TODO
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // TODO: add sample calls for Valid Parenthesis String
        // Signature: public boolean checkValidString(String s) {
        }
}
