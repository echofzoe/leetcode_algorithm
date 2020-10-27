package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Lc_144_二叉树的前序遍历 {

    // 二叉树的前序遍历
    // https://leetcode-cn.com/problems/binary-tree-preorder-traversal/

    public static void main(String[] args) {
        Lc_144_二叉树的前序遍历 lc = new Lc_144_二叉树的前序遍历();
        TreeNode root = new TreeNode(1);
        lc.treeInitialize(root);    // [1,null,2,3]

        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(root, 3) + " 的前序遍历序列是 " + lc.preorderTraversal_Iteration(root).toString());
    }

    List<Integer> res = new LinkedList<>();

    // 递归 - 时间复杂度 O(N) 遍历所有节点 - 空间复杂度 O(N) 为递归栈的开销,平均情况下为 O(logN),最坏情况下树呈链状为 O(N)
    public List<Integer> preorderTraversal_Recursion(TreeNode root) {
        if (root == null) return res;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        res.add(node.val);
        dfs(node.left);
        dfs(node.right);
    }

    // 迭代 - 时间复杂度 O(N) - 空间复杂度 O(N) 为迭代过程中显示栈的开销
    public List<Integer> preorderTraversal_Iteration(TreeNode root) {
        if (root == null) return res;

        Deque<TreeNode> stack = new LinkedList<>() {{
            offerFirst(root);
        }};

        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            res.add(node.val);
            if (node.right != null) stack.offerFirst(node.right);
            if (node.left != null) stack.offerFirst(node.left);
        }

        return res;
    }

    private void treeInitialize(TreeNode root) {
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
    }
}
