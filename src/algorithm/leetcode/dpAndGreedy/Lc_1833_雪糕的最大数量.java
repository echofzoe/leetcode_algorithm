package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

/**
 * 雪糕的最大数量
 * <P>https://leetcode-cn.com/problems/maximum-ice-cream-bars/</P>
 *
 * @author echofzoe
 * @since 2021.7.2
 */
public class Lc_1833_雪糕的最大数量 {

    public static void main(String[] args) {
        Lc_1833_雪糕的最大数量 lc = new Lc_1833_雪糕的最大数量();

        int[] costs = {1,6,3,1,2,5};
        int coins = 20;

        System.out.println("夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。\n" +
                "商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。\n" +
                "给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。\n" +
                "注意：Tony 可以按任意顺序购买雪糕。\n");
        System.out.println("输入：costs = " + Arrays.toString(costs) + ", coins = " + coins);
        System.out.println("输出：" + lc.maxIceCream(costs, coins));
    }

    // 排序 + 贪心 - 时间复杂度 O(NlogN) - 空间复杂度 O(logN) 为排序使用的额外空间
    public int maxIceCream(int[] costs, int coins) {
        int n = costs.length;

        Arrays.sort(costs);

        int idx = 0, sum = 0;
        while (idx < n) {
            if ((sum += costs[idx]) > coins) break;

            idx++;
        }

        return idx;
    }

    // 桶排序 + 贪心 - 时间复杂度 O(N+C)，其中N是数组costs的长度，C是数组costs中的元素的最大可能值 - 空间复杂度 O(C)
    public int maxIceCream1(int[] costs, int coins) {
        int[] b = new int[100001];
        for (int cost : costs) {
            b[cost]++;
        }

        int res = 0;
        for (int i = 1; i <= 100000 && coins >= i; i++) {
            int curCnt = Math.min(b[i], coins / i);
            coins -= i * curCnt;
            res += curCnt;
        }

        return res;
    }

    // 二维DP（超内存） - 时间复杂度 O(N * coins) 其中N为costs的长度 - 空间复杂度 O(N * coins)
    public int maxIceCreamDp1(int[] costs, int coins) {
        int n = costs.length;

        // dp[i][j] 表示在前i支雪糕中花费j能获得的最大数量
        int[][] dp = new int[n + 1][coins + 1];

        for (int i = 1; i <= n; i++) {
            int cur = costs[i - 1];
            for (int j = 0; j <= coins; j++) {
                if (cur > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cur] + 1);
                }
            }
        }

        return dp[n][coins];
    }

    // 一维DP（超时） - 时间复杂度 O(N * coins) - 空间复杂度 O(coins)
    public int maxIceCreamDp2(int[] costs, int coins) {
        int n = costs.length;

        // dp[i] 表示花费i能获得的最大数量
        int[] dp = new int[coins + 1];

        for (int i = 1; i <= n; i++) {
            int cur = costs[i - 1];
            for (int j = coins; j >= cur; j--) {
                dp[j] = Math.max(dp[j], dp[j - cur] + 1);
            }
        }

        return dp[coins];
    }

}
