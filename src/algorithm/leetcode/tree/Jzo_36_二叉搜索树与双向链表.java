package algorithm.leetcode.tree;

import algorithm.leetcode.utils.Node;

import java.util.LinkedList;
import java.util.Queue;

public class Jzo_36_二叉搜索树与双向链表 {

    // 二叉搜索树与双向链表
    // https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/

    public static void main(String[] args) {
        Jzo_36_二叉搜索树与双向链表 lc = new Jzo_36_二叉搜索树与双向链表();
        Node root = new Node(0);
        lc.treeInitialize(root);

        System.out.println(lc.TreeNodeSerialize(root) + " 按要求转换成双向列表后的输出为 " + lc.DoubleListSerialize(lc.treeToDoublyList(root)));
    }

    Node pre, head;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        dfs(root);

        head.left = pre;
        pre.right = head;

        return head;
    }

    private void dfs(Node curr) {
        if (curr == null) return;

        dfs(curr.left);

        if (pre != null) pre.right = curr;
        else head = curr;

        curr.left = pre;
        pre = curr;

        dfs(curr.right);
    }

    private String TreeNodeSerialize(Node root) {
        if (root == null) return "[]";

        StringBuffer buf = new StringBuffer("[");
        Queue<Node> queue = new LinkedList<>() {{
            offer(root);
        }};

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur != null) {
                buf.append(cur.val).append(",");
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                buf.append("null,");
            }
        }

        return buf.deleteCharAt(buf.length() - 1).append("]").toString();
    }

    private String DoubleListSerialize(Node head) {
        if (head == null) return "[]";

        Node foo = head;
        StringBuffer buf = new StringBuffer("[");

        while (foo.right != head) {
            buf.append(foo.val).append(",");
            foo = foo.right;
        }
        // 注意这里的 while 循环不会遍历到最后一个元素

        return buf.append(foo.val).append("]").toString();
    }

    private void treeInitialize(Node root) {
        // depth = 1
        root.val = 4;

        // depth = 2
        root.left = new Node(2);
        root.right = new Node(5);

        // depth = 3
        root.left.left = new Node(1);
        root.left.right = new Node(3);
    }
}
