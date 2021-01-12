package algorithm.leetcode.tree;

import algorithm.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Lc_111_二叉树的最小深度 {

    // 二叉树的最小深度
    // https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/

    public static void main(String[] args) {
        Lc_111_二叉树的最小深度 lc = new Lc_111_二叉树的最小深度();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);    // [3,9,20,null,null,15,7]

        System.out.println("DFS: " + lc.minDepthDFS(root) + "\nBFS: " + lc.minDepthBFS(root));
    }

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int minDepthRecur(TreeNode root) {
        if (root == null) return 0;

        if (root.left != null && root.right != null)
            return 1 + Math.min(minDepthRecur(root.left), minDepthRecur(root.right));

        if (root.left != null || root.right != null)
            return 1 + Math.max(minDepthRecur(root.left), minDepthRecur(root.right));

        return 1;
    }

    // DFS - 时间复杂度 O(N) - 空间复杂度 O(H) H为树高，最坏情况下树退化成链表，为O(H)，平均情况下为O(logN)
    public int minDepthDFS(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) return 1;

        int minDepth = Integer.MAX_VALUE;

        if (root.left != null) minDepth = Math.min(minDepthDFS(root.left), minDepth);

        if (root.right != null) minDepth = Math.min(minDepthDFS(root.right), minDepth);

        return 1 + minDepth;
    }

    // BFS - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int minDepthBFS(TreeNode root) {
        if (root == null) return 0;

        Queue<QueueNode> queue = new LinkedList<>() {{
            offer(new QueueNode(root, 1));
        }};

        while (!queue.isEmpty()) {
            QueueNode curQueueNode = queue.poll();

            TreeNode curNode = curQueueNode.node;
            int curDepth = curQueueNode.depth;

            // 遇到叶子节点直接返回
            if (curNode.left == null && curNode.right == null) return curDepth;

            // 非空子节点入队，下一轮进行判定
            if (curNode.left != null) queue.offer(new QueueNode(curNode.left, curDepth + 1));
            if (curNode.right != null) queue.offer(new QueueNode(curNode.right, curDepth + 1));
        }

        return 0;
    }

    // 二叉树初始化
    private void treeInitialize(TreeNode root) {
        TreeNode head = root;
        head.val = 3;
        head.left = new TreeNode(9);
        head.right = new TreeNode(20);
        head = head.right;
        head.left = new TreeNode(15);
        head.right = new TreeNode(7);
    }
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
