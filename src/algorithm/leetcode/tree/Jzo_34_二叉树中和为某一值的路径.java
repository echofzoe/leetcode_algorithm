package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Jzo_34_二叉树中和为某一值的路径 {

    // 二叉树中和为某一值的路径
    // https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/

    public static void main(String[] args) {
        Jzo_34_二叉树中和为某一值的路径 lc = new Jzo_34_二叉树中和为某一值的路径();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);
        int sum = 22;

        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(root, 4) + " 中和为 " + sum + " 的路径有 " + Arrays.toString(lc.pathSum(root, sum).toArray()));
    }

    List<List<Integer>> res = new LinkedList<>();    // 结果
    List<Integer> path = new LinkedList<>();    // 路径

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        recur(root, sum);
        return res;
    }

    private void recur(TreeNode root, int cur) {
        if (root == null) return;

        path.add(root.val);
        cur -= root.val;
        if (cur == 0 && root.left == null && root.right == null) {
            res.add(new LinkedList<>(path));
        }

        recur(root.left, cur);
        recur(root.right, cur);

        path.remove(path.size() - 1);
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 5;

        // depth = 2
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);

        // depth = 3
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);

        // depth = 4
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);

        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
    }
}
