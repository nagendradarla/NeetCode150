package LeetCode22;

import java.util.*;

/**
 * NeetCode #24 | LeetCode 22: Generate Parentheses
 * Pattern: Stack | Difficulty: Medium
 *
 * Approach hint:
 * Backtrack with open/close counts: add '(' if open < n; add ')' if close < open.
 *
 * Similar problems:
 *   - Valid Parentheses (LC 20)
 *   - Different Ways to Add Parentheses (LC 241)
 *
 * Problem:
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 *
 * Constraints:
 *
 * - 1 <= n <= 8
 *
 * Link: https://leetcode.com/problems/generate-parentheses/
 */
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        backtracking(0, 0, n, new StringBuilder(), result);
        return result;
    }

    private void backtracking(int lCount, int rCount, int n, StringBuilder sb, List<String> result) {
        if (lCount == n && rCount == n) {
            result.add(sb.toString());
            return;
        }

        if (lCount < n) {
            sb.append("(");
            backtracking(lCount + 1, rCount, n, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (rCount < lCount) {
            sb.append(")");
            backtracking(lCount, rCount + 1, n, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
        return;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.generateParenthesis(3)); // ["((()))","(()())","(())()","()(())","()()()"]
        System.out.println(sol.generateParenthesis(1)); // ["()"]
    }
}
