package algorithm.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lc_454_四数相加II {

    // 四数相加 II
    // https://leetcode-cn.com/problems/4sum-ii/

    public static void main(String[] args) {
        Lc_454_四数相加II lc = new Lc_454_四数相加II();
        int[] A = {1, 2};
        int[] B = {-2, -1};
        int[] C = {-1, 2};
        int[] D = {0, 2};

        System.out.println("四个包含整数的数组列表" + Arrays.toString(A) + ", " + Arrays.toString(B) + ", " + Arrays.toString(C) + ", " + Arrays.toString(D) + "中有" + lc.fourSumCount(A, B, C, D) + "个元组(i,j,k,l)，使得A[i]+B[j]+C[k]+D[l]=0");
    }

    // 哈希 - 时间复杂度 O(N^2) - 空间复杂度 O(N^2)
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;

        Map<Integer, Integer> mapAB = new HashMap<>();
        for (int i : A) {
            for (int j : B) {
                int tmp = i + j;
                mapAB.put(tmp, mapAB.getOrDefault(tmp, 0) + 1);
            }
        }

        for (int i : C) {
            for (int j : D) {
                int tmp = i + j;
                if (mapAB.containsKey(-tmp)) {
                    res += mapAB.get(-tmp);
                }
            }
        }

        return res;
    }

}
