# [967. Numbers With Same Consecutive Differences](https://leetcode.com/problems/numbers-with-same-consecutive-differences)

## Description

<p>Return all <strong>non-negative</strong> integers of length <code>n</code> such that the absolute difference between every two consecutive digits is <code>k</code>.</p>

<p>Note that <strong>every</strong> number in the answer <strong>must not</strong> have leading zeros. For example, <code>01</code> has one leading zero and is invalid.</p>

<p>You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 7
<strong>Output:</strong> [181,292,707,818,929]
<strong>Explanation:</strong> Note that 070 is not a valid number, because it has leading zeroes.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, k = 1
<strong>Output:</strong> [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 9</code></li>
	<li><code>0 &lt;= k &lt;= 9</code></li>
</ul>


## Solutions

<!-- tabs:start -->
# Solution 1
DFS, 通过递归处理每一个状态

### **Java**
```java
class Solution {
    // DFS
    public int[] numsSameConsecDiff(int N, int K) {
        if (N == 1) return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> queue = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        for (int i = 1; i < N; i++) {
            List<Integer> newArr = new ArrayList<>();
            for (int cur : queue) {
                if (cur % 10 + K < 10) {
                    newArr.add(cur * 10 + cur % 10 + K);
                }
                if (cur % 10 - K >= 0 && K != 0) {
                    newArr.add(cur * 10 + cur % 10 - K);
                }
            }
            queue = newArr;
        }
        return queue.stream().mapToInt(i -> i).toArray();
     }
}
```
# Solution 2 BFS
本体的BFS不是通过队列进行处理的，而是用数组记录每一层的状态，因为每一层元素数量是固定的，所以可以采用这种方法
```java
class Solution {
    // DFS
    public int[] numsSameConsecDiff(int N, int K) {
        if (N == 1) return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> queue = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        for (int i = 1; i < N; i++) {
            List<Integer> newArr = new ArrayList<>();
            for (int cur : queue) {
                int tail = cur % 10;
                if (tail + K < 10) {
                    newArr.add(cur * 10 + tail + K);
                }
                if (tail - K >= 0 && K != 0) {
                    newArr.add(cur * 10 + tail - K);
                }
            }
            queue = newArr;
        }
        return queue.stream().mapToInt(i -> i).toArray();
     }
}
```
### **...**

```

```

<!-- tabs:end -->