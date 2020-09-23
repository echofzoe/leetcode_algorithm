package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Lc_897_递增顺序查找树 {

    // 递增顺序查找树
    // https://leetcode-cn.com/problems/increasing-order-search-tree/

    public static void main(String[] args) {
        Lc_897_递增顺序查找树 lc = new Lc_897_递增顺序查找树();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);

        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(root, 4) + " 的递增顺序查找树为 " + BinaryTreeSerialize.serialize(lc.increasingBST2(root), 9));
    }

    TreeNode cur;

    public TreeNode increasingBST1(TreeNode root) {
        TreeNode res = new TreeNode(0);
        cur = res;
        inorder(root);
        return res.right;
    }

    public TreeNode increasingBST2(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        inorder(root, vals);

        TreeNode res = new TreeNode(0), cur = res;
        for (int val : vals) {
            cur.right = new TreeNode(val);
            cur = cur.right;
        }
        return res.right;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        root.left = null;
        cur.right = root;
        cur = root;

        inorder(root.right);
    }

    private void inorder(TreeNode root, List<Integer> vals) {
        if (root == null) return;

        inorder(root.left, vals);

        vals.add(root.val);

        inorder(root.right, vals);
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

        root.right.right = new TreeNode(8);

        // depth = 4
        root.left.left.left = new TreeNode(1);

        root.right.right.left = new TreeNode(7);
        root.right.right.right = new TreeNode(9);
    }

}
