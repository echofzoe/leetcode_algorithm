package algorithm.leetcode.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 直线上最多的点数
 * <P>https://leetcode-cn.com/problems/max-points-on-a-line/</P>
 *
 * @author echofzoe
 * @since 2021.6.24
 */
public class Lc_149_直线上最多的点数 {

    public static void main(String[] args) {
        Lc_149_直线上最多的点数 lc = new Lc_149_直线上最多的点数();

        int[][] points = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};

        System.out.println("给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。");
        System.out.println("输入：points = " + Arrays.deepToString(points));
        System.out.println("输出：" + lc.maxPoints(points));  // 4
    }

    // 枚举直线 + 哈希表统计
    // - 时间复杂度 O(N^2 * logM) 其中N为点的数量，M为横纵坐标差的最大值，单次最大公约数计算的时间复杂度是O(logM)
    // - 空间复杂度 O(N) 为哈希表的开销
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n < 3) return n;

        int max = 2;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            Map<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];
                int k = gcd(dx, dy);

                String key = (dy / k) + "/" + (dx / k);
                map.put(key, map.getOrDefault(key, 0) + 1);

                cnt = Math.max(cnt, map.get(key));
            }

            // cnt个相同的斜率是由cnt+1个点构出的
            max = Math.max(max, cnt + 1);
        }

        return max;
    }

    /**
     * 辗转相除法计算最大公约数
     */
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 暴力枚举直线 - 时间复杂度 O(N^3) - 空间复杂度 O(1)
    public int maxPoints1(int[][] points) {
        int n = points.length;
        if (n < 3) return n;

        int max = 2;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int cnt = 2;
                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];
                for (int k = j + 1; k < n; k++) {
                    if (dx * (points[i][1] - points[k][1])
                            == dy * (points[i][0] - points[k][0])) {
                        cnt++;
                    }
                }

                max = Math.max(max, cnt);

                if (max > n / 2) return max;
            }
        }

        return max;
    }

}
