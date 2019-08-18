package LCQuestions;

import java.util.ArrayList;
import java.util.List;

/**
 Given an integer n, return all distinct solutions to the n-queens puzzle.

 Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

 Example:

 Input: 4
 Output: [
 [".Q..",  // Solution 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // Solution 2
 "Q...",
 "...Q",
 ".Q.."]
 ]
 Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */
public class _051_NQueens {
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
