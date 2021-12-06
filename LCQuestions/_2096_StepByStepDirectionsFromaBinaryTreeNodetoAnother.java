package LCQuestions;

import Utils.TreeNode;


/*
https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/submissions/
1.Build directions for both start and destination from the root.
Say we get "LLRRL" and "LRR".
2.Remove common prefix path.
We remove "L", and now start direction is "LRRL", and destination - "RR"
3.Replace all steps in the start direction to "U" and add destination direction.
The result is "UUUU" + "RR".
*/
// Find common ancestor
public class _2096_StepByStepDirectionsFromaBinaryTreeNodetoAnother {
    private boolean find(TreeNode n, int val, StringBuilder sb) {
        if (n.val == val) {
            return true;
        }
        if (n.left != null && find(n.left, val, sb)) {
            sb.append("L");
        }
        else if (n.right != null && find(n.right, val, sb)) {
            sb.append("R");
        }
        return sb.length() > 0;
    }
    
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder s = new StringBuilder(), d = new StringBuilder();
        find(root, startValue, s);
        find(root, destValue, d);
        int i = 0, max_i = Math.min(d.length(), s.length());
        while (i < max_i && s.charAt(s.length() - i - 1) == d.charAt(d.length() - i - 1)) {
            i++;
        }
        return "U".repeat(s.length() - i) + d.reverse().toString().substring(i);
    }
}