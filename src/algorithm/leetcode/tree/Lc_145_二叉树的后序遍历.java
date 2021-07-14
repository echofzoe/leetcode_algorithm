package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的后序遍历
 * <P>https://leetcode-cn.com/problems/binary-tree-postorder-traversal/</P>
 *
 * @author echofzoe
 * @since unknown
 * @updated 2021.7.14
 */
public class Lc_145_二叉树的后序遍历 {

    public static void main(String[] args) {
        Lc_145_二叉树的后序遍历 lc = new Lc_145_二叉树的后序遍历();
        
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        System.out.println("给定一个二叉树，返回它的 后序 遍历。");
        System.out.println("输入：root = " + BinaryTreeSerialize.serialize(root));
        System.out.println("输出：" + lc.postorderTraversalIteration(root));  // [3,2,1]
    }

    List<Integer> res = new LinkedList<>();

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        if (root == null) return res;

        postorderTraversalRecursive(root.left);
        postorderTraversalRecursive(root.right);
        res.add(root.val);

        return res;
    }

    // 迭代 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public List<Integer> postorderTraversalIteration(TreeNode root) {
        if (root == null) return res;

        Deque<TreeNode> stack = new LinkedList<>() {{
            offerLast(root);
        }};

        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();

            res.add(0, node.val);

            if (node.left != null) stack.offerLast(node.left);
            if (node.right != null) stack.offerLast(node.right);
        }

        return res;
    }
}
