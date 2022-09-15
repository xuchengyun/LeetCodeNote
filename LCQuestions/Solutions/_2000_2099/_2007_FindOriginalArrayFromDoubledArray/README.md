# [2007. Find Original Array From Doubled Array](https://leetcode.com/problems/find-original-array-from-doubled-array)

## Description

<p>An integer array <code>original</code> is transformed into a <strong>doubled</strong> array <code>changed</code> by appending <strong>twice the value</strong> of every element in <code>original</code>, and then randomly <strong>shuffling</strong> the resulting array.</p>

<p>Given an array <code>changed</code>, return <code>original</code><em> if </em><code>changed</code><em> is a <strong>doubled</strong> array. If </em><code>changed</code><em> is not a <strong>doubled</strong> array, return an empty array. The elements in</em> <code>original</code> <em>may be returned in <strong>any</strong> order</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> changed = [1,3,4,2,6,8]
<strong>Output:</strong> [1,3,4]
<strong>Explanation:</strong> One possible original array could be [1,3,4]:
- Twice the value of 1 is 1 * 2 = 2.
- Twice the value of 3 is 3 * 2 = 6.
- Twice the value of 4 is 4 * 2 = 8.
Other original arrays could be [4,3,1] or [3,1,4].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> changed = [6,3,0,1]
<strong>Output:</strong> []
<strong>Explanation:</strong> changed is not a doubled array.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> changed = [1]
<strong>Output:</strong> []
<strong>Explanation:</strong> changed is not a doubled array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= changed.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= changed[i] &lt;= 10<sup>5</sup></code></li>
</ul>


## Solutions

<!-- tabs:start -->


### **Java**

```java
class Solution {
    public int[] findOriginalArray(int[] changed) {
        if (changed == null || changed.length == 0) return new int[0];
        int length = changed.length;
        if (length % 2 != 0) {
            return new int[0];
        }
        Arrays.sort(changed);
        Map<Integer, Integer> freq = new HashMap<>();
        
        for (int value: changed) {
            freq.put(value, freq.getOrDefault(value, 0) + 1);
        }
        
        int[] original= new int[changed.length / 2];
        int index = 0;
        for (int i = 0; i < length; i++) {
            int val = changed[i];
            if (!freq.containsKey(val)) {
               continue;
            }

            original[index++] = val;
            int curCnt = freq.get(val);
            if (curCnt == 1) {
                freq.remove(val);
            } else {
                freq.put(val, curCnt - 1);
            }
            
            if (!freq.containsKey(val * 2)) {
                return new int[0];
            }
            int doubleCnt = freq.get(val * 2);

            if (doubleCnt == 1) {
                freq.remove(val * 2);
            } else {
                freq.put(val * 2, doubleCnt - 1);
            }
        }
        return original;
    }
}
```
Solution2 Counting Sort
```java
class Solution {
    public int[] findOriginalArray(int[] changed) {
        // It can't be doubled array, if the size is odd
        if (changed.length % 2 == 1) {
            return new int[0];
        }
        
        int maxNum = 0;
        // Find the max element in the array
        for (int num : changed) {
            maxNum = Math.max(maxNum, num);
        }
        
        int[] freq = new int[2 * maxNum + 1];
        // Store the frequency in the map
        for (int num : changed) {
            freq[num]++;
        }
        
        int[] original = new int[changed.length / 2];
        int index = 0;
        for (int num = 0; num <= maxNum; num++) {
            // If element exists
            if (freq[num] > 0) {
                freq[num]--;
                
                int twiceNum = num * 2;
                if (freq[twiceNum] > 0) {
                    // Pair up the elements, decrement the count
                    freq[twiceNum]--;
                    // Add the original number to answer
                    original[index++] = num;
                    num--;
                } else {
                    return new int[0];
                }
            }
        }
        
        return original;
    }
};
```
### **...**

```

```

<!-- tabs:end -->