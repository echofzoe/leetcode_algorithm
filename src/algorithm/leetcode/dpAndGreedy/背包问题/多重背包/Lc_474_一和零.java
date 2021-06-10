package algorithm.leetcode.dpAndGreedy.背包问题.多重背包;

import java.util.Arrays;

/**
 * 一和零
 * <P>https://leetcode-cn.com/problems/ones-and-zeroes/</P>
 *
 * @author echofzoe
 * @since 2021.6.6
 */
public class Lc_474_一和零 {

    public static void main(String[] args) {
        Lc_474_一和零 lc = new Lc_474_一和零();

        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;

        System.out.println("给你一个二进制字符串数组 strs 和两个整数 m 和 n 。\n" +
                "请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。\n" +
                "如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。");
        System.out.println("输入：strs = " + Arrays.toString(strs) + ", m = " + m + ", n = " + n);
        System.out.println("输出：" + lc.findMaxForm(strs, m, n));
    }

    // DP - 时间复杂度 O(l*m*n + L) 其中l是数组strs的长度，m和n分别是0和1的容量，L是数组strs中所有字符串的长度总和 - 空间复杂度 O(l*m*n)
    /*
        dp[l][i][j] 表示在前 l 个字符串中，使用 i 个 0 和 j 个 1 的情况下最多可以得到的字符串数量
        - 边界条件：没有任何字符串可使用时，可得到的字符串数量为 0，即 dp[0][x][y] = 0, 0 <= x <= m and 0 <= y <= n
        - 状态转移方程：{
            设zeros为strs[z]中0的个数，ones为strs[z]中1的个数
            dp[k][i][j] = dp[k - 1][i][j], i < zeros || j < ones
            dp[k][i][j] = max(dp[k - 1][i - zeros][j - ones] + 1, dp[k - 1][i][j]), i >= zeros && j >= ones
        }
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;

        int[][][] dp = new int[l + 1][m + 1][n + 1];
        // base case
        // dp[l][i][j] = 0, l = 0

        for (int k = 1; k <= l; k++) {
            int zeros = 0, ones = 0, len = strs[k - 1].length();
            for (int i = 0; i < len; i++) {
                if (strs[k - 1].charAt(i) == '0') zeros++;
                else ones++;
            }

            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    if (i < zeros || j < ones) dp[k][i][j] = dp[k - 1][i][j];
                    else if (i >= zeros && j >= ones) dp[k][i][j] = Math.max(dp[k - 1][i][j], dp[k - 1][i - zeros][j - ones] + 1);
                }
            }
        }

        return dp[l][m][n];
    }

}
