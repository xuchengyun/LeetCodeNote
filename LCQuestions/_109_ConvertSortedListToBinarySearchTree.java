package LCQuestions;

import Utils.ListNode;
import Utils.TreeNode;

public class _109_ConvertSortedListToBinarySearchTree {
    /**
     用快慢指针找中点
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return helper(head, null);
    }

    private TreeNode helper(ListNode head, ListNode tail) {
        if (head == tail) return null;
        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode cur = new TreeNode(slow.val);
        cur.left = helper(head, slow);
        cur.right = helper(slow.next, tail);
        return cur;
    }
}
