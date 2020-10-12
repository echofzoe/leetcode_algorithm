package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Lc_530_二叉搜索树的最小绝对差 {

    // 二叉搜索树的最小绝对差
    // https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/

    public static void main(String[] args) {
        Lc_530_二叉搜索树的最小绝对差 lc = new Lc_530_二叉搜索树的最小绝对差();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);

        System.out.println("二叉搜索树 " + BinaryTreeSerialize.serialize(root, 3) + " 的最小绝对差是 " + lc.getMinimumDifference(root));
    }

    public int getMinimumDifference(TreeNode root) {
        int res = Integer.MAX_VALUE;
        int pre = -1;

        midOrder(root);
        
        if (root != null) {

            for (int i : list) {

                if (pre == -1) {
                    pre = root.val;
                } else {
                    res = Math.min(i - pre, res);
                    pre = i;
                }
            }
        }

        return res;
    }

    List<Integer> list = new ArrayList<>();

    private void midOrder(TreeNode root) {
        if (root == null) return;

        midOrder(root.left);

        list.add(root.val);

        midOrder(root.right);
    }
}
