package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.Arrays;
import java.util.Stack;

public class Jzo_33_二叉搜索树的后序遍历序列 {

    // 二叉搜索树的后序遍历序列
    // https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/

    public static void main(String[] args) {
        Jzo_33_二叉搜索树的后序遍历序列 lc = new Jzo_33_二叉搜索树的后序遍历序列();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);

        int[] postorder1 = new int[] {1,6,3,2,5};
        int[] postorder2 = new int[] {1,3,2,6,5};

        System.out.print("整数数组 " + Arrays.toString(postorder2) + (lc.verifyPostorder_Recursion(postorder2) ? " 是" : " 不是") + "二叉搜索树 " + BinaryTreeSerialize.serialize(root, 3) + " 的后序遍历结果");
    }

    public boolean verifyPostorder_Recursion(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    private boolean recur(int[] postorder, int i, int j) {
        if (i >= j) return true;

        int cur = i;

        while (postorder[cur] < postorder[j]) cur++;

        int leftSubtreeMaxIndex = cur - 1;    // 确定左子树的右边界

        while (postorder[cur] > postorder[j]) cur++;

        return cur == j && recur(postorder, i, leftSubtreeMaxIndex) && recur(postorder, leftSubtreeMaxIndex + 1, j - 1);
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 5;

        // depth = 2
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);

        // depth = 3
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
    }
}
