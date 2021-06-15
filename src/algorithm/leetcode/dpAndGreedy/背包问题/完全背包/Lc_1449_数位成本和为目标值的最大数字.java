package algorithm.leetcode.dpAndGreedy.背包问题.完全背包;

import java.util.Arrays;

/**
 * 数位成本和为目标值的最大数字
 * <P>https://leetcode-cn.com/problems/form-largest-integer-with-digits-that-add-up-to-target/</P>
 *
 * @author echofzoe
 * @since 2021.6.15
 */
public class Lc_1449_数位成本和为目标值的最大数字 {

    public static void main(String[] args) {
        Lc_1449_数位成本和为目标值的最大数字 lc = new Lc_1449_数位成本和为目标值的最大数字();

        int[] cost = {4, 3, 2, 5, 6, 7, 2, 5, 5};
        int target = 9;

        System.out.println("给你一个整数数组 cost 和一个整数 target 。请你返回满足如下规则可以得到的 最大 整数：\n" +
                "  - 给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。\n" +
                "  - 总成本必须恰好等于 target 。\n" +
                "  - 添加的数位中没有数字 0 。\n" +
                "由于答案可能会很大，请你以字符串形式返回。\n" +
                "如果按照上述要求无法得到任何整数，请你返回 \"0\" 。\n" +
                "注： cost.length == 9, 1 <= cost[i] <= 5000, 1 <= target <= 5000\n");
        System.out.println("输入：cost = " + Arrays.toString(cost) + ", target = " + target);
        System.out.println("输出：" + lc.largestNumber1(cost, target));  // "7772"
    }

    // DP - 时间复杂度 O(n * target) - 空间复杂度 O(n * target)
    public String largestNumber(int[] cost, int target) {
        int n = cost.length;

        // dp[i][j] 表示使用前i个数位且花费成本恰好为j时能得到的最大位数
        /*
            边界条件：{
                1. 成本为0时，得不到任何位数
                - dp[i][0] = 0
                2. 成本非0时，初始化为最小值，方便之后的max比较
                - dp[i][j] = Integer.MIN_VALUE
            }
            状态转移方程：{
                1. 当前位数i的成本cur大于当前的总成本j
                - dp[i][j] = dp[i - 1][j], 1<=i<=n && 0<=j<=target
                2. 使用k个位数i的成本小于等于当前的总成本j
                - dp[i][j] = dp[i - 1][j - k * cur] + k, 1<=i<=n && 0<=j<=target && 1<=k && k*cur<=j
            }
         */
        int[][] dp = new int[n + 1][target + 1];
        // base case
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 0xbf3f3f3f);
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            int c = cost[i - 1];
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; k * c <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * c] + k);
                }
            }
        }

        if (dp[n][target] < 0) return "0";

        // 贪心选择最大整数
        StringBuilder sb = new StringBuilder();
        for (int i = n, j = target; i >= 1; i--) {
            int c = cost[i - 1];
            while (j >= c && dp[i][j] == dp[i][j - c] + 1) {
                sb.append(i);
                j -= c;
            }
        }

        return sb.toString();
    }

    // DP 空间优化 - 时间复杂度 O(n * target) - 空间复杂度 O(target)
    public String largestNumber1(int[] cost, int target) {
        int n = cost.length;

        // dp[i] 表示成本恰好为j时能得到的最大位数
        /*
            边界条件：{
                1. 成本为0时，得不到任何位数
                - dp[0] = 0
                2. 成本非0时，初始化为最小值，方便之后的max比较
                - dp[i] = Integer.MIN_VALUE
            }
            状态转移方程：{
                1. 当前位数i的成本c小于等于当前的总成本i
                - dp[i] = Math.max(dp[i], dp[i - c] + 1), min(cost[])<=i<=target
            }
         */
        int[] dp = new int[target + 1];
        // base case
        Arrays.fill(dp, 0xbf3f3f3f);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            int c = cost[i - 1];
            for (int j = c; j <= target; j++) {
                dp[j] = Math.max(dp[j], dp[j - c] + 1);
            }
        }

        if (dp[target] < 0) return "0";

        // 贪心选择最大整数
        StringBuilder sb = new StringBuilder();
        for (int i = n, j = target; i >= 1; i--) {
            int c = cost[i - 1];
            while (j >= c && dp[j] == dp[j - c] + 1) {
                sb.append(i);
                j -= c;
            }
        }

        return sb.toString();
    }

}
