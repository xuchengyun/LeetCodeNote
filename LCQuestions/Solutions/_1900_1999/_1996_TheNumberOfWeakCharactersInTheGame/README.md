# [1996. The Number of Weak Characters in the Game](https://leetcode.com/problems/the-number-of-weak-characters-in-the-game)

## Description

<p>You are playing a game that contains multiple characters, and each of the characters has <strong>two</strong> main properties: <strong>attack</strong> and <strong>defense</strong>. You are given a 2D integer array <code>properties</code> where <code>properties[i] = [attack<sub>i</sub>, defense<sub>i</sub>]</code> represents the properties of the <code>i<sup>th</sup></code> character in the game.</p>

<p>A character is said to be <strong>weak</strong> if any other character has <strong>both</strong> attack and defense levels <strong>strictly greater</strong> than this character&#39;s attack and defense levels. More formally, a character <code>i</code> is said to be <strong>weak</strong> if there exists another character <code>j</code> where <code>attack<sub>j</sub> &gt; attack<sub>i</sub></code> and <code>defense<sub>j</sub> &gt; defense<sub>i</sub></code>.</p>

<p>Return <em>the number of <strong>weak</strong> characters</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> properties = [[5,5],[6,3],[3,6]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> No character has strictly greater attack and defense than the other.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> properties = [[2,2],[3,3]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The first character is weak because the second character has a strictly greater attack and defense.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> properties = [[1,5],[10,4],[4,3]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The third character is weak because the second character has a strictly greater attack and defense.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= properties.length &lt;= 10<sup>5</sup></code></li>
	<li><code>properties[i].length == 2</code></li>
	<li><code>1 &lt;= attack<sub>i</sub>, defense<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>


## Solutions

<!-- tabs:start -->

Solution 1 Sorting
### **Java**

```java
class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        if (properties == null || properties.length == 0 || properties[0].length == 0) {
            return 0;
        }
        /**
         * sort attack in ascending order, sort defense in descending order, so
         * in case of a tie in the attack values, we can ignore the defense value of the pairs
         * with the same attack value.
        */
        Arrays.sort(properties, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int res = 0;
        int maxDefense = Integer.MIN_VALUE;
        for (int i = properties.length - 1; i >=0; i--) {
            if (properties[i][1] < maxDefense) {
                res++;
            }
            maxDefense = Math.max(maxDefense, properties[i][1]);
        }
        
        return res;
    }
}
```

Solution 2 Greedy
### **Java**

```java
```

### **...**

```

```

<!-- tabs:end -->