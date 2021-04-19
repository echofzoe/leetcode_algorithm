package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;
import java.util.Comparator;

public class Lc_1024_视频拼接 {

    // 视频拼接
    // https://leetcode-cn.com/problems/video-stitching/solution/shi-pin-pin-jie-by-leetcode-solution/

    public static void main(String[] args) {
        Lc_1024_视频拼接 lc = new Lc_1024_视频拼接();

        int[][] clips = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
        int[][] clips1 = {{0, 6}, {1, 7}, {0, 9}, {2, 10}, {0, 4}, {0, 2}, {10, 10}, {7, 9}, {0, 3}};
        int T = 10, T1 = 15;

        System.out.println("将片段 " + Arrays.deepToString(clips) + " 进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, " + T + "]）所需的最小片段数目是 " + lc.videoStitching(clips, T));  // 3
        System.out.println("将片段 " + Arrays.deepToString(clips1) + " 进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, " + T1 + "]）所需的最小片段数目是 " + lc.videoStitching1(clips1, T1));  // -1
    }

    // DP - 时间复杂度 O(T*N) 其中T是区间的长度，N是子区间的数量 - 空间复杂度 O(T)
    // - dp[i] 表示将片段i剪辑拼接到整个运动过程所需的最小片段数目
    // - 状态转移方程: {
    //   dp[i] = min{dp[j]} + 1, aj < i <= bj
    // }
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

    // 贪心 - 时间复杂度 O() - 空间复杂度 O()
    public int videoStitching1(int[][] clips, int T) {
        int[] max = new int[T];

        for (int[] clip : clips) {
            if (clip[0] < T) {
                max[clip[0]] = Math.max(max[clip[0]], clip[1]);
            }
        }

        int pre = 0, last = 0, res = 0;

        // 枚举每一个位置
        for (int i = 0; i < T; i++) {
            last = Math.max(last, max[i]);  // 更新能达到的最远位置

            if (i == last) return -1;  // 到不了下一个位置，返回-1

            if (i == pre) {
                // 到达了新区间的开始
                pre = last;
                res++;
            }
        }

        return res;
    }

}
