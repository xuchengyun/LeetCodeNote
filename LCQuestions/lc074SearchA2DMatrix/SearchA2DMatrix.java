package LCQuestions.lc074SearchA2DMatrix;

public class SearchA2DMatrix {
    /*
    find row first and find column then
     */
    //TODO
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int row = matrix.length;
        int column = matrix[0].length;


        int targetRow = 0;
        for (int i = 0; i < row; i++) {
            if (target <= matrix[i][0]) {

            }
        }
    return false;
    }
}
