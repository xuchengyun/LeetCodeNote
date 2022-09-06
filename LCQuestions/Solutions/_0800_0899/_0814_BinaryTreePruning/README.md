# [814. Binary Tree Pruning](https://leetcode.com/problems/binary-tree-pruning)

## Description

<p>Given the <code>root</code> of a binary tree, return <em>the same tree where every subtree (of the given tree) not containing a </em><code>1</code><em> has been removed</em>.</p>

<p>A subtree of a node <code>node</code> is <code>node</code> plus every node that is a descendant of <code>node</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/06/1028_2.png" style="width: 500px; height: 140px;" />
<pre>
<strong>Input:</strong> root = [1,null,0,0,1]
<strong>Output:</strong> [1,null,0,null,1]
<strong>Explanation:</strong> 
Only the red nodes satisfy the property &quot;every subtree not containing a 1&quot;.
The diagram on the right represents the answer.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/06/1028_1.png" style="width: 500px; height: 115px;" />
<pre>
<strong>Input:</strong> root = [1,0,1,0,0,0,1]
<strong>Output:</strong> [1,null,1,null,1]
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/05/1028.png" style="width: 500px; height: 134px;" />
<pre>
<strong>Input:</strong> root = [1,1,0,1,1,0,1,0]
<strong>Output:</strong> [1,1,0,1,1,null,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 200]</code>.</li>
	<li><code>Node.val</code> is either <code>0</code> or <code>1</code>.</li>
</ul>


## Solutions

<!-- tabs:start -->
# Solution 1 DFS
在递归函数traverse中，若果return true，则说明当前子树需要被剪裁掉，如果return false，说明当前子树含有1，需要保留
### **Java**
```java
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        return traverse(root) ? null : root;
    }
    
    private boolean traverse(TreeNode node)  {
        if (node == null) return true;
        boolean left = traverse(node.left);
        boolean right = traverse(node.right);
        if (left) {
            node.left = null;
        }
        if (right) {
            node.right = null;
        }
        return node.val == 0 && left && right;
    }
}
```
# Solution 2 DFS-2
我们来看一个另一种DFS的方法，这种方法不用 true 或者 false 来判断子树是否需要被剪枝，而是通过return null 或者 root 来判断，
如果 return null， 则说明包含当前结点的子树需要剪枝。这种方法的好处是不需要单独处理root节点，更加的简洁
```java
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        return traverse(root);
    }
    
    private TreeNode traverse(TreeNode node)  {
        if (node == null) return null;
        node.left = traverse(node.left);
        node.right = traverse(node.right);
        if (node.val == 0 && node.left == null && node.right == null) {
            return null;
        }
        return node;
    }
}
```

### **...**

```

```

<!-- tabs:end -->