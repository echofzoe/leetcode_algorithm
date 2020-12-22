package algorithm.leetcode.tree;

import algorithm.leetcode.utils.TreeNode;

import java.util.*;

public class lc_103_二叉树的锯齿形层次遍历 {

    // 二叉树的锯齿形层次遍历
    // https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/

    public static void main(String[] args) {
        lc_103_二叉树的锯齿形层次遍历 lc = new lc_103_二叉树的锯齿形层次遍历();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);

        if (lc.CompareAnswer(lc.correct, lc.zigzagLevelOrder(root))) {
            System.out.println("答案正确");
        } else {
            System.out.println("答案错误");
        }
    }
    
    private List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> deque = new LinkedList<>() {{
            offerLast(root);
        }};

        boolean flag = true;

        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> tmp = new ArrayList<>();

            while (size > 0) {
                TreeNode cur = deque.pollFirst();

                if (flag) tmp.add(cur.val);
                else tmp.add(0, cur.val);

                if (cur.left != null) deque.offerLast(cur.left);
                if (cur.right != null) deque.offerLast(cur.right);

                size--;
            }

            flag = !flag;
            res.add(tmp);
        }

        return res;
    }

    // 正确答案
    List<List<Integer>> correct = new ArrayList<>() {{
        add(new ArrayList<>() {{
            add(3);
        }});
        add(new ArrayList<>() {{
            add(20);
            add(9);
        }});
        add(new ArrayList<>() {{
            add(15);
            add(7);
        }});
    }};

    // 比较结果是否正确
    private boolean CompareAnswer(List<List<Integer>> correct, List<List<Integer>> yourAnswer) {
        if (yourAnswer.size() == 0) return false;

        for (int i = 0; i < correct.size(); i++) {
            for (int j = 0; j < correct.get(i).size(); j++) {
                if (!correct.get(i).get(j).equals(yourAnswer.get(i).get(j))) {
                    return false;
                }
            }
        }

        return true;
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
