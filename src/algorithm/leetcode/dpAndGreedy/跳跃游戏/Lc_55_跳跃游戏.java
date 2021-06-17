package algorithm.leetcode.dpAndGreedy.跳跃游戏;

import java.util.Arrays;

/**
 * 跳跃游戏
 * <P>https://leetcode-cn.com/problems/jump-game/</P>
 *
 * @author echofzoe
 * @since unknown
 * @updated  2021.6.17
 */
public class Lc_55_跳跃游戏 {

    public static void main(String[] args) {
        Lc_55_跳跃游戏 lc = new Lc_55_跳跃游戏();
        int[] input1 = {2, 3, 1, 1, 4};
        int[] input2 = {3, 2, 1, 0, 4};
        
        System.out.println("给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。\n" +
                "数组中的每个元素代表你在该位置可以跳跃的最大长度。\n" +
                "判断你是否能够到达最后一个下标。\n");
        System.out.println("输入：" + Arrays.toString(input1));
        System.out.println("输出：" + lc.canJumpGreedy(input1));
        System.out.println("输入：" + Arrays.toString(input2));
        System.out.println("输出：" + lc.canJumpDP(input2));
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean canJumpGreedy(int[] nums) {
        int n = nums.length;

        int far = 0;
        for (int i = 0; i < n && far < n - 1; i++) {
            far = Math.max(far, i + nums[i]);
            if (far == i) break;
        }

        return far >= n - 1;
    }

    // DFS + 记忆化（超时） - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    private boolean[] vis;
    private int[] nums;
    private boolean res;
    private int n;

    public boolean canJumpDFS(int[] nums) {
        n = nums.length;
        vis = new boolean[n];
        this.nums = nums;

        dfs(0);

        return res;
    }

    private void dfs(int idx) {
        // exit
        if (idx >= n - 1) {
            res = true;
            return;
        }

        // pruning
        if (vis[idx] || res) return;

        // mark
        vis[idx] = true;

        // dfs
        for (int i = 1; i <= nums[idx]; i++) dfs(idx + i);
    }

    // DP - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public boolean canJumpDP(int[] nums) {
        int n = nums.length;

        boolean[] dp = new boolean[n];
        // base case
        dp[0] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && j + nums[j] >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n - 1];
    }
}
