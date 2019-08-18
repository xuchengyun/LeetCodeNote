package LCQuestions;

public class _303_RangeSumQueryImmutable {

    private int[] sums;

    public _303_RangeSumQueryImmutable(int[] nums) {
        if(nums.length != 0){
            sums = new int[nums.length];

            sums[0] = nums[0];
            for(int i = 1; i < nums.length; i++){
                sums[i] = nums[i] + sums[i-1];
            }
        }
    }

    public int sumRange(int i, int j) {
        return i == 0 ? sums[j] : sums[j] - sums[i-1];
    }
}
