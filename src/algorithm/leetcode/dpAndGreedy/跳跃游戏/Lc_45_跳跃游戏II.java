package algorithm.leetcode.dpAndGreedy.跳跃游戏;

import java.util.Arrays;

/**
 * 跳跃游戏 II
 * <P>https://leetcode-cn.com/problems/jump-game-ii/</P>
 * 
 * @author echofzoe 
 * @since unknown
 * @updated 2021.6.17
 */
public class Lc_45_跳跃游戏II {

    public static void main(String[] args) {
        Lc_45_跳跃游戏II lc = new Lc_45_跳跃游戏II();
        int[] input = {2, 3, 1, 1, 4};

        System.out.println("给定一个非负整数数组，你最初位于数组的第一个位置。\n" +
                "数组中的每个元素代表你在该位置可以跳跃的最大长度。\n" +
                "你的目标是使用最少的跳跃次数到达数组的最后一个位置。\n" +
                "假设你总是可以到达数组的最后一个位置。\n");
        System.out.println("输入：" + Arrays.toString(input));
        System.out.println("输出：" + lc.jumpGreedy(input));
    }

    // 贪心 从前往后 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int jumpGreedy(int[] nums) {
        int n = nums.length;

        int res = 0, boundary = 0, far = 0;
        // 不访问最后一个元素，因为在访问最后一个元素之前，边界值一定大于等于最后一个位置，否则就不能到达最后一个位置
        // 如果访问最后一个位置，在边界正好为最后一个位置的情况下，会增加一次不必要的跳跃次数
        for (int i = 0; i < n - 1; i++) {
            far = Math.max(far, i + nums[i]);

            if (i == boundary) {
                
                res++;
                boundary = far;
            }
        }

        return res;
    }

    // 贪心 从后往前 - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    public int jumpGreedy2(int[] nums) {
        int n = nums.length;

        int res = 0, far = n - 1;
        while (far > 0) {
            for (int i = 0; i < far; i++) {
                if (i + nums[i] >= far) {
                    far = i;
                    res++;
                    break;
                }
            }
        }

        return res;
    }

    // DP - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public int jumpDP(int[] nums) {
        int n = nums.length;

        // dp[i] 表示跳跃到位置i时所用的最少跳跃次数
        /*
            状态转移方程：{
                dp[i] = min(dp[j] + 1), 0 <= j < i && j + nums[j] >= i
            }
        */
        int[] dp = new int[n];
        // base case
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n - 1];
    }

}
