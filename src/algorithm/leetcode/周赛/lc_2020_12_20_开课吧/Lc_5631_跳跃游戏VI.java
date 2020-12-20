package algorithm.leetcode.周赛.lc_2020_12_20_开课吧;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Lc_5631_跳跃游戏VI {

    // 跳跃游戏 VI
    // https://leetcode-cn.com/problems/jump-game-vi/

    public static void main(String[] args) {
        Lc_5631_跳跃游戏VI lc = new Lc_5631_跳跃游戏VI();
        int[] input1 = {1, -1, -2, 4, -7, 3};
        int[] input2 = {10, -5, -2, 4, 0, 3};
        int[] input3 = {1, -5, -20, 4, -1, 3, -6, -3};
        int k1 = 2, k2 = 3, k3 = 2;

        System.out.println("数组" + Arrays.toString(input1) + "在步长" + k1 + "下能得到的最大得分是" + lc.maxResultDp(input1, k1));
        System.out.println("数组" + Arrays.toString(input2) + "在步长" + k2 + "下能得到的最大得分是" + lc.maxResultDpOptimization(input2, k2));
        System.out.println("数组" + Arrays.toString(input3) + "在步长" + k3 + "下能得到的最大得分是" + lc.maxResultDpOptimization(input3, k3));
    }

    // DP 超时 - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public int maxResultDp(int[] nums, int k) {
        int n = nums.length;

        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        // base case
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            for (int j = Math.max(0, i - k); j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j]);
            }
            dp[i] += nums[i];
        }

        return dp[n - 1];
    }
    
    // DP + 滑动窗口 - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public int maxResultDpOptimization(int[] nums, int k) {
        int n = nums.length;

        int[] dp = new int[n];
        // base case
        dp[0] = nums[0];

        Deque<Integer> windowIndices = new LinkedList<>();
        for (int i = 1; i < n; i++) {
            while (!windowIndices.isEmpty() && dp[i - 1] >= dp[windowIndices.peekLast()]) {
                windowIndices.pollLast();
            }

            windowIndices.offerLast(i - 1);

            if (windowIndices.peekFirst() < i - k) {
                windowIndices.pollFirst();
            }

            dp[i] = dp[windowIndices.peekFirst()] + nums[i];
        }

        return dp[n - 1];
    }

}
