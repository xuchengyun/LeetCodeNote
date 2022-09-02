# [637. Average of Levels in Binary Tree](https://leetcode.com/problems/average-of-levels-in-binary-tree)

## Description

Given the <code>root</code> of a binary tree, return <em>the average value of the nodes on each level in the form of an array</em>. Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/09/avg1-tree.jpg" style="width: 277px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [3,9,20,null,null,15,7]
<strong>Output:</strong> [3.00000,14.50000,11.00000]
Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
Hence return [3, 14.5, 11].
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/09/avg2-tree.jpg" style="width: 292px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [3,9,20,15,7]
<strong>Output:</strong> [3.00000,14.50000,11.00000]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-2<sup>31</sup> &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li>
</ul>


## Solutions

<!-- tabs:start -->



### **Java**
## 方法1
层序遍历 BFS， 通过每一层来遍历树， 然后计算每一层的平均值并且存入list中。
q.size() 可以 表示当前层的节点数量， 然后对队列中每一个节点进行操作
```java
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<Double> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int n = q.size();
            double cur = 0.0;
            for (int i = 0; i < n; i++) {
                TreeNode curNode = q.poll();
                cur += Objects.requireNonNull(curNode).val;
                if (curNode.left != null) {
                    q.offer(curNode.left);
                }
                if (curNode.right != null) {
                    q.offer(curNode.right);
                }
            }
            res.add(cur / n);
        }
        return res;
    }
}
```
### 方法2
DFS 通过数组的顺序记录每一层的信息， 也可以通过map来实现
```java
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        // User 2 list to store sum and num of nodes for each layer
        List<Double> sum = new ArrayList<>();
        List<Integer> cnt = new ArrayList<>();
        dfs(sum, cnt, root, 0);

        List<Double> res = new ArrayList<>();
        for (int i = 0; i < sum.size(); i++) {
            res.add(sum.get(i)/cnt.get(i));
        }
        return res;
    }

    public void dfs(List<Double> sum, List<Integer> cnt, TreeNode node, int i) {
        if (node == null) return;
        if (sum.size() <= i) {
            sum.add((double)node.val);
            cnt.add(1);
        } else {
            sum.set(i, sum.get(i) + node.val);
            cnt.set(i, cnt.get(i) + 1);
        }
        dfs(sum, cnt, node.left, i + 1);
        dfs(sum, cnt, node.right, i + 1);
    }
}
```

```

```

<!-- tabs:end -->