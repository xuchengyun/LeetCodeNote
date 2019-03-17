package LCQuestions.lc021MergetTwoSortedList;

public class MergetTwoSortedList {

    // Iteration
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l2 != null) {
            while (l2 != null) {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
                cur = cur.next;
            }
        }

        if (l1 != null) {
            while (l1 != null) {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
                cur = cur.next;
            }
        }

        return dummy.next;
    }

    // recursion
    public ListNode mergeTwoList2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val <= l2.val) {
            l1.next = mergeTwoList2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoList2(l1, l2.next);
            return l2;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }
    }
}
