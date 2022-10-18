package LCQuestions.Solutions._2400_2499._2443_SumOfNumberAndItsReverse;

public class _2443_SumOfNumberAndItsReverse {
    public boolean sumOfNumberAndReverse(int num) {
        for (int i = 0; i <= num; i++) {
            if (i + getReverse(i) == num) {
                return true;
            }
        }
        return false;
    }

    private int getReverse(int n) {
        int rev = 0; // reversed number
        int rem;   // remainder

        while(n>0){

            rem = n%10;
            rev = (rev*10) + rem;
            n = n/10;
        }
        return rev;
    }
}
