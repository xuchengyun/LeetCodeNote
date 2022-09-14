package LCQuestions.Solutions._1400_1499._1457_Pseudo_palindromicPathsInABinaryTree;

import Utils.TreeNode;
import com.sun.source.tree.Tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
count & (count - 1) == 0. check if there is only 1 bit in bit set
 */
public class _1457_Pseudo_palindromicPathsInABinaryTree {
    // Bit Manipulation
    public int pseudoPalindromicPaths(TreeNode root) {
        return dfs(root, 0);
    }

    /*
    count ^= 1 << (root.val - 1); -> same as count = count ^ ( 1 << (root.val - 1) );. we are shifting 1 by root.val - 1 bits and toggling that bit using the XOR operator.
    How does XOR work: 1^1=0 and 0^1=1. So this is how we toggle the root.val - 1th bit.
    count & (count - 1) -> this is used to check if there is only one bit set.
    Here's the logic behind that: If count has only 1 set bit, then that would be a power of 2 and count-1 would be the previous number, which would have all the bits that are less significant (bits towards the right) to the set bit will be set. Performing an & operation will result in 0
    Let's take an example to understand the situation. Let take the count represented by 8 bits, and has a value:
    16 ->00010000. Now, count-1 would be:
    15 ->00001111. Performing an & would result in 0:
    & --> 00000000.
     */
    private int dfs(TreeNode root, int count) {
        if (root == null) return 0;
        count ^= 1 << (root.val - 1);
        int res = dfs(root.left, count) + dfs(root.right, count);
        if (root.left == root.right && (count & (count - 1)) == 0) res++;
        return res;
    }

    int cnt = 0;
    public int pseudoPalindromicPaths1(TreeNode root) {
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

    // BFS - pre order
    /*
    用BFS 做这种遍历树的path的题的时候，当添加下一个节点时，需要储存当前节点的状态。
    这样的话，当我们遍历节点的时猴，我们就知道了此节点前一个节点的状态。
    一般可以用Pair或者Map来存储状态
     */
    public int pseudoPalindromicPaths2(TreeNode root) {
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
