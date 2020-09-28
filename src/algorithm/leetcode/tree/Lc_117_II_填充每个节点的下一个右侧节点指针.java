package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.Node;
import algorithm.leetcode.utils.TreeConstruction;
import algorithm.leetcode.utils.TreeConstructionAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Lc_117_II_填充每个节点的下一个右侧节点指针 extends TreeConstructionAdapter {

    // 填充每个节点的下一个右侧节点指针 II
    // https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/

    public static void main(String[] args) {
        Lc_117_II_填充每个节点的下一个右侧节点指针 lc = new Lc_117_II_填充每个节点的下一个右侧节点指针();
        Node root = new Node(0);
        lc.treeConstruct(root);

        lc.connectSpaceComplexityOptimization(root);
        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(root, 3) + " 经过填充 next 节点后的结果为 " + BinaryTreeSerialize.nextNodeSerialize(root, 3));
    }

    // 时间复杂度 - O(N) - 空间复杂度 - O(N)
    public Node connect(Node root) {
        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>() {{
            offer(root);
        }};

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                Node cur = queue.poll();

                if (size == 1) {
                    cur.next = null;
                } else {
                    cur.next = queue.peek();
                }

                if (cur.left != null) {
                    queue.offer(cur.left);
                }

                if (cur.right != null) {
                    queue.offer(cur.right);
                }

                size--;
            }
        }
        
        return root;
    }

    // 时间复杂度 - O(N) - 空间复杂度 - O(1)
    public Node connectSpaceComplexityOptimization(Node root) {
        if (root == null) return null;

        Node cur = root;

        while (cur != null) {

            // 遍历当前层的时候, 为了方便操作在下一层前面添加一个哑结点（注意这里是访问当前层的节点，然后把下一层的节点串起来）
            Node dummy = new Node(0);
            // pre 节点表示下一层节点的头结点
            Node pre = dummy;

            // 开始遍历当前层的链表, 将其下一层的节点串联起来
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;    // 更新 pre
                }

                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }

                // 继续访问这一层的下一个节点
                cur = cur.next;
            }

            // 将当前层的下一层串联完毕后, 让 cur 指向下一层, 为下下层继续上述操作
            cur = dummy.next;
        }

        return root;
    }

    @Override
    public void treeConstruct(Node root) {
        // depth = 1
        root.val = 1;

        // depth = 2
        root.left = new Node(2);
        root.right = new Node(3);

        // depth = 3
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.right = new Node(7);
    }
}
