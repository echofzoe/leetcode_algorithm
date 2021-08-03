package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.*;

public class Lc_987_二叉树的垂序遍历 {

    public static void main(String[] args) {
        Lc_987_二叉树的垂序遍历 lc = new Lc_987_二叉树的垂序遍历();

        TreeNode root = new TreeNode();
        lc.treeInitialize(root);  // [3,1,4,0,2,2]

        System.out.println("给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。\n" +
                "对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。\n" +
                "二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则按结点的值从小到大进行排序。\n" +
                "返回二叉树的 垂序遍历 序列。\n");
        System.out.println("输入：root = " + BinaryTreeSerialize.serialize(root));
        System.out.println("输出：" + lc.verticalTraversal(root));  // [[0],[1],[3,2,2],[4]]
    }

    // BFS + 排序 - 时间复杂度 O(NlogN) - 空间复杂度 O(N)
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        // <col, {[row, val], ...}>
        Map<Integer, List<int[]>> m = new TreeMap<>(Comparator.comparingInt(a -> a));

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        Queue<int[]> pos = new LinkedList<>();  // <[row, col]>
        pos.offer(new int[]{0, 0});

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                TreeNode cur = q.poll();
                int[] p = pos.poll();
                int row = p[0], col = p[1];

                m.computeIfAbsent(col, key -> new ArrayList<>()).add(new int[]{row, cur.val});

                if (cur.left != null) {
                    q.offer(cur.left);
                    pos.offer(new int[]{p[0] + 1, p[1] - 1});
                }

                if (cur.right != null) {
                    q.offer(cur.right);
                    pos.offer(new int[]{p[0] + 1, p[1] + 1});
                }
            }
        }

        Set<Integer> keySet = m.keySet();
        for (int key : keySet) {
            List<int[]> cur = m.get(key);
            cur.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

            List<Integer> tmp = new ArrayList<>();
            for (int[] arr : cur) {
                tmp.add(arr[1]);
            }

            res.add(tmp);
        }

        return res;
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 3;

        // depth = 2
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);

        // depth = 3
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
    }

}
