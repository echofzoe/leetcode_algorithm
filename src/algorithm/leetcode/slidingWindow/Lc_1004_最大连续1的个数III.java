package algorithm.leetcode.slidingWindow;

import java.util.Arrays;

public class Lc_1004_最大连续1的个数III {

    // 最大连续1的个数 III
    // https://leetcode-cn.com/problems/max-consecutive-ones-iii/

    public static void main(String[] args) {
        Lc_1004_最大连续1的个数III lc = new Lc_1004_最大连续1的个数III();
        int[] A = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int K = 3;

        System.out.println(Arrays.toString(A) + "中可以进行最多将" + K + "个0变为1的操作，则此数组中仅包含1的最长（连续）子数组的长度是" + lc.longestOnes(A, K));
    }

    // 滑动窗口 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int longestOnes(int[] A, int K) {
        int n = A.length;

        int lo = 0, hi = 0, res = 0, zeroCnt = 0;
        while (hi < n) {
            if (A[hi] == 0) {
                zeroCnt++;
            }

            while (zeroCnt > K) {
                if (A[lo++] == 0) {
                    zeroCnt--;
                }
            }

            res = Math.max(res, hi - lo + 1);
            hi++;
        }

        return res;
    }

}
