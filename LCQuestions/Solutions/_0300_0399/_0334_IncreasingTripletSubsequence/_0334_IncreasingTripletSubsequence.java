package LCQuestions.Solutions._0300_0399._0334_IncreasingTripletSubsequence;

public class _0334_IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for (int num: nums) {
            if (num <= min) {
                min = num;
            } else if(num <= secondMin) {
                secondMin = num;
            } else {
                return true;
            }
        }
        return false;
    }
}
