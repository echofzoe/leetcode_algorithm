package algorithm.剑指offer.tree;

import algorithm.leetcode.utils.TreeNode;

/**
 * 二叉树的最近公共祖先
 * <P>https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/</P>
 *
 * @author echofzoe
 * @since unknown
 * @updated 2021.7.14
 */
public class Jzo_68_二叉树的最近公共祖先_II {

    public static void main(String[] args) {
        Jzo_68_二叉树的最近公共祖先_II lc = new Jzo_68_二叉树的最近公共祖先_II();

        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);  // [3,5,1,6,2,0,8,null,null,7,4]

        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);

        System.out.println("给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。\n" +
                "百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”\n");
        System.out.println("输入：p = " + p.val + ", q = " + q.val);
        System.out.println("输出：" + lc.lowestCommonAncestor(root, p, q).val);  // 3
    }

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null && right == null) return null;  // 遇到叶子结点、即已经找到最底仍没找到，返回空
        if (left == null) return right;  // 左子树中没找到 p, q, 递归函数返回空；故 p, q 都在 root 的右子树中，则最终结果应是递归的右子树找到的节点
        if (right == null) return left;  // 右子树中没找到 p, q, 递归函数返回空；故 p, q 都在 root 的左子树中，则最终结果应是递归的左子树找到的节点
        return root;  // left & right 同时非空，说明 p, q 分别在 root 的左右子树中，则 root 即为最近公共祖先节点
    }

    // 二叉树初始化
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
