package algorithm.剑指offer.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

public class Jzo_54_二叉搜索树的第k大节点 {

    // 二叉搜索树的第k大节点
    // https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/

    public static void main(String[] args) {
        Jzo_54_二叉搜索树的第k大节点 lc = new Jzo_54_二叉搜索树的第k大节点();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);

        System.out.println("BST " + BinaryTreeSerialize.serialize(root) + " 的第 3 大节点为: " + lc.kthLargest(root, 3));
    }

    int res, k;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode curr) {
        if (curr == null) return;

        dfs(curr.right);
        if (--k == 0) {
            res = curr.val;
            return;
        }

        dfs(curr.left);
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 5;

        // depth = 2
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);

        // depth = 3
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        // depth = 4
        root.left.left = new TreeNode(1);
    }

}
