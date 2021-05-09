package algorithm.leetcode.search.binarySearch;

import java.util.Arrays;

/**
 * 制作 m 束花所需的最少天数
 * <P>https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/</P>
 *
 * @author echofzoe
 * @since 2021.5.9
 */
public class Lc_1482_制作m束花所需的最少天数 {

    public static void main(String[] args) {
        Lc_1482_制作m束花所需的最少天数 lc = new Lc_1482_制作m束花所需的最少天数();
        int[] bloomDay = {7, 7, 7, 7, 12, 7, 7};
        int m = 2, k = 3;

        System.out.println("给你一个整数数组 bloomDay = " + Arrays.toString(bloomDay) + "，以及两个整数 m = " + m + " 和 k = " + k + " 。\n" +
                "现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。\n" +
                "花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。\n" +
                "\n" +
                "则从花园中摘 " + m + " 束花需要等待的最少的天数(如果不能摘到则返回-1)是 " + lc.minDays(bloomDay, m, k));
    }

    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (m > n / k) return -1;

        int lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE;
        for (int j : bloomDay) {
            lo = Math.min(lo, j);
            hi = Math.max(hi, j);
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canMake(bloomDay, mid, m, k)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private boolean canMake(int[] bloomDay, int days, int m, int k) {
        int a = 0;  // 花束
        int b = 0;  // 花
        int n = bloomDay.length;

        for (int i = 0; i < n && a < m; i++) {
            if (bloomDay[i] <= days) {
                b++;
                if (b == k) {
                    a++;
                    b = 0;
                }
            } else {
                b = 0;
            }
        }

        return a >= m;
    }

}
