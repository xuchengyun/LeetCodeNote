package LCQuestions;

public class _034_FindFirstandLastPositionofElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        // find start
        int start = findStart(nums, target);
        if (start == nums.length || nums[start] != target) {
            return new int[]{-1, -1};
        }
        int end = findEnd(nums, target);
        return new int[]{start, end};
    }

    private int findStart(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            System.out.println(left);
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target){
                right = mid - 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int findEnd(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target){
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
