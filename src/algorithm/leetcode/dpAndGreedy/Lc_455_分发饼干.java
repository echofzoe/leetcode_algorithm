package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Lc_455_分发饼干 {

    // 分发饼干
    // https://leetcode-cn.com/problems/assign-cookies/

    public static void main(String[] args) {
        Lc_455_分发饼干 lc = new Lc_455_分发饼干();
        int[] g = {1, 2, 3}, s = {1, 1};

        System.out.println("在胃口值" + Arrays.toString(g) + "和尺寸值" + Arrays.toString(s) + "的条件下，能满足" + lc.findContentChildren(g, s) + "个孩子");
    }

    public int findContentChildren(int[] g, int[] s) {
        int gn = g.length, sn = s.length;
        int res = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i : s) pq.offer(i);

        Arrays.sort(g);

        for (int i = 0; i < gn && !pq.isEmpty(); i++) {
            while (!pq.isEmpty() && pq.peek() < g[i]) pq.poll();

            if (pq.isEmpty()) break;

            pq.poll();
            res++;
        }

        return res;
    }

}
