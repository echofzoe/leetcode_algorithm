package algorithm.leetcode.queue;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Lc_1046_最后一块石头的重量 {

    // 最后一块石头的重量
    // https://leetcode-cn.com/problems/last-stone-weight/

    public static void main(String[] args) {
        Lc_1046_最后一块石头的重量 lc = new Lc_1046_最后一块石头的重量();
        int[] stones = {2, 7, 4, 1, 8, 1};

        System.out.println(Arrays.toString(stones) + "中，按题意得到的最后一块石头的重量是" + lc.lastStoneWeight(stones));
    }

    // 大顶堆 - 时间复杂度 O(N*logN) - 空间复杂度 O(N)
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int i : stones) pq.offer(i);

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();

            if (a > b) pq.offer(a - b > 0 ? a - b : b - a);
        }

        return pq.size() > 0 ? pq.poll() : 0;
    }

}
