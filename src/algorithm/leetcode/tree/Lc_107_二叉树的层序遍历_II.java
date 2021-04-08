package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.*;

/**
 * 二叉树的层序遍历 II
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 *
 * @author echofzoe
 * @since 2021.4.8
 */
public class Lc_107_二叉树的层序遍历_II {

    public static void main(String[] args) {
        Lc_107_二叉树的层序遍历_II lc = new Lc_107_二叉树的层序遍历_II();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);

        System.out.println("二叉树" + BinaryTreeSerialize.serialize(root, 3) + "的自底向上的层序遍历的结果是" + lc.levelOrderBottom1(root));
    }

    // BFS - 时间复杂度 O(N) - 空间复杂度 O(N)
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;

        // 后面要用到头插法，所以这里使用链表的实现方式
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>();

            while (size > 0) {
                TreeNode cur = queue.poll();
                assert cur != null;
                tmp.add(cur.val);

                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);

                size--;
            }

            res.add(0, tmp);
        }

        return res;
    }

    // DFS - 时间复杂度 O(N) - 空间复杂度 O(N)
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        dfs(root, res, 1);

        Collections.reverse(res);
        return res;
    }

    private void dfs(TreeNode node, List<List<Integer>> res, int idx) {
        if (node == null) return;

        if (idx > res.size()) res.add(new ArrayList<>());

        // 将当前层的节点直接加入当前层链表的结尾
        res.get(idx - 1).add(node.val);
        // 继续遍历当前层的这个节点的左右子节点，同时将层高+1
        dfs(node.left, res, idx + 1);
        dfs(node.right, res, idx + 1);
    }

    // 二叉树初始化
    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 3;

        // depth = 2
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        // depth = 3
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
    }

}
