package LCQuestions;

public class _240_Searcha2DMatrixII {
    // mathod1 for each row, do binary search O(m * log(n)))
    public boolean searchMatrix(int[][] matrix, int target) {
        return true;
    }

    // mathod1 for each row, do binary search O(m * log(n)))
    public boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
            return false;
        }
        int sr = m - 1;
        int sc = 0;
        while (true) {
            if (sr < 0 || sr >= m || sc < 0 || sc >= n) {
                break;
            }
            if (matrix[sr][sc] == target) {
                return true;
            }
            if (matrix[sr][sc] < target) {
                sc++;
            } else {
                sr--;
            }
        }
        return false;
    }
}
