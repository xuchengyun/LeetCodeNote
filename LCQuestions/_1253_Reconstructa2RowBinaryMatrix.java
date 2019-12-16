package LCQuestions;

import java.util.ArrayList;
import java.util.List;

public class _1253_Reconstructa2RowBinaryMatrix {
    // greedy algorithm
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>());
        matrix.add(new ArrayList<>());

        for (int i = 0; i < colsum.length; i++) {
            if (colsum[i] == 2) {
                matrix.get(0).add(1);
                matrix.get(1).add(1);
                upper--;
                lower--;
            } else if (colsum[i] == 0) {
                matrix.get(0).add(0);
                matrix.get(1).add(0);
            } else {
                if (upper > lower) {
                    matrix.get(0).add(1);
                    matrix.get(1).add(0);
                    upper--;
                } else {
                    matrix.get(0).add(0);
                    matrix.get(1).add(1);
                    lower--;
                }
            }
            if (upper < 0 || lower < 0) {
                return new ArrayList<>();
            }
        }
        if (upper == 0 && lower == 0) {
            return matrix;
        }
        return new ArrayList<>();
    }
}
