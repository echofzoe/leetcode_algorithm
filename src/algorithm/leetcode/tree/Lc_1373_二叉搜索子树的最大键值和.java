package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

public class Lc_1373_二叉搜索子树的最大键值和 {

    // 二叉搜索子树的最大键值和
    // https://leetcode-cn.com/problems/maximum-sum-bst-in-binary-tree/

    public static void main(String[] args) {
        Lc_1373_二叉搜索子树的最大键值和 lc = new Lc_1373_二叉搜索子树的最大键值和();
        TreeNode root = new TreeNode(0);
        // [4,3,null,1,2]
        lc.treeInitialize(root);

        System.out.println("树" + BinaryTreeSerialize.serialize(root) + "中，二叉搜索子树的最大键值和是" + lc.maxSumBST(root));
    }

    int maxSum = 0;

    // 递归 + 后序遍历 - 时间复杂度 O(N) - 空间复杂度 O(logN)
    public int maxSumBST(TreeNode root) {
        traverse(root);
        return maxSum;
    }

    // 返回一个 capacity 为 4 的 res 数组
    // res[0] 记录以 root 为根的二叉树是否是 BST
    // res[1] 记录以 root 为根的二叉树所有节点中的最小值
    // res[2] 记录以 root 为根的二叉树所有节点中的最大值
    // res[3] 记录以 root 为根的二叉树所有节点值之和
    private int[] traverse(TreeNode root) {
        if (root == null) return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};

        // 递归左右子树
        int[] left = traverse(root.left);
        int[] right = traverse(root.right);

        /* ****** 后序遍历位置 ****** */
        int[] res = new int[4];
        // 判断以 root 节点为根的树是不是 BST
        if (left[0] == 1 && right[0] == 1 && root.val > left[2] && root.val < right[1]) {
            // 设置以 root 为根的二叉树是 BST
            res[0] = 1;

            // 计算以 root 为根的 BST 的最小值
            res[1] = Math.min(root.val, left[1]);
            // 计算以 root 为根的 BST 的最大值
            res[2] = Math.max(root.val, right[2]);
            // 计算以 root 为根的 BST 的所有节点之和
            res[3] = root.val + left[3] + right[3];

            // 更新最大和
            maxSum = Math.max(maxSum, res[3]);
        } else {
            // 以 root 为根的二叉树不是 BST
            res[0] = 0;
        }

        return res;
    }


    // 递归 + 前序遍历 - 时间复杂度 极高 - 空间复杂度 极高
    public int maxSumBST1(TreeNode root) {
        traverse1(root);
        return maxSum;
    }

    private void traverse1(TreeNode root) {
        if (root == null) return;

        /* ****** 前序遍历位置 ****** */
        // 判断左右子树是不是 BST
        if (!isBST(root.left) || !isBST(root.right)) {
            traverse1(root.left);
            traverse1(root.right);
            return;
        }

        // 计算左子树的最大值和右子树的最小值
        int leftMax = findMax(root.left);
        int rightMin = findMin(root.right);

        // 判断以 root 节点为根的树是不是 BST
        if (root.val <= leftMax || root.val >= rightMin) {
            traverse1(root.left);
            traverse1(root.right);
            return;
        }

        // 如果条件都符合，计算当前 BST 的节点之和
        int leftSum = findSum(root.left);
        int rightSum = findSum(root.right);
        int rootSum = root.val + leftSum + rightSum;

        // 计算 BST 节点的最大和
        maxSum = Math.max(maxSum, rootSum);

        // 递归左右子树
        traverse1(root.left);
        traverse1(root.right);
    }

    private boolean isBST(TreeNode root) {
        return isBST(root, null, null);
    }

    private boolean isBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return true;

        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;

        return isBST(root.left, min, root)
                && isBST(root.right, root, max);
    }

    private int findMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;

        return Math.max(root.val, Math.max(findMax(root.left), findMax(root.right)));
    }

    private int findMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;

        return Math.min(root.val, Math.min(findMin(root.left), findMin(root.right)));
    }

    private int findSum(TreeNode root) {
        if (root == null) return 0;

        return root.val + findSum(root.left) + findSum(root.right);
    }


    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 4;
        // depth = 2
        root.left = new TreeNode(3);
        // depth = 3
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
    }

}
