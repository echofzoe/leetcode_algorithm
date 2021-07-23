package algorithm.leetcode.array.diff;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 检查是否区域内所有整数都被覆盖
 * <P>https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered/</P>
 *
 * @author echofzoe
 * @since 2021.7.23
 */
public class Lc_1893_检查是否区域内所有整数都被覆盖 {

    public static void main(String[] args) {
        Lc_1893_检查是否区域内所有整数都被覆盖 lc = new Lc_1893_检查是否区域内所有整数都被覆盖();

        int[][] ranges = {{1, 2}, {3, 4}, {5, 6}};
        int left = 2, right = 5;

        System.out.println("给你一个二维整数数组 ranges 和两个整数 left 和 right。每个ranges[i] = [starti, endi]表示一个从 starti 到 endi 的闭区间。\n" +
                "如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true，否则返回 false。\n" +
                "已知区间 ranges[i] = [starti, endi]，如果整数 x 满足 starti <= x <= endi，那么我们称整数 x 被覆盖了。\n");
        System.out.println("输入：ranges = " + Arrays.deepToString(ranges));
        System.out.println("输出：" + lc.isCovered(ranges, left, right));  // true
    }

    // DIFF - 时间复杂度 O(N) - 空间复杂度 O(N)
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];

        // 预处理差分数组
        for (int[] range : ranges) {
            diff[range[0]]++;
            diff[range[1] + 1]--;
        }

        int[] sum = new int[52];
        for (int i = 1; i <= 51; i++) {
            sum[i] = sum[i - 1] + diff[i];

            // 从left到right判断是否满足 sum > 0
            if (i >= left && i <= right && sum[i] <= 0) return false;
        }
        
        return true;
    }

    // 暴力 - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public boolean isCovered1(int[][] ranges, int left, int right) {
        int[] cnt = new int[51];
        for (int[] range : ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                cnt[i]++;
            }
        }

        for (int i = left; i <= right; i++) {
            if (cnt[i] == 0) return false;
        }

        return true;
    }

    // 排序 - 时间复杂度 O(NlogN) - 空间复杂度 O(logN)
    public boolean isCovered2(int[][] ranges, int left, int right) {
        Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));
        for (int[] range : ranges) {
            int l = range[0], r = range[1];

            if (l <= left && left <= r) {
                left = r + 1;
            }
        }

        return left > right;
    }

}
