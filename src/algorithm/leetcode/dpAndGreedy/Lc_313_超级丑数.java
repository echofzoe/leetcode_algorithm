package algorithm.leetcode.dpAndGreedy;

import java.util.*;

/**
 * 超级丑数
 * <P>https://leetcode-cn.com/problems/super-ugly-number/</P>
 *
 * @author echofzoe
 * @since 2021.8.9
 */
public class Lc_313_超级丑数 {

    public static void main(String[] args) {
        Lc_313_超级丑数 lc = new Lc_313_超级丑数();

        int n = 12;
        int[] primes = {2, 7, 13, 19};

        System.out.println("超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。\n" +
                "给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。\n" +
                "题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。\n");
        System.out.println("输入：n = " + n + ", primes = " + Arrays.toString(primes));
        System.out.println("输出：" + lc.nthSuperUglyNumber(n, primes));  // 32
    }

    // dp - 时间复杂度 O(NM) - 空间复杂度 O(N+M) M为数组primes的长度
    public int nthSuperUglyNumber(int n, int[] primes) {
        int m = primes.length;

        // dp[i] 表示第i个超级丑数
        int[] dp = new int[n + 1];
        // base case
        dp[1] = 1;

        int[] ps = new int[m];
        Arrays.fill(ps, 1);

        for (int i = 2; i <= n; i++) {
            int[] nums = new int[m];
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < m; j++) {
                nums[j] = dp[ps[j]] * primes[j];
                min = Math.min(min, nums[j]);
            }

            dp[i] = min;
            for (int j = 0; j < m; j++) {
                if (min == nums[j]) {
                    ps[j]++;
                }
            }
        }

        return dp[n];
    }

    // 小根堆 - 时间复杂度 O(NM*log(NM)) - 空间复杂度 O(NM) M为数组primes的长度
    public int nthSuperUglyNumber1(int n, int[] primes) {
        int m = primes.length;

        Queue<Long> pq = new PriorityQueue<>();
        pq.offer(1L);

        Set<Long> set = new HashSet<>();
        set.add(1L);

        for (int i = 2; i <= n; i++) {
            long cur = pq.poll();
            for (int j = 0; j < m; j++) {
                long tmp = cur * primes[j];
                if (set.add(tmp)) {
                    pq.offer(tmp);
                }
            }
        }

        return (int) pq.poll().longValue();
    }

}
