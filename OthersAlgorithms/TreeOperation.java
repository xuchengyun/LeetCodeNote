package OthersAlgorithms;

//给你一个tree，node是+/-或者数字，问从leaf到root的运算结果最小是多少？
public class TreeOperation {
    int globalMin;

    int findMinimum(TreeNode root) {
        dfs(root, root.val, 0);
        return 0;
    }

    void dfs(TreeNode root, String curVal, int curRes) {
        if (root == null) {
            globalMin = Math.min(globalMin, curRes);
            return;
        }

    }

    class TreeNode {
        TreeNode child;
        String val;

        TreeNode(String s) {
            this.val = s;
        }
    }
}
