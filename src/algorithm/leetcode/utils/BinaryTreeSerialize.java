package algorithm.leetcode.utils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeSerialize {

    /*
     * 针对 TreeNode 节点
     */

    public static String serialize(TreeNode root) {
        if (root == null) return "[]";

        StringBuffer buf = new StringBuffer("[");
        Queue<TreeNode> queue = new LinkedList<>() {{
            offer(root);
        }};

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();

            if (curr != null) {
                buf.append(curr.val).append(",");
                queue.offer(curr.left);
                queue.offer(curr.right);
            } else {
                buf.append("null,");
            }
        }

        return buf.deleteCharAt(buf.length() - 1).append(']').toString();
    }

    public static String serialize(TreeNode root, int levels) {
        if (root == null) return "[]";

        StringBuffer buf = new StringBuffer("[");
        Queue<TreeNode> queue = new LinkedList<>() {{
            offer(root);
        }};

        while (!queue.isEmpty() && levels > 0) {
            int size = queue.size();

            while (size > 0) {
                TreeNode curr = queue.poll();

                if (curr != null) {
                    buf.append(curr.val).append(",");
                    queue.offer(curr.left);
                    queue.offer(curr.right);
                } else {
                    buf.append("null,");
                }

                size--;
            }

            levels--;
        }

        return buf.deleteCharAt(buf.length() - 1).append(']').toString();
    }



    /*
     * 针对 Node 节点
     */

    public static String serialize(Node root) {
        if (root == null) return "[]";

        StringBuffer buf = new StringBuffer("[");
        Queue<Node> queue = new LinkedList<>() {{
            offer(root);
        }};

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr != null) {
                buf.append(curr.val).append(",");
                queue.offer(curr.left);
                queue.offer(curr.right);
            } else {
                buf.append("null,");
            }
        }

        return buf.deleteCharAt(buf.length() - 1).append(']').toString();
    }

    public static String serialize(Node root, int levels) {
        if (root == null) return "[]";

        StringBuffer buf = new StringBuffer("[");
        Queue<Node> queue = new LinkedList<>() {{
            offer(root);
        }};

        while (!queue.isEmpty() && levels > 0) {
            int size = queue.size();

            while (size > 0) {
                Node curr = queue.poll();

                if (curr != null) {
                    buf.append(curr.val).append(",");
                    queue.offer(curr.left);
                    queue.offer(curr.right);
                } else {
                    buf.append("null,");
                }

                size--;
            }

            levels--;
        }

        return buf.deleteCharAt(buf.length() - 1).append(']').toString();
    }

    public static String nextNodeSerialize(Node root, int levels) {
        if (root == null) return "[]";

        StringBuffer buf = new StringBuffer("[");
        Queue<Node> queue = new LinkedList<>() {{
            offer(root);
        }};

        while (!queue.isEmpty() && levels > 0) {
            int size = queue.size();

            while (size > 0) {
                Node curr = queue.poll();
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);

                if (curr.next == null) {
                    buf.append(curr.val).append(",#,");
                } else {
                    buf.append(curr.val).append(",");
                }

                size--;
            }

            levels--;
        }

        return buf.deleteCharAt(buf.length() - 1).append(']').toString();
    }

}
