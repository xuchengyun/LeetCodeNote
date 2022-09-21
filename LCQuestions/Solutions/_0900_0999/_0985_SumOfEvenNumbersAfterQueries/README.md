# [985. Sum of Even Numbers After Queries](https://leetcode.com/problems/sum-of-even-numbers-after-queries)

## Description

<p>You are given an integer array <code>nums</code> and an array <code>queries</code> where <code>queries[i] = [val<sub>i</sub>, index<sub>i</sub>]</code>.</p>

<p>For each query <code>i</code>, first, apply <code>nums[index<sub>i</sub>] = nums[index<sub>i</sub>] + val<sub>i</sub></code>, then print the sum of the even values of <code>nums</code>.</p>

<p>Return <em>an integer array </em><code>answer</code><em> where </em><code>answer[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
<strong>Output:</strong> [8,6,2,4]
<strong>Explanation:</strong> At the beginning, the array is [1,2,3,4].
After adding 1 to nums[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
After adding -3 to nums[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
After adding -4 to nums[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
After adding 2 to nums[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1], queries = [[4,0]]
<strong>Output:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= val<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= index<sub>i</sub> &lt; nums.length</code></li>
</ul>


## Solutions

<!-- tabs:start -->
### Solution 1
There are 4 cases for odd / even property of A[k] and queries[i][0], where k = queries[i][1]:
1. even and odd, lose an even item in A; sum need to deduct A[k];
2. even and even, get a bigger even item in A; sum need to add queries[i][0](same as deduct A[k] first then add both);
3. odd and odd, get a bigger even item in A; sum need to add both;
4. odd and even, no influence on even items in A;

Therefore, we can simplify the above as following procedure:

find sum of all even #s;
for each queries, check the item in A and after-added-up value, if
- the item in A is even, deduct it from sum; according to #1 & #2.
- after-added-up we have an even value, then add the new value to sum; according to 2) & 3).

### **Java**

```java
class Solution {
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        // Sum the even number first
        int sum = 0;
        for (int num : A) {
            if (num % 2 == 0) sum += num;
        }
        
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int index = queries[i][1];
            if (A[index] % 2 == 0) sum -= A[index];
            A[index] += queries[i][0];
            if (A[index] % 2 == 0) sum += A[index];
            res[i] = sum;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->