package algorithm.leetcode.array.diff;

import java.util.Arrays;

/**
 * 航班预订统计
 * <P>https://leetcode-cn.com/problems/corporate-flight-bookings/</P>
 *
 * @author echofzoe
 * @since 2021.8.31
 */
public class Lc_1109_航班预订统计 {

    public static void main(String[] args) {
        Lc_1109_航班预订统计 lc = new Lc_1109_航班预订统计();

        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int n = 5;

        System.out.println("这里有 n 个航班，它们分别从 1 到 n 进行编号。\n" +
                "有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。\n" +
                "请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。\n");
        System.out.println("输入：bookings = " + Arrays.toString(bookings) + ", n = " + n);
        System.out.println("输出：" + Arrays.toString(lc.corpFlightBookings(bookings, n)));  // [10,55,45,25,25]
    }

    // diff - 时间复杂度 O(N + M) N为结果数组的长度，M为预订记录的长度 - 空间复杂度 O(1) 不计返回值
    // 解题思路：对预订记录做差分，再求查分数组的前缀和
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int m = bookings.length;

        int[] diff = new int[n];

        for (int[] b : bookings) {
            diff[b[0] - 1] += b[2];
            if (b[1] < n) diff[b[1]] -= b[2];
        }

        for (int i = 1; i < n; i++) {
            diff[i] += diff[i - 1];
        }

        return diff;
    }

}
