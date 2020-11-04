package algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc_57_插入区间 {

    // 插入区间
    // https://leetcode-cn.com/problems/insert-interval/

    public static void main(String[] args) {
        Lc_57_插入区间 lc = new Lc_57_插入区间();
//        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
//        int[] newInterval = {4, 8};
        int[][] intervals = {{1, 5}, {6, 8}};
        int[] newInterval = {0, 9};

        System.out.println("将新区间" + Arrays.toString(newInterval) + "加入到区间列表" + Arrays.deepToString(intervals) + "并合并所有重叠区间后的新区间列表是" + Arrays.deepToString(lc.insert(intervals, newInterval)));
    }

    // 时间复杂度 O(N) - 空间复杂度 O(1)
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> res = new ArrayList<>();
        int left = newInterval[0];
        int right = newInterval[1];

        // 判断一趟遍历后是否插入成功的标志位
        // - 因为当 newInterval[0] > intervals[intervals.length - 1][1] 时, 需要将 newInterval 插入到列表的最后
        // - 而这个操作是在一趟遍历后进行的
        boolean placed = false;

        for (int[] interval : intervals) {
            // 待插入区间在遍历到的区间的右边 - 直接插入遍历到的区间到结果集
            if (left > interval[1]) {
                res.add(interval);
            }
            // 待插入区间在遍历到的区间的左边 - 先插入新区间再插入遍历到的区间
            else if (interval[0] > right) {
                if (!placed) {
                    res.add(new int[]{left, right});
                    placed = true;
                }
                res.add(interval);
            }
            // 待插入区间与遍历到的区间有交集 - 计算它们的并集
            else {
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }

        if (!placed) {
            res.add(new int[]{left, right});
        }

        return res.toArray(new int[0][0]);
    }

}
