package LCQuestions;

public class _153_FindMinimuminRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (left == right) {
                break;
            }
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[right]) {
                return nums[mid];
            }
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
