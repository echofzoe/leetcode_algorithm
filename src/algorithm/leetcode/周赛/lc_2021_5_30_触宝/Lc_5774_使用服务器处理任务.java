package algorithm.leetcode.周赛.lc_2021_5_30_触宝;

import java.util.*;

/**
 * 使用服务器处理任务
 * <P>https://leetcode-cn.com/problems/process-tasks-using-servers/</P>
 *
 * @author echofzoe
 * @since 2021.5.30
 */
public class Lc_5774_使用服务器处理任务 {

    public static void main(String[] args) {
        Lc_5774_使用服务器处理任务 lc = new Lc_5774_使用服务器处理任务();

        int[] servers = {3, 3, 2}, tasks = {1, 2, 3, 2, 1, 2};

        System.out.println("给你两个 下标从 0 开始 的整数数组 servers 和 tasks ，长度分别为 n 和 m 。servers[i] 是第 i 台服务器的 权重 ，而 tasks[j] 是处理第 j 项任务 所需要的时间（单位：秒）。\n" +
                "你正在运行一个仿真系统，在处理完所有任务后，该系统将会关闭。每台服务器只能同时处理一项任务。第 0 项任务在第 0 秒可以开始处理，相应地，第 j 项任务在第 j 秒可以开始处理。处理第 j 项任务时，你需要为它分配一台 权重最小 的空闲服务器。如果存在多台相同权重的空闲服务器，请选择 下标最小 的服务器。如果一台空闲服务器在第 t 秒分配到第 j 项任务，那么在 t + tasks[j] 时它将恢复空闲状态。\n" +
                "如果没有空闲服务器，则必须等待，直到出现一台空闲服务器，并 尽可能早 地处理剩余任务。 如果有多项任务等待分配，则按照 下标递增 的顺序完成分配。\n" +
                "如果同一时刻存在多台空闲服务器，可以同时将多项任务分别分配给它们。\n" +
                "构建长度为 m 的答案数组 ans ，其中 ans[j] 是第 j 项任务分配的服务器的下标。\n" +
                "返回答案数组 ans。");
        System.out.println("输入：servers = " + Arrays.toString(servers) + ", tasks = " + Arrays.toString(tasks));
        System.out.println("输出：" + Arrays.toString(lc.assignTasks(servers, tasks)));
    }

    // 优先队列 - 时间复杂度 O((M + N) * logM) - 空间复杂度 O(M + N)
    public int[] assignTasks(int[] servers, int[] tasks) {
        int m, n;
        if (servers == null || tasks == null || (m = servers.length) == 0 || (n = tasks.length) == 0) return new int[0];

        // serv 存入 [服务器编号, 服务器权重]
        Queue<int[]> serv = new PriorityQueue<>(m, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        for (int i = 0; i < m; i++) {
            serv.offer(new int[]{i, servers[i]});
        }

        // running 存入 [服务器编号, 服务器权重, 服务器释放时间]
        Queue<int[]> running = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        // 就绪任务的时间队列
        Deque<Integer> ready = new LinkedList<>();

        int[] res = new int[n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            // 释放完成任务的服务器
            while (!running.isEmpty() && running.peek()[2] <= i) {
                int[] t = running.poll();
                serv.offer(new int[]{t[0], t[1]});
            }

            // 任务就绪
            ready.offerLast(tasks[i]);

            // 运行任务
            while (!ready.isEmpty() && !serv.isEmpty()) {
                int[] s = serv.poll();
                int t = ready.pollFirst();
                res[idx++] = s[0];
                running.offer(new int[]{s[0], s[1], i + t});
            }
        }

        int time = m;
        // 还有任务在等待运行，说明服务器资源已满，即空闲服务器列表 serv 为空
        while (!ready.isEmpty()) {
            // 先释放服务器
            if (!running.isEmpty()) {
                // 下一次释放服务器的时间
                time = running.peek()[2];
                while (!running.isEmpty() && running.peek()[2] == time) {
                    int[] r = running.poll();
                    serv.offer(new int[]{r[0], r[1]});
                }
            }

            // 再运行任务
            while (!serv.isEmpty() && !ready.isEmpty()) {
                int[] s = serv.poll();
                int t = ready.pollFirst();
                res[idx++] = s[0];
                running.offer(new int[]{s[0], s[1], time + t});
            }
        }

        return res;
    }

}
