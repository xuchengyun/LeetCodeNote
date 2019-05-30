package LCQuestions.lc396RotateFunction;

public class RotateFunction {

    public int maxRotateFunction(int[] A) {
        int length = A.length;
        int sum = 0, F = 0;

        for (int i = 0; i < length; i++) {
            sum += A[i];
            F += i * A[i];
        }
        int max = F;

        for (int i = 0; i < length; i++) {
            F = F + sum - length * A[length - i -1];
            max = Math.max(max, F);
        }

        return max;
    }
}
