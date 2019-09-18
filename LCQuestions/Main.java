package LCQuestions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static int kConcatenationMaxSum(int[] arr, int k) {
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }

        if (sum < 0) {
            if (k > 1) {
                int[] newArr = new int[arr.length * 2];
                for (int i = 0; i < newArr.length; i++) {
                    newArr[i] = arr[i % arr.length];
                }
                return maxSubArraySum(newArr);
            } else {
                return maxSubArraySum(arr);
            }
        } else {
            int[] newArr = new int[arr.length * 2];
            for (int i = 0; i < newArr.length; i++) {
                newArr[i] = arr[i % arr.length];
            }
            int res1 = maxSubArraySum(newArr);
            if (k <= 2) {
                return res1;
            }
            int postSum = 0;
            int postMax = Integer.MIN_VALUE;
            for (int i = arr.length - 1; i >= 0; i--) {
                postSum += arr[i];
                postMax = Math.max(postSum, postMax);
            }

            int preSum = 0;
            int preMax = Integer.MIN_VALUE;
            for (int i = 0; i < arr.length; i++) {
                preSum += arr[i];
                preMax = Math.max(preSum, preMax);
            }

            int res2 = ((k - 2)* sum  + postMax + preMax) % (10^9 + 7);
            return Math.max(res1, res2);
        }
    }

    static int  maxSubArraySum(int a[]) {
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

        for (int i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }
    public static void main(String[] args)  {
        int k = 36263;
        System.out.println(kConcatenationMaxSum(new int[]{}, k));
    }
}
