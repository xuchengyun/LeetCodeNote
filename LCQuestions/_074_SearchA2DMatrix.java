package LCQuestions;

public class _074_SearchA2DMatrix {
    /*
    find row first and find column then
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = matrix[mid / n][mid % n];
            if (val == target) {
                return true;
            }
            if (val < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0, right = m - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            int val = matrix[mid][0];
            if (val == target) {
                return true;
            }
            if (val < target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        int row = right;
        left = 0;
        right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2 + 1;
            if (matrix[row][mid] == target) {
                return true;
            }
            if (matrix[row][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
