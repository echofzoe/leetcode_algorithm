package algorithm.剑指offer.tree;

import java.util.Arrays;

/**
 * 二叉搜索树的后序遍历序列
 * <P>https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/</P>
 *
 * @author echofzoe
 * @since unknown
 * @updated 2021.7.15
 */
public class Jzo_33_二叉搜索树的后序遍历序列 {

    public static void main(String[] args) {
        Jzo_33_二叉搜索树的后序遍历序列 lc = new Jzo_33_二叉搜索树的后序遍历序列();

        int[] postorder = new int[] {1,6,3,2,5};

        System.out.println("输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。");
        System.out.println("输入：postorder = " + Arrays.toString(postorder));
        System.out.println("输出：" + lc.verifyPostorder(postorder));
    }

    // 递归 - 时间复杂度 O(N^2) - 空间复杂度 O(N) 最差情况下，树退化成链表，递归深度达到N
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    private boolean recur(int[] postorder, int i, int j) {
        if (i >= j) return true;

        int cur = i;
        while (postorder[cur] < postorder[j]) cur++;
        int leftSubtreeMaxIndex = cur - 1;  // 确定左子树的索引右边界

        while (postorder[cur] > postorder[j]) cur++;
        int rightSubtreeMaxIndex = cur - 1;  // 确定右子树的索引右边界

        return rightSubtreeMaxIndex + 1 == j && recur(postorder, i, leftSubtreeMaxIndex)
                && recur(postorder, leftSubtreeMaxIndex + 1, rightSubtreeMaxIndex);
    }

}
