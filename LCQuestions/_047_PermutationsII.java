package LCQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _047_PermutationsII {
    /**
     * Input: [1,1,2]
     Output:
     [
     [1,1,2],
     [1,2,1],
     [2,1,1]
     ]


     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0 || nums == null) return res;
        Arrays.sort(nums);
        helper(res, nums, new ArrayList<>(), new int[nums.length]);
        return res;
    }

    public void helper(List<List<Integer>> res, int[] nums, List<Integer> temp, int[] visited) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1 || (i > 0 && visited[i - 1] == 0 && nums[i] == nums[i - 1])) continue;
            visited[i] = 1;
            temp.add(nums[i]);
            helper(res, nums, temp, visited);
            visited[i] = 0;
            temp.remove(temp.size() - 1);
        }
    }
}
