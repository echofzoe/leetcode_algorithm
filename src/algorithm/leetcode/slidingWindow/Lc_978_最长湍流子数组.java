package algorithm.leetcode.slidingWindow;

import java.util.Arrays;
import java.util.logging.Level;

public class Lc_978_最长湍流子数组 {

    // 最长湍流子数组
    // https://leetcode-cn.com/problems/longest-turbulent-subarray/

    public static void main(String[] args) {
        Lc_978_最长湍流子数组 lc = new Lc_978_最长湍流子数组();
        int[] arr = {9, 4, 2, 10, 7, 8, 8, 1, 9};

        System.out.println(Arrays.toString(arr) + "中的最长湍流子数组的长度是" + lc.maxTurbulenceSizeSlidingWindow(arr));
    }

    // 滑动窗口 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int maxTurbulenceSizeSlidingWindow(int[] arr) {
        int n = arr.length;

        int lo = 0, hi = 0, res = 0;
        while (hi < n - 1) {
            if (lo == hi) {
                if (arr[lo] == arr[lo + 1]) lo++;
                hi++;
            } else {
                if (arr[hi - 1] < arr[hi] && arr[hi] > arr[hi + 1]) hi++;
                else if (arr[hi - 1] > arr[hi] && arr[hi] < arr[hi - 1]) hi++;
                else lo = hi;
            }

            res = Math.max(res, hi - lo + 1);
        }

        return res;
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N) 可优化为O(1)
    public int maxTurbulenceSizeDp(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;

        int[][] dp = new int[n][2];
        for (int[] tmp : dp) Arrays.fill(tmp, 1);

        int res = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) dp[i][0] += dp[i - 1][1];
            else if (arr[i - 1] > arr[i]) dp[i][1] += dp[i - 1][0];

            res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
        }

        return res;
    }

}
