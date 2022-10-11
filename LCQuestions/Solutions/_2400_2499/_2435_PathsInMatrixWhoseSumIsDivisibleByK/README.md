# [2435. Paths in Matrix Whose Sum Is Divisible by K](https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k)

## Description

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> integer matrix <code>grid</code> and an integer <code>k</code>. You are currently at position <code>(0, 0)</code> and you want to reach position <code>(m - 1, n - 1)</code> moving only <strong>down</strong> or <strong>right</strong>.</p>

<p>Return<em> the number of paths where the sum of the elements on the path is divisible by </em><code>k</code>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://assets.leetcode.com/uploads/2022/08/13/image-20220813183124-1.png" style="width: 437px; height: 200px;" />
<pre>
<strong>Input:</strong> grid = [[5,2,4],[3,0,5],[0,7,2]], k = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are two paths where the sum of the elements on the path is divisible by k.
The first path highlighted in red has a sum of 5 + 2 + 4 + 5 + 2 = 18 which is divisible by 3.
The second path highlighted in blue has a sum of 5 + 3 + 0 + 5 + 2 = 15 which is divisible by 3.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img src="https://assets.leetcode.com/uploads/2022/08/17/image-20220817112930-3.png" style="height: 85px; width: 132px;" />
<pre>
<strong>Input:</strong> grid = [[0,0]], k = 5
<strong>Output:</strong> 1
<strong>Explanation:</strong> The path highlighted in red has a sum of 0 + 0 = 0 which is divisible by 5.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img src="https://assets.leetcode.com/uploads/2022/08/12/image-20220812224605-3.png" style="width: 257px; height: 200px;" />
<pre>
<strong>Input:</strong> grid = [[7,3,4,9],[2,3,6,2],[2,3,7,0]], k = 1
<strong>Output:</strong> 10
<strong>Explanation:</strong> Every integer is divisible by 1 so the sum of the elements on every possible path is divisible by k.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
</ul>


## Solutions

<!-- tabs:start -->

### Solution 1 Recursion with memorization
### **Java**

```java
class Solution {
    int[][][] dp;
    int mod = 1_000_000_007;
    
    public int numberOfPaths(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        dp = new int[rows][cols][k];
        for(int[][] a : dp) {
            for(int[] b : a) {
                Arrays.fill(b, -1);
            }
        }
        
        return helper(grid, 0, 0, 0, k);
    }
    
    int helper(int[][] grid, int r, int c, int sum, int k) {
        if(r < 0 || r == grid.length || c < 0 || c == grid[0].length) {
            return 0;
        }
        
        sum += grid[r][c];
        if(r == grid.length-1 && c == grid[0].length-1) {
            return sum%k==0 ? 1 : 0;
        }
        
        if(dp[r][c][sum%k] != -1) {
            return dp[r][c][sum%k];
        }
        
        dp[r][c][sum%k] = (helper(grid, r+1, c, sum, k) + helper(grid, r, c+1, sum, k)) % mod;
        return dp[r][c][sum%k];
    }
}
```

### Solution 2 DP
```java
class Solution {

    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, mod = 1000_000_007;
        int[][][] dp = new int[m][n][k];
        dp[0][0][grid[0][0] % k] = 1;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int s = 0; s < k; s++) {
                    int moddedSum = (s + grid[i][j]) % k;
                    if (j > 0) dp[i][j][moddedSum] += dp[i][j - 1][s];
                    if (i > 0) dp[i][j][moddedSum] += dp[i - 1][j][s];
                    dp[i][j][moddedSum] %= mod;
                }
        return dp[m - 1][n - 1][0];
    }
}
```

### **...**

```

```

<!-- tabs:end -->