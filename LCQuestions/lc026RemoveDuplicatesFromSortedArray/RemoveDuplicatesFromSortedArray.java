package LCQuestions.lc026RemoveDuplicatesFromSortedArray;

public class RemoveDuplicatesFromSortedArray {

    // two pointers
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[index]) {
                index++;
                nums[index] = nums[i];
            }
        }
        return index + 1;
    }
}
