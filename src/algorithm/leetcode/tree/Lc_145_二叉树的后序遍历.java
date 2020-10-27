package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Lc_145_二叉树的后序遍历 {

    // 二叉树的后序遍历
    // https://leetcode-cn.com/problems/binary-tree-postorder-traversal/

    public static void main(String[] args) {
        Lc_145_二叉树的后序遍历 lc = new Lc_145_二叉树的后序遍历();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);


        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(root, 3) + " 的后序遍历结果是 " + lc.postorderTraversal_Iteration(root));
    }

    List<Integer> res = new LinkedList<>();

    public List<Integer> postorderTraversal_Recursive(TreeNode root) {
        if (root == null) return res;

        dfs(root);

        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;

        dfs(node.left);
        dfs(node.right);
        res.add(node.val);
    }

    public List<Integer> postorderTraversal_Iteration(TreeNode root) {
        if (root == null) return res;

        Deque<TreeNode> stack = new LinkedList<>() {{
            offerFirst(root);
        }};

        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            res.add(0, node.val);

            if (node.left != null) {
                stack.offerFirst(node.left);
            }

            if (node.right != null) {
                stack.offerFirst(node.right);
            }
        }

        return res;
    }
}
