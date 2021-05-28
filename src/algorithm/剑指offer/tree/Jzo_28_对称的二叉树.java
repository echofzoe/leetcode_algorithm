package algorithm.剑指offer.tree;

import algorithm.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Jzo_28_对称的二叉树 {

    // 对称的二叉树
    // https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/

    public static void main(String[] args) {
        Jzo_28_对称的二叉树 lc = new Jzo_28_对称的二叉树();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);    // [1,2,2,3,4,4,3]

        System.out.println("(递归方法)该二叉树是镜像对称的吗？ -" + lc.isSymmetricRecursive(root));

        System.out.println("(BFS)该二叉树是镜像对称的吗？ -" + lc.isSymmetricBFS(root));
    }

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N) 最差情况下,二叉树退化成链表
    public boolean isSymmetricRecursive(TreeNode root) {
        if (root == null) return true;

        return recur(root.left, root.right);
    }

    private boolean recur(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;

        return recur(left.left, right.right) && recur(left.right, right.left);
    }

    // BFS - 时间复杂度 O(N) - 空间复杂度 O(N)
    public boolean isSymmetricBFS(TreeNode root) {
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
