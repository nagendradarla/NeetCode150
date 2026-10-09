package LeetCode20;

/**
 * NeetCode #21 | LeetCode 20: Valid Parentheses
 * Pattern: Stack | Difficulty: Easy
 *
 * Approach hint:
 * Push opens; on close, pop and match. Stack empty at end ⇒ valid.
 *
 * Similar problems:
 *   - Generate Parentheses (LC 22)
 *   - Remove Invalid Parentheses (LC 301)
 *   - Valid Parenthesis String (LC 678)
 *
 * Problem:
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * - Open brackets must be closed by the same type of brackets.
 *
 * - Open brackets must be closed in the correct order.
 *
 * - Every close bracket has a corresponding open bracket of the same type.
 *
 * Example 1:
 *
 * Input: s = "()"
 *
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "()[]{}"
 *
 * Output: true
 *
 * Example 3:
 *
 * Input: s = "(]"
 *
 * Output: false
 *
 * Example 4:
 *
 * Input: s = "([])"
 *
 * Output: true
 *
 * Example 5:
 *
 * Input: s = "([)]"
 *
 * Output: false
 *
 * Constraints:
 *
 * - 1 <= s.length <= 10^4
 *
 * - s consists of parentheses only '()[]{}'.
 *
 * Link: https://leetcode.com/problems/valid-parentheses/
 */
class Solution {
    public boolean isValid(String s) {
        // TODO
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // TODO: add sample calls for Valid Parentheses
        // Signature: public boolean isValid(String s) {
        }
}
