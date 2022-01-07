package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的中序遍历
 * <P>https://leetcode-cn.com/problems/binary-tree-inorder-traversal/</P>
 *
 * @author echofzoe
 * @since unknown
 * @updated 2021.7.14
 */
public class Lc_94_二叉树的中序遍历 {

    public static void main(String[] args) {
        Lc_94_二叉树的中序遍历 lc = new Lc_94_二叉树的中序遍历();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);

        System.out.println("给定一个二叉树的根节点 root ，返回它的 中序 遍历。");
        System.out.println("输入：root = " + BinaryTreeSerialize.serialize(root));
        System.out.println("输出：" + lc.inorderTraversalIteration(root));  // [1,3,2]
    }

    List<Integer> res = new LinkedList<>();

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        if (root == null) return res;

        inorderTraversalRecursive(root.left);
        res.add(root.val);
        inorderTraversalRecursive(root.right);

        return res;
    }

    // 迭代 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public List<Integer> inorderTraversalIteration(TreeNode root) {
        if (root == null) return res;

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode tmp = root;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.offerLast(root);
                root = root.left;
            }

            root = stack.pollLast();
            res.add(root.val);
            root = root.right;
        }

        root = tmp;  // 还原
        return res;
    }

}
