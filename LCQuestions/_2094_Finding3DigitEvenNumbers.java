package LCQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 https://leetcode.com/problems/finding-3-digit-even-numbers/


 */
public class _2094_Finding3DigitEvenNumbers {
    // My Anwser: backtracking
    public int[] findEvenNumbers(int[] digits) {
        Arrays.sort(digits);
        List<Integer> res = permute(digits);
        return res.stream().mapToInt(i -> i).toArray();
    }
    
    public List<Integer> permute(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        helper(nums, new ArrayList<>(), res, 0, new boolean[nums.length]);
        return res;
    }

    private void helper(int[] nums, List<Integer> list, List<Integer> res, int layer, boolean[] isVisited) {
        if (list.size() == 3) {
            int value = list.get(0) * 100 + list.get(1) * 10 + list.get(2);
            if (value % 2 == 0) {
                res.add(value);
            }
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (layer == 0 && nums[i] == 0) continue;
            if (isVisited[i] || (i > 0 && !isVisited[i - 1] && nums[i] == nums[i - 1])) continue;
            list.add(nums[i]);
            isVisited[i] = true;
            helper(nums, list, res, layer + 1, isVisited);
            isVisited[i] = false;
            list.remove(list.size() - 1);
        }
    }


    /**
     brute force
     1. Iterate
     */
    public int[] findEvenNumbers1(int[] digits) {
        Set<Integer> result = new HashSet<>();
        
        for(int i=0; i < digits.length; i++) {
                if(digits[i] != 0) {
                    for(int j = 0; j < digits.length; j++) {
                        if(j != i) {
                            for(int k = 0; k < digits.length; k++) {
                                if(digits[k] %2 ==0 && k != j && k != i) {
                                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                                    result.add(num);
                                }
                            }
                        }
                    }
                }
                
        }
        int[] res = result.stream().mapToInt(Number::intValue).toArray();
        Arrays.sort(res);
        return res;
    }
}