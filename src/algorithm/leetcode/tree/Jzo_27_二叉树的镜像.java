package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.Stack;

public class Jzo_27_二叉树的镜像 {

    // 二叉树的镜像
    // https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/

    public static void main(String[] args) {
        Jzo_27_二叉树的镜像 lc = new Jzo_27_二叉树的镜像();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);

        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(root) + " 的镜像二叉树是：" + BinaryTreeSerialize.serialize(lc.mirrorTree(root)));
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;

        Stack<TreeNode> stack = new Stack<>() {{
            add(root);
        }};

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr.left != null) stack.add(curr.left);
            if (curr.right != null) stack.add(curr.right);

            // 交换左右子树位置
            TreeNode tmp = curr.left;
            curr.left = curr.right;
            curr.right = tmp;
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
