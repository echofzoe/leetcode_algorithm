package algorithm.leetcode.math;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 丑数 II
 * https://leetcode-cn.com/problems/ugly-number-ii/
 *
 * @author echofzoe
 * @since 2021.4.11
 */
public class Lc_264_丑数_II {

    public static void main(String[] args) {
        Lc_264_丑数_II lc = new Lc_264_丑数_II();

        int n = 10;

        System.out.println("第" + n + "个丑数是" + lc.nthUglyNumber2(n));
    }

    // 小根堆 - 时间复杂度 O(NlogN) 得到第n个丑数需要进行n次循环，每次循环都要从最小堆中取出1个元素以及向最小堆中加入最多3个元素，因此每次循环的时间复杂度是O(logN + log3N) = O(logN) - 空间复杂度 O(N)
    public int nthUglyNumber1(int n) {
        Queue<Long> pq = new PriorityQueue<>() {{
            offer(1L);
        }};
        Set<Long> set = new HashSet<>() {{
            add(1L);
        }};

        for (int i = 0; i < n - 1; i++) {
            long a = pq.poll();

            long[] t = new long[3];
            t[0] = 2 * a;
            t[1] = 3 * a;
            t[2] = 5 * a;

            for (int j = 0; j < 3; j++) {
                if (!set.contains(t[j])) {
                    set.add(t[j]);
                    pq.offer(t[j]);
                }
            }
        }

        long res = pq.poll();
        return (int) res;
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int nthUglyNumber2(int n) {
        int[] dp = new int[n + 1];
        // base case
        dp[1] = 1;

        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int a = dp[p2] * 2, b = dp[p3] * 3, c = dp[p5] * 5;
            dp[i] = Math.min(a, (Math.min(b, c)));
            if (dp[i] == a) p2++;
            if (dp[i] == b) p3++;
            if (dp[i] == c) p5++;
        }

        return dp[n];
    }

}
