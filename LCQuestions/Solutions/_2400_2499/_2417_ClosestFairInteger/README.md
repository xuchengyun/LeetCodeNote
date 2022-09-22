# [2417. Closest Fair Integer](https://leetcode.com/problems/closest-fair-integer)

## Description

<p>You are given a <strong>positive</strong> integer <code>n</code>.</p>

<p>We call an integer <code>k</code> fair if the number of <strong>even</strong> digits in <code>k</code> is equal to the number of <strong>odd</strong> digits in it.</p>

<p>Return <em>the <strong>smallest</strong> fair integer that is <strong>greater than or equal</strong> to </em><code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 10
<strong>Explanation:</strong> The smallest fair integer that is greater than or equal to 2 is 10.
10 is fair because it has an equal number of even and odd digits (one odd digit and one even digit).</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 403
<strong>Output:</strong> 1001
<strong>Explanation:</strong> The smallest fair integer that is greater than or equal to 403 is 1001.
1001 is fair because it has an equal number of even and odd digits (two odd digits and two even digits).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>


## Solutions

<!-- tabs:start -->

Scan from right to left, making n[i]+=1 (if possible), and set all digits >=i to either 0 or 1. Keep scanning until the first success. This should give you the smallest fair number bigger than n.
e.g. n = 333333, the trying order should be:
-> 333334 -> 33334x -> 3334xx -> 334xxx -> 34xxxx -> 4xxxxx
Where "x" can be either "0" or "1", whichever makes total odd and even digits same. Stop at the first success.

Corner cases:

1. If the inital n has odd number of digits, then the answer is smallest fair number plus 1 digit. e.g. if n is 5 digits, answer is 6 digits: 100011.
2. If we scan all digits and still can't find an answer, the answer is the smallest fair number plus 2 digits. e.g if n is 4 digits, answer will be 6 digits: 100011.
3. If n[i] is even and all digits >i must also be even in order to success, we need to do n[i]+=2, not n[i]+=1
Time complexity: O(log10(n)). Since n<=10**9, time complexity is O(9)
### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->