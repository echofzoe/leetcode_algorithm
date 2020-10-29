package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Lc_129_求根到叶子节点数字之和 {

    // 求根到叶子节点数字之和
    // https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/

    public static void main(String[] args) {
        Lc_129_求根到叶子节点数字之和 lc = new Lc_129_求根到叶子节点数字之和();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);    // [4,9,0,5,1]

        long l1 = System.currentTimeMillis();
        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(root, 3) + " 中，从根到叶子节点路径 1->2->3 代表数字 123，则根到叶子结点的数字之和为 " + lc.sumNumbers_BFS(root));
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }

    // DFS - 时间复杂度 O(N) - 空间复杂度 O(N) 主要为递归栈开销 递归栈的深度等于二叉树的高度
    public int sumNumbers_DFS(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int i) {
        if (root == null) return 0;

        int temp = i * 10 + root.val;
        if (root.left == null && root.right == null) return temp;

        return dfs(root.left, temp) + dfs(root.right, temp);
    }

    // BFS - 时间复杂度 O(N) - 空间复杂度 O(N) 二叉树节点个数决定的双队列开销 不会超过N
    public int sumNumbers_BFS(TreeNode root) {
        if (root == null) return 0;

        int res = 0;

        // 维护两个队列，层次遍历
        Queue<TreeNode> nodeQueue = new LinkedList<>();    // 节点队列
        Queue<Integer> numQueue = new LinkedList<>();    // 数字队列

        nodeQueue.offer(root);
        numQueue.offer(root.val);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int num = numQueue.poll();
            if (node.left == null && node.right == null) {
                res += num;
            } else {
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    numQueue.offer(num * 10 + node.left.val);
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    numQueue.offer(num * 10 + node.right.val);
                }
            }
        }

        return res;
    }
    
    

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 4;

        // depth = 2
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);

        // depth = 3
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);
    }
}
