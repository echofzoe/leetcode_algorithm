package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的前序遍历
 * <P>https://leetcode-cn.com/problems/binary-tree-preorder-traversal/</P>
 *
 * @author echofzoe
 * @since unknown
 * @updated 2021.7.14
 */
public class Lc_144_二叉树的前序遍历 {

    public static void main(String[] args) {
        Lc_144_二叉树的前序遍历 lc = new Lc_144_二叉树的前序遍历();
        
        TreeNode root = new TreeNode(1);
        lc.treeInitialize(root);  // [1,null,2,3]

        System.out.println("给你二叉树的根节点 root ，返回它节点值的 前序 遍历。");
        System.out.println("输入：root = " + BinaryTreeSerialize.serialize(root));
        System.out.println("输出：" + lc.preorderTraversalIteration(root));  // [1,2,3]
    }

    List<Integer> res = new LinkedList<>();

    // 递归 - 时间复杂度 O(N) 遍历所有节点 - 空间复杂度 O(N) 为递归栈的开销,平均情况下为 O(logN),最坏情况下树呈链状为 O(N)
    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        if (root == null) return res;

        res.add(root.val);
        preorderTraversalRecursive(root.left);
        preorderTraversalRecursive(root.right);

        return res;
    }

    // 迭代 - 时间复杂度 O(N) - 空间复杂度 O(N) 为迭代过程中显示栈的开销
    public List<Integer> preorderTraversalIteration(TreeNode root) {
        if (root == null) return res;

        Deque<TreeNode> stack = new LinkedList<>() {{
            offerLast(root);
        }};

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollLast();

            res.add(cur.val);

            if (cur.right != null) stack.offerLast(cur.right);
            if (cur.left != null) stack.offerLast(cur.left);
        }

        return res;
    }

    private void treeInitialize(TreeNode root) {
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
    }
}
