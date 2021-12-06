package LCQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Utils.ListNode;


public class Main {
    public static ListNode deleteMiddle(ListNode head) {
        // 2 pass
        int n = 0;
        while (head != null) {
            head = head.next;
            n++;
        }
        int pos = n / 2;
        
        ListNode newHead = head;
        while (newHead != null) {
            if (pos > 1) {
                newHead = newHead.next;
                pos--;
            } else {
                break;
            }
        }
        if (newHead.next != null) {
            newHead.next = newHead.next.next;
        }
        return head;
    }

    public static void main(String[] args) {
        List<ListNode> l = new ArrayList<>();
        ListNode a = new ListNode(1);
        a.next = new ListNode(3);
        a.next.next = new ListNode(4);



        deleteMiddle(a);

    }
}
