package algorithm.leetcode.dpAndGreedy.连续子数组或子序列的DP问题;

import java.util.Arrays;

/**
 * 最长重复子数组
 * <P>https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/</P>
 *
 * @author echofzoe
 * @since 2021.7.20
 */
public class Lc_718_最长重复子数组 {

    public static void main(String[] args) {
        Lc_718_最长重复子数组 lc = new Lc_718_最长重复子数组();

        int[] A = {1, 2, 3, 2, 1}, B = {3, 2, 1, 4, 7};

        System.out.println("给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。");
        System.out.println("输入：A = " + Arrays.toString(A) + ", B = " + Arrays.toString(B));
        System.out.println("输出：" + lc.findLength(A, B));  // 3
    }

    // DP - 时间复杂度 O(MN) - 空间复杂度 O(MN) M为A数组长度，N为B数组长度
    public int findLength(int[] A, int[] B) {
        int na = A.length, nb = B.length;

        /*
            状态转移方程：{
                dp[i][j] = A[i - 1] == B[j - 1] ? dp[i - 1][j - 1] + 1 : 0, 1<=i<=na && 1<=j<=nb
            }
         */
        int[][] dp = new int[na + 1][nb + 1];

        int res = 0;
        for (int i = 1; i <= na; i++) {
            for (int j = 1; j <= nb; j++) {
                dp[i][j] = A[i - 1] == B[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
                res = Math.max(res, dp[i][j]);
            }
        }

        return res;
    }

    // 滑动窗口 - 时间复杂度 O((M+N)*min(M,N)) - 空间复杂度 O(1) M为A数组长度，N为B数组长度
    public int findLength1(int[] A, int[] B) {
        int na = A.length, nb = B.length;

        int res = 0;
        for (int i = 0; i < na; i++) {
            int len = Math.min(na - i, nb);
            res = Math.max(res, maxLen(A, i, B, 0, len));
        }

        for (int i = 0; i < nb; i++) {
            int len = Math.min(nb - i, na);
            res = Math.max(res, maxLen(A, 0, B, i, len));
        }
        
        return res;
    }

    private int maxLen(int[] A, int startA, int[] B, int startB, int len) {
        int res = 0, cnt = 0;

        for (int i = 0; i < len; i++) {
            if (A[startA + i] == B[startB + i]) cnt++;
            else cnt = 0;

            res = Math.max(res, cnt);
        }

        return res;
    }

}
