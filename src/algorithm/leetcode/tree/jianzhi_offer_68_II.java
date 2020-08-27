package algorithm.leetcode.tree;

import algorithm.leetcode.utils.TreeNode;

public class jianzhi_offer_68_II {

    // 二叉树的最近公共祖先
    // https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/

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

        if (left == null && right == null) return null;    // 遇到叶子结点、即已经找到最底仍没找到，返回空
        if (left == null) return right;    // 左子树中没找到 p, q, 递归函数返回空；故 p, q 都在 root 的右子树中，则最终结果应是递归的右子树找到的节点
        if (right == null) return left;    // 右子树中没找到 p, q, 递归函数返回空；故 p, q 都在 root 的左子树中，则最终结果应是递归的左子树找到的节点
        return root;    // left & right 同时非空，说明 p, q 分别在 root 的左右子树中，则 root 即为最近公共祖先节点
    }


    // 二叉树初始化
    private void TreeInitialize(TreeNode root) {
        // depth = 1
        TreeNode head = root;
        head.val = 3;

        // depth = 2
        head.left = new TreeNode(5);
        head.right = new TreeNode(1);

        TreeNode headL = head.left;
        TreeNode headR = head.right;

        // depth = 3
        headL = head.left;
        headL.left = new TreeNode(6);
        headL.right = new TreeNode(2);

        headR = head.right;
        headR.left = new TreeNode(0);
        headR.right = new TreeNode(8);

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
