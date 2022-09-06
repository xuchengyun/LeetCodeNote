# [429. N-ary Tree Level Order Traversal](https://leetcode.com/problems/n-ary-tree-level-order-traversal)

## Description

<p>Given an n-ary tree, return the <em>level order</em> traversal of its nodes&#39; values.</p>

<p><em>Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png" style="width: 100%; max-width: 300px;" /></p>

<pre>
<strong>Input:</strong> root = [1,null,3,2,4,null,5,6]
<strong>Output:</strong> [[1],[3,2,4],[5,6]]
</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/11/08/sample_4_964.png" style="width: 296px; height: 241px;" /></p>

<pre>
<strong>Input:</strong> root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>Output:</strong> [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The height of the n-ary tree is less than or equal to <code>1000</code></li>
	<li>The total number of nodes is between <code>[0, 10<sup>4</sup>]</code></li>
</ul>


## Solutions

<!-- tabs:start -->


### **Java**
# 方法1 BFS
经典层序遍历，在循环中，找到到队列中剩余所有元素，对每一个进行遍历，即对当前层进行遍历
```java
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Node cur = q.poll();
                if (cur != null) {
                    l.add(cur.val);
                }
                for (Node child: cur.children) {
                    q.offer(child);
                }
            }
            res.add(l);
        }
        return res;
    }
}
```

# 方法2 通过数组的层序遍历
```java
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        List<Node> preLayer = Arrays.asList(root);
        while (!preLayer.isEmpty()) {
            List<Integer> preValues = new ArrayList<>();
            List<Node> curLayer = new ArrayList<>();
            for (Node node : preLayer) {
                preValues.add(node.val);
                curLayer.addAll(node.children);
            }
            res.add(preValues);
            preLayer = curLayer;
        }
        return res;
    }
}
```
# 方法3 DFS（Recursion）
用DFS方法来做的话需要在递归函数中传入每一层的层数，通过比较res.size()和当前层数来判断是否在当前res中新增一层
```java
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, Node node, int level) {
        if(node == null) return;
        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        for (Node n: node.children) {
            dfs(res, n, level + 1);
        }
    }
}
```
### **...**

```

```

<!-- tabs:end -->