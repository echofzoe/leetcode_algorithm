package algorithm.leetcode.array.diff;

import java.util.Arrays;

/**
 * 形成目标数组的子数组最少增加次数
 * <P>https://leetcode-cn.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/</P>
 *
 * @author echofzoe
 * @since 2021.7.23
 */
public class Lc_1526_形成目标数组的子数组最少增加次数 {

    public static void main(String[] args) {
        Lc_1526_形成目标数组的子数组最少增加次数 lc = new Lc_1526_形成目标数组的子数组最少增加次数();

        int[] target = {1, 2, 3, 2, 1};

        System.out.println("给你一个整数数组 target 和一个数组 initial ，initial 数组与 target  数组有同样的维度，且一开始全部为 0 。\n" +
                "请你返回从 initial 得到  target 的最少操作次数，每次操作需遵循以下规则：\n" +
                "  - 在 initial 中选择 任意 子数组，并将子数组中每个元素增加 1 。\n" +
                "答案保证在 32 位有符号整数以内。\n");
        System.out.println("输入：target = " + Arrays.toString(target));
        System.out.println("输出：" + lc.minNumberOperations1(target));  // 3
    }

    // DIFF - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int minNumberOperations(int[] target) {
        int n = target.length, res = target[0];

        for (int i = 1; i < n; i++) {
            res += Math.max(target[i] - target[i - 1], 0);
        }

        return res;
    }

    // DP - 时间复杂度 O() - 空间复杂度 O()
    public int minNumberOperations1(int[] target) {
        int n = target.length;

        // dp[i] 表示区间为[0, i]的子数组形成目标数组所需的操作次数
        int[] dp = new int[n];
        // base case
        dp[0] = target[0];

        for (int i = 1; i < n; i++) {
            if (target[i - 1] < target[i]) {
                dp[i] = dp[i - 1] + target[i] - target[i - 1];
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[n - 1];
    }

}
