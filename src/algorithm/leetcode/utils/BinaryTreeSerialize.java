package algorithm.leetcode.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeSerialize {

    /**
     * 针对 TreeNode 节点
     */
    public static String serialize(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>() {{
            offer(root);
        }};

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                TreeNode cur = queue.poll();

                if (cur != null) {
                    res.add(cur.val);
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                } else {
                    res.add(null);
                }

                size--;
            }
        }

        while (res.get(res.size() - 1) == null) res.remove(res.size() - 1);

        return res.toString();
    }

    /**
     * 针对 Node 节点
     */
    public static String serialize(Node root) {
        List<Integer> res = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>() {{
            offer(root);
        }};

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur != null) {
                res.add(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                res.add(null);
            }
        }

        while (res.get(res.size() - 1) == null) res.remove(res.size() - 1);

        return res.toString();
    }

    public static String nextNodeSerialize(Node root) {
        List<String> res = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>() {{
            offer(root);
        }};

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                Node cur = queue.poll();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);

                res.add(String.valueOf(cur.val));
                if (cur.next == null) res.add("#");

                size--;
            }
        }

        return res.toString();
    }

}
