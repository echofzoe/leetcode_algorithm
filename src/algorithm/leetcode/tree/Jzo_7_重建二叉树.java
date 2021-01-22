package algorithm.leetcode.tree;

import algorithm.leetcode.utils.TreeNode;
import com.sun.source.tree.Tree;

import java.util.*;

public class Jzo_7_重建二叉树 {

    // 重建二叉树
    // https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/

    public static void main(String[] args) {

        Jzo_7_重建二叉树 lc = new Jzo_7_重建二叉树();
        int[] preorder = {3, 9, 8, 12, 20, 15, 7};
        int[] inorder = {8, 9, 12, 3, 15, 20, 7};

        TreeNode root = lc.buildTree(preorder, inorder);

        if (lc.checkAnswer(preorder, root)) {
            System.out.println("正确");
        } else {
            System.out.println("错误");
        }

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder == null || preorder.length == 0) return null;

        Map<Integer, Integer> inorderMap = new HashMap<>();
        int n = inorder.length;

        // 为中序遍历数组建立哈希表，之后：
        // - 1. 通过前序遍历数组确定根节点
        // - 2. 再根据根节点索引在中序遍历数组中确定根节点的左右子树各自的全部节点数量
        // - 3. 上一步确定数量后，对根节点的左右子树递归调用建树方法
        for (int i = 0; i < n; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, n - 1, inorder, 0, n - 1, inorderMap);

    }

    private TreeNode buildTree(int[] preorder, int preorderStart, int preorderEnd, int[] inorder, int inorderStart, int inorderEnd, Map<Integer, Integer> inorderMap) {

        if (preorderStart > preorderEnd) return null;    // 当前二叉树中没有节点

        int rootVal = preorder[preorderStart];
        TreeNode root = new TreeNode(rootVal);

        if (preorderStart == preorderEnd) {
            // 当前二叉树中只有一个节点
            return root;
        } else {
            // 当前二叉树中有多个节点
            int rootIndex = inorderMap.get(rootVal);    // 在中序遍历中得到根节点的位置，从而得到左子树和右子树各自的下标范围和节点数量
            int leftNodes = rootIndex - inorderStart;    // 左子树全部节点数量
            int rightNodes = inorderEnd - rootIndex;    // 右子树全部节点数量

            // 递归遍历建立根节点的左子树
            // - 左子树节点区间为前序遍历数组的 [起始索引（根节点索引） + 1, 起始索引 + 左子树全部节点数量]
            // - 左子树节点区间为中序遍历数组的 [起始索引, 根节点索引 - 1]
            TreeNode leftSubtree = buildTree(preorder, preorderStart + 1, preorderStart + leftNodes, inorder, inorderStart, rootIndex - 1, inorderMap);

            // 递归遍历建立根节点的右子树
            // - 右子树节点区间为前序遍历数组的 [末尾索引 - 右子树全部节点数量, 末尾索引]
            // - 右子树节点区间为中序遍历数组的 [根节点索引 + 1, 末尾索引]
            TreeNode rightSubtree = buildTree(preorder, preorderEnd - rightNodes + 1, preorderEnd, inorder, rootIndex + 1, inorderEnd, inorderMap);

            root.left = leftSubtree;
            root.right = rightSubtree;
            return root;
        }

    }


    // 检查答案
    private boolean checkAnswer(int[] preorder, TreeNode root) {
        List<TreeNode> myAnswer = preorder(root);

        for (int i = 0; i < myAnswer.size(); i++) {
            if (myAnswer.get(i).val != preorder[i]) return false;
        }

        return true;
    }

    private List<TreeNode> preorder(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        if (root == null) return res;

        Deque<TreeNode> stack = new LinkedList<>() {{
            offerFirst(root);
        }};

        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            res.add(node);
            if (node.right != null) stack.offerFirst(node.right);
            if (node.left != null) stack.offerFirst(node.left);
        }

        return res;
    }

}
