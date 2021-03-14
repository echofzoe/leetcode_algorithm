package algorithm.leetcode.周赛.lc_2021_3_14_shopee;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Lc_5703_最大平均通过率 {

    // 最大平均通过率
    // https://leetcode-cn.com/problems/maximum-average-pass-ratio/

    public static void main(String[] args) {
        Lc_5703_最大平均通过率 lc = new Lc_5703_最大平均通过率();
        int[][] classes = {{1, 2}, {3, 5}, {2, 2}};
        int extraStudents = 2;

        System.out.println("安排" + extraStudents + "个学生去" + Arrays.deepToString(classes) + "中的对应班级后能获得的最大平均通过率是" + lc.maxAverageRatio(classes, extraStudents));
    }

    // 大顶堆 + 贪心 - 时间复杂度 O(NlogN) - 空间复杂度 O(N)
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;
        if (n == 0) return 0;

        // 大顶堆
        Queue<double[]> pq = new PriorityQueue<>((x, y) -> {
            double a = (x[0] + 1) / (x[1] + 1) - x[0] / x[1];
            double b = (y[0] + 1) / (y[1] + 1) - y[0] / y[1];
            if (a < b) return 1;
            if (a > b) return -1;
            return 0;
        });

        for (int[] c : classes) {
            pq.offer(new double[]{c[0], c[1]});
        }

        for (int i = 0; i < extraStudents; i++) {
            double[] c = pq.poll();
            c[0] += 1;
            c[1] += 1;

            pq.offer(c);
        }

        double totalPassRate = 0;
        while (!pq.isEmpty()) {
            double[] c = pq.poll();
            totalPassRate += c[0] / c[1];
        }

        return totalPassRate / n;
    }

}
