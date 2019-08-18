package LCQuestions;

public class _052_NQueensII {
    /**
     判断有多少个n皇后， 用布尔类型变量来解
     * @param n
     * @return
     */
    int res = 0;
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] d1 = new boolean[2 * n]; // \
        boolean[] d2 = new boolean[2 * n]; // /
        helper(0, cols, d1, d2);
        return res;
    }

    private void helper(int rows, boolean[] cols, boolean[] d1, boolean[] d2) {
    }
}
