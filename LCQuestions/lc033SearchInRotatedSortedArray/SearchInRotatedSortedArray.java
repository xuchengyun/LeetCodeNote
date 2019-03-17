package LCQuestions.lc033SearchInRotatedSortedArray;

public class SearchInRotatedSortedArray {
//    0　　1　　2　　 4　　5　　6　 7
//
//    7　　0　　1　　 2　　4　　5　 6
//
//    6　　7　　0　　 1　　2　　4　 5
//
//    5　　6　　7　　 0　　1　　2　　4
//
//    4　　5　　6　　 7　　0　　1　　2
//
//    2　　4　　5　　 6　　7　　0　　1
//
//    1　　2　　4　　 5　　6　　7　　0
    // 1.如果中间的数小于右半边的数，则有半段是有序的。 （Ascending）
    // 2, 如果中间的数大于有半段的数，则左半段是有序的。
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < nums[right]) {
                if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (nums[mid] > target && nums[left] <= target) {
                    right = mid - 1;
                } else {
                    right = mid + 1;
                }
            }
        }
        return -1;
    }
}
