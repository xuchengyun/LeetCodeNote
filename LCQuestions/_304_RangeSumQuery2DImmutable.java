package LCQuestions;

public class _304_RangeSumQuery2DImmutable {

    int[][] sumdp;
    public _304_RangeSumQuery2DImmutable(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return;
        int row = matrix.length, col = matrix[0].length;
        sumdp = new int[row + 1][col + 1];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sumdp[i + 1][j + 1] = sumdp[i + 1][j] + sumdp[i][j + 1] - sumdp[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sumdp[row2 + 1][col2 + 1] - sumdp[row2 + 1][col1] - sumdp[row1][col2 + 1] + sumdp[row1][col1];
    }
}
