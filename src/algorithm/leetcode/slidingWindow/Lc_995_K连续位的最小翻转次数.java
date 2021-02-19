package algorithm.leetcode.slidingWindow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Lc_995_K连续位的最小翻转次数 {

    // K 连续位的最小翻转次数
    // https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips/

    public static void main(String[] args) {
        Lc_995_K连续位的最小翻转次数 lc = new Lc_995_K连续位的最小翻转次数();
        int[] A = {0, 0, 0, 1, 0, 1, 1, 0};
        int K = 3;

        System.out.println("将" + Arrays.toString(A) + "进行" + K + "连续位翻转使得整个数组全为1的最小次数是" + lc.minKBitFlips1(A, K));
    }

    // 滑动窗口 - 时间复杂度 O(N) - 空间复杂度 O(K)=O(1)
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;

        int res = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (queue.size() > 0 && i > queue.peek() + K - 1) {
                queue.poll();
            }

            if ((A[i] == 0 && (queue.size() % 2) == 0) || (A[i] == 1 && ((queue.size() % 2) == 1))) {
                if (i + K - 1 >= n) return -1;
                queue.offer(i);
                res++;
            }
        }

        return res;
    }

    // 暴力贪心 - 时间复杂度 O(N*K) - 空间复杂度 O(1)
    public int minKBitFlips1(int[] A, int K) {
        int n = A.length;

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] == 0) {
                if (i + K - 1 >= n) return -1;

                for (int j = 0; j < K; j++) A[i + j] ^= 1;

                res++;
            }
        }

        return res;
    }

}
