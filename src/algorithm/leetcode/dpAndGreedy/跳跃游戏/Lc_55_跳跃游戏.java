package algorithm.leetcode.dpAndGreedy.跳跃游戏;

import java.util.Arrays;

public class Lc_55_跳跃游戏 {

    // 跳跃游戏
    // https://leetcode-cn.com/problems/jump-game/

    public static void main(String[] args) {
        Lc_55_跳跃游戏 lc = new Lc_55_跳跃游戏();
        int[] input1 = {2, 3, 1, 1, 4};
        int[] input2 = {3, 2, 1, 0, 4};

        System.out.println("在跳跃游戏" + Arrays.toString(input1) + "中，" + (lc.canJumpGreedy(input1) ? "能" : "不能") + "到达最后一个位置");
        System.out.println("在跳跃游戏" + Arrays.toString(input2) + "中，" + (lc.canJumpDP(input2) ? "能" : "不能") + "到达最后一个位置");
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean canJumpGreedy(int[] nums) {
        int n = nums.length;

        int last = 0;  // 实时最远到达位置

        for (int i = 0; i < n; i++) {
            last = Math.max(last, i + nums[i]);

            if (last >= n - 1) return true;

            if (last == i) break;
        }

        return false;
    }

    // DFS 记忆化 - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    boolean flag = false;

    public boolean canJumpDFS(int[] nums) {

        boolean[] visited = new boolean[nums.length];

        dfs(nums, 0, visited);

        return flag;
    }

    private void dfs(int[] nums, int start, boolean[] visited) {
        if (start >= nums.length - 1) {
            flag = true;
            return;
        }

        if (flag) return;

        if (visited[start]) return;

        visited[start] = true;

        if (nums[start] == 0) return;

        for (int i = 1; i <= nums[start]; i++) {
            dfs(nums, start + i, visited);
        }
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
