package LCQuestions.lc334IncreasingTripletSubsequence;

public class IncreasingTripletSubsequence {
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
