package LeetCode224;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 224: Basic Calculator
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Stack
 *
 * Problem:
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Example 1:
 *
 * Input: s = "1 + 1"
 * Output: 2
 *
 * Example 2:
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 *
 * Example 3:
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 * Constraints:
 *
 * - 1 <= s.length <= 3 * 10^5
 *
 * - s consists of digits, '+', '-', '(', ')', and ' '.
 *
 * - s represents a valid expression.
 *
 * - '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 *
 * - '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 *
 * - There will be no two consecutive operators in the input.
 *
 * - Every number and running calculation will fit in a signed 32-bit integer.
 *
 * Link: https://leetcode.com/problems/basic-calculator/
 */
class Solution {
    public int calculate(String s) {
        int result = 0, sign = 1, number = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '+') {
                result += number * sign;
                number = 0;
                sign = +1;
            } else if (c == '-') {
                result += number * sign;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = +1;
                number = 0;
            } else if (c == ')') {
                result += number * sign;
                result *= stack.pop(); // prev sign
                result += stack.pop(); // prev result
                number = 0;
                sign = +1;
            }
        }
        if (number != 0) {
            result += number * sign;
        }
        return result;
    }

    private static void runTestCase(String s, int expected) {
        Solution solution = new Solution();
        int result = solution.calculate(s);
        System.out.println("Input: s = " + s);
        System.out.println("Output: " + result);
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + (result == expected ? "Pass" : "Fail"));
        System.out.println();
    }

    public static void main(String[] args) {
        runTestCase("1 + 1", 2);
        runTestCase(" 2-1 + 2 ", 3);
        runTestCase("(1+(4+5+2)-3)+(6+8)", 23);
    }
}
