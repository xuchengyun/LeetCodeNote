package LCQuestions;

public class _167_TwoSumII {

    /**
     * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
     The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
     Note:
     Your returned answers (both index1 and index2) are not zero-based.
     You may assume that each input would have exactly one solution and you may not use the same element twice.
     * @param numbers
     * @param target
     * @return
     */
    //two pointer
    public int[] twoSum(int[] numbers, int target) {
        int start = 0, end = numbers.length - 1;
        while (start < end) {
            int cur = numbers[start] + numbers[end];
            if (cur == target){
                return new int[]{start, end};
            }
            if (cur > target) {
                end--;
            } else {
                start++;
            }

        }
        return new int[2];
    }
}
