package LCQuestions.Solutions._0100_0199._0113_PathSumIi;

import Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _0113_PathSumIi {
    private void recurseTree(TreeNode node, int remainingSum, List<Integer> pathNodes, List<List<Integer>> pathsList) {

        if (node == null) {
            return;
        }

        // Add the current node to the path's list
        pathNodes.add(node.val);

        // Check if the current node is a leaf and also, if it
        // equals our remaining sum. If it does, we add the path to
        // our list of paths
        if (remainingSum == node.val && node.left == null && node.right == null) {
            pathsList.add(new ArrayList<>(pathNodes));
        } else {

            // Else, we will recurse on the left and the right children
            this.recurseTree(node.left, remainingSum - node.val, pathNodes, pathsList);
            this.recurseTree(node.right, remainingSum - node.val, pathNodes, pathsList);
        }

        // We need to pop the node once we are done processing ALL of its
        // subtrees.
        pathNodes.remove(pathNodes.size() - 1);
    }

    // DFS
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> pathsList = new ArrayList<>();
        List<Integer> pathNodes = new ArrayList<>();
        this.recurseTree(root, sum, pathNodes, pathsList);
        return pathsList;
    }
}
