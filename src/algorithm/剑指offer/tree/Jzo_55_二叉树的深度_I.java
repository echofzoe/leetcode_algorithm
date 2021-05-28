package algorithm.剑指offer.tree;

import algorithm.leetcode.utils.TreeNode;

import java.util.*;

public class Jzo_55_二叉树的深度_I {

    // 二叉树的深度
    // https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/

    public static void main(String[] args) {
        Jzo_55_二叉树的深度_I lc = new Jzo_55_二叉树的深度_I();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);

        int depth = lc.maxDepth(root);
        List res = lc.printBinaryTree(root, depth);
        System.out.println("二叉树 " + res.toString() + " 的最大深度为：" + depth);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode curr = queue.poll();

                if (curr.left != null) {
                    queue.offer(curr.left);
                }

                if (curr.right != null) {
                    queue.offer(curr.right);
                }

                size--;
            }
            depth++;
        }
        return depth;
    }

    private List printBinaryTree(TreeNode root, int depth) {
        if (root == null) return null;

        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int count = 0;
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                TreeNode curr = queue.poll();

                if (count < depth) {
                    if (curr != null) {
                        res.add(curr.val);

                        if (curr.left != null) {
                            queue.offer(curr.left);
                        } else {
                            queue.offer(null);
                        }

                        if (curr.right != null) {
                            queue.offer(curr.right);
                        } else {
                            queue.offer(null);
                        }
                    } else {
                        res.add(null);
                    }
                }

                size--;
            }
            count++;
        }
        return res;
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 3;

        // depth = 2
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        // depth = 3
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
    }

}
