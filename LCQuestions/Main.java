package LCQuestions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    // recursion
    public int countVowelPermutation(int n) {
        int[] count = new int[5];
        Arrays.fill(count, 1);
        long number = (long)(Math.pow(10, 9) + 7);

        while (n > 1) {
            n--;
            int temp0 = count[1] + count[2] + count[4];
            int temp1 = count[0] + count[2];
            int temp2 = count[1] + count[3];
            int temp3 = count[2];
            int temp4 = count[2] + count[3];
            count[0] = temp0;
            count[1] = temp1;
            count[2] = temp2;
            count[3] = temp3;
            count[4] = temp4;
        }
        int sum = 0;
        for(int i : count) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        Main obj = new Main();
        int[][] gold = new int[][]{{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
        System.out.println(obj.maxEqualFreq(new int[]{1,1,2,2,3,3,3,4,4,75,20,67,37,24,33,71,1,91,60,65,53,70,96,77,52,4,57,51,8,63,79,57,26,99,80,24,39,89,70,73,70,92,56,52,45,39,6,45,41,94,55,92,24,21,76,25,28,58,53,35,95,98,94,85,35,60,25,85,29,66,25,38,22,77,54,85,100,65,30,7,8,49,66,38,79,4,99,49,63,63,69,82,43,38,83,11,56,85,25,38,61,40,36,67,84}));
    }


    public int maxEqualFreq(int[] nums) {
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();
        for (int num : nums) {
            m1.put(num, m1.getOrDefault(num, 0) + 1);
        }

        for (int value : m1.values()) {
            m2.put(value, m2.getOrDefault(value, 0) + 1);
        }

        int end = nums.length - 1;
        boolean flag = false;
        while (!((m2.keySet().size() == 2 && m2.containsKey(1) && m2.get(1) == 1)
                || (m2.keySet().size() == 1 && m2.containsKey(1))
                || (m1.size() == 1 && m2.size() == 1))) {
            if (m2.keySet().size() == 2) {
                int a = Integer.MAX_VALUE;
                for (int m : m2.values()) {
                    if (m == 1) {
                        flag = true;
                    }
                }
            }
            if (flag) {
                break;
            }
            int cnt = m1.get(nums[end]);
            int value = m2.get(cnt);
            if (--value == 0) {
                m2.remove(cnt);
            }
            cnt--;
            if (cnt == 0) {
                int val = m1.remove(nums[end]);
                if (m2.containsKey(val)) {
                    if (m2.get(val) - 1 == 0) {
                        m2.remove(val);
                    } else {
                        m2.put(val, m2.get(val) - 1);

                    }

                }
            }  else {
                m2.put(cnt, m2.getOrDefault(cnt, 0) + 1);
                if (m2.containsKey(cnt + 1)) {
                    m2.put(cnt + 1, m2.get(cnt + 1) - 1);
                    if (m2.get(cnt + 1) == 0) {
                        m2.remove(cnt + 1);
                    }
                }

                m1.put(nums[end], cnt);
            }
            end--;
        }
        return end + 1;
    }

}
