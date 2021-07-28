package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.*;

/**
 * 二叉树中所有距离为 K 的结点
 * <P>https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree/</P>
 *
 * @author echofzoe
 * @since 2021.7.28
 */
public class Lc_863_二叉树中所有距离为K的结点 {

    public static void main(String[] args) {
        Lc_863_二叉树中所有距离为K的结点 lc = new Lc_863_二叉树中所有距离为K的结点();

        TreeNode root = new TreeNode();
        lc.treeInitialize(root);  // [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode target = lc.findTarget(root, 5);
        int K = 2;

        System.out.println("给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。\n" +
                "\n" +
                "返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。\n");
        System.out.println("输入：root = " + BinaryTreeSerialize.serialize(root) + ", target = " + target + ", K = " + K);
        System.out.println("输出：" + lc.distanceK(root, target, K));  // [7,4,1]
    }

    // dfs - 时间复杂度 O(N) - 空间复杂度 O(N)
    private Map<TreeNode, TreeNode> parents;
    private Set<TreeNode> set;
    private int k;
    private List<Integer> res;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        parents = new HashMap<>();
        set = new HashSet<>();
        this.k = k;
        res = new ArrayList<>();

        if (root == null) return res;

        findParents(root);

        findRes(target, 0);

        return res;
    }

    private void findParents(TreeNode node) {
        if (node.left != null) {
            parents.put(node.left, node);
            findParents(node.left);
        }

        if (node.right != null) {
            parents.put(node.right, node);
            findParents(node.right);
        }
    }

    private void findRes(TreeNode node, int depth) {
        if (node == null) return;

        set.add(node);

        if (depth == k) {
            res.add(node.val);
            return;
        }

        // dfs 子节点
        if (!set.contains(node.left)) {
            findRes(node.left, depth + 1);
        }

        if (!set.contains(node.right)) {
            findRes(node.right, depth + 1);
        }

        // dfs 父节点
        if (!set.contains(parents.get(node))) {
            findRes(parents.get(node), depth + 1);
        }
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 3;

        // depth = 2
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        // depth = 3
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        // depth = 4
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
    }

    private TreeNode findTarget(TreeNode root, int v) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                TreeNode cur = q.poll();

                if (cur.val == v) return cur;

                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
        }

        return new TreeNode(-1);
    }

}
