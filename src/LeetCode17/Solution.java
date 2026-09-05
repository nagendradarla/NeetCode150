package LeetCode17;

import java.util.ArrayList;
import java.util.List;

class Solution {
    String[] digitToLetter = new String[] {"","","abc", "def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder curr, List<String> result) {
        if (index == digits.length()) {
            result.add(curr.toString());
            return;
        }
        String combination = digitToLetter[digits.charAt(index)-'0'];
        for (char ch : combination.toCharArray()) {
            curr.append(ch);
            backtrack(digits, index+1, curr, result);
            curr.deleteCharAt(curr.length()-1);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.letterCombinations("23"));
    }
}
