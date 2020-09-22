package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

public class Lc_538_把二叉搜索树转换为累加树 {

    // 把二叉搜索树转换为累加树
    // https://leetcode-cn.com/problems/convert-bst-to-greater-tree/

    public static void main(String[] args) {
        Lc_538_把二叉搜索树转换为累加树 lc = new Lc_538_把二叉搜索树转换为累加树();

        // 输入
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);

        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(root, 2) + " 的累加树为 " + BinaryTreeSerialize.serialize(lc.convertBST(root), 2));
    }

    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

}
