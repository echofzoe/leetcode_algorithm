package algorithm.leetcode.tree;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;

/**
 * 二叉搜索树的最近公共祖先
 * <P>https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/</P>
 *
 * @author echofzoe
 * @since 2021.6.7
 */
public class Lc_235_二叉搜索树的最近公共祖先 {

    public static void main(String[] args) {
        Lc_235_二叉搜索树的最近公共祖先 lc = new Lc_235_二叉搜索树的最近公共祖先();

        TreeNode root = new TreeNode();
        lc.treeInitialize(root);  // [6,2,8,0,4,7,9,null,null,3,5]
        TreeNode p = new TreeNode(2), q = new TreeNode(4);

        System.out.println("给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。\n" +
                "百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。\n");
        System.out.println("输入：root = " + BinaryTreeSerialize.serialize(root) + ", p = " + p.val + ", q = " + q.val);
        System.out.println("输出：" + lc.lowestCommonAncestor(root, p, q).val);
    }

    // 模拟 - 时间复杂度 O(N) 其中N是给定的二叉搜索树中的节点个数 - 空间复杂度 O(1)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = root;

        while (true) {
            if (res.val > p.val && res.val > q.val) res = res.left;
            else if (res.val < p.val && res.val < q.val) res = res.right;
            else break;
        }

        return res;
    }

    private void treeInitialize(TreeNode root) {
        // depth = 1
        root.val = 6;

        // depth = 2
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);

        // depth = 3
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        // depth = 4
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
    }

}
