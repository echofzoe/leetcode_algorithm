package algorithm.剑指offer.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

import java.util.*;

/**
 * 序列化二叉树
 * <P>https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/</P>
 *
 * @author echofzoe
 * @since unknown
 * @date 2021.6.30
 */
public class Jzo_37_序列化二叉树 {

    public static void main(String[] args) {
        Jzo_37_序列化二叉树 lc = new Jzo_37_序列化二叉树();
        TreeNode root = new TreeNode(0);
        lc.treeInitialize(root);  // [1,2,3,null,null,4,5]

        System.out.println("序列化：" + lc.serialize(root));
        System.out.println("反序列化：" + BinaryTreeSerialize.serialize(lc.deserialize(lc.serialize(root))));
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "[]";

//        return BinaryTreeSerialize.serialize(root);
        return fromBinaryTreeToString(root);
    }

    private String fromBinaryTreeToString(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> d = new LinkedList<>() {{
            add(root);
        }};

        while (!d.isEmpty()) {
            int size = d.size();

            while (size-- > 0) {
                TreeNode cur = d.poll();

                if (cur != null) {
                    res.add(cur.val);
                    d.add(cur.left);
                    d.add(cur.right);
                } else {
                    res.add(null);
                }
            }
        }

        while (res.get(res.size() - 1) == null) res.remove(res.size() - 1);

        StringBuilder sb = new StringBuilder("[");
        for (Integer x : res) sb.append(x == null ? "null," : x + ",");

        return sb.deleteCharAt(sb.length() - 1).append("]").toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 2) return null;
        String[] vals = data.substring(1, data.length() - 1).split(",");

        int n = vals.length;

        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Deque<TreeNode> d = new LinkedList<>() {{
            add(root);
        }};

        int idx = 1;
        while (!d.isEmpty() && idx < n) {
            int size = d.size();

            while (size-- > 0 && idx < n) {
                TreeNode cur = d.poll();
                if (cur == null) continue;

                d.add(cur.left = vals[idx].equals("null") ? null : new TreeNode(Integer.parseInt(vals[idx])));
                idx++;
                if (idx == n) break;

                d.add(cur.right = vals[idx].equals("null") ? null : new TreeNode(Integer.parseInt(vals[idx])));
                idx++;
            }
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
        root.left.left = null;
        root.left.right = null;
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
    }

}
