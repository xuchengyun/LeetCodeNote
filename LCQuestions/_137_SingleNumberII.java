package LCQuestions;

public class _137_SingleNumberII {

    /**
     * 0 -> 1 -> 2 -> 0
     * 00 -> 01 -> 10 -> 00
     * 00 -> 10 -> 01 -> 00
     *
     * ones  twos
     * 0      0
     * 0 -> 1 0 -> 0
     * 1 -> 0 0 -> 1
     * 0 -> 0 1 -> 0
     *
     * 1.存入ones里面
     * 2.清空ones 存入twos
     * 3.twos清空
     *
     * 1 1 1 2
     *
     * 1： 0001
     * ones : 0001
     * twos : 0000
     *
     * 2: 0001
     * ones : 0000
     * twos : 0001
     *
     * 3: 0001
     * ones: 0000
     *
     *
     */
    public int singleNumber(int[] nums) {
        // ones 第一位
        // twos 第二位
        int ones = 0, twos = 0;
        for (int i = 0; i < nums.length; i++) {
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;
    }
}
