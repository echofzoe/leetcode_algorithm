package algorithm.leetcode.tree;

import algorithm.leetcode.utils.TreeNode;

/**
 * 二叉搜索树中第K小的元素
 * <P>https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/</P>
 *
 * @author echofzoe
 * @since 2021.7.27
 */
public class Lc_230_二叉搜索树中第K小的元素 {

    public static void main(String[] args) {
        Lc_230_二叉搜索树中第K小的元素 lc = new Lc_230_二叉搜索树中第K小的元素();

        TreeNode root = new TreeNode();
        lc.treeInitialize(root);  // [3,1,4,null,2]

        System.out.println("给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。");
        System.out.println("输入：");
        System.out.println("输出：");
    }

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N) 最坏情况下，遍历整棵树
    private int k, res;
    boolean flag;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        res = 0;
        flag = false;

        inorder(root);

        return res;
    }

    private void inorder(TreeNode root) {
        if (root == null || flag) return;

        inorder(root.left);

        if (--k == 0) {
            res = root.val;
            flag = true;
            return;
        }

        inorder(root.right);
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 3;

        // depth = 2
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);

        // depth = 3
        root.left.right = new TreeNode(2);
    }
}
