package algorithm.leetcode.dpAndGreedy.背包问题;

import java.util.Arrays;

/**
 * 最后一块石头的重量 II
 * <P>https://leetcode-cn.com/problems/last-stone-weight-ii/</P>
 *
 * @author echofzoe
 * @since 2021.6.8
 */
public class Lc_1049_最后一块石头的重量II {

    public static void main(String[] args) {
        Lc_1049_最后一块石头的重量II lc = new Lc_1049_最后一块石头的重量II();

        int[] stones = {31, 26, 33, 21, 40};

        System.out.println("有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。\n" +
                "每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：\n" +
                "  - 如果 x == y，那么两块石头都会被完全粉碎；\n" +
                "  - 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。\n" +
                "最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。\n");
        System.out.println("输入：stones = " + Arrays.toString(stones));
        System.out.println("输出：" + lc.lastStoneWeightII(stones));
    }

    // DP - 时间复杂度 O(n * half) n为stones数组长度,half为数组元素和的一半 - 空间复杂度 O(n*half)
    // 思路：将石堆划分成差值最小的两堆，这样，最后剩下的石头就是两堆石头的差值；问题转化为01背包问题
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;

        int sum = 0;
        for (int x : stones) sum += x;
        int half = sum / 2;

        // dp[i][j] 表示前i块石头在重量和不超过j的情况下能得到的最大重量
        int[][] dp = new int[n + 1][half + 1];

        for (int i = 1; i <= n; i++) {
            int cur = stones[i - 1];
            for (int j = 0; j <= half; j++) {
                if (j < cur) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cur] + cur);
            }
        }

        return Math.abs(sum - dp[n][half] - dp[n][half]);
    }

    // DP 空间优化 - 时间复杂度 O(n * half) - 空间复杂度 O(half)
    public int lastStoneWeightII1(int[] stones) {
        int n = stones.length;

        int sum = 0;
        for (int x : stones) sum += x;
        int half = sum / 2;

        // dp[i] 表示数组中的元素在重量和不超过i的情况下能得到的最大重量
        int[] dp = new int[half + 1];

        for (int i = 1; i <= n; i++) {
            int cur = stones[i - 1];
            for (int j = half; j >= cur; j--) {
                dp[j] = Math.max(dp[j], dp[j - cur] + cur);
            }
        }

        return Math.abs(sum - dp[half] - dp[half]);
    }

}
