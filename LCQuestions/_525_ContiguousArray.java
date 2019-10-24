package LCQuestions;

public class _525_ContiguousArray {

    // brute force
    public int findMaxLength(int[] nums) {
        int res = 0;
        int count0 = 0;
        int count1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count0++;
            } else {
                count1++;
            }
            int tmp0 = count0;
            int tmp1 = count1;
            for (int j = 0; j < i; j++) {
                if (tmp0 == tmp1) {
                    res = Math.max(res, i - j + 1);
                }
                if (nums[i] == 0) {
                    tmp0--;
                } else {
                    tmp1--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _525_ContiguousArray a = new _525_ContiguousArray();
        System.out.println(a.findMaxLength(new int[]{0, 0, 1, 0, 0, 0, 1, 1}));

    }
}
