package algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lc_56_合并区间 {

    // 合并区间
    // https://leetcode-cn.com/problems/merge-intervals/

    public static void main(String[] args) {
        Lc_56_合并区间 lc = new Lc_56_合并区间();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        System.out.println("区间集合 " + Arrays.deepToString(intervals) + " 合并了所有重叠区间后的结果为: " + Arrays.deepToString(lc.merge(intervals)));
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][2];

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
//        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < left) {
                merged.add(new int[]{left, right});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], right);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
