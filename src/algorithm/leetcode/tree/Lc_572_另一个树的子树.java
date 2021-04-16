package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

public class Lc_572_另一个树的子树 {

    // 另一个树的子树
    // https://leetcode-cn.com/problems/subtree-of-another-tree/

    public static void main(String[] args) {
        Lc_572_另一个树的子树 lc = new Lc_572_另一个树的子树();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);

        TreeNode target = new TreeNode(4);
        target.left = new TreeNode(1);
        target.right = new TreeNode(2);

        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(target) + (lc.isSubtree(root, target) ? " 是 " : " 不是 ") + BinaryTreeSerialize.serialize(root) + " 的一颗子树");
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        return dfs(s, t);
    }

    private boolean dfs(TreeNode s, TreeNode t) {
        if (s == null) return false;
        return check(s, t) || dfs(s.left, t) || dfs(s.right, t);
    }

    private boolean check(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null || s.val != t.val) return false;
        return check(s.left, t.left) && check(s.right, t.right);
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 3;

        // depth = 2
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);

        // depth = 3
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);

        // depth = 4
        root.left.right.left = new TreeNode(0);
    }

}
