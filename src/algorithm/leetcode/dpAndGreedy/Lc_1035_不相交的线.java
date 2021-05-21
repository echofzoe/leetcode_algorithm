package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

/**
 * 不相交的线
 * <P>https://leetcode-cn.com/problems/uncrossed-lines/</P>
 *
 * @author echofzoe
 * @since 2021.5.21
 */
public class Lc_1035_不相交的线 {

    public static void main(String[] args) {
        Lc_1035_不相交的线 lc = new Lc_1035_不相交的线();

        int[] nums1 = {2, 5, 1, 2, 5}, nums2 = {10, 5, 2, 1, 5, 2};

        System.out.println("在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。\n" +
                "现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足：\n" +
                "  - nums1[i] == nums2[j]\n" +
                "  - 且绘制的直线不与任何其他连线（非水平线）相交。\n" +
                "请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。\n" +
                "以这种方法绘制线条，并返回可以绘制的最大连线数。");
        System.out.println("输入：nums1 = " + Arrays.toString(nums1) + ", nums2 = " + Arrays.toString(nums2));
        System.out.println("输出：" + lc.maxUncrossedLines(nums1, nums2));
    }

    // DP - 时间复杂度 O(MN) - 空间复杂度 O(MN)
    /* 将此问题转换为 两个数组的最长公共子序列 的问题
       - dp[i][j] 表示 nums1 的 [0, i] 区间和 nums2 的 [0, j] 区间中的最长公共子序列
       - 状态转移方程: {
         dp[i][j] = dp[i - 1][j - 1] + 1, nums1[i] = nums2[j]
         dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]), nums1[i] != nums2[j]
       }
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            int a = nums1[i - 1];
            for (int j = 1; j <= n; j++) {
                int b = nums2[j - 1];

                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

}
