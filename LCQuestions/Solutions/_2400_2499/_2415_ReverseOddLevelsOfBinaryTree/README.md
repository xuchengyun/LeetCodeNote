# [2415. Reverse Odd Levels of Binary Tree](https://leetcode.com/problems/reverse-odd-levels-of-binary-tree)

## Description

<p>Given the <code>root</code> of a <strong>perfect</strong> binary tree, reverse the node values at each <strong>odd</strong> level of the tree.</p>

<ul>
	<li>For example, suppose the node values at level 3 are <code>[2,1,3,4,7,11,29,18]</code>, then it should become <code>[18,29,11,7,4,3,1,2]</code>.</li>
</ul>

<p>Return <em>the root of the reversed tree</em>.</p>

<p>A binary tree is <strong>perfect</strong> if all parent nodes have two children and all leaves are on the same level.</p>

<p>The <strong>level</strong> of a node is the number of edges along the path between it and the root node.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/07/28/first_case1.png" style="width: 626px; height: 191px;" />
<pre>
<strong>Input:</strong> root = [2,3,5,8,13,21,34]
<strong>Output:</strong> [2,5,3,8,13,21,34]
<strong>Explanation:</strong> 
The tree has only one odd level.
The nodes at level 1 are 3, 5 respectively, which are reversed and become 5, 3.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/07/28/second_case3.png" style="width: 591px; height: 111px;" />
<pre>
<strong>Input:</strong> root = [7,13,11]
<strong>Output:</strong> [7,11,13]
<strong>Explanation:</strong> 
The nodes at level 1 are 13, 11, which are reversed and become 11, 13.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
<strong>Output:</strong> [0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
<strong>Explanation:</strong> 
The odd levels have non-zero values.
The nodes at level 1 were 1, 2, and are 2, 1 after the reversal.
The nodes at level 3 were 1, 1, 1, 1, 2, 2, 2, 2, and are 2, 2, 2, 2, 1, 1, 1, 1 after the reversal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 2<sup>14</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li><code>root</code> is a <strong>perfect</strong> binary tree.</li>
</ul>


## Solutions
### Solution 1 BFS
<!-- tabs:start -->


### **Java**

```java
class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {
        if (root == null) return root;
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root);
        int level = 0;
        while (!dq.isEmpty()) {
            int size = dq.size();
            List<TreeNode> l = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = dq.poll();
                if (level % 2 == 1) {
                    l.add(cur);
                }
                if (cur.left != null) {
                   dq.offer(cur.left);
                }
                if (cur.right != null) {
                   dq.offer(cur.right);
                }
            }
            level++;
            if (l.size() > 1) {
                reverse(l);
            }
        }
        return root;
    }
    
    private void reverse(List<TreeNode> l) {
        if (l.size() <= 1) {
            return;
        }
        
        int left = 0, right = l.size() - 1;
        while (left < right) {
            TreeNode leftNode = l.get(left), rightNode = l.get(right);
            int tmp = rightNode.val;
            rightNode.val = leftNode.val;
            leftNode.val = tmp;
            left++;
            right--;
        }
    }
}
```
### Solution 2 DFS
```java
class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {
        if (root == null) return null;
        traverse(root.left, root.right, 1);
        return root;
    }
    
    private void traverse(TreeNode l, TreeNode r, int level) {
        if (l == null || r == null) return;
        if (level % 2 == 1) {
            int tmp = l.val;
            l.val = r.val;
            r.val = tmp;
        }
        
        traverse(l.left, r.right, level + 1);
        traverse(l.right, r.left, level + 1);
    }
}
```

### **...**

```

```

<!-- tabs:end -->