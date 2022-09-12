# [2405. Optimal Partition of String](https://leetcode.com/problems/optimal-partition-of-string)

## Description

<p>Given a string <code>s</code>, partition the string into one or more <strong>substrings</strong> such that the characters in each substring are <strong>unique</strong>. That is, no letter appears in a single substring more than <strong>once</strong>.</p>

<p>Return <em>the <strong>minimum</strong> number of substrings in such a partition.</em></p>

<p>Note that each character should belong to exactly one substring in a partition.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abacaba&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong>
Two possible partitions are (&quot;a&quot;,&quot;ba&quot;,&quot;cab&quot;,&quot;a&quot;) and (&quot;ab&quot;,&quot;a&quot;,&quot;ca&quot;,&quot;ba&quot;).
It can be shown that 4 is the minimum number of substrings needed.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ssssss&quot;
<strong>Output:</strong> 6
<strong>Explanation:
</strong>The only valid partition is (&quot;s&quot;,&quot;s&quot;,&quot;s&quot;,&quot;s&quot;,&quot;s&quot;,&quot;s&quot;).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only English lowercase letters.</li>
</ul>


### Solutions

<!-- tabs:start -->
## Solution 1
通过set存储当前便利的字符， 如果遇到重复的则result + 1

### **Java**
```java
class Solution {
    public int partitionString(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Set<Character> set = new HashSet<>();
        int res = 1;
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                res++;
                set.clear();
                set.add(c);
            } else {
                set.add(c);
            }
        }
        return res;
    }
}
```

### Solution 2
```java
class Solution {
    public int partitionString(String s) {
       int[] pos = new int[26];
        int res = 0, last = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (pos[s.charAt(i) - 'a'] >= last) {
                ++res;
                last = i + 1;
            }
            pos[s.charAt(i) - 'a'] = i + 1;
        }
        return res;       
    }
}
```

### Solution 3 比特运算
```java
class Solution {
    public int partitionString(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // Integer 是32位的，可以覆盖26个英文字母
        int map = 0;
        int res = 1;
        for (char c : s.toCharArray()) {
			// 判断字母是否已经存在
            if ((map & (1 << (c - 'a'))) != 0) {
                res++;
				// 清零map
                map = 0;
            }
			// 把字母加到map中
            map ^= 1 << (c - 'a');
        }
        return res;     
    }
}
```
```

```

<!-- tabs:end -->