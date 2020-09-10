package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.*;

public class jianzhi_offer_32_II_从上到下打印二叉树 {

    // 从上到下打印二叉树
    // https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/

    public static void main(String[] args) {
        jianzhi_offer_32_II_从上到下打印二叉树 lc = new jianzhi_offer_32_II_从上到下打印二叉树();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);

        List<List<Integer>> res = lc.levelOrder(root);
        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(root) + " 从上到下的打印结果为：" + Arrays.toString(new List[]{res}));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>() {{
            add(root);
        }};

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new LinkedList<>();

            while (size > 0) {
                TreeNode curr = queue.poll();
                list.add(curr.val);

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);

                size--;
            }

            res.add(list);
        }

        return res;
    }

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
