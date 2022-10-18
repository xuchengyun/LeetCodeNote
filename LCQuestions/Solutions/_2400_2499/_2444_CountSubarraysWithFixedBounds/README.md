# [2444. Count Subarrays With Fixed Bounds](https://leetcode.com/problems/count-subarrays-with-fixed-bounds)

## Description

<p>You are given an integer array <code>nums</code> and two integers <code>minK</code> and <code>maxK</code>.</p>

<p>A <strong>fixed-bound subarray</strong> of <code>nums</code> is a subarray that satisfies the following conditions:</p>

<ul>
	<li>The <strong>minimum</strong> value in the subarray is equal to <code>minK</code>.</li>
	<li>The <strong>maximum</strong> value in the subarray is equal to <code>maxK</code>.</li>
</ul>

<p>Return <em>the <strong>number</strong> of fixed-bound subarrays</em>.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,2,7,5], minK = 1, maxK = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> The fixed-bound subarrays are [1,3,5] and [1,3,5,2].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1], minK = 1, maxK = 1
<strong>Output:</strong> 10
<strong>Explanation:</strong> Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], minK, maxK &lt;= 10<sup>6</sup></code></li>
</ul>


## Solutions

<!-- tabs:start -->


### **Java**

```java
public class _2444_CountSubarraysWithFixedBounds {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        /**
        Three pointer sliding window:
         Intuition: Loop through the entire array one time.
         At each index ith, find all fixed-bound subarrays with ith index as the right edge of subarray.
         
         How to check fixed-bound arrays
         There are two types of subarrays that end at ith index.

         1.Subarrays where all values are within bound. e.g. minK <= value<= maxK
         2.Subarrays where there exists a value equal to minK, and a value equal to maxK.
         If both 1 and 2 are satisfied, it will be a fixed bound subarray.

         Implementation
         1.Use start variable to exclude values outside bound, where start is exclusive. All subarrays in (start, end] are within bound.

         2.Use hasMax to store latest index where nums[hasMax] == maxK
           Use hasMin to store latest index where nums[hasMin] == minK

         3.Use Math.min(hasMax, hasMin) to find largest left edge where subarray [left, right] contains both minK and maxK
         */
        long res = 0, jbad = -1, jmin = -1, jmax = -1, n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < minK || nums[i] > maxK) jbad = i;
            if (nums[i] == minK) jmin = i;
            if (nums[i] == maxK) jmax = i;
            res += Math.max(0L, Math.min(jmin, jmax) - jbad);
        }
        return res;
    }
}

```

### **...**

```

```

<!-- tabs:end -->