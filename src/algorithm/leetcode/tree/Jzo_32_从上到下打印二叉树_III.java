package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.*;

public class Jzo_32_从上到下打印二叉树_III {

    // 从上到下打印二叉树
    // https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/

    public static void main(String[] args) {
        Jzo_32_从上到下打印二叉树_III lc = new Jzo_32_从上到下打印二叉树_III();
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
        boolean flag = false;    // false = 从左往右打印; true = 从右往左打印

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new LinkedList<>();

            while (size > 0) {
                TreeNode curr = queue.poll();

                if (flag) {
                    list.add(0, curr.val);
                } else {
                    list.add(curr.val);
                }

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);

                size--;
            }

            flag = !flag;
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
