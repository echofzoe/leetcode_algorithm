package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Lc_114_二叉树展开为链表 {

    // 二叉树展开为链表
    // https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/

    public static void main(String[] args) {
        Lc_114_二叉树展开为链表 lc = new Lc_114_二叉树展开为链表();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);    // [1,2,5,3,4,null,6]

        System.out.println("二叉树" + BinaryTreeSerialize.serialize(root) + "平展后的结果为");
        lc.flattenIteration(root);
        System.out.println(BinaryTreeSerialize.serialize(root));
    }

    // 递归 + in place - 时间复杂度 O(N) - 空间复杂度 O(N)
    public void flattenRecursiveInPlace(TreeNode root) {
        if (root == null) return;

        flattenRecursiveInPlace(root.left);
        flattenRecursiveInPlace(root.right);

        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;

        while (root.right != null) {
            root = root.right;
        }

        root.right = temp;
    }

    // 前序遍历 + 递归 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public void flattenRecursive(TreeNode root) {
        if (root == null) return;

        List<TreeNode> list = new ArrayList<>();
        preorderTraversal(root, list);

        for (int i = 1; i < list.size(); i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right  =curr;
        }
    }

    // 前序遍历 + 迭代 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public void flattenIteration(TreeNode root) {
        if (root == null) return;

        List<TreeNode> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>() {{
            offerFirst(root);
        }};

        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            list.add(node);
            if (node.right != null) stack.offerFirst(node.right);
            if (node.left != null) stack.offerFirst(node.left);
        }

        for (int i = 1; i < list.size(); i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    private void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root == null) return;

        list.add(root);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 1;

        // depth = 2
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);

        // depth = 3
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
    }

}
