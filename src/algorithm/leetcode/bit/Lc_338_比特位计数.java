package algorithm.leetcode.bit;

import java.util.Arrays;

public class Lc_338_比特位计数 {

    // 比特位计数
    // https://leetcode-cn.com/problems/counting-bits/

    public static void main(String[] args) {
        Lc_338_比特位计数 lc = new Lc_338_比特位计数();
        int num = 5;

        System.out.println("对于[0," + num + "]范围内的每个数字，其二进制中1的数目的数组是" + Arrays.toString(lc.countBitsDp2(num)));
    }

    // BF - 时间复杂度 O(N * num) - 空间复杂度 O(1)
    public int[] countBitsBf(int num) {
        int[] res = new int[num + 1];

        for (int i = 0; i <= num; i++) {
            int tmp = i;
            while (tmp > 0) {
                tmp &= (tmp - 1);
                res[i]++;
            }
        }

        return res;
    }
    
    // 动态规划 + 最低设置位 - 时间复杂度 O(num) - 空间复杂度 O(1)
    public int[] countBitsDp1(int num) {
        int[] res = new int[num + 1];

        for (int i = 1; i <= num; i++) {
            // 操作 i & (i - 1) 将 i 的二进制表示中的最后一个 1 变成 0
            res[i] = res[i & (i - 1)] + 1;
        }

        return res;
    }

    // 动态规划 + 最低有效位 - 时间复杂度 O(num) - 空间复杂度 O(1)
    public int[] countBitsDp2(int num) {
        int[] res = new int[num + 1];

        for (int i = 1; i <= num; i++) {
            // 操作 i >> 1 会把 i 的最低位去掉
            // - 若 i 为偶数（偶数最低位是0，右移一位对二进制中1的个数没有影响），则 res[i] = res[i >> 1]
            // - 若 i 为奇数，则 res[i] = res[i >> 1] + 1
            res[i] = res[i >> 1] + (i & 1);
        }

        return res;
    }

}
