# [606. Construct String from Binary Tree](https://leetcode.com/problems/construct-string-from-binary-tree)

## Description

<p>Given the <code>root</code> of a binary tree, construct a string consisting of parenthesis and integers from a binary tree with the preorder traversal way, and return it.</p>

<p>Omit all the empty parenthesis pairs that do not affect the one-to-one mapping relationship between the string and the original binary tree.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/05/03/cons1-tree.jpg" style="width: 292px; height: 301px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4]
<strong>Output:</strong> &quot;1(2(4))(3)&quot;
<strong>Explanation:</strong> Originally, it needs to be &quot;1(2(4)())(3()())&quot;, but you need to omit all the unnecessary empty parenthesis pairs. And it will be &quot;1(2(4))(3)&quot;
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/05/03/cons2-tree.jpg" style="width: 207px; height: 293px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,null,4]
<strong>Output:</strong> &quot;1(2()(4))(3)&quot;
<strong>Explanation:</strong> Almost the same as the first example, except we cannot omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>


## Solutions

<!-- tabs:start -->
#Solution1 DFS Recursion
本题比较让人迷惑的地方在于需要忽略空括号对， 对每一个节点来说就有四种情况
* 当前节点有左子节点和右子节点， 这种情况下左子树和右子树的部分都应该被括号包括
* 当前节点没有左子节点和右子节点， 这种情况下直接返回当前节点的值就行
* 当前节点只有左节点没有右节点，这种情况就比较tricky，如果要满足字符串和树一对一mapping的话，需要填充一对空括号
* 当前节点只有右节点没有左节点，可以忽略掉括号也能满足一对一mapping
### **Java**
```java
class Solution {
    public String tree2str(TreeNode root) {
        return traverse(root);
    }

    private String traverse(TreeNode node) {
        if (node == null) return "";
        String left = traverse(node.left);
        String right = traverse(node.right);
        if (left.equals("") && right.equals("")) {
            return "" + node.val;
        }
        if (left.equals("")) {
            return node.val + "()("+ right + ")";
        }
        if (right.equals("")) {
            return node.val + "("+ left + ")";
        }
        return node.val + "(" + left + ")("+ right + ")";
    }
}
```

# Solution 2 DFS Stack
### **Java**
```java
class Solution {
    public String tree2str(TreeNode root) {
        if (root == null)
            return "";
        TreeNode dummy = new TreeNode(0);
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> s = new ArrayDeque<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            /*Every time you append a open parentheses,
            you push dummy node onto stack to remind that we also need a close parentheses when pop this dummy node.            *
             */
            if (cur == dummy) {
                sb.append(')');
                continue;
            }
            sb.append('(').append(cur.val);
            s.push(dummy);
            if (cur.left == null && cur.right != null) {
                sb.append("()");
            }
            if (cur.right != null) {
                s.push(cur.right);
            }
            if (cur.left != null) {
                s.push(cur.left);
            }
        }
        return sb.substring(1, sb.length() - 1);
    }

}
```

### **...**

```

```

<!-- tabs:end -->