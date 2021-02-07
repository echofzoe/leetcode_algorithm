package algorithm.leetcode.周赛.lc_2021_2_7;

import java.util.PriorityQueue;
import java.util.Queue;

public class Lc_5673_移除石子的最大得分 {

    // 移除石子的最大得分
    // https://leetcode-cn.com/problems/maximum-score-from-removing-stones/

    public static void main(String[] args) {
        Lc_5673_移除石子的最大得分 lc = new Lc_5673_移除石子的最大得分();
        int a = 3, b = 4, c = 5;
        System.out.println("在大小为" + a + "," + b + "," + c + "的三个石堆中按题意能获得的最大得分是" + lc.maximumScore(a, b, c));
    }

    // 贪心 + 优先队列 - 时间复杂度 O(NlogK) K为堆的数量 - 空间复杂度 O(K)
    public int maximumScore(int a, int b, int c) {
        Queue<Integer> pq = new PriorityQueue<>((x, y) -> y - x) {{
            offer(a);
            offer(b);
            offer(c);
        }};

        int res = 0;
        while (true) {
            int max = pq.poll(), mid = pq.poll(), min = pq.peek();
            // 结束条件 - 2个空堆
            if (mid == 0) break;

            // 每次拿取后，使得mid和min数量一样
            int take = Math.max(mid - min, 1);
            max -= take;
            mid -= take;

            // 更新答案
            res += take;

            pq.offer(max);
            pq.offer(mid);
        }

        return res;
    }

}
