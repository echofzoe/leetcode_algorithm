package algorithm.acwing.背包问题;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 01背包问题
 * <P>https://www.acwing.com/problem/content/2/</P>
 *
 * @author echofzoe
 * @since 2021.4.28
 */
public class ZeroOneKnapsack {
    /**
     * 输入样例：
     <P>4 5</P>
     <P>1 2</P>
     <P>2 4</P>
     <P>3 4</P>
     <P>4 5</P>
     * 输出样例：
     <P>10</P>
     */

    private static int[] w, v;
    private static int N, V;

    public static void main(String[] args) {
        ZeroOneKnapsack ac = new ZeroOneKnapsack();

        Scanner in = new Scanner(System.in);
        // 第一行两个整数 N、V，用空格隔开，分别表示物品数量和背包容积
        N = in.nextInt();
        V = in.nextInt();
        // 接下来有 N 行，每行两个整数 vi、wi，用空格隔开，分别表示第 i 件物品的体积和价值
        w = new int[N + 1];
        v = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            w[i] = in.nextInt();
            v[i] = in.nextInt();
        }

        System.out.println("现有" + N + "种物品，每种物品的体积和价值分别为" + Arrays.toString(Arrays.copyOfRange(w, 1, w.length)) + "和" + Arrays.toString(Arrays.copyOfRange(v, 1, v.length)));
        System.out.println("若每种物品都只能使用一次，则在最大容量为" + V + "的背包中，装入物品能获得的最大总价值是" + ac.calc3());
    }

    // 二维DP - 时间复杂度 O(N^2) - 空间复杂度 O(N^2)
    private int calc1() {

        // dp[i][j] 表示前i件物品在背包容量为j下的最优解
        // - 状态转移方程: {
        //   dp[i][j] = max(不选i物品放入背包的决策, 选择i物品放入背包的决策)
        // }
        int[][] dp = new int[N + 1][V + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                if (w[i] > j) {
                    // 背包装不下第i件物品
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 背包装的下第i件物品
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                }
            }
        }

        return dp[N][V];
    }

    // 一维DP - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    private int calc2() {

        // dp[j] 表示N件物品在背包容量为j下的最优解
        int[] dp = new int[V + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= 0; j--) {
                if (w[i] > j) {
                    // 背包装不下第i件物品
                    dp[j] = dp[j];
                } else {
                    // 背包装的下第i件物品
                    dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
                }
            }
        }

        return dp[V];
    }

    // 一维DP 简化写法 - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    private int calc3() {

        // dp[j] 表示N件物品在背包容量为j下的最优解
        int[] dp = new int[V + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= w[i]; j--) {
                // 背包装的下第i件物品
                // 等价于 dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i]] + v[i])
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }

        return dp[V];
    }

}
