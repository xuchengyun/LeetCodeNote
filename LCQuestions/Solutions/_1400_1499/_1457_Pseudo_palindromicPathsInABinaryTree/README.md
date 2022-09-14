# [1457. Pseudo-Palindromic Paths in a Binary Tree](https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree)

## Description

<p>Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be <strong>pseudo-palindromic</strong> if at least one permutation of the node values in the path is a palindrome.</p>

<p><em>Return the number of <strong>pseudo-palindromic</strong> paths going from the root node to leaf nodes.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/05/06/palindromic_paths_1.png" style="width: 300px; height: 201px;" /></p>

<pre>
<strong>Input:</strong> root = [2,3,1,3,1,null,1]
<strong>Output:</strong> 2 
<strong>Explanation:</strong> The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/05/07/palindromic_paths_2.png" style="width: 300px; height: 314px;" /></strong></p>

<pre>
<strong>Input:</strong> root = [2,1,1,1,3,null,null,null,null,null,1]
<strong>Output:</strong> 1 
<strong>Explanation:</strong> The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [9]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 9</code></li>
</ul>


## Solutions

<!-- tabs:start -->


### **Java**
### Solution1 DFS
Bit manipulation + DFS
```java
class Solution {
    // advanced solution, use xor to count
    int cnt = 0;
    public int pseudoPalindromicPaths (TreeNode root) {
        traverse(root, 0);
        return cnt;
    }
    
    private void traverse(TreeNode node, int xor) {
        if (node == null) return;
        int p = xor ^ (1 << node.val);
        if (node.left == null && node.right == null) {
            if ((p & (p - 1)) == 0) {
                cnt++;
            }
            return;
        }
        traverse(node.left, p);
        traverse(node.right, p);
    }
}
```
### Solution2 BFS
用BFS 做这种遍历树的path的题的时候，当添加下一个节点时，需要储存当前节点的状态。
这样的话，当我们遍历节点的时猴，我们就知道了此节点前一个节点的状态。
一般可以用Pair或者Map来存储状态
```java
class Solution {
    // advanced solution, use xor to count
    public int pseudoPalindromicPaths (TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        int count = 0;
        stack.push(root);
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(root, 0);
        // Need to store pre xor value for each node
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            int xor = map.get(cur) ^ (1 << cur.val);
            if (cur.left == null && cur.right == null) {
                if ((xor & (xor - 1)) == 0) {
                    count += 1;
                }
            }
            if (cur.right != null) {
                map.put(cur.right, xor);
                stack.push(cur.right);
            }
            if (cur.left != null) {
                map.put(cur.left, xor);
                stack.push(cur.left);
            }

        }
        return count;
    }
}
```
### **...**

```

```

<!-- tabs:end -->