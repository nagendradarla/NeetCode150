package LeetCode36;

import java.util.HashMap;
import java.util.HashSet;

/**
 * NeetCode #7 | LeetCode 36: Valid Sudoku
 * Pattern: Arrays & Hashing | Difficulty: Medium
 *
 * Approach hint:
 * Track seen digits in rows, cols, and 3×3 boxes with HashSets (or bitmasks). Reject duplicates.
 *
 * Similar problems:
 *   - Sudoku Solver (LC 37)
 *   - Check if Every Row and Column Contains All Numbers (LC 2133)
 *
 * Problem:
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * - Each row must contain the digits 1-9 without repetition.
 *
 * - Each column must contain the digits 1-9 without repetition.
 *
 * - Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Note:
 *
 * - A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 *
 * - Only the filled cells need to be validated according to the mentioned rules.
 *
 * Example 1:
 *
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 *
 * Example 2:
 *
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 * Constraints:
 *
 * - board.length == 9
 *
 * - board[i].length == 9
 *
 * - board[i][j] is a digit 1-9 or '.'.
 *
 * Link: https://leetcode.com/problems/valid-sudoku/
 */
class Solution {
    /*public boolean isValidSudoku(char[][] board) {
        // Keeps track of elements in each row
        HashMap<Integer, HashSet<Character>> rowMap = new HashMap<Integer, HashSet<Character>>();
        // Keeps track of elements in each col
        HashMap<Integer, HashSet<Character>> colMap = new HashMap<Integer, HashSet<Character>>();
        // Keeps track of elements in each sub box
        HashMap<Integer, HashSet<Character>> subBoxMap = new HashMap<Integer, HashSet<Character>>();
        int subBoxIdx = 0;
        for (int i=0; i<board.length; i++) {
            HashSet<Character> row = rowMap.getOrDefault(i, new HashSet<Character>());
            for(int j=0; j<board[0].length; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                //1. validate rows
                if (!row.add(c)) return false;
                rowMap.put(i, row);
                //2. validate columns
                HashSet<Character> col = colMap.getOrDefault(j, new HashSet<Character>());
                if (!col.add(c)) return false;
                colMap.put(j, col);
                //3. validate sub-boxes
                subBoxIdx = (i / 3) * 3 + (j / 3);
                HashSet<Character> subBox = subBoxMap.getOrDefault(subBoxIdx, new HashSet<Character>());
                if (!subBox.add(c)) return false;
                subBoxMap.put(subBoxIdx, subBox);
            }
        }
        return true;
    }*/

    /*
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] subBox = new boolean[9][9];
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int num = c - '1';
                int subBoxIdx = (i/3)*3 + (j/3);
                if(row[i][num] || col[j][num] || subBox[subBoxIdx][num]) {
                    return false;
                }
                row[i][num] = true;
                col[j][num] = true;
                subBox[subBoxIdx][num] = true;
            }
        }
        return true;
    }
    */

    public boolean isValidSudoku(char[][] board) {
        HashSet<String> rows = new HashSet<String>();
        HashSet<String> cols = new HashSet<String>();
        HashSet<String> subBoxes = new HashSet<String>();
        String s = null;
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                char c = board[i][j];
                if (c == '.') continue;

                s = "row=" + i + " num=" + c;
                if (!rows.add(s)) return false;
                s = "col=" + j + " num=" + c;
                if (!cols.add(s)) return false;
                int subBoxIdx = (i/3)*3 + (j/3);
                s = "subBox=" + subBoxIdx + " num=" + c;
                if (!subBoxes.add(s)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(sol.isValidSudoku(board)); // true

        board = new char[][] {
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(sol.isValidSudoku(board)); // false
    }
}
