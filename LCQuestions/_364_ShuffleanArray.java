package LCQuestions;

import java.util.Random;

public class _364_ShuffleanArray {
    Random rd;
    int[] nums;

    public _364_ShuffleanArray(int[] nums) {
        this.rd = new Random();
        this.nums = nums;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return nums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    // inside out algorithm
    public int[] shuffle() {
        int[] rand = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int r = rd.nextInt(i + 1);
            rand[i] = rand[r];
            rand[r] = nums[i];
        }
        return rand;
    }
}
