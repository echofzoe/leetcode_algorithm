package algorithm.leetcode.双周赛.lc_2021_5_29_图森未来;

import java.util.*;

/**
 * 矩阵中最大的三个菱形和
 * <P>https://leetcode-cn.com/problems/get-biggest-three-rhombus-sums-in-a-grid/</P>
 *
 * @author echofzoe
 * @since 2021.5.30
 */
public class Lc_1878_矩阵中最大的三个菱形和 {

    public static void main(String[] args) {
        Lc_1878_矩阵中最大的三个菱形和 lc = new Lc_1878_矩阵中最大的三个菱形和();

        int[][] grid = {{3, 4, 5, 1, 3}, {3, 3, 4, 2, 3}, {20, 30, 200, 40, 10}, {1, 5, 5, 4, 1}, {4, 3, 2, 2, 5}};
        int[][] grid1 = {{7, 7, 7}};

        System.out.println("输入：grid = " + Arrays.deepToString(grid));
        System.out.println("输出：" + Arrays.toString(lc.getBiggestThree(grid)));
        System.out.println("输入：grid1 = " + Arrays.deepToString(grid1));
        System.out.println("输出：" + Arrays.toString(lc.getBiggestThree(grid1)));

    }

    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        Queue<Integer> cand = new PriorityQueue<>((a, b) -> b - a);
        Set<Integer> set = new HashSet<>();
        int[][] dirs = {{1, -1}, {1, 1}, {-1, 1}, {-1, -1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 以 grid[i][j] 为中心
                if (set.add(grid[i][j])) {
                    cand.offer(grid[i][j]);
                }

                // 枚举菱形的边长
                for (int k = 1; k <= Math.min(m, n); k++) {
                    if (i - k < 0 || i + k >= m || j - k < 0 || j + k >= n) break;

                    // 从最高点开始遍历菱形的4条边
                    int x = i - k, y = j;
                    int sum = 0;
                    for (int c = 0; c < 4; c++) {
                        int step = k;
                        while (step > 0) {
                            x += dirs[c][0];
                            y += dirs[c][1];
                            sum += grid[x][y];
                            step--;
                        }
                    }

                    if (set.add(sum)) {
                        cand.offer(sum);
                    }
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3 && !cand.isEmpty(); i++) list.add(cand.poll());

        // 先将 List<Integer> 转成 IntStream，再转成 int[]
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

}
