# [658. Find K Closest Elements](https://leetcode.com/problems/find-k-closest-elements)

## Description

<p>Given a <strong>sorted</strong> integer array <code>arr</code>, two integers <code>k</code> and <code>x</code>, return the <code>k</code> closest integers to <code>x</code> in the array. The result should also be sorted in ascending order.</p>

<p>An integer <code>a</code> is closer to <code>x</code> than an integer <code>b</code> if:</p>

<ul>
	<li><code>|a - x| &lt; |b - x|</code>, or</li>
	<li><code>|a - x| == |b - x|</code> and <code>a &lt; b</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> arr = [1,2,3,4,5], k = 4, x = 3
<strong>Output:</strong> [1,2,3,4]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> arr = [1,2,3,4,5], k = 4, x = -1
<strong>Output:</strong> [1,2,3,4]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= arr.length</code></li>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>arr</code> is sorted in <strong>ascending</strong> order.</li>
	<li><code>-10<sup>4</sup> &lt;= arr[i], x &lt;= 10<sup>4</sup></code></li>
</ul>


## Solutions

<!-- tabs:start -->


### **Java**
### Solution 1 - 2 pointers
```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lo = 0, hi = arr.length - 1;
        while (hi - lo >= k) {
            if (Math.abs(arr[lo] - x) > Math.abs(arr[hi] - x)) {
                lo++;
            } else {
                hi--;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = lo; i <= hi; i++) {
            res.add(arr[i]);
        }
        return res;    
    }
}
```
### Solution 2 - custom sort
```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Convert from array to list first to make use of Collections.sort()
        List<Integer> sortedArr = new ArrayList<Integer>();
        for (int num: arr) {
            sortedArr.add(num);
        }
        
        // Sort using custom comparator
        Collections.sort(sortedArr, (num1, num2) -> Math.abs(num1 - x) - Math.abs(num2 - x));
        
        // Only take k elements
        sortedArr = sortedArr.subList(0, k);
        
        // Sort again to have output in ascending order
        Collections.sort(sortedArr);
        return sortedArr;
    }
}
```
### **...**
So the main idea of it is to find out the lower bound of that k length range. The numbers between "left" and "right" are the candidates of the lower bound.
The if condition "x - A[mid] > A[mid + k] - x" is used to compare A[mid] and A[mid+k], see which is closer to x.
If A[mid] is closer to x, then A[mid+k] can never be in the k length range. So we can confidently remove all (A[mid+1], A[mid+2], A[mid+3]...) from the candidates list by set right=mid.
If A[mid+k] is closer to x, then A[mid] can never be in the k length range. So we can confidently remove all (...A[mid-2], A[mid-1], A[mid]) from the candidates list by set left=mid+1.
Once we remain only one candidate, that is left==right, we got our final lower bound.
- If A[mid] is closer to x, then (A[mid+1]...A[mid+k]) can never be the lower bound we are looking for => set right to mid
- If A[mid+k] is closer to x, then the farthest we can go towards left is A[mid+1] (i.e., the potential lower bound) => set left to mid+1
### Solution 3 - Binary Search
```java
class Solution {
    // Find left bound binary search
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Initialize binary search bounds
        int left = 0;
        int right = arr.length - k;
        
        // Binary search against the criteria described
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        // Create output in correct format
        List<Integer> result = new ArrayList<Integer>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }
        
        return result;
    }
}
```
```

```

<!-- tabs:end -->