package algorithm.leetcode.tree;

import algorithm.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Lc_111 {
    public static void main(String[] args) {
        Lc_111 lc = new Lc_111();
        TreeNode root = new TreeNode(0);
        lc.TreeInitialize(root);

        System.out.println("DFS: " + lc.minDepth_DFS(root) + "\nBFS: " + lc.minDepth_BFS(root));
    }

    // 深度优先搜索
    public int minDepth_DFS(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) return 1;

        int depth = Integer.MAX_VALUE;
        if (root.left != null) {
            depth = Math.min(minDepth_DFS(root.left), depth);
        }

        if (root.right != null) {
            depth = Math.min(minDepth_DFS(root.right), depth);
        }

        return depth + 1;
    }

    // 广度优先搜索
    public int minDepth_BFS(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) return 1;

        Queue<QueueNode> queue = new LinkedList<QueueNode>();
        queue.offer(new QueueNode(root, 1));
        while (!queue.isEmpty()) {
            // 队头出队，用于判定
            QueueNode nodeDepth = queue.poll();
            TreeNode node = nodeDepth.node;
            int depth = nodeDepth.depth;

            if (node.left == null && node.right == null) {
                // 遇到叶子节点直接返回
                return depth;
            }

            if (node.left != null) {
                // 非空子节点入队，下一轮进行判定
                queue.offer(new QueueNode(root.left, depth + 1));
            }

            if (node.right != null) {
                queue.offer(new QueueNode(root.right, depth + 1));
            }
        }

        return 0;
    }

    // BFS 所用队列结构 - 节点 + 当前深度
    class QueueNode {
        TreeNode node;
        int depth;

        public QueueNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    // 二叉树初始化
    private void TreeInitialize(TreeNode root) {
        TreeNode head = root;
        head.val = 3;
        head.left = new TreeNode(9);
        head.right = new TreeNode(20);
        head = head.right;
        head.left = new TreeNode(15);
        head.right = new TreeNode(7);
    }
}
