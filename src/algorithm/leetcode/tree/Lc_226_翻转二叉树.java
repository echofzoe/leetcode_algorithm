package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Lc_226_翻转二叉树 {

    // 翻转二叉树
    // https://leetcode-cn.com/problems/invert-binary-tree/

    public static void main(String[] args) {
        Lc_226_翻转二叉树 lc = new Lc_226_翻转二叉树();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);    // [4,2,7,1,3,6,9]

        System.out.println("二叉树" + BinaryTreeSerialize.serialize(root) + "翻转后的结果是" + BinaryTreeSerialize.serialize(lc.invertTreeIteration(root)));
    }

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public TreeNode invertTreeRecursive(TreeNode root) {
        if (root == null) return root;

        TreeNode tmp = invertTreeRecursive(root.left);
        root.left = invertTreeRecursive(root.right);
        root.right = tmp;

        return root;
    }

    // 迭代 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public TreeNode invertTreeIteration(TreeNode root) {
        if (root == null) return root;

        Queue<TreeNode> queue = new LinkedList<>() {{
            offer(root);
        }};

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
        }

        return root;
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 4;

        // depth = 2
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);

        // depth = 3
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
    }

}
