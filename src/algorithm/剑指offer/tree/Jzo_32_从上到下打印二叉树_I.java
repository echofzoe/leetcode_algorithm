package algorithm.剑指offer.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Jzo_32_从上到下打印二叉树_I {

    // 从上到下打印二叉树
    // https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/

    public static void main(String[] args) {
        Jzo_32_从上到下打印二叉树_I lc = new Jzo_32_从上到下打印二叉树_I();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);

        int[] res = lc.levelOrder(root);
        System.out.println("二叉树 " + BinaryTreeSerialize.serialize(root) + " 从上到下的打印结果为：" + Arrays.toString(res));
    }

    public int[] levelOrder(TreeNode root) {
        if (root == null) return null;

        ArrayList<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>() {{
            add(root);
        }};

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            ans.add(curr.val);

            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }

        int[] res = new int[ans.size()];
        int i = 0;
        for (Integer integer : ans) {
            res[i++] = integer;
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
