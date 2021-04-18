package algorithm.leetcode.周赛.lc_2021_4_18_依图;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 单线程 CPU
 * <p>题目链接: https://leetcode-cn.com/problems/single-threaded-cpu/</p>
 *
 * @author echofzoe
 * @since 2021.4.18
 */
public class Lc_5736_单线程CPU {

    public static void main(String[] args) {
        Lc_5736_单线程CPU lc = new Lc_5736_单线程CPU();

        int[][] tasks = {{1, 2}, {2, 4}, {3, 2}, {4, 1}};

        System.out.println("单线程CPU执行任务列表" + Arrays.deepToString(tasks) + "的执行顺序是" + Arrays.toString(lc.getOrder(tasks)));
    }

    // 排序 + 小根堆 - 时间复杂度 O(NlogN) - 空间复杂度 O(N)
    public int[] getOrder(int[][] oldTasks) {
        int n = oldTasks.length;

        int[][] tasks = new int[n][3];
        for (int i = 0; i < oldTasks.length; i++) {
            tasks[i][0] = oldTasks[i][0];  // 就绪时间
            tasks[i][1] = oldTasks[i][1];  // 执行用时
            tasks[i][2] = i;  // 下标
        }

        // 将任务按就绪先后时间排序
        Arrays.sort(tasks, Comparator.comparingInt(a -> a[0]));

        // 就绪队列
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if (tasks[a][1] == tasks[b][1]) return tasks[a][2] - tasks[b][2];
            return tasks[a][1] - tasks[b][1];
        });


        int now = 1;
        int[] res = new int[n];
        int i = 0, j = 0;

        while (i < n) {
            // 任务就绪时，将任务加入就绪队列，并更新时刻
            while (i < n && (pq.isEmpty() || now >= tasks[i][0])) {
                now = Math.max(now, tasks[i][0]);
                pq.offer(i++);
            }

            // CPU空闲时，执行就绪队列中执行用时最少的任务，当多任务执行用时相同时，执行下标最小的任务
            int[] task = tasks[pq.poll()];
            res[j++] = task[2];  // 更新答案
            now += task[1];  // 更新时刻
        }

        while (!pq.isEmpty()) {
            // 按就绪队列中任务的执行用时从小到大执行相应任务，并更新答案
            res[j++] = tasks[pq.poll()][2];
        }

        return res;
    }

}
