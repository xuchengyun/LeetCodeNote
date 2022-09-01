# [1448. Count Good Nodes in Binary Tree](https://leetcode.com/problems/count-good-nodes-in-binary-tree)

## Description

<p>Given a binary tree <code>root</code>, a node <em>X</em> in the tree is named&nbsp;<strong>good</strong> if in the path from root to <em>X</em> there are no nodes with a value <em>greater than</em> X.</p>



<p>Return the number of <strong>good</strong> nodes in the binary tree.</p>



<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>



<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/04/02/test_sample_1.png" style="width: 263px; height: 156px;" /></strong></p>



<pre>

<strong>Input:</strong> root = [3,1,4,3,null,1,5]

<strong>Output:</strong> 4

<strong>Explanation:</strong> Nodes in blue are <strong>good</strong>.

Root Node (3) is always a good node.

Node 4 -&gt; (3,4) is the maximum value in the path starting from the root.

Node 5 -&gt; (3,4,5) is the maximum value in the path

Node 3 -&gt; (3,1,3) is the maximum value in the path.</pre>



<p><strong>Example 2:</strong></p>



<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/04/02/test_sample_2.png" style="width: 157px; height: 161px;" /></strong></p>



<pre>

<strong>Input:</strong> root = [3,3,null,4,2]

<strong>Output:</strong> 3

<strong>Explanation:</strong> Node 2 -&gt; (3, 3, 2) is not good, because &quot;3&quot; is higher than it.</pre>



<p><strong>Example 3:</strong></p>



<pre>

<strong>Input:</strong> root = [1]

<strong>Output:</strong> 1

<strong>Explanation:</strong> Root is considered as <strong>good</strong>.</pre>



<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>



<ul>

	<li>The number of nodes in the binary tree is in the range&nbsp;<code>[1, 10^5]</code>.</li>

	<li>Each node&#39;s value is between <code>[-10^4, 10^4]</code>.</li>

</ul>

## Solutions

<!-- tabs:start -->

### **Java**
## Approach 1 Pre-order DFS(Recursion)
DFS Record the maximum value along the path from the root to the node.
```java
class Solution {
    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    private int dfs(TreeNode node, int val) {
        if (node == null) return 0;
        int cur = node.val >= val ? 1 : 0;
        cur += dfs(node.left, Math.max(val, node.val));
        cur += dfs(node.right, Math.max(val, node.val));
        return cur;
    }
}
```
## Approach 2  DFS Iteration
```java
class Pair {
    public TreeNode node;
    public int maxSoFar;

    public Pair(TreeNode node, int maxSoFar) {
        this.node = node;
        this.maxSoFar = maxSoFar;
    }
}

class Solution {
    public int goodNodes(TreeNode root) {
        int numGoodNodes = 0;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, Integer.MIN_VALUE));

        while (stack.size() > 0) {
            Pair curr = stack.pop();
            if (curr.maxSoFar <= curr.node.val) {
                numGoodNodes++;
            }

            if (curr.node.left != null) {
                stack.push(new Pair(curr.node.left, Math.max(curr.node.val, curr.maxSoFar)));
            }

            if (curr.node.right != null) {
                stack.push(new Pair(curr.node.right, Math.max(curr.node.val, curr.maxSoFar)));
            }
        }

        return numGoodNodes;
    }
}
```
## Approach 3  BFS
```java

class Solution {

    class Pair {
        public TreeNode node;
        public int maxSoFar;

        public Pair(TreeNode node, int maxSoFar) {
            this.node = node;
            this.maxSoFar = maxSoFar;
        }
    }

    public int goodNodes(TreeNode root) {
        int numGoodNodes = 0;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, Integer.MIN_VALUE));

        while (stack.size() > 0) {
            Pair curr = stack.pop();
            if (curr.maxSoFar <= curr.node.val) {
                numGoodNodes++;
            }

            if (curr.node.left != null) {
                stack.push(new Pair(curr.node.left, Math.max(curr.node.val, curr.maxSoFar)));
            }

            if (curr.node.right != null) {
                stack.push(new Pair(curr.node.right, Math.max(curr.node.val, curr.maxSoFar)));
            }
        }

        return numGoodNodes;
    }
}
```
```
```

<!-- tabs:end -->