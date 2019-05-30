package LCQuestions.lc004MedianOfTwoSortedArrays;

/**
 index : 0 1 2 3 4 5
 */
public class MedianOfTwoSortedArrays {

    /**
       method 1 brute force, combine two arrays and find the median
       time : O(m + n)
       space : O(m + n);
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
       int len1 = nums1.length;
       int len2 = nums2.length;
       int len = len1 + len2;
       int[] nums = new int[len];
       int i = 0, j = 0, k = 0;
       while (i < len1 && j < len2) {
           if (nums1[i] < nums2[j]) {
               nums[k++] = nums1[i++];
           } else {
               nums[k++] = nums2[j++];
           }
       }
       while (i < len1) {
           nums[k++] = nums[i++];
       }

       while (j < len2) {
           nums[k++] = nums[j++];
       }

       if (len % 2 == 0) {
           return (nums[len / 2 - 1] + nums[len / 2]) / 2.0;
       } else {
           return nums[len / 2];
       }
    }

    /**
     * O(log(min(m, n)))
     *
     * 参考博客 http://blog.csdn.net/chen_xinjia/article/details/69258706
     *
     * index: 0 1   2 3 4 5
     *          L1  R1
     * num1:  3 5 | 8 9             4 cut1
     * num2:  1 2 7 |10 11 12       6 cut2
     *            L2 R2
     * num3:  1 2 3 5 7 | 8 9 10 11 12
     *
     * num3 -> num1 + num2 -> num1
     *
     *
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int len = nums1.length + nums2.length;
        int cut1 = 0;
        int cut2 = 0;
        int cutL = 0;
        int cutR = nums1.length;
        while (cut1 <= nums1.length) {
            cut1 = (cutR - cutL) / 2 + cutL;
            cut2 = len / 2 - cut1;
            double L1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            double L2 = (cut2 == 0) ? Integer.MIN_VALUE : nums1[cut2 - 1];
            double R1 = (cut1 == 0) ? Integer.MAX_VALUE : nums1[cut1];
            double R2 = (cut2 == 0) ? Integer.MAX_VALUE : nums1[cut1 - 1];
            if (L1 > R2) {
                cutR = cut1 - 1;
            } else if (L2 > R1) {
                cutL = cutR + 1;
            } else {
                if (len % 2 == 0) {
                    L1 = L1 > L2 ? L1 : L2;
                    R1 = R1 < R2 ? R1 : R2;
                    return (L1 + R1) / 2;
                } else {
                    R1 = (R1 < R2) ? R1 : R2;
                    return R1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays m = new MedianOfTwoSortedArrays();
        int[] nums1 = new int[]{1,2};
        int[] nums2 = new int[]{3,4};
        m.findMedianSortedArrays1(nums1, nums2);
    }
}
