package algorithm.leetcode.tree;

import algorithm.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Jzo_28_对称的二叉树 {

    // 对称的二叉树
    // https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/

    public static void main(String[] args) {
        Jzo_28_对称的二叉树 lc = new Jzo_28_对称的二叉树();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);

        System.out.println("(递归方法)该二叉树是镜像对称的吗？ " + lc.isSymmetric(root));

        System.out.println("(非递归 - BFS 方法)该二叉树是镜像对称的吗？ " + lc.isSymmetric_2(root));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        return recur(root.left, root.right);
    }

    private boolean recur(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;

        return recur(left.left, right.right) && recur(left.right, right.left);
    }

    private boolean isSymmetric_2(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>() {{
            add(root.left);
            add(root.right);
        }};

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if (left == null && right == null) continue;
            if (left == null || right == null || left.val != right.val) return false;

            queue.offer(left.left);
            queue.offer(right.right);

            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 1;

        // depth = 2
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        // depth = 3
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
    }

}
