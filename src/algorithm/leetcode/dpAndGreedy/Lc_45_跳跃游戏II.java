package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_45_跳跃游戏II {

    // 跳跃游戏 II
    // https://leetcode-cn.com/problems/jump-game-ii/

    public static void main(String[] args) {
        Lc_45_跳跃游戏II lc = new Lc_45_跳跃游戏II();
        int[] input = {2, 3, 1, 1, 4};

        System.out.println("跳到非负整数数组" + Arrays.toString(input) + "的最后一个位置的最小跳跃数是" + lc.jumpGreedy(input));
    }

    // 贪心 从前往后 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int jumpGreedy(int[] nums) {
        int n = nums.length, res = 0;

        int nextMaxPosition = 0, curEndPosition = 0;

        for (int i = 0; i < n - 1; i++) {
            nextMaxPosition = Math.max(nextMaxPosition, nums[i] + i);
            if (i == curEndPosition) {
                curEndPosition = nextMaxPosition;
                res++;
            }
        }

        return res;
    }
    
    // 贪心 从后往前 - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    public int jumpGreedy2(int[] nums) {
        int endPosition = nums.length - 1, res = 0;

        while (endPosition > 0) {
            for (int i = 0; i < endPosition; i++) {
                if (nums[i] + i >= endPosition) {
                    endPosition = i;
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
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        // base case
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int nextPos = i + 1;
            for (int j = 1; j <= nums[i] && nextPos < n; j++, nextPos++) {
                dp[nextPos] = Math.min(dp[nextPos], dp[i] + 1);
            }
        }

        return dp[n - 1];
    }

}
