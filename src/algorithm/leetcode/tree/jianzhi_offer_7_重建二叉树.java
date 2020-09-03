package algorithm.leetcode.tree;

import algorithm.leetcode.utils.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class jianzhi_offer_7_重建二叉树 {

    // 重建二叉树
    // https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/

    public static void main(String[] args) {

        jianzhi_offer_7_重建二叉树 lc = new jianzhi_offer_7_重建二叉树();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = lc.buildTree(preorder, inorder);

        if (lc.checkAnswer(preorder, root)) {
            System.out.println("正确");
        } else {
            System.out.println("错误");
        }

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder == null || preorder.length == 0) return null;

        Map<Integer, Integer> indexMap = new HashMap<>();
        int length = preorder.length;

        // 为中序遍历数组建立哈希表，之后：
        // - 1. 通过前序遍历数组确定根节点
        // - 2. 再根据根节点索引在中序遍历数组中确定根节点的左右子树各自的全部节点数量
        // - 3. 上一步确定数量后，对根节点的左右子树递归调用建树方法
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }

        TreeNode root = buildTree(preorder, 0, length - 1, inorder, 0, length - 1, indexMap);
        return root;

    }

    private TreeNode buildTree(int[] preorder, int preorderStart, int preorderEnd, int[] inorder, int inorderStart, int inorderEnd, Map<Integer, Integer> indexMap) {

        if (preorderStart > preorderEnd) return null;    // 当前二叉树中没有节点

        int rootVal = preorder[preorderStart];
        TreeNode root = new TreeNode(rootVal);

        if (preorderStart == preorderEnd) return root;    // 当前二叉树中只有一个节点

        // 当前二叉树中有多个节点
        else {
            int rootIndex = indexMap.get(rootVal);    // 在中序遍历中得到根节点的位置，从而得到左子树和右子树各自的下标范围和节点数量
            int leftNodes = rootIndex - inorderStart;    // 左子树全部节点数量
            int rightNodes = inorderEnd - rootIndex;    // 右子树全部节点数量

            // 递归遍历建立根节点的左子树
            // - 左子树节点区间为前序遍历数组的 [起始索引（根节点索引） + 1, 起始索引 + 左子树全部节点数量]
            // - 左子树节点区间为中序遍历数组的 [起始索引, 根节点索引 - 1]
            TreeNode leftSubTree = buildTree(preorder, preorderStart + 1, preorderStart + leftNodes, inorder, inorderStart, rootIndex - 1, indexMap);

            // 递归遍历建立根节点的右子树
            // - 右子树节点区间为前序遍历数组的 [末尾索引 - 右子树全部节点数量, 末尾索引]
            // - 右子树节点区间为中序遍历数组的 [根节点索引 + 1, 末尾索引]
            TreeNode rightSubTree = buildTree(preorder, preorderEnd - rightNodes + 1, preorderEnd, inorder, rootIndex + 1, inorderEnd, indexMap);

            root.left = leftSubTree;
            root.right = rightSubTree;
            return root;
        }

    }

    // 检查答案
    private boolean checkAnswer(int[] preorder, TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int[] res = new int[preorder.length];
        int index = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {

            int size = queue.size();
            while (size > 0) {
                TreeNode curr = queue.poll();
                res[index] = curr.val;

                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }

                size--;
                index++;
            }
        }

        for (int i = 0; i < preorder.length; i++) {
            if (res[i] != preorder[i]) {
                return false;
            }
        }

        return true;
    }

}
