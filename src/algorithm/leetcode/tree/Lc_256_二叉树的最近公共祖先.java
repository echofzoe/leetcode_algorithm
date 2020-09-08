package algorithm.leetcode.tree;

import algorithm.leetcode.utils.TreeNode;

public class Lc_256_二叉树的最近公共祖先 {

    // 二叉树的最近公共祖先
    // https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/

    public static void main(String[] args) {
        Lc_256_二叉树的最近公共祖先 lc = new Lc_256_二叉树的最近公共祖先();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);    // root = [3,5,1,6,2,0,8,null,null,7,4]

        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);

        System.out.println("节点 " + p.val + " 和节点 " + q.val + " 的最近公共祖先是：" + lc.lowestCommonAncestor(root, p, q).val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null && right == null) return null;
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 3;

        // depth = 2
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        // depth = 3
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        // depth = 4
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
    }
}
