package algorithm.leetcode.周赛.lc_2021_4_11_万得;

import java.util.Arrays;

/**
 * 最少侧跳次数
 * https://leetcode-cn.com/problems/minimum-sideway-jumps/
 *
 * @author echofzoe
 * @since 2021.4.11
 */
public class Lc_5728_最少侧跳次数 {

    public static void main(String[] args) {
        Lc_5728_最少侧跳次数 lc = new Lc_5728_最少侧跳次数();

//        int[] obstacles = {0, 1, 2, 3, 0};
        int[] obstacles = {0, 2, 2, 1, 0, 3, 0, 3, 0, 1, 3, 1, 1, 0, 1, 3, 1, 1, 1, 0, 2, 0, 0, 3, 3, 0, 3, 2, 2, 0, 0, 3, 3, 3, 0, 0, 2, 0, 0, 3, 3, 0, 3, 3, 0, 0, 3, 1, 0, 1, 0, 2, 3, 1, 1, 0, 3, 3, 0, 3, 1, 3, 0, 2, 2, 0, 1, 3, 0, 1, 0, 3, 0, 1, 3, 1, 2, 2, 0, 0, 3, 0, 1, 3, 2, 3, 2, 1, 0, 3, 2, 2, 0, 3, 3, 0, 3, 0, 0, 1, 0, 2, 0, 0, 0, 2, 1, 2, 0, 2, 2, 3, 3, 3, 0, 0, 1, 1, 3, 0, 0, 0, 1, 2, 2, 1, 2, 1, 3, 2, 2, 3, 1, 3, 0, 1, 1, 1, 3, 0, 0, 0, 2, 0, 2, 0, 3, 1, 2, 3, 3, 2, 2, 2, 0, 0, 0, 1, 0, 0, 0, 0, 3, 0, 0, 0, 3, 0, 2, 1, 2, 3, 1, 0, 3, 3, 2, 0, 1, 1, 0, 1, 0, 2, 2, 2, 1, 3, 0, 3, 0, 2, 1, 1, 3, 1, 0, 1, 2, 2, 0, 2, 2, 0, 0, 3, 3, 1, 3, 0, 1, 1, 0, 3, 0, 2, 1, 2, 2, 0, 0, 0, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 1, 0, 2, 3, 3, 3, 0, 2, 3, 2, 0, 0, 0, 1, 0, 2, 2, 0, 0, 2, 0, 2, 0, 1, 1, 0, 3, 1, 3, 3, 0, 1, 0, 3, 0, 3, 1, 2, 3, 1, 0, 0, 2, 3, 2, 0, 0, 3, 1, 2, 3, 2, 2, 3, 1, 3, 3, 2, 0, 1, 3, 0, 3, 2, 2, 3, 2, 1, 2, 2, 0, 3, 2, 0, 2, 1, 2, 2, 3, 1, 3, 2, 2, 0, 0, 1, 0, 3, 1, 3, 3, 0, 0, 2, 2, 2, 2, 0, 1, 0, 3, 1, 3, 3, 3, 0, 2, 3, 2, 0, 3, 3, 3, 3, 3, 3, 2, 2, 1, 1, 0, 3, 1, 3, 2, 3, 0, 0, 0, 2, 1, 1, 3, 1, 3, 2, 1, 3, 0, 1, 1, 3, 2, 2, 1, 0, 0, 3, 2, 1, 3, 2, 3, 3, 2, 1, 2, 0, 2, 2, 0, 2, 2, 3, 2, 0, 2, 3, 3, 1, 1, 2, 0, 1, 1, 1, 2, 3, 2, 1, 2, 1, 0, 2, 3, 1, 1, 3, 3, 2, 0, 1, 3, 2, 3, 3, 0, 1, 2, 3, 2, 1, 1, 2, 1, 0, 0, 1, 0, 3, 1, 1, 1, 0, 2, 0, 2, 2, 3, 0, 1, 0, 2, 0, 0, 3, 1, 1, 2, 0, 0, 2, 1, 1, 0, 2, 2, 2, 3, 1, 2, 0, 1, 2, 0, 1, 2, 1, 2, 3, 1, 1, 1, 1, 0, 3, 3, 2, 1, 0, 0, 1, 0, 3, 0, 0, 2, 2, 2, 1, 1, 2, 1, 2, 1, 1, 2, 0, 3, 1, 3, 2, 1, 2, 2, 3, 1, 0, 1, 1, 1, 0, 0, 0, 1, 3, 3, 2, 2, 1, 2, 0, 0, 0, 3, 1, 3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 0, 1, 2, 1, 1, 2, 1, 3, 0, 1, 1, 1, 3, 3, 1, 0, 0, 3, 2, 2, 3, 1, 1, 0, 3, 0, 0, 3, 0, 3, 1, 2, 0, 2, 3, 2, 3, 0, 3, 2, 3, 0, 2, 2, 3, 0, 3, 3, 3, 1, 0, 1, 2, 2, 0, 3, 3, 1, 3, 2, 2, 3, 2, 1, 1, 0, 0, 0, 0, 2, 1, 0, 1, 1, 1, 1, 0, 3, 0, 1, 0, 0, 1, 0, 2, 0, 0, 1, 2, 0, 0, 0, 3, 3, 1, 0, 3, 2, 1, 2, 3, 2, 0, 3, 3, 0, 2, 3, 1, 1, 0, 2, 2, 3, 3, 0, 1, 0, 0, 3, 1, 2, 3, 0, 1, 2, 3, 2, 2, 0, 1, 2, 0, 3, 0, 3, 0, 1, 1, 3, 2, 2, 2, 3, 1, 2, 0, 0, 3, 0, 2, 3, 3, 1, 0, 3, 3, 0, 0, 0, 3, 2, 1, 1, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3, 0, 0, 1, 1, 1, 1, 2, 2, 0, 1, 0, 2, 2, 0, 2, 1, 3, 1, 1, 1, 2, 1, 1, 0, 3, 1, 0, 2, 3, 0, 1, 2, 0, 0, 3, 1, 2, 3, 0, 0, 3, 1, 0, 2, 2, 0, 1, 1, 2, 2, 1, 3, 1, 2, 1, 0, 1, 2, 3, 2, 3, 0, 3, 1, 3, 0, 2, 0, 3, 1, 1, 0, 3, 2, 0, 3, 0, 2, 0, 0, 3, 3, 1, 1, 1, 0, 0, 1, 1, 1, 2, 3, 1, 3, 1, 2, 0, 0, 3, 3, 0, 3, 3, 0, 0, 0, 3, 3, 0, 3, 3, 2, 3, 3, 3, 3, 1, 1, 1, 3, 1, 1, 3, 3, 1, 0, 0, 3, 1, 2, 0, 2, 0, 3, 0, 2, 1, 0, 1, 0, 2, 3, 3, 3, 2, 3, 3, 2, 0, 0, 0, 2, 2, 3, 0, 0, 3, 0, 2, 3, 0, 1, 3, 2, 1, 2, 0, 1, 3, 2, 2, 0, 1, 1, 3, 3, 0, 2, 3, 0, 3, 3, 1, 2, 3, 2, 1, 0, 2, 3, 2, 2, 2, 3, 0, 1, 1, 3, 1, 0, 2, 1, 3, 2, 2, 2, 3, 3, 1, 1, 1, 3, 2, 3, 1, 0, 2, 3, 0, 2, 3, 0, 1, 3, 3, 1, 1, 1, 1, 0, 1, 1, 2, 2, 0, 2, 1, 1, 0, 1, 0, 3, 1, 1, 1, 3, 3, 2, 1, 2, 3, 2, 2, 3, 1, 0, 3, 2, 0, 1, 0, 1, 3, 3, 3, 0, 3, 3, 2, 3, 1, 2, 2, 1, 1, 0, 0, 3, 0};

        System.out.println("在有障碍的跑道" + Arrays.toString(obstacles) + "上，青蛙最少侧跳" + lc.minSideJumps1(obstacles) + "次可以到达终点");
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    // - dp[i][j]表示到达第i处第j跑道的最少测跳次数
    public int minSideJumps1(int[] obstacles) {
        int n = obstacles.length;
        int[][] dp = new int[n][4];
        // base case
        for (int[] t : dp) Arrays.fill(t, 0x3f3f3f3f);
        dp[0][1] = 1;
        dp[0][2] = 0;
        dp[0][3] = 1;

        for (int i = 1; i < n; i++) {
            if (obstacles[i] != 1) dp[i][1] = dp[i - 1][1];
            if (obstacles[i] != 2) dp[i][2] = dp[i - 1][2];
            if (obstacles[i] != 3) dp[i][3] = dp[i - 1][3];

            if (obstacles[i] != 1) dp[i][1] = Math.min(dp[i][1], Math.min(dp[i][2], dp[i][3]) + 1);
            if (obstacles[i] != 2) dp[i][2] = Math.min(dp[i][2], Math.min(dp[i][1], dp[i][3]) + 1);
            if (obstacles[i] != 3) dp[i][3] = Math.min(dp[i][3], Math.min(dp[i][1], dp[i][2]) + 1);
        }

        return Math.min(dp[n - 1][1], Math.min(dp[n - 1][2], dp[n - 1][3]));
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int minSideJumps2(int[] obstacles) {
        int n = obstacles.length;

        int res = 0, pos = 2;

        for (int i = 0; i < n - 1; ) {
            if (obstacles[i + 1] != pos) {
                i++;
                continue;
            }

            int a = (pos + 1) % 3, b = (pos + 2) % 3;
            a = a == 0 ? 3 : a;
            b = b == 0 ? 3 : b;

            int j1 = i, j2 = i;
            while (j1 < n && obstacles[j1] != a) j1++;
            while (j2 < n && obstacles[j2] != b) j2++;

            i = Math.max(j1, j2) - 1;
            pos = j1 > j2 ? a : b;
            res++;
        }

        return res;
    }

}
