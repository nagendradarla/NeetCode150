package LeetCode150;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * NeetCode #23 | LeetCode 150: Evaluate Reverse Polish Notation
 * Pattern: Stack | Difficulty: Medium
 *
 * Approach hint:
 * Push numbers; on operator, pop two, apply, push result. Classic postfix evaluation.
 *
 * Similar problems:
 *   - Basic Calculator (LC 224)
 *   - Basic Calculator II (LC 227)
 *
 * Problem:
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 *
 * Evaluate the expression. Return an integer that represents the value of the expression.
 *
 * Note that:
 *
 * - The valid operators are '+', '-', '*', and '/'.
 *
 * - Each operand may be an integer or another expression.
 *
 * - The division between two integers always truncates toward zero.
 *
 * - There will not be any division by zero.
 *
 * - The input represents a valid arithmetic expression in a reverse polish notation.
 *
 * - The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 * Example 1:
 *
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 *
 * Example 2:
 *
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 *
 * Example 3:
 *
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 * Constraints:
 *
 * - 1 <= tokens.length <= 10^4
 *
 * - tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 *
 * Link: https://leetcode.com/problems/evaluate-reverse-polish-notation/
 */
class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int a = 0, b = 0;
        for (String token : tokens) {
            if ("+".equals(token)) {
                stack.push(stack.pop()+stack.pop());
            } else if ("*".equals(token)) {
                stack.push(stack.pop()*stack.pop());
            } else if ("/".equals(token)) {
                b = stack.pop();
                a = stack.pop();
                stack.push(a/b);
            } else if ("-".equals(token)) {
                stack.push(-stack.pop()+stack.pop());
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] tokens1 = {"2","1","+","3","*"};
        System.out.println(sol.evalRPN(tokens1)); // Output: 9
        String[] tokens2 = {"4","13","5","/","+"};
        System.out.println(sol.evalRPN(tokens2)); // Output: 6
        String[] tokens3 = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(sol.evalRPN(tokens3)); // Output: 22
    }
}
