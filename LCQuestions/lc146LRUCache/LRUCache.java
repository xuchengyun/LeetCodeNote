package LCQuestions.lc146LRUCache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    int capacity;
    Node head;
    Node tail;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
        map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            cur.next.pre = cur.pre;
            cur.pre.next = cur.next;
            appendToHead(cur);
            return cur.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            cur.next.pre = cur.pre;
            cur.pre.next = cur.next;
            appendToHead(cur);
            cur.val = value;
            map.put(key, cur);
        } else {
            if (map.size() == capacity) {
                Node last = tail.pre;
                last.pre.next = last.next;
                last.next.pre = last.pre;
                map.remove(last.key);
            }
            Node node = new Node(key, value);
            appendToHead(node);
            map.put(key, node);
        }
    }

    void appendToHead(Node cur) {
        cur.next = head.next;
        cur.pre = head;
        head.next.pre = cur;
        head.next = cur;
    }

    // doubly linkedList define node + Hashmap
    class Node {
        Node pre;
        Node next;
        int key;
        int val;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
