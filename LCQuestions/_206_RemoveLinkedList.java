package LCQuestions;

import Utils.ListNode;

public class _206_RemoveLinkedList {
    // recursion
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tmp = head.next;
        ListNode node = reverseList(head.next);
        tmp.next = head;
        head.next = null;
        return node;
    }

    // iteration
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
