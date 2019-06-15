package LCQuestions;

import java.util.ArrayList;
import java.util.List;

public class _055_NQueens {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        //dfs helper
        helper(res, board, 0);
        return res;
    }

    private void helper(List<List<String>> res, char[][] board, int curRow) {
        int n = board.length;
        if (curRow == n) {
            res.add(construct(board));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(board, curRow, i)) {
                board[curRow][i] = 'Q';
                helper(res, board, curRow + 1);
                board[curRow][i] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; --i, --j) {
            if (board[i][j] == 'Q') return false;
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; --i, ++j) {
            if (board[i][j] == 'Q') return false;
        }
        return true;

    }

    private List<String> construct(char[][] board) {
        int n = board.length;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }


}
