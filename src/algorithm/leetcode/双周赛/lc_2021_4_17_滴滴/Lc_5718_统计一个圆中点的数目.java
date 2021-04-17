package algorithm.leetcode.双周赛.lc_2021_4_17_滴滴;

import java.util.Arrays;

/**
 * 统计一个圆中点的数目
 * <p>题目链接: https://leetcode-cn.com/problems/queries-on-number-of-points-inside-a-circle/</p>
 * <p>题解链接: https://leetcode-cn.com/problems/queries-on-number-of-points-inside-a-circle/solution/java-zhi-jie-mo-ni-by-echofzoe-coik/</p>
 *
 * @author echofzoe
 * @since 2021.4.18
 */
public class Lc_5718_统计一个圆中点的数目 {

    public static void main(String[] args) {
        Lc_5718_统计一个圆中点的数目 lc = new Lc_5718_统计一个圆中点的数目();

        int[][] points1 = {{1, 3}, {3, 3}, {5, 3}, {2, 2}}, queries1 = {{2, 3, 1}, {4, 3, 1}, {1, 1, 2}};
        int[][] points2 = {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}}, queries2 = {{1, 2, 2}, {2, 2, 2}, {4, 3, 2}, {4, 3, 3}};

        System.out.println("点集" + Arrays.deepToString(points1) + "在圆集" + Arrays.deepToString(queries1) + "上各个圆里的分布数目是" + Arrays.toString(lc.countPoints(points1, queries1)));
        System.out.println("点集" + Arrays.deepToString(points2) + "在圆集" + Arrays.deepToString(queries2) + "上各个圆里的分布数目是" + Arrays.toString(lc.countPoints(points2, queries2)));
    }

    // 模拟 - 时间复杂度 O(N1 * N2) - 空间复杂度 O(1)
    public int[] countPoints(int[][] points, int[][] queries) {
        int n1 = points.length, n2 = queries.length;

        int[] res = new int[n2];
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n1; j++) {
                if (isInCircle(queries[i][2], queries[i][0], queries[i][1], points[j][0], points[j][1])) {
                    res[i]++;
                }
            }
        }

        return res;
    }

    private boolean isInCircle(double r, double x1, double y1, double x2, double y2) {
        double dist = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));

        return dist <= r;
    }

}
