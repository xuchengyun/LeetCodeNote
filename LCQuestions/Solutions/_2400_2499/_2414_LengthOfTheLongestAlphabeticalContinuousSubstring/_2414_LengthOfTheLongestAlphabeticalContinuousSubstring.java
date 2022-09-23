package LCQuestions.Solutions._2400_2499._2414_LengthOfTheLongestAlphabeticalContinuousSubstring;

public class _2414_LengthOfTheLongestAlphabeticalContinuousSubstring {
    public int smallestEvenMultiple(int n) {
        return n << (n & 1);
    }
}
