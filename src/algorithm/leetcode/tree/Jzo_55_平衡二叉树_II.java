package algorithm.leetcode.tree;

import algorithm.leetcode.utils.TreeNode;

public class Jzo_55_平衡二叉树_II {

    // 平衡二叉树
    // https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/

    public static void main(String[] args) {
        Jzo_55_平衡二叉树_II lc = new Jzo_55_平衡二叉树_II();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);    // [3,9,20,null,null,15,7,null,null,null,null,1]

        System.out.print("这棵树");
        if (lc.isBalanced(root)) {
            System.out.print("是");
        } else {
            System.out.print("不是");
        }
        System.out.println(" AVL 树");
    }

    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null) return 0;

        int left = recur(root.left);
        if (left == -1) return -1;    // 左子树不是 AVL, 则整棵树不是 AVl

        int right = recur(root.right);
        if (right == -1) return -1;    // 右子树不是 AVL, 则整棵树不是 AVl

        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        TreeNode head = root;
        head.val = 3;

        // depth = 2
        head.left = new TreeNode(9);
        head.right = new TreeNode(20);

        // depth = 3
        head.right.left = new TreeNode(15);
        head.right.right = new TreeNode(7);

        // depth = 4
        head.right.right.left = new TreeNode(1);
    }

}
