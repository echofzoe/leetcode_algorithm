package algorithm.leetcode.math;

import java.util.Arrays;

public class Lc_1232_缀点成线 {

    // 缀点成线
    // https://leetcode-cn.com/problems/check-if-it-is-a-straight-line/

    public static void main(String[] args) {
        Lc_1232_缀点成线 lc = new Lc_1232_缀点成线();
        int[][] coordinates = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}};
        int[][] coordinates1 = {{1, 2}, {2, 3}, {3, 5}};

        System.out.println(Arrays.deepToString(coordinates) + "中的坐标" + (lc.checkStraightLine(coordinates) ? "在" : "不在") + "一条直线上");
        System.out.println(Arrays.deepToString(coordinates1) + "中的坐标" + (lc.checkStraightLine(coordinates1) ? "在" : "不在") + "一条直线上");
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean checkStraightLine(int[][] coordinates) {
        int m = coordinates.length;
        if (m == 1) return false;

        // 取定点 (x1, y1) = coordinates[0], (x2, y2) = coordinates[m - 1]
        int x1 = coordinates[0][0], y1 = coordinates[0][1];
        int x2 = coordinates[m - 1][0], y2 = coordinates[m - 1][1];

        for (int i = 1; i < m - 1; i++) {
            // 判断斜率  ==>  除法转乘法防止除0错误
            // (y - y1) / (x - x1) = (y - y2) / (x - x2)  ==>  (y - y1) * (x - x2) = (y - y2) * (x - x1)
            int x = coordinates[i][0], y = coordinates[i][1];

            if ((y - y1) * (x - x2) != (y - y2) * (x - x1)) return false;
        }

        return true;
    }

}
