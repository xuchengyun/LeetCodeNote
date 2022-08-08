# [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)

[English Version](/solution/0000-0099/0034.Find%20First%20and%20Last%20Position%20of%20Element%20in%20Sorted%20Array/README_EN.md)

## 题目描述

<div><p>Given an integer array <code>nums</code>, return the length of the longest strictly increasing subsequence.</p>

<p>A <strong>subsequence</strong> is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, <code>[3,6,2,7]</code> is a subsequence of the array <code>[0,3,1,6,2,2,7]</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> nums = [10,9,2,5,3,7,101,18]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> nums = [0,1,0,3,2,3]
<strong>Output:</strong> 4
</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>Input:</strong> nums = [7,7,7,7,7,7,7]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2500</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><b>Follow up:</b>&nbsp;Can you come up with an algorithm that runs in&nbsp;<code>O(n log(n))</code> time complexity?</p>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一: 动态规划**

定义 `dp[i]` 为以 `nums[i]` 结尾的最长子序列的长度，`dp[i]` 初始化为 `1(i∈[0, n))`。 即题目求的是 `dp[i] （i ∈[0, n-1]）`的最大值。
状态转移方程为：`dp[i] = max(dp[j]) + 1`，其中 `0≤j<i `且 `nums[j] < nums[i]`。

时间复杂度：`O(n^2)`.
```java
class Solution {
   public int lengthOfLIS(int[] nums) {
      int n = nums.length;
      int[] dp = new int[n];
      Arrays.fill(dp, 1);
      int res = 1;
      for (int i = 1; i < n; ++i) {
         for (int j = 0; j < i; ++j) {
            if (nums[j] < nums[i]) {
               dp[i] = Math.max(dp[i], dp[j] + 1);
            }
         }
         res = Math.max(res, dp[i]);
      }
      return res;
   }
}
```
**方法二: 贪心 + 二分法**

维持一个`list`,`list[i]` 表示长度为 i 的最长上升子序列末尾元素的最小值， 我们的任务是遍历数组来生成这个**list**。
如果:
1. 若 `nums[i] > list` 中最后一个元素，则直接将 nums[i] 加入到 `list` 的末尾, 说明最长上升子序列长度加一；
2. 否则，在 `list` 中二分查找（`list` 是一个单调递增数组），找到第一个大于等于` nums[i]` 的位置 `idx`，更新 `d[idx]` = `nums[i]`。
```java
class Solution {
    List<Integer> list = new ArrayList<>();
    public int lengthOfLIS(int[] nums) {
        list.add(nums[0]);
        
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > list.get(list.size() - 1)) {
                list.add(num);  
            } else {
                list.set(binarySearch(num), num)  ;
            }
        }
        return list.size();
    }
    
    private int binarySearch(int num) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) == num)  return mid;
            if (list.get(mid) < num) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }
}
```
**方法三 : 递归， top-down 动态规划**
```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[][] memo = new int[nums.length + 1][nums.length];
        return lengthOfLIS(nums, -1, 0, memo);
    }
    
    private int lengthOfLIS(int[] nums, int preIndex, int pos, int[][] memo) {
        if (nums.length == pos) {
            return 0;
        }
        if (memo[preIndex + 1][pos] > 0) {
            return memo[preIndex + 1][pos];
        }
        int taken = 0;
        int notaken = 0;
        if (preIndex < 0 || nums[pos] > nums[preIndex]) {
            taken = 1 + lengthOfLIS(nums, pos, pos + 1, memo);
            notaken = lengthOfLIS(nums, preIndex, pos + 1, memo);
        } else {
            notaken = lengthOfLIS(nums, preIndex, pos + 1, memo);
        }
        memo[preIndex + 1][pos] = Math.max(taken, notaken);
        return memo[preIndex + 1][pos];
    }
}
```


<!-- tabs:end -->