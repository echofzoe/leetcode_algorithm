package algorithm.leetcode.math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Lc_888_公平的糖果棒交换 {

    // 公平的糖果棒交换
    // https://leetcode-cn.com/problems/fair-candy-swap/

    public static void main(String[] args) {
        Lc_888_公平的糖果棒交换 lc = new Lc_888_公平的糖果棒交换();
        int[] A = {1, 2, 5}, B = {2, 4};

        System.out.println(Arrays.toString(A) + "与" + Arrays.toString(B) + "各自交换一根糖果后可以使得他们的糖果总数相等，那么，他们交换的糖果分别是" + Arrays.toString(lc.fairCandySwap(A, B)));
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int[] fairCandySwap(int[] A, int[] B) {
        // aSum = A[0] + A[1] + ...
        // bSum = B[0] + B[1] + ...
        // aSum - A[i] + B[j] = bSum - B[j] + A[i]
        // 则 A[i] = (aSum - bSum) / 2 + B[j]
        int[] res = new int[2];

        int aSum = Arrays.stream(A).sum(), bSum = Arrays.stream(B).sum(), delta = (aSum - bSum) / 2;
        Set<Integer> set = new HashSet<>();
        for (int a : A) {
            set.add(a);
        }

        for (int b : B) {
            int a = delta + b;
            if (set.contains(a)) {
                res[0] = a;
                res[1] = b;
            }
        }

        return res;
    }

}
