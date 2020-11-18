package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Lc_94_二叉树的中序遍历 {

    // 二叉树的中序遍历
    // https://leetcode-cn.com/problems/binary-tree-inorder-traversal/

    public static void main(String[] args) {
        Lc_94_二叉树的中序遍历 lc = new Lc_94_二叉树的中序遍历();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);


        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(root, 3) + " 的中序遍历结果是 " + lc.inorderTraversalRecursive(root));
    }

    List<Integer> res = new LinkedList<>();

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        if (root == null) return res;

        dfs(root);

        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;

        dfs(node.left);

        res.add(node.val);

        dfs(node.right);
    }

    // 迭代 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public List<Integer> inorderTraversalIteration(TreeNode root) {
        if (root == null) return res;

        Deque<TreeNode> stack = new LinkedList<>() {{
            offerFirst(root);
        }};

        TreeNode dummy = root;
        while (dummy != null || !stack.isEmpty()) {

            while (dummy != null) {
                stack.offerFirst(dummy);
                dummy = dummy.left;
            }

            dummy = stack.pollFirst();

            res.add(dummy.val);

            dummy = dummy.right;

        }

        return res;
    }

}
