package LCQuestions;

public class _088_MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int l1 = m - 1;
        int l2 = n - 1;
        int k = m + n - 1;
        while (l1 >= 0 && l2 >= 0) {
            nums1[k--] = nums1[l1] >= nums2[l2] ? nums1[l1--] : nums2[l2--];
        }

        while (l2 >= 0) {
            nums1[k--] = nums2[l2--];
        }
    }
}
