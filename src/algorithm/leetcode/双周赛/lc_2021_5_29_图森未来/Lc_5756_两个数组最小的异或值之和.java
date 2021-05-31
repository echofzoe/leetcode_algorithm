package algorithm.leetcode.双周赛.lc_2021_5_29_图森未来;

import java.util.Arrays;

/**
 * 两个数组最小的异或值之和
 * <P>https://leetcode-cn.com/problems/minimum-xor-sum-of-two-arrays/</P>
 *
 * @author echofzoe
 * @since 2021.5.30
 */
public class Lc_5756_两个数组最小的异或值之和 {

    public static void main(String[] args) {
        Lc_5756_两个数组最小的异或值之和 lc = new Lc_5756_两个数组最小的异或值之和();

        int[] nums1 = {1, 0, 3}, nums2 = {5, 3, 4};

        System.out.println("给你两个整数数组 nums1 和 nums2 ，它们长度都为 n 。\n" +
                "两个数组的 异或值之和 为 (nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1]) （下标从 0 开始）。\n" +
                "  - 比方说，[1,2,3] 和 [3,2,1] 的 异或值之和 等于 (1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4 。\n" +
                "请你将 nums2 中的元素重新排列，使得 异或值之和 最小 。\n" +
                "请你返回重新排列之后的 异或值之和。");
        System.out.println("输入：nums1 = " + Arrays.toString(nums1) + ", nums2 = " + Arrays.toString(nums2));
        System.out.println("输出：" + lc.minimumXORSum(nums1, nums2));
    }

    // 状态压缩DP - 时间复杂度 O(N * 2^N) - 空间复杂度 O(2^N) 为状态的数量
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;

        // 使用长度为n的二进制数mask表示数组nums2中的数被选择的状态
        // 如果mask从低到高的第i位为1，则说明nums2[i]已经被选择，否则说明其未被选择
        int range = 1 << n;

        // dp[mask] 表示当我们选择了nums2中的元素的状态为mask，并且选择了nums1的前count(mask)个元素的情况下，可以组成的最小异或值之和
        /*
            - 状态转移方程: {
                dp[mask] = min(dp[mask], dp[mask ^ (1 << i)] + (nums1[Integer.bitCount(mask) - 1] ^ nums2[i])), 0 < i < n
            }
        */
        int[] dp = new int[range];
        // base case
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int mask = 1; mask < range; mask++) {
            // 枚举 nums2 的每一位选择
            for (int i = 0; i < n; i++) {
                // 判断 mask 的第 i 位是否为 1
                if ((mask & (1 << i)) > 0) {
                    // 当 mask 的第 i 位是 1 时，操作 mask ^ (1 << i) 会将 mask 的第 i 位置 0
                    dp[mask] = Math.min(dp[mask], dp[mask ^ (1 << i)] + (nums1[Integer.bitCount(mask) - 1] ^ nums2[i]));
                }
            }
        }

        return dp[range - 1];
    }

    // 回溯（超时） - 时间复杂度 O() - 空间复杂度 O()
    int n, res;

    public int minimumXORSum1(int[] nums1, int[] nums2) {
        this.n = nums1.length;
        this.res = Integer.MAX_VALUE;

        dfs(nums1, nums2, 0, 0);

        return res;
    }

    private void dfs(int[] nums1, int[] nums2, int j, int tmp) {
        // exit
        if (j == n - 1) {
            tmp += nums1[j] ^ nums2[j];
            res = Math.min(res, tmp);
            return;
        }

        // dfs
        for (int i = j; i < n; i++) {
            swap(nums2, j, i);
            tmp += nums1[j] ^ nums2[j];

            // 剪枝
            if (tmp >= res) {
                tmp -= nums1[j] ^ nums2[j];
                swap(nums2, j, i);
                continue;
            }

            dfs(nums1, nums2, j + 1, tmp);
            // 回溯
            tmp -= nums1[j] ^ nums2[j];
            swap(nums2, j, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
