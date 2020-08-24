package algorithm.leetcode.tree;

import algorithm.leetcode.utils.TreeNode;

public class jianzhi_offer_68_II {
    public static void main(String[] args) {
        jianzhi_offer_68_II lc = new jianzhi_offer_68_II();
        TreeNode root = new TreeNode(0);
        lc.TreeInitialize(root);    // [3,5,1,6,2,0,8,null,null,7,4]

        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);

        System.out.println(p.val + " and " + q.val + " 's lowest common ancestor is " + lc.lowestCommonAncestor(root, p, q).val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null && right == null) return null;    // 遇到叶子结点，返回 null
        if (left == null) return right;    // p, q 都在递归的右子节点中
        if (right == null) return left;    // p, q 都在递归的左子节点中

        return root;    // 递归的左右子节点都非空
    }


    // 二叉树初始化
    private void TreeInitialize(TreeNode root) {
        // depth = 2
        TreeNode head = root;
        TreeNode headL = root;
        TreeNode headR = root;
        head.val = 3;
        head.left = new TreeNode(5);
        head.right = new TreeNode(1);

        // depth = 3
        headL = head.left;
        headL.left = new TreeNode(6);
        headL.right = new TreeNode(2);

        headR = head.right;
        headL.left = new TreeNode(0);
        headL.right = new TreeNode(8);

        // depth = 4
        headL = headL.right;
        headL.left = new TreeNode(7);
        headL.right = new TreeNode(4);

        // free
        head = null;
        headL = null;
        headR = null;
    }

}
