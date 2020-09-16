package algorithm.leetcode.tree;

import algorithm.leetcode.utils.TreeNode;

public class Jzo_26_树的子结构 {

    // 树的子结构
    // https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
    public static void main(String[] args) {
        Jzo_26_树的子结构 lc = new Jzo_26_树的子结构();

        // 给定的树 A
        TreeNode A = new TreeNode(0);
        lc.treeInitialize(A);

        // 给定的树 B
        TreeNode B = new TreeNode(4);
        B.left = new TreeNode(1);

        System.out.println("树 B 是树 A 的子结构吗？ " + lc.isSubStructure(A, B));
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;    // 约定空树不是任意一个树的子结构

        if (A.val == B.val && recur(A.left, B.left) && recur(A.right, B.right)) return true;

        return isSubStructure(A.left, B) || isSubStructure(A.right, B);    // 继续遍历树 A, 同时与树 B 进行比较
    }

    private boolean recur(TreeNode r1, TreeNode r2) {
        if (r2 == null) return true;
        if (r1 == null) return false;    // r2 非空，r1 为空

        if (r1.val == r2.val) {
            return recur(r1.left, r2.left) && recur(r1.right, r2.right);    // 继续遍历比较树 A, B 的子结构
        } else {
            return false;
        }
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 3;

        // depth = 2
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);

        // depth = 3
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
    }
}
