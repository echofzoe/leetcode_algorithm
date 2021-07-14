package algorithm.剑指offer.tree;

import algorithm.leetcode.utils.TreeNode;

/**
 * 二叉树的最近公共祖先
 * <P>https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/</P>
 *
 * @author echofzoe
 * @since unknown
 * @updated 2021.7.14
 */
public class Jzo_68_二叉树的最近公共祖先_I {

    public static void main(String[] args) {
        Jzo_68_二叉树的最近公共祖先_I lc = new Jzo_68_二叉树的最近公共祖先_I();

        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);  // root = [6,2,8,0,4,7,9,null,null,3,5]

        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(8);

        System.out.println("给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。\n" +
                "百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”\n");
        System.out.println("输入：p = " + p.val + ", q = " + q.val);
        System.out.println("输出：" + lc.lowestCommonAncestor_Recursion(root, p, q).val);  // 6
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
