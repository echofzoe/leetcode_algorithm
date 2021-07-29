package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 路径总和
 * <P>https://leetcode-cn.com/problems/path-sum/</P>
 *
 * @author echofzoe
 * @since 2021.7.29
 */
public class Lc_112_路径总和 {

    public static void main(String[] args) {
        Lc_112_路径总和 lc = new Lc_112_路径总和();

        TreeNode root = new TreeNode();
        lc.treeInitialize(root);  // [5,4,8,11,null,13,4,7,2,null,null,null,1]
        int targetSum = 22;
        
        System.out.println("给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。\n" +
                "叶子节点 是指没有子节点的节点。\n");
        System.out.println("输入：root = " + BinaryTreeSerialize.serialize(root) + ", targetSum = " + targetSum);
        System.out.println("输出：" + lc.hasPathSum(root, targetSum));
    }

    // dfs - 时间复杂度 O(N) - 空间复杂度 O(N) 取决于树高
    private boolean res;
    public boolean hasPathSum(TreeNode root, int targetSum) {
        res = false;
        if (root == null) return res;

        dfs(root, targetSum);

        return res;
    }

    private void dfs(TreeNode node, int sum) {
        sum -= node.val;
        if (node.left == null && node.right == null && sum == 0) {
            res = true;
            return;
        }

        if (node.left != null) dfs(node.left, sum);
        if (node.right != null) dfs(node.right, sum);
    }
    
    // bfs - 时间复杂度 O(N) - 空间复杂度 O(N)
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) return false;

        Queue<TreeNode> nodeQ = new LinkedList<>();
        nodeQ.offer(root);
        Queue<Integer> sumQ = new LinkedList<>();
        sumQ.offer(0);

        while (!nodeQ.isEmpty()) {
            TreeNode node = nodeQ.poll();
            int curSum = sumQ.poll() + node.val;

            if (node.left == null && node.right == null && curSum == targetSum) {
                return true;
            }

            if (node.left != null) {
                nodeQ.offer(node.left);
                sumQ.offer(curSum);
            }

            if (node.right != null) {
                nodeQ.offer(node.right);
                sumQ.offer(curSum);
            }
        }

        return false;
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 5;

        // depth = 2
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);

        // depth = 3
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);

        // depth = 4
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
    }

}
