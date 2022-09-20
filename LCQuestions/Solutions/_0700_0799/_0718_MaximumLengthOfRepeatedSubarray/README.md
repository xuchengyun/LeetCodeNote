# [718. Maximum Length of Repeated Subarray](https://leetcode.com/problems/maximum-length-of-repeated-subarray)

## Description

<p>Given two integer arrays <code>nums1</code> and <code>nums2</code>, return <em>the maximum length of a subarray that appears in <strong>both</strong> arrays</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The repeated subarray with maximum length is [3,2,1].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
<strong>Output:</strong> 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 100</code></li>
</ul>


## Solutions

<!-- tabs:start -->
### Solution 1

### **Java**
`dp[i][j]` represent the length  of longest common subarray ending with `nums1[i-1]` and `nums2[j-1]`

```java
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    res = Math.max(res, dp[i + 1][j + 1]);
                }
            }
        }
        
        return res;
    }
}
```

### Solution 4 Sliding window
```java
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int result = 0;
        for (int i = 0; i < nums1.length + nums2.length - 1; ++i) {
            // The current overlapping window is A[aStart, Math.min(A.length, B.length)] and B[bStart, Math.min(A.length, B.length)].
            int aStart = Math.max(0, nums1.length - 1 - i);
            int bStart = Math.max(0, i - (nums1.length - 1));
            int numConsecutiveMatches = 0;
            for (int aIdx = aStart, bIdx = bStart; aIdx < nums1.length && bIdx < nums2.length; ++aIdx, ++bIdx) {
                // Maintain number of equal consecutive elements in the current window (overlap) and the max number ever computed.
                numConsecutiveMatches = nums1[aIdx] == nums2[bIdx] ? numConsecutiveMatches + 1 : 0;
                result = Math.max(result, numConsecutiveMatches);
            }
        }
        return result;
    }
}
```

### **...**

```

```

<!-- tabs:end -->