package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.NavigableMap;

public class Lc_814_二叉树剪枝 {

    // 二叉树剪枝
    // https://leetcode-cn.com/problems/binary-tree-pruning/

    public static void main(String[] args) {
        Lc_814_二叉树剪枝 lc = new Lc_814_二叉树剪枝();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);
        
        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(root) + " 按题目要求剪枝后的结果树为 " + BinaryTreeSerialize.serialize(lc.pruneTree(root)));
    }

    public TreeNode pruneTree(TreeNode root) {
        if (root == null || isContainsOne(root)) return null;

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        return root;
    }

    private boolean isContainsOne(TreeNode node) {
        if (node == null) return true;
        if (node.val == 1) return false;

        return isContainsOne(node.left) && isContainsOne(node.right);
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 1;

        // depth = 2
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);

        // depth = 3
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);
    }

}
