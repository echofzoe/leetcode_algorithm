package algorithm.leetcode.array;

import java.util.Arrays;

public class Lc_976_三角形的最大周长 {

    // 三角形的最大周长
    // https://leetcode-cn.com/problems/largest-perimeter-triangle/

    public static void main(String[] args) {
        Lc_976_三角形的最大周长 lc = new Lc_976_三角形的最大周长();
        int[] input = {3, 2, 3, 4};

        System.out.println(Arrays.toString(input) + "中由三个长度组成的、面积不为零的三角形的最大周长是" + lc.largestPerimeter(input));
    }

    // 贪心 - 时间复杂度O(N*logN) 为排序时间复杂度 - 空间复杂度(logN) 为排序所需额外空间
    public int largestPerimeter(int[] A) {
        int n = A.length;
        if (n < 3) return 0;

        Arrays.sort(A);
        System.out.println(Arrays.toString(A));;

        for (int i = n - 1; i >= 2; i--) {
            int tmp = A[i - 1] + A[i - 2];
            if (tmp > A[i]) {
                return tmp + A[i];
            }
        }

        return 0;
    }

}
