package algorithm.leetcode.dpAndGreedy.打家劫舍;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

/**
 * 打家劫舍 III
 * https://leetcode-cn.com/problems/house-robber-iii/
 *
 * @author echofzoe
 * @since 2021.4.15
 */
public class Lc_337_打家劫舍_III {

    public static void main(String[] args) {
        Lc_337_打家劫舍_III lc = new Lc_337_打家劫舍_III();

        TreeNode root = new TreeNode();
        lc.treeInitialized(root);  // [3,4,5,1,3,null,1]

        System.out.println("窃贼在二叉树状房屋排列" + BinaryTreeSerialize.serialize(root) + "中按照题意能够窃取的高大金额为" + lc.rob(root));
    }

    // 树形DP - 时间复杂度 O(N) - 空间复杂度 O(N) 为递归栈的额外开销
    public int rob(TreeNode root) {
        int[] res = dfs(root);

        return Math.max(res[0], res[1]);
    }

    private int[] dfs(TreeNode node) {
        if (node == null) return new int[] {0, 0};

        // 通过树的后序遍历往回推父节点的值
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int[] dp = new int[2];

        // dp[0] - 在不偷取当前节点的情况下，以当前节点为根节点的子树能够偷取的最大价值
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // dp[1] - 在偷取当前节点的情况下，以当前节点为根节点的子树能够偷取的最大价值
        dp[1] = left[0] + right[0] + node.val;

        return dp;
    }

    private void treeInitialized(TreeNode root) {
        // depth = 1
        root.val = 3;

        // depth = 2
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);

        // depth = 3
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right.right = new TreeNode(1);
    }

}
