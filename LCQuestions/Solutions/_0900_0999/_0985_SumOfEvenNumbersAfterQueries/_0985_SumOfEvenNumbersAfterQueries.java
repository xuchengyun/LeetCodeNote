package LCQuestions.Solutions._0900_0999._0985_SumOfEvenNumbersAfterQueries;

public class _0985_SumOfEvenNumbersAfterQueries {
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        // Sum the even number first
        int sum = 0;
        for (int num : A) {
            if (num % 2 == 0) sum += num;
        }

        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int index = queries[i][1];
            if (A[index] % 2 == 0) sum -= A[index];
            A[index] += queries[i][0];
            if (A[index] % 2 == 0) sum += A[index];
            res[i] = sum;
        }
        return res;
    }
}
