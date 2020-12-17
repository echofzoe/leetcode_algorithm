package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_738_单调递增的数字 {

    // 单调递增的数字
    // https://leetcode-cn.com/problems/monotone-increasing-digits/

    public static void main(String[] args) {
        Lc_738_单调递增的数字 lc = new Lc_738_单调递增的数字();
        int N = 221;

        System.out.println("小于等于非负整数" + N + "，同时满足各个位数上的数字是单调递增的整数是" + lc.monotoneIncreasingDigits(N));
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int monotoneIncreasingDigits(int N) {
        char[] str = Integer.toString(N).toCharArray();

        int n = str.length;

        int index = -1;
        for (int i = n - 1; i >= 1; i--) {
            if (str[i] >= str[i - 1]) continue;

            str[i - 1]--;
            index = i;
        }

        if (index != -1) {
            for (int j = index; j < n; j++) {
                str[j] = '9';
            }
        }

        return Integer.parseInt(String.valueOf(str));
    }

}
