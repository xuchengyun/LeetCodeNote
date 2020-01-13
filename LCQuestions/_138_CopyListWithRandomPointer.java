package LCQuestions;

import java.util.HashMap;
import java.util.Map;

public class _138_CopyListWithRandomPointer {
    // iteration
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node copyHead = new Node(head.val);
        Node n = copyHead;
        Node cur = head;
        while (cur != null) {
            map.put(cur, n);
            if (cur.next != null) {
                n.next = new Node(cur.next.val);
            } else {
                n.next = null;
            }
            cur = cur.next;
            n = n.next;
        }
        cur = head;
        while (cur != null) {
            Node copy = map.get(cur);
            if (cur.random != null) {
                copy.random = map.get(cur.random);
            } else {
                copy.random = null;
            }
            cur = cur.next;
        }
        return copyHead;
    }

    //recursion
    public Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        return copyHelper(head, map);
    }

    private Node copyHelper(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }
        Node newNode = new Node(node.val);
        map.put(node, newNode);
        newNode.next = copyHelper(node.next, map);
        newNode.random = copyHelper(node.random, map);
        return newNode;
    }


    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
