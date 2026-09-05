package LeetCode51;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> answer = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) { // Pre fill all the places with dots
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(board, 0, answer);
        return answer;
    }

    private void backtrack(char[][] board, int col, List<List<String>> answer) {
        if (col == board.length) { //board.length is the row = column size
            answer.add(construct(board));
            return;
        }

        for (int i = 0; i < board.length; i++) { //board.length is the row = column size
            if (isValid(board, i, col)) {
                board[i][col] = 'Q';
                backtrack(board, col + 1, answer);
                board[i][col] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) { // Upper diagonal
            if (board[i][j] == 'Q')
                return false;
        }

        for (int i = col; i >= 0; i--) { // current row
            if (board[row][i] == 'Q')
                return false;
        }

        for (int i = row, j = col; i < board.length && j >= 0; i++, j--) { // Lower diagonal
            if (board[i][j] == 'Q')
                return false;
        }
        return true;
    }

    private List<String> construct(char[][] board) {
        ArrayList<String> option = new ArrayList<String>();
        for (char[] row : board) {
            option.add(new String(row));
        }
        return option;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solveNQueens(4));
    }
}

/*
Optimized Solution

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> answer = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(board[i], '.');

        // Optimization: Use boolean arrays to track blocked paths
        boolean[] rows = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; // row + col
        boolean[] diag2 = new boolean[2 * n]; // row - col + n

        backtrack(board, 0, answer, rows, diag1, diag2);
        return answer;
    }

    private void backtrack(char[][] board, int col, List<List<String>> answer,
                           boolean[] rows, boolean[] diag1, boolean[] diag2) {
        if (col == board.length) {
            answer.add(construct(board));
            return;
        }

        for (int i = 0; i < board.length; i++) {
            // O(1) Check instead of O(N) isValid()
            if (!rows[i] && !diag1[i + col] && !diag2[i - col + board.length]) {

                board[i][col] = 'Q';
                rows[i] = diag1[i + col] = diag2[i - col + board.length] = true;

                backtrack(board, col + 1, answer, rows, diag1, diag2);

                // Backtrack
                board[i][col] = '.';
                rows[i] = diag1[i + col] = diag2[i - col + board.length] = false;
            }
        }
    }

    private List<String> construct(char[][] board) {
        List<String> res = new ArrayList<>();
        for (char[] row : board) res.add(new String(row));
        return res;
    }
}

 */