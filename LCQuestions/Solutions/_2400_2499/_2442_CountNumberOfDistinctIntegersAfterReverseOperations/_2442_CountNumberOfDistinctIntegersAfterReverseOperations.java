package LCQuestions.Solutions._2400_2499._2442_CountNumberOfDistinctIntegersAfterReverseOperations;

import java.util.HashSet;
import java.util.Set;

public class _2442_CountNumberOfDistinctIntegersAfterReverseOperations {
    public int countDistinctIntegers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            int res = getReverse(n);
            set.add(res);
            set.add(n);
        }
        return set.size();
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
