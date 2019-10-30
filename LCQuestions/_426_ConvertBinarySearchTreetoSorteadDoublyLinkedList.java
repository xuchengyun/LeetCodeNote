package LCQuestions;

public class _426_ConvertBinarySearchTreetoSorteadDoublyLinkedList {
    Node head;
    Node pre;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        this.head = null;
        this.pre = null;
        inorder(root, this.pre ,this.head);
        pre.right = head;
        head.left = pre;
        return head;
    }

    private void inorder(Node node, Node pre, Node head) {
        if (node == null) {
            return;
        }
        inorder(node.left, pre, head);
        if (head == null) {
            this.head = node;
            this.pre = node;
        } else {
            node.left = this.pre;
            this.pre.right = node;
            this.pre = node;
        }
        inorder(node.right, pre, head);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
