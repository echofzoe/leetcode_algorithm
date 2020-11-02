package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_845_数组中的最长山脉 {

    // 数组中的最长山脉
    // https://leetcode-cn.com/problems/longest-mountain-in-array/

    public static void main(String[] args) {
        Lc_845_数组中的最长山脉 lc = new Lc_845_数组中的最长山脉();
        int[] input = {2, 1, 4, 7, 3, 2, 5};

        System.out.print("数组" + Arrays.toString(input) + "中");
        int temp = lc.longestMountain_DP(input);
        if (temp > 0) {
            System.out.println("含有山脉,最长的山脉长度为 " + temp + ".");
        } else {
            System.out.println("不含山脉.");
        }
    }

    // DP - 枚举山顶 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int longestMountain_DP(int[] A) {
        int len = A.length;
        if (len < 3) return 0;

        int[] left = new int[len];
        for (int i = 1; i < len; i++) {
            if (A[i] > A[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 0;
            }
        }

        int[] right = new int[len];
        for (int i = len - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 0;
            }
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            if (left[i] > 0 && right[i] > 0) {
                res = Math.max(res, left[i] + right[i] + 1);
            }
        }

        return res;
    }

    // 双指针 - 枚举山脚 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int longestMountain_DoublePoint(int[] A) {
        int len = A.length;
        int res = 0;
        int left = 0;

        while (left + 2 < len) {
            int right = left + 1;
            if (A[left] < A[left + 1]) {
                while (right + 1 < len && A[right] < A[right + 1]) {
                    right++;
                }
                if (right < len - 1 && A[right] > A[right + 1]) {
                    while (right + 1 < len && A[right] > A[right + 1]) {
                        right++;
                    }
                    res = Math.max(res, right - left + 1);
                }
            }
            left = right;
        }

        return res;
    }
}
