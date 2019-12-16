package LCQuestions;

public class _330_PatchingArray {
    /**
     Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number
     in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.
     Example 1:
     Input: nums = [1,3], n = 6
     Output: 1
     Explanation:
     Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
     Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
     Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
     So we only need 1 patch.

     https://leetcode.com/submissions/detail/115253726/
     https://leetcode.com/problems/patching-array/discuss/78488/Solution-%2B-explanation
     * @param nums
     * @param n
     * @return
     */
    //greedy
    // 此题本质上是一个数学题
    //nums = [1, 2, 4, 13, 43] and n = 100

    /**
     * 1 : miss : 2
     * 2 : miss : 4
     * 4 : miss : 8
     * 13 : miss 16 , added : 1
     * 13 : miss 29, added : 1
     * 43 : miss 58 , added : 1
     */
    public int minPatches(int[] nums, int n) {
        int miss = 1, added = 0, i = 0;
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            } else {
                miss += miss;
                added++;
            }
        }
        return added;
    }
}
