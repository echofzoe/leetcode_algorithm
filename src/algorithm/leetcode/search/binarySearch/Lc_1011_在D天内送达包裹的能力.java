package algorithm.leetcode.search.binarySearch;

import java.util.Arrays;

/**
 * 在 D 天内送达包裹的能力
 * <P>https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/</P>
 *
 * @author echofzoe
 * @since 2021.4.26
 */
public class Lc_1011_在D天内送达包裹的能力 {

    public static void main(String[] args) {
        Lc_1011_在D天内送达包裹的能力 lc = new Lc_1011_在D天内送达包裹的能力();

        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int D = 5;

        System.out.println("船舶最低载重" + lc.shipWithinDays(weights, D) + "就能在" + D + "天内送达" + Arrays.toString(weights) + "中的所有包裹");  // 15
    }

    // 二分 - 时间复杂度 O(Nlog(sum(weights))) - 空间复杂度 O(1)
    public int shipWithinDays(int[] weights, int D) {
        int lo = Arrays.stream(weights).max().orElse(0), mid, hi = Arrays.stream(weights).sum();
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;

            if (check(mid, weights) > D) lo = mid + 1;
            else hi = mid;
        }

        return lo;
    }

    private int check(int mid, int[] weights) {
        int sum = 0, days = 1;
        for (int w : weights) {
            if (sum + w > mid) {
                sum = 0;
                days++;
            }

            sum += w;
        }

        return days;
    }

}
