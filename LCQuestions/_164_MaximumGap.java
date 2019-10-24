package LCQuestions;

import java.util.Arrays;

public class _164_MaximumGap {
    //bucket sort
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int n : nums) {
            min = Math.min(n, min);
            max = Math.max(n, max);
        }
        int len = nums.length;
        int gap = (max - min) / len + 1;
        int buckets = (max - min) / gap + 1;
        int[] bucketMax = new int[buckets];
        int[] bucketMin = new int[buckets];
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        Arrays.fill(bucketMin, Integer.MAX_VALUE);

        for (int n : nums) {
            int idx = (n - min) / gap;
            bucketMax[idx] = Math.max(bucketMax[idx], n);
            bucketMin[idx] = Math.min(bucketMin[idx], n);
        }
        int res = 0;
        int pre = bucketMax[0];
        for (int i = 1; i < buckets; i++) {
            if (bucketMax[i] == Integer.MIN_VALUE && bucketMin[i] == Integer.MAX_VALUE) {
                continue;
            }
            res = Math.max(res, bucketMin[i] - pre);
            pre = bucketMax[i];
        }
        return res;
    }

    // radix sort
    public int maximumGap1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        int exp = 1;
        int digits = 10;
        int[] output = new int[nums.length];
        int index = 0;
        while (max / exp > 0) {
            System.out.println(index + "," +exp);
            index++;
            int[] count = new int[digits];
            for (int i = 0; i < nums.length; i++) {
                count[(nums[i] / exp) % digits]++;
            }

            for (int i = 1; i < count.length; i++) {
                System.out.print(" " + count[i]);
                count[i] += count[i - 1];
            }

            for (int i = nums.length - 1; i >= 0; i--) {
                output[--count[(nums[i]/ exp) % digits]] = nums[i];
            }
            nums = Arrays.copyOf(output, output.length);
            exp *= 10;
        }

        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(res, nums[i] - nums[i - 1]);
        }
        return res;
    }

    public static void main(String[] args) {
        _164_MaximumGap obj = new _164_MaximumGap();
        obj.maximumGap1(new int[]{1, 1000000});
    }


}
