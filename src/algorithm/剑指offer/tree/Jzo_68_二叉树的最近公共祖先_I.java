package algorithm.剑指offer.tree;

import algorithm.leetcode.utils.TreeNode;

public class Jzo_68_二叉树的最近公共祖先_I {

    // 二叉树的最近公共祖先
    // https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/

    public static void main(String[] args) {
        Jzo_68_二叉树的最近公共祖先_I lc = new Jzo_68_二叉树的最近公共祖先_I();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);    // root = [6,2,8,0,4,7,9,null,null,3,5]

        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(8);
        System.out.println(p.val + " and " + q.val + " 's lowest common ancestor is " + lc.lowestCommonAncestor_Recursion(root, p, q).val);
    }

    // 迭代 - 时间复杂度 O(N) - 空间复杂度 O(1)
    private TreeNode lowestCommonAncestor_Iteration(TreeNode root, TreeNode p, TreeNode q) {

        if (p.val > q.val) {
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }

        while (root != null) {
            if (root.val < p.val) root = root.right;
            else if (root.val > q.val) root = root.left;
            else break;
        }

        return root;
    }

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N)
    private TreeNode lowestCommonAncestor_Recursion(TreeNode root, TreeNode p, TreeNode q) {

        if (p.val > q.val) {
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }

        if (root.val < p.val) return lowestCommonAncestor_Recursion(root.right, p, q);
        if (root.val > q.val) return lowestCommonAncestor_Recursion(root.left, p, q);
        return root;
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 6;

        // depth = 2
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);

        // depth = 3
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        // depth = 4
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
    }
}
