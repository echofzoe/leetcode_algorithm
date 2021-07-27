package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

/**
 * 二叉树中第二小的节点
 * <P>https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/</P>
 *
 * @author echofzoe
 * @since 2021.7.27
 */
public class Lc_671_二叉树中第二小的节点 {

    public static void main(String[] args) {
        Lc_671_二叉树中第二小的节点 lc = new Lc_671_二叉树中第二小的节点();

        TreeNode root = new TreeNode();
        lc.treeInitialize(root);  // [2,2,5,null,null,5,7]

        System.out.println("给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。\n" +
                "更正式地说，root.val = min(root.left.val, root.right.val) 总成立。\n" +
                "给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。\n");
        System.out.println("输入：root = " + BinaryTreeSerialize.serialize(root));
        System.out.println("输出：" + lc.findSecondMinimumValue(root));  // 5
    }

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N) 最坏情况下，遍历完所有子节点
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null || root.right == null) return -1;

        int left = root.val == root.left.val ? findSecondMinimumValue(root.left) : root.left.val;
        int right = root.val == root.right.val ? findSecondMinimumValue(root.right) : root.right.val;

        int res = Math.min(left, right);
        return res > 0 ? res : Math.max(left, right);
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 2;

        // depth = 2
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);

        // depth = 3
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
    }

}
