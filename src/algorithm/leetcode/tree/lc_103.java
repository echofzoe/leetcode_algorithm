package algorithm.leetcode.tree;

import algorithm.leetcode.utils.TreeNode;
import com.sun.source.tree.Tree;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class lc_103 {

    // 二叉树的锯齿形层次遍历
    // https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/

    // 正确答案
    List<List<Integer>> correct = new ArrayList<>() {
        {
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
        }
    };

    public static void main(String[] args) {
        lc_103 lc = new lc_103();
        TreeNode root = new TreeNode(0);
        lc.TreeInitialize(root);

        if (lc.CompareAnswer(lc.correct, lc.zigzagLevelOrder(root))) {
            System.out.println("答案正确");
        } else {
            System.out.println("答案错误");
        }
    }


    private List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> list;
        TreeNode curr;

        if (root == null) return answer;

        boolean flag = true;    // 1 表奇数层正向保存节点，0 表偶数层反向保存节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            list = new ArrayList<>();
            while (size > 0) {
                curr = queue.poll();

                if (flag) {
                    list.add(curr.val);    // 尾插
                } else {
                    list.add(0, curr.val);    // 头插
                }

                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }

                size--;
            }
            flag = !flag;
            answer.add(list);
        }
        return answer;
    }

    // 比较结果是否正确
    private boolean CompareAnswer(List<List<Integer>> correct, @NotNull List<List<Integer>> yourAnswer) {
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
    private void TreeInitialize(TreeNode root) {
        // depth = 1
        TreeNode head = root;
        head.val = 3;

        // depth = 2
        head.left = new TreeNode(9);
        head.right = new TreeNode(20);

        // depth = 3
        TreeNode headR = head.right;
        headR.left = new TreeNode(15);
        headR.right = new TreeNode(7);

        // free
        head = null;
        headR = null;
    }

}
