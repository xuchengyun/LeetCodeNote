# [838. Push Dominoes](https://leetcode.com/problems/push-dominoes)

## Description

<p>There are <code>n</code> dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.</p>

<p>After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.</p>

<p>When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.</p>

<p>For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.</p>

<p>You are given a string <code>dominoes</code> representing the initial state where:</p>

<ul>
	<li><code>dominoes[i] = &#39;L&#39;</code>, if the <code>i<sup>th</sup></code> domino has been pushed to the left,</li>
	<li><code>dominoes[i] = &#39;R&#39;</code>, if the <code>i<sup>th</sup></code> domino has been pushed to the right, and</li>
	<li><code>dominoes[i] = &#39;.&#39;</code>, if the <code>i<sup>th</sup></code> domino has not been pushed.</li>
</ul>

<p>Return <em>a string representing the final state</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> dominoes = &quot;RR.L&quot;
<strong>Output:</strong> &quot;RR.L&quot;
<strong>Explanation:</strong> The first domino expends no additional force on the second domino.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/05/18/domino.png" style="height: 196px; width: 512px;" />
<pre>
<strong>Input:</strong> dominoes = &quot;.L.R...LR..L..&quot;
<strong>Output:</strong> &quot;LL.RR.LLRRLL..&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == dominoes.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>dominoes[i]</code> is either <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, or <code>&#39;.&#39;</code>.</li>
</ul>


## Solutions

<!-- tabs:start -->


### **Java**
### Solution 1 - 2 Pointers
```java
class Solution {
    // 2 pointers
    /**
	In this approach, you just need to find sections like this
	X .   .   .   . X
	i                j
	Where X can be 'R' or 'L' and in between there can be as many dots
	Now,
	- you know the length of mid-part
	- If char[i] == char[j] == 'R', means all go towards right (R)
	-  char[i]  == char[j] == 'L', means all go towards Left (L)
	-  If char[i] = 'L' and char[j] = 'R', means middle part is not affected so they remain '.'
	-  If char[i] = 'R' and char[j] = 'L', then it will affect the middle part.
	   The middle_part/2 close to i will be affected by 'R' and middle_part/2 close to j will be   
	   effected by 'L'  and the last mid point (middle_part%2) will be unaffected due to equal  
	   force from left and right so it remains '.'
    */
    public String pushDominoes(String dominoes) {
        dominoes = "L" + dominoes + "R";
        int n = dominoes.length();
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 1;
        while (i < n && j < n) {
            if (dominoes.charAt(j) == '.') {
                j++;
                continue;
            }
            if (dominoes.charAt(i) == dominoes.charAt(j)) {
                while (i < j) {
                    sb.append(dominoes.charAt(j));
                    i++;
                }
            } else {
                if (dominoes.charAt(i) == 'L') {
                    sb.append('L');
                    i++;
                    while (i < j) {
                        sb.append('.');
                        i++;
                    }
                } else {
                    sb.append('R');
                    int mid = (j - i - 1) / 2;
                    if ((j - i) % 2 == 0) {
                        for (int x = 0; x < mid; x++) {
                            sb.append('R');
                        }
                        sb.append('.');
                        for (int x = 0; x < mid; x++) {
                            sb.append('L');
                        }
                    } else {
                        for (int x = 0; x < mid; x++) {
                            sb.append('R');
                        }
                        for (int x = 0; x < mid; x++) {
                            sb.append('L');
                        }
                    }
                    i = j;
                }
            }
            j++;
        }
        return sb.substring(1);    
    }
}
```
### Solution 2 - User force
```java
class Solution {
    public String pushDominoes(String dominoes) {
        char[] dominoesArr = dominoes.toCharArray();
        int n = dominoesArr.length;
        int[] forces = new int[n];
        
        int force = 0;
        for (int i = 0; i < n; i++) {
            if (dominoesArr[i] == 'R') {
                force = n;
            } else if (dominoesArr[i] == 'L') {
                force = 0;
            } else {
                force = Math.max(0, force - 1);
            }
            forces[i] = force;
        }
        
        for (int i = n - 1; i >= 0; i--) {
            if (dominoesArr[i] == 'L') {
                force = n;
            } else if (dominoesArr[i] == 'R') {
                force = 0;
            } else {
                force = Math.max(0, force - 1);
            }
            forces[i] -= force;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int f: forces) {
            if (f > 0) {
                sb.append('R');
            } else if (f < 0) {
                sb.append('L');
            } else {
                sb.append('.');
            }
        }
        
        return sb.toString();
    }
}
```
### **...**

```

```

<!-- tabs:end -->