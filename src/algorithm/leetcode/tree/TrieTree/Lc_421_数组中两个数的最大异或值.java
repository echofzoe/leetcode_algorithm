package algorithm.leetcode.tree.TrieTree;

import java.util.Arrays;

/**
 * 数组中两个数的最大异或值
 * <P>https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/</P>
 *
 * @author echofzoe
 * @since 2021.5.17
 */
public class Lc_421_数组中两个数的最大异或值 {

    public static void main(String[] args) {
        Lc_421_数组中两个数的最大异或值 lc = new Lc_421_数组中两个数的最大异或值();

        int[] nums = {3, 10, 5, 25, 2, 8};

        System.out.println("给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。\n" +
                "进阶：你可以在 O(n) 的时间解决这个问题吗");
        System.out.println("输入：" + Arrays.toString(nums));
        System.out.println("输出：" + lc.findMaximumXOR(nums));  // 28
    }

    // 字典树 - 时间复杂度 O(N * logC) 其中n是数组nums的长度，C是数组中的元素范围，在本题中C<2^31
    //       - 空间复杂度 O(N * logC) 每一个元素在字典树中需要使用 O(logC) 的空间
    Trie421 root = new Trie421();
    static final int HIGH_BIT = 30;

    public int findMaximumXOR(int[] nums) {
        int n = nums.length;

        int x = 0;
        for (int i = 1; i < n; i++) {
            // 将 nums[i - 1] 放入字典树，此时 nums[0 ... i-1] 都在字典树中
            add(nums[i - 1]);
            // 将 nums[i] 看作 ai，找出最大的 x 更新答案
            x = Math.max(x, check(nums[i]));
        }

        return x;
    }

    private void add(int num) {
        Trie421 cur = root;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                if (cur.left == null) cur.left = new Trie421();
                cur = cur.left;
            } else {
                if (cur.right == null) cur.right = new Trie421();
                cur = cur.right;
            }
        }
    }

    private int check(int num) {
        Trie421 cur = root;
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            // x = ai ^ aj
            if (bit == 0) {
                // ai 的第 k 个二进制位为 0，应当往表示 1 的子节点 right 走，这样 0^1=1，可以保证 x 的第 k 位为 1
                if (cur.right != null) {
                    cur = cur.right;
                    x = x * 2 + 1;
                } else {
                    cur = cur.left;
                    x = x * 2;
                }
            } else {
                // ai 的第 k 个二进制位为 1，应当往表示 0 的子节点 left 走，这样 1^0=1，可以保证 x 的第 k 位为 1
                if (cur.left != null) {
                    cur = cur.left;
                    x = x * 2 + 1;
                } else {
                    cur = cur.right;
                    x = x * 2;
                }
            }
        }

        return x;
    }

}

class Trie421 {
    Trie421 left = null;   // 0
    Trie421 right = null;  // 1
}
