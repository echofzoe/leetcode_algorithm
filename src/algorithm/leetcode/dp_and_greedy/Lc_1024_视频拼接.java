package algorithm.leetcode.dp_and_greedy;

import java.util.Arrays;

public class Lc_1024_视频拼接 {

    // 视频拼接
    // https://leetcode-cn.com/problems/video-stitching/solution/shi-pin-pin-jie-by-leetcode-solution/

    public static void main(String[] args) {
        Lc_1024_视频拼接 lc = new Lc_1024_视频拼接();
        int[][] clips = {{0, 4}, {2, 8}};
        int T = 5;

        System.out.println("将片段 " + Arrays.deepToString(clips) + " 进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）所需的最小片段数目是 " + lc.videoStitching(clips, T));
    }

    public int videoStitching(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= T; i++) {
            for (int[] clip : clips) {
                if (clip[0] < i && i <= clip[1]) {
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }

        return dp[T] == Integer.MAX_VALUE ? -1 : dp[T];
    }
}
