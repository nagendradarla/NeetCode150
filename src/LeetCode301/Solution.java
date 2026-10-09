package LeetCode301;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * LeetCode 301: Remove Invalid Parentheses
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Stack
 *
 * Problem:
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 *
 * Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 *
 * Example 2:
 *
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 *
 * Example 3:
 *
 * Input: s = ")("
 * Output: [""]
 *
 * Constraints:
 *
 * - 1 <= s.length <= 25
 *
 * - s consists of lowercase English letters and parentheses '(' and ')'.
 *
 * - There will be at most 20 parentheses in s.
 *
 * Link: https://leetcode.com/problems/remove-invalid-parentheses/
 */
class Solution {
    int maxLength = -1;

    public List<String> removeInvalidParentheses(String s) {
        HashSet<String> result = new HashSet<String>();
        dfs(s, 0, 0, 0, new StringBuilder(), result);
        return new ArrayList<String>(result);
    }

    private void dfs(String s, int index, int lCount, int rCount, StringBuilder sb, HashSet<String> res) {

        if (rCount > lCount) {
            return;
        }

        if (index == s.length()) {
            if (lCount == rCount) {
                if (sb.length() > maxLength) {
                    maxLength = sb.length();
                    res.clear();
                    res.add(sb.toString());
                } else if (sb.length() == maxLength) {
                    res.add(sb.toString());
                }
            }
            return;
        }

        char ch = s.charAt(index);
        if (ch == '(') {
            dfs(s, index + 1, lCount, rCount, sb, res);

            sb.append(ch);
            dfs(s, index + 1, lCount + 1, rCount, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        } else if (ch == ')') {
            dfs(s, index + 1, lCount, rCount, sb, res);

            sb.append(ch);
            dfs(s, index + 1, lCount, rCount + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            sb.append(ch);
            dfs(s, index + 1, lCount, rCount, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("LC 301: Remove Invalid Parentheses — implement Solution then add sample calls here.");
        System.out.println(sol.removeInvalidParentheses("()())()")); // Expected output: ["(())()","()()()"]
        System.out.println(sol.removeInvalidParentheses("(a)())()")); // Expected output: ["(a())()","(a)()()"]
        System.out.println(sol.removeInvalidParentheses(")(")); // Expected output: [""]
    }
}
