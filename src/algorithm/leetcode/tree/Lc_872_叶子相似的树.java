package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Lc_872_叶子相似的树 {

    // 叶子相似的树
    // https://leetcode-cn.com/problems/leaf-similar-trees/

    public static void main(String[] args) {
        Lc_872_叶子相似的树 lc = new Lc_872_叶子相似的树();

        // root1 - 叶节点为 [6, 7, 4, 1]
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(2);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(4);

        // root2 - 叶节点为 [6, 7, 1]
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(6);
        root2.right = new TreeNode(2);
        root2.right.left = new TreeNode(7);
        root2.right.right = new TreeNode(4);
        root2.right.right.right = new TreeNode(1);

        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(root1) + " 和 " + BinaryTreeSerialize.serialize(root2) + (lc.leafSimilar(root1, root2) ? " 是 " : " 不是 ") + "叶相似的");
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();

        dfs(root1, list1);
        dfs(root2, list2);

        return list1.equals(list2);
    }

    private void dfs(TreeNode root, List<Integer> list) {

        if (root == null) return;

        if (root.left == null && root.right == null) list.add(root.val);

        dfs(root.left, list);
        dfs(root.right, list);

    }
}
