package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.*;

/**
 * 路径总和 II
 * <P>https://leetcode-cn.com/problems/path-sum-ii/</P>
 *
 * @author echofzoe
 * @since 2021.7.29
 */
public class Lc_113_路径总和_II {

    public static void main(String[] args) {
        Lc_113_路径总和_II lc = new Lc_113_路径总和_II();

        TreeNode root = new TreeNode();
        lc.treeInitialize(root);  // [5,4,8,11,null,13,4,7,2,null,null,5,1]
        int targetSum = 22;

        System.out.println("给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。\n" +
                "叶子节点 是指没有子节点的节点。\n");
        System.out.println("输入：root = " + BinaryTreeSerialize.serialize(root) + ", targetSum = " + targetSum);
        System.out.println("输出：" + lc.pathSum(root, targetSum));
    }

    // dfs - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    private List<List<Integer>> res;
    private List<Integer> tmp;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        if (root == null) return res;

        tmp = new ArrayList<>();

        dfs(root, sum);

        return res;
    }

    private void dfs(TreeNode node, int sum) {
        tmp.add(node.val);
        sum -= node.val;

        if (node.left == null && node.right == null && sum == 0) {
            res.add(new ArrayList<>(tmp));
        }

        if (node.left != null) dfs(node.left, sum);
        if (node.right != null) dfs(node.right, sum);

        // backtrace
        tmp.remove(tmp.size() - 1);
    }

    // bfs - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    private Map<TreeNode, TreeNode> parents;

    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        res = new ArrayList<>();
        if (root == null) return res;

        parents = new HashMap<>();

        Queue<TreeNode> nodeQ = new LinkedList<>();
        nodeQ.offer(root);
        Queue<Integer> sumQ = new LinkedList<>();
        sumQ.offer(0);

        while (!nodeQ.isEmpty()) {
            TreeNode node = nodeQ.poll();
            int curSum = sumQ.poll() + node.val;

            if (node.left == null && node.right == null && curSum == sum) {
                res.add(getPath(node));
            }

            if (node.left != null) {
                nodeQ.offer(node.left);
                sumQ.offer(curSum);
                parents.put(node.left, node);
            }

            if (node.right != null) {
                nodeQ.offer(node.right);
                sumQ.offer(curSum);
                parents.put(node.right, node);
            }
        }

        return res;
    }

    private List<Integer> getPath(TreeNode node) {
        List<Integer> ans = new LinkedList<>();

        while (node != null) {
            ans.add(node.val);
            node = parents.get(node);
        }

        Collections.reverse(ans);

        return ans;
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
