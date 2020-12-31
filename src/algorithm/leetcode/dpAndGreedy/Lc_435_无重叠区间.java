package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;
import java.util.Comparator;

public class Lc_435_无重叠区间 {

    // 无重叠区间
    // https://leetcode-cn.com/problems/non-overlapping-intervals/

    public static void main(String[] args) {
        Lc_435_无重叠区间 lc = new Lc_435_无重叠区间();
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};

        System.out.println("移除至少" + lc.eraseOverlapIntervalsDp(intervals) + "个区间后，可使得剩余区间互不重叠");
        System.out.println("移除至少" + lc.eraseOverlapIntervalsGreedy(intervals2) + "个区间后，可使得剩余区间互不重叠");
    }

    // DP - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public int eraseOverlapIntervalsDp(int[][] intervals) {
        int n = intervals.length;
        if (n == 0) return 0;

        Arrays.sort(intervals, Comparator.comparing(x -> x[0]));

        // dp[i] 表示「以区间 i 为最后一个区间，可以选出的区间数量的最大值」
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (intervals[j][1] <= intervals[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return n - Arrays.stream(dp).max().getAsInt();
    }

    // 贪心 - 时间复杂度 O(N*logN) - 空间复杂度 O(logN)
    public int eraseOverlapIntervalsGreedy(int[][] intervals) {
        int n = intervals.length;
        if (n == 0) return 0;

        Arrays.sort(intervals, Comparator.comparing(x -> x[1]));

        int max = 1, right = intervals[0][1];
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= right) {
                max++;
                right = intervals[i][1];
            }
        }

        return n - max;
    }

}
