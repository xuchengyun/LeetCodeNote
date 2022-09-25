# [1383. Maximum Performance of a Team](https://leetcode.com/problems/maximum-performance-of-a-team)

## Description

<p>You are given two integers <code>n</code> and <code>k</code> and two integer arrays <code>speed</code> and <code>efficiency</code> both of length <code>n</code>. There are <code>n</code> engineers numbered from <code>1</code> to <code>n</code>. <code>speed[i]</code> and <code>efficiency[i]</code> represent the speed and efficiency of the <code>i<sup>th</sup></code> engineer respectively.</p>

<p>Choose <strong>at most</strong> <code>k</code> different engineers out of the <code>n</code> engineers to form a team with the maximum <strong>performance</strong>.</p>

<p>The performance of a team is the sum of their engineers&#39; speeds multiplied by the minimum efficiency among their engineers.</p>

<p>Return <em>the maximum performance of this team</em>. Since the answer can be a huge number, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
<strong>Output:</strong> 60
<strong>Explanation:</strong> 
We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
<strong>Output:</strong> 68
<strong>Explanation:
</strong>This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
<strong>Output:</strong> 72
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>speed.length == n</code></li>
	<li><code>efficiency.length == n</code></li>
	<li><code>1 &lt;= speed[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= efficiency[i] &lt;= 10<sup>8</sup></code></li>
</ul>


## Solutions
### Solution 1 Priority Queue
<!-- tabs:start -->
Same ideal with **857. Minimum Cost to Hire K Workers.**

`Performance = sum(speed) * min(efficiency)`. Idea is simple: try every efficiency value from highest to lowest and at the same time maintain an as-large-as-possible speed group, keep adding speed to total speed, if size of engineers group exceeds K, lay off the engineer with lowest speed.

1. Sort efficiency with descending order. Because, afterwards, when we iterate whole engineers, every round, when calculating the current performance, minimum efficiency is the effiency of the new incoming engineer.
2. Maintain a pq to track of the minimum speed in the group. If size of group is == K, kick the engineer with minimum speed out (since efficiency is fixed by new coming engineer, the only thing matters now is sum of speed).
3. Calculate/Update performance.

### **Java**

```java
class Solution {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] engineers = new int[n][2];
        for (int i = 0; i < n; i++) {
            engineers[i] = new int[]{speed[i], efficiency[i]};
        }
        // Sort by efficiency
        Arrays.sort(engineers, (a, b) -> (b[1] - a[1]));
        Queue<Integer> pq = new PriorityQueue<>(k + 1);
        
        long res = 0, sumSpeed = 0;
        for (int[] engineer: engineers) {
            pq.add(engineer[0]);
            sumSpeed += engineer[0];
            if (pq.size() > k) {
                sumSpeed -= pq.poll();
            }
            res = Math.max(res, sumSpeed * engineer[1]);
        }
        
        return (int)(res % (int)(1e9 + 7));
    }
}
```

### **...**

```

```

<!-- tabs:end -->