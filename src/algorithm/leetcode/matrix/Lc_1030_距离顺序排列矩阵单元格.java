package algorithm.leetcode.matrix;

import java.util.*;

public class Lc_1030_距离顺序排列矩阵单元格 {

    // 距离顺序排列矩阵单元格
    // https://leetcode-cn.com/problems/matrix-cells-in-distance-order/

    public static void main(String[] args) {
        Lc_1030_距离顺序排列矩阵单元格 lc = new Lc_1030_距离顺序排列矩阵单元格();
        int R = 2, C = 2, r0 = 0, c0 = 1;

        System.out.println(R + "行" + C + "列的矩阵按到(" + r0 + ", " + c0 + ")的距离从最小到最大的顺序排序的结果是" + Arrays.deepToString(lc.allCellsDistOrderBucket(R, C, r0, c0)));
    }

    // 直接排序 - 时间复杂度 O(RC*logRC) - 空间复杂度 O(logRC) 为排序栈所需空间
    public int[][] allCellsDistOrderDirect(int R, int C, int r0, int c0) {
        List<int[]> list = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                list.add(new int[]{i, j});
            }
        }

        list.sort((x, y) -> {
            int dist1 = Math.abs(x[0] - r0) + Math.abs(x[1] - c0);
            int dist2 = Math.abs(y[0] - r0) + Math.abs(y[1] - c0);
            return dist1 - dist2;
        });

        return list.toArray(new int[R * C][]);
    }

    // 桶排序(计数排序) - 时间复杂度 O(RC) - 空间复杂度 O(RC)
    // - 计数排序本质上是一种特殊的桶排序，当桶的个数最大的时候，就是计数排序
    public int[][] allCellsDistOrderBucket(int R, int C, int r0, int c0) {
        // 最大的曼哈顿距离
        int maxDist = Math.max(r0, R - 1 - r0) + Math.max(c0, C - 1 - c0);

        // 各距离装桶
        List<List<int[]>> bucket = new ArrayList<>();
        for (int i = 0; i <= maxDist; i++) {
            bucket.add(new ArrayList<>());
        }

        // 各点装桶
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int dist = Math.abs(i - r0) + Math.abs(j - c0);
                bucket.get(dist).add(new int[]{i, j});
            }
        }

        // 拆桶进集合
        int[][] res = new int[R * C][];
        int index = 0;
        for (int i = 0; i <= maxDist; i++) {
            for (int[] it : bucket.get(i)) {
                res[index++] = it;
            }
        }

        return res;
    }

}
