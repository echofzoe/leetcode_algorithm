package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_452_用最少数量的箭引爆气球 {

    // 用最少数量的箭引爆气球
    // https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/

    public static void main(String[] args) {
        Lc_452_用最少数量的箭引爆气球 lc = new Lc_452_用最少数量的箭引爆气球();
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};

        System.out.println("最少使用" + lc.findMinArrowShots(points) + "把弓箭就可以射爆所有气球");
    }

    public int findMinArrowShots(int[][] points) {
        if (points.length <= 0) return 0;

        Arrays.sort(points, (a, b) -> (a[1] < b[1] ? -1 : 1));

        int res = 1;
        int pre = points[0][1];

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > pre) {
                res++;
                pre = points[i][1];
            }
        }

        return res;
    }

}
