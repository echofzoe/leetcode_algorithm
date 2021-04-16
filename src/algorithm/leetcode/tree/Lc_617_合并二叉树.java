package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.Stack;

public class Lc_617_合并二叉树 {

    // 合并二叉树
    // https://leetcode-cn.com/problems/merge-two-binary-trees/

    public static void main(String[] args) {
        Lc_617_合并二叉树 lc = new Lc_617_合并二叉树();
        TreeNode root1 = new TreeNode(0);
        lc.treeInitialize1(root1);
        TreeNode root2 = new TreeNode(0);
        lc.treeInitialize2(root2);

        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(root1) + " 和 " + BinaryTreeSerialize.serialize(root2) + " 合并后的二叉树为 " + BinaryTreeSerialize.serialize(lc.mergeTrees_Iteration(root1, root2)));
    }

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public TreeNode mergeTrees_Recursion(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null) return t2;
        if (t2 == null) return t1;

        t1.val += t2.val;
        t1.left = mergeTrees_Recursion(t1.left, t2.left);
        t1.right = mergeTrees_Recursion(t1.right, t2.right);
        return t1;
    }

    // 迭代 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public TreeNode mergeTrees_Iteration(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;

        Stack<TreeNode[]> stack = new Stack<>() {{
            push(new TreeNode[]{t1, t2});
        }};

        while (!stack.isEmpty()) {
            TreeNode[] t = stack.pop();
            if (t[0] == null || t[1] == null) {
                continue;
            }
            t[0].val += t[1].val;

            if (t[0].left == null) {
                t[0].left = t[1].left;
            } else {
                stack.push(new TreeNode[] {t[0].left, t[1].left});
            }

            if (t[0].right == null) {
                t[0].right = t[1].right;
            } else {
                stack.push(new TreeNode[] {t[0].right, t[1].right});
            }
        }
        return t1;
    }

    private void treeInitialize1(TreeNode root) {
        // depth = 1
        root.val = 1;

        // depth = 2
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);

        // depth = 3
        root.left.left = new TreeNode(5);
    }

    private void treeInitialize2(TreeNode root) {
        // depth = 1
        root.val = 2;

        // depth = 2
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        // depth = 3
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
    }

}
