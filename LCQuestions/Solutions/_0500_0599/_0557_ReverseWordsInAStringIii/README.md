# [557. Reverse Words in a String III](https://leetcode.com/problems/reverse-words-in-a-string-iii)

## Description

<p>Given a string <code>s</code>, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "Let's take LeetCode contest"
<strong>Output:</strong> "s'teL ekat edoCteeL tsetnoc"
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "God Ding"
<strong>Output:</strong> "doG gniD"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> contains printable <strong>ASCII</strong> characters.</li>
	<li><code>s</code> does not contain any leading or trailing spaces.</li>
	<li>There is <strong>at least one</strong> word in <code>s</code>.</li>
	<li>All the words in <code>s</code> are separated by a single space.</li>
</ul>


## Solutions

<!-- tabs:start -->


### **Java**

```java
class Solution {
	public String reverseWords(String s) {
		String[] sarr = s.split(" ");
		for (int i = 0; i < sarr.length; i++) {
			sarr[i] = reverse(sarr[i]);
		}
		return String.join(" ", sarr);

	}

	private String reverse(String s) {
		char[] carr = s.toCharArray();
		int i = 0, j = carr.length - 1;
		while (i < j) {
			swap(carr, i++, j--);
		}
		return new String(carr);
	}

	private void swap(char[] carr, int i, int j){
		char tmp = carr[i];
		carr[i] = carr[j];
		carr[j] = tmp;
	}
}
```

### **...**

```

```

<!-- tabs:end -->