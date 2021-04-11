package algorithm.leetcode.周赛.lc_2021_4_10_LCCUP21_春季赛_团队赛;

import java.util.*;

public class lccup_01_蓄水 {

    public static void main(String[] args) {
        lccup_01_蓄水 lc = new lccup_01_蓄水();

        int[] bucket = {9, 0, 1}, vat = {0, 2, 2};

        System.out.println(lc.storeWater(bucket, vat));
    }

    public int storeWater(int[] bucket, int[] vat) {
        int n = bucket.length;

        int res = 0;

        for (int i = 0; i < n; i++) {
            if (bucket[i] == 0) {
                bucket[i]++;
                res++;
            }
        }

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        for (int i = 0; i < n; i++) {
            if (vat[i] == 0) continue;

            int a = (int) Math.ceil((double) vat[i] / bucket[i]);
            pq.offer(new int[] {i, a});
        }

        int min = 0;
        if (!pq.isEmpty()) {
            min = pq.poll()[1];
        }

        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            if (t[1] == min) continue;

            t[1] = (int) Math.ceil((double) vat[t[0]] / (bucket[t[0]] + 1));
            res++;
            pq.offer(t);
        }

        res += min;

        return res;
    }

}
