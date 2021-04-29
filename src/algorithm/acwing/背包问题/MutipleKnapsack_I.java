package algorithm.acwing.背包问题;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 多重背包问题I
 */
public class MutipleKnapsack_I {
    /**
     * 输入样例：
     * <P>4 5</P>
     * <P>1 2 3</P>
     * <P>2 4 1</P>
     * <P>3 4 3</P>
     * <P>4 5 2</P>
     * 输出样例：
     * <P>10</P>
     */

    private static int[] w, v, s;
    private static int N, V;

    public static void main(String[] args) {
        MutipleKnapsack_I ac = new MutipleKnapsack_I();

        Scanner in = new Scanner(System.in);
        // 第一行两个整数 N、V，用空格隔开，分别表示物品数量和背包容积
        N = in.nextInt();
        V = in.nextInt();
        // 接下来有 N 行，每行两个整数 vi、wi，用空格隔开，分别表示第 i 件物品的体积和价值
        w = new int[N + 1];
        v = new int[N + 1];
        s = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            w[i] = in.nextInt();
            v[i] = in.nextInt();
            s[i] = in.nextInt();
        }

        System.out.println("现有" + N + "种物品，每种物品的体积、价值和数量分别为" + Arrays.toString(Arrays.copyOfRange(w, 1, w.length)) + "、" + Arrays.toString(Arrays.copyOfRange(v, 1, v.length)) + "和" + Arrays.toString(Arrays.copyOfRange(s, 1, s.length)));
        System.out.println("若每种物品的使用次数被限制在它的数量下，则在最大容量为" + V + "的背包中，装入物品能获得的最大总价值是" + ac.calc1());
    }

    // 三重循环 + 二维DP - 时间复杂度 O(N^3) - 空间复杂度 O(N^2)
    private int calc1() {

        // dp[i][j] 表示前i件物品在背包容量为j下的最优解
        // - 状态转移方程: {
        //   dp[i][j] = max(选择0个i物品放入背包的决策, 选择1个i物品放入背包的决策, ... , 选择s[i]个i物品放入背包的决策)
        // }
        int[][] dp = new int[N + 1][V + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                for (int k = 0; k <= s[i] && k * w[i] <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * w[i]] + k * v[i]);
                }
            }
        }

        return dp[N][V];
    }

    // 三重循环 + 一维DP - 时间复杂度 O(N^3) - 空间复杂度 O(N)
    // - 优化原理: 根据二维DP表达式做等价变形，注意遍历重量时需要从后往前遍历，否则前置状态值可能会被覆盖
    private int calc2() {

        // dp[i][j] 表示前i件物品在背包容量为j下的最优解
        int[] dp = new int[V + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= 0; j--) {
                for (int k = 0; k <= s[i] && k * w[i] <= j; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * w[i]] + k * v[i]);
                }
            }
        }

        return dp[V];
    }

}
