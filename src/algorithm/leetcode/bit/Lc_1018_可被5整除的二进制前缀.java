package algorithm.leetcode.bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc_1018_可被5整除的二进制前缀 {

    // 可被 5 整除的二进制前缀
    // - 1 <= A.length <= 30000
    // - A[i] 为 0 或 1
    // https://leetcode-cn.com/problems/binary-prefix-divisible-by-5/

    public static void main(String[] args) {
        Lc_1018_可被5整除的二进制前缀 lc = new Lc_1018_可被5整除的二进制前缀();
        int[] A = {0, 1, 1, 1, 1, 1};

        System.out.println("二进制数组" + Arrays.toString(A) + "中可被5整除的二进制前缀对应的布尔值是" + lc.prefixesDivBy5(A));
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public List<Boolean> prefixesDivBy5(int[] A) {
        int n = A.length;
        List<Boolean> res = new ArrayList<>();

        if (n == 0) return res;

        int prefix = 0;
        for (int i = 0; i < n; i++) {
            prefix = ((prefix << 1) + A[i]) % 5;
            res.add(prefix == 0);
        }

        return res;
    }

}
