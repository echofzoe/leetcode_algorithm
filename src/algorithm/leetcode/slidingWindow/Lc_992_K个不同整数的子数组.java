package algorithm.leetcode.slidingWindow;

import java.security.Key;
import java.util.Arrays;

public class Lc_992_K个不同整数的子数组 {

    // K 个不同整数的子数组
    // https://leetcode-cn.com/problems/subarrays-with-k-different-integers/

    public static void main(String[] args) {
        Lc_992_K个不同整数的子数组 lc = new Lc_992_K个不同整数的子数组();
        int[] A = {1,3,2,3};
        int K = 3;

        System.out.println(Arrays.toString(A) + "中好子数组的数目是" + lc.subarraysWithKDistinct(A, K));
    }

    public int subarraysWithKDistinct(int[] A, int K) {
        // A的子数组中不同整数的个数恰好为K的个数 = A的子数组中不同整数的个数最多为K的个数 - A的子数组中不同整数的个数最多为K-1的个数
        return subarraysWithMostKDistinct(A, K) - subarraysWithMostKDistinct(A, K - 1);
    }

    private int subarraysWithMostKDistinct(int[] A, int K) {
        int n = A.length;

        // 1 <= A[i] <= A.length
        int[] freq = new int[n + 1];

        int lo = 0, hi = 0, diff = 0, res = 0;
        while (hi < n) {
            if (freq[A[hi]] == 0) diff++;
            freq[A[hi]]++;

            while (diff > K) {
                freq[A[lo]]--;
                if (freq[A[lo]] == 0) diff--;

                lo++;
            }

            res += hi - lo + 1;
            hi++;
        }

        return res;
    }

}
