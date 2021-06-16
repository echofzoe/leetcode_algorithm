package algorithm.leetcode.dpAndGreedy.石子游戏;

import java.util.Arrays;

/**
 * 石子游戏 II
 * <P>https://leetcode-cn.com/problems/stone-game-ii/</P>
 *
 * @author echofzoe
 * @since 2021.6.16
 */
public class Lc_1140_石子游戏II {

    public static void main(String[] args) {
        Lc_1140_石子游戏II lc = new Lc_1140_石子游戏II();

        int[] piles = {2, 7, 9, 4, 4};

        System.out.println("亚历克斯和李继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。\n" +
                "亚历克斯和李轮流进行，亚历克斯先开始。最初，M = 1。\n" +
                "在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。\n" +
                "游戏一直持续到所有石子都被拿走。\n" +
                "假设亚历克斯和李都发挥出最佳水平，返回亚历克斯可以得到的最大数量的石头。\n");
        System.out.println("输入：piles = " + Arrays.toString(piles));
        System.out.println("输出：" + lc.stoneGameII(piles));  // 10
    }

    public int stoneGameII(int[] piles) {
        int n = piles.length;

        // dp[i][j] 表示剩余[i:len-1]堆石子时，在M为j的情况下，先取的人能获得的最多石子数
        /*
            状态转移方程：{
                1. 剩下的石堆能够全部取走，则最优情况是取走全部石子
                - dp[i][M] = sum[i:len-1], i + 2M >= len
                2. 剩下的石堆不能全部取走，则最优情况是让下一个人取得更少
                - dp[i][M] = max(dp[i][M], sum[i:len-1] - dp[i + x][max(M, x)]), i + 2M < len
            }
         */
        int[][] dp = new int[n][n + 1];
        
        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += piles[i];
            for (int M = 1; M <= n; M++) {
                if (i + 2 * M >= n) {
                    dp[i][M] = sum;
                } else {
                    for (int x = 1; x <= 2 * M; x++) {
                        dp[i][M] = Math.max(dp[i][M], sum - dp[i + x][Math.max(x, M)]);
                    }
                }
            }
        }

        return dp[0][1];
    }

}
