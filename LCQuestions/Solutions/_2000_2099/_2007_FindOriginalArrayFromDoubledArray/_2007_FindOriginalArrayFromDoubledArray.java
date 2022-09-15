package LCQuestions.Solutions._2000_2099._2007_FindOriginalArrayFromDoubledArray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _2007_FindOriginalArrayFromDoubledArray {
    public int[] findOriginalArray(int[] changed) {
        if (changed == null || changed.length == 0) return new int[0];
        int length = changed.length;
        if (length % 2 != 0) {
            return new int[0];
        }
        Arrays.sort(changed);
        Map<Integer, Integer> freq = new HashMap<>();

        for (int value: changed) {
            freq.put(value, freq.getOrDefault(value, 0) + 1);
        }

        int[] original= new int[changed.length / 2];
        int index = 0;
        for (int i = 0; i < length; i++) {
            int val = changed[i];
            if (!freq.containsKey(val)) {
                continue;
            }

            original[index++] = val;
            int curCnt = freq.get(val);
            if (curCnt == 1) {
                freq.remove(val);
            } else {
                freq.put(val, curCnt - 1);
            }

            if (!freq.containsKey(val * 2)) {
                return new int[0];
            }
            int doubleCnt = freq.get(val * 2);

            if (doubleCnt == 1) {
                freq.remove(val * 2);
            } else {
                freq.put(val * 2, doubleCnt - 1);
            }
        }
        return original;
    }


    // Counting Sort
    public int[] findOriginalArray1(int[] changed) {
        // It can't be doubled array, if the size is odd
        if (changed.length % 2 == 1) {
            return new int[0];
        }

        int maxNum = 0;
        // Find the max element in the array
        for (int num : changed) {
            maxNum = Math.max(maxNum, num);
        }

        int[] freq = new int[2 * maxNum + 1];
        // Store the frequency in the map
        for (int num : changed) {
            freq[num]++;
        }

        int[] original = new int[changed.length / 2];
        int index = 0;
        for (int num = 0; num <= maxNum; num++) {
            // If element exists
            if (freq[num] > 0) {
                freq[num]--;

                int twiceNum = num * 2;
                if (freq[twiceNum] > 0) {
                    // Pair up the elements, decrement the count
                    freq[twiceNum]--;
                    // Add the original number to answer
                    original[index++] = num;
                    num--;
                } else {
                    return new int[0];
                }
            }
        }

        return original;
    }
}
