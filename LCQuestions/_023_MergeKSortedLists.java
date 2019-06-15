package LCQuestions;

import java.util.PriorityQueue;

public class _023_MergeKSortedLists {
    // divide and conquer
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int len = lists.length;
        return merge(lists, 0, len - 1);
    }

    // method 2, use priorityQueue
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (o1, o2) -> o1.val - o2.val);
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode progress = dummy;
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            if (cur.next != null) {
                pq.add(cur.next);

            }
            progress.next = cur;
            progress = progress.next;
        }
        return dummy.next;
    }

    private ListNode merge(ListNode[] lists, int lo, int hi) {
        if (lo >= hi) return lists[lo];
        int mid = lo + (hi - lo) / 2;
        ListNode left = merge(lists, lo, mid);
        ListNode right = merge(lists, mid + 1, hi);
        return mergeTwoSortedList(left, right);
    }

    private ListNode mergeTwoSortedList(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        while (left != null) {
            cur.next = left;
            cur = cur.next;
            left = left.next;
        }

        while (right != null) {
            cur.next = right;
            cur = cur.next;
            right = right.next;
        }
        return dummy.next;
    }

    private ListNode mergeTwoSortedList1(ListNode left, ListNode right) {
        if (left == null) return right;
        if (right == null) return left;
        if (left.val <= right.val) {
            left.next = mergeTwoSortedList1(left.next, right);
            return left;
        } else {
            right.next = mergeTwoSortedList1(left, right.next);
            return right;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
