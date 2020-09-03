package algorithm.leetcode.tree;

import algorithm.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class jianzhi_offer_37_序列化二叉树 {

    // 序列化二叉树
    // https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/

    public static void main(String[] args) {
        jianzhi_offer_37_序列化二叉树 lc = new jianzhi_offer_37_序列化二叉树();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);

        System.out.println("序列化：" + lc.serialize(root));
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if (root == null) return "[]";

        return fromBinaryTreeToString(root);

    }

    private String fromBinaryTreeToString(TreeNode root) {

        StringBuffer buf = new StringBuffer("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

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

        buf.deleteCharAt(buf.length() - 1);
        buf.append(']');

        return buf.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 2) return null;

        String[] vals = data.substring(1, data.length() - 1).split(",");

        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<>() {{
            add(root);
        }};

        int i = 1;

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();

            if (!vals[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.offer(curr.left);
            }else {
                curr.left = null;
            }
            i++;

            if (!vals[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.offer(curr.right);
            }else {
                curr.right = null;
            }
            i++;
        }

        return root;
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 1;

        // depth = 2
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        // depth = 3
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
    }

}
