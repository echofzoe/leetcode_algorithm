package algorithm.leetcode.周赛.lc_2021_2_21;

import java.util.Arrays;

public class Lc_5686_移动所有球到每个盒子所需的最小操作数 {

    // 移动所有球到每个盒子所需的最小操作数
    // https://leetcode-cn.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/

    public static void main(String[] args) {
        Lc_5686_移动所有球到每个盒子所需的最小操作数 lc = new Lc_5686_移动所有球到每个盒子所需的最小操作数();
//        String boxes = "001011";
        String boxes = "110";

        System.out.println("在球盒模型\"" + boxes + "\"中，将所有小球移动到第i个盒子所需的最小操作数数组是" + Arrays.toString(lc.minOperations(boxes)));
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int[] minOperations(String boxes) {
        int n = boxes.length();

        // left - 当前盒子左边的小球数
        // right - 当前盒子右边的小球数（包括他自己）
        int left = 0, right = 0;

        int[] dp = new int[n];
        // base case
        for (int i = 0; i < n; i++) {
            if (boxes.charAt(i) == '1') {
                right++;
                dp[0] += i;
            }
        }

        for (int i = 1; i < n; i++) {
            if (boxes.charAt(i - 1) == '1') {
                right--;
                left++;
            }

            dp[i] = dp[i - 1] + left - right;
        }

        return dp;
    }

}
