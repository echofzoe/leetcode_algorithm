package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

public class Lc_222_完全二叉树的节点个数 {

    // 完全二叉树的节点个数
    // https://leetcode-cn.com/problems/count-complete-tree-nodes/

    public static void main(String[] args) {
        Lc_222_完全二叉树的节点个数 lc = new Lc_222_完全二叉树的节点个数();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);    // [1,2,3,4,5,6,null]

        System.out.println("完全二叉树" + BinaryTreeSerialize.serialize(root) + "的节点个数为" + lc.countNodes(root));
    }

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int countNodesRecursive(TreeNode root) {
        if (root == null) return 0;

        return 1 + countNodesRecursive(root.left) + countNodesRecursive(root.right);
    }
    
    // 二分 + 位运算 - 时间复杂度 O((logN)^2) - 空间复杂度 O(1)
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        // 计算完全二叉树的层数,索引从0开始
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }

        // 完全二叉树的节点索引范围是 [left, right)
        // left = 2^level + 1, right = 2^(level+1)
        int left = (1 << level) + 1, right = 1 << (level + 1);
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (exists(root, level, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left - 1;
    }

    private boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 1;

        // depth = 2
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        // depth = 3
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
    }

}
