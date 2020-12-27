package algorithm.leetcode.周赛.Lc_2020_12_27_RingCentral;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Lc_5638_吃苹果的最大数目 {

    // 吃苹果的最大数目
    // https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/

    public static void main(String[] args) {
        Lc_5638_吃苹果的最大数目 lc = new Lc_5638_吃苹果的最大数目();
        int[] apples = {3, 0, 0, 0, 0, 2}, days = {3, 0, 0, 0, 0, 2};
        int[] apples1 = {1, 2, 3, 5, 2}, days1 = {3, 2, 1, 4, 2};

        System.out.println("在 apples = " + Arrays.toString(apples) + ", 和 days = " + Arrays.toString(days) + "的情况下，最多可以吃" + lc.eatenApplesPriorityQueue(apples, days) + "个苹果");
        System.out.println("在 apples = " + Arrays.toString(apples1) + ", 和 days = " + Arrays.toString(days1) + "的情况下，最多可以吃" + lc.eatenApplesSimulate(apples1, days1) + "个苹果");
    }

    // 优先队列 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int eatenApplesPriorityQueue(int[] apples, int[] days) {
        // 优先队列 - 队首最早过期; int[0] = 苹果个数; int[1] = 过期时间
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);

        int n = apples.length, res = 0;

        for (int i = 0; i < n || pq.size() > 0; i++) {
            // 移除过期苹果
            while (!pq.isEmpty()) {
                int[] expire = pq.peek();
                if (expire[1] <= i) pq.poll();
                else break;
            }

            // 添加当天新长出来的苹果
            if (i < n && apples[i] > 0) {
                pq.add(new int[]{apples[i], i + days[i]});
            }

            // 吃掉已存储的苹果
            if (!pq.isEmpty() && pq.peek()[0] > 0) {
                res++;

                if (--pq.peek()[0] == 0) pq.poll();
            }
        }

        return res;
    }
    
    // 模拟 - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public int eatenApplesSimulate(int[] apples, int[] days) {
        int n = apples.length;

        int maxExpireDay = 0;    // 最大过期天数，下标从0开始
        for (int i = 0; i < n; i++) {
            maxExpireDay = Math.max(maxExpireDay, i + days[i] - 1);
        }
        int[] expire = new int[maxExpireDay + 1];

        int store = 0, res = 0;
        for (int i = 0; i < n; i++) {
            if (apples[i] > 0) {
                store += apples[i];
                expire[i + days[i] - 1] += apples[i];
            }

            if (store > 0) {
                store--;

                for (int j = i; j <= maxExpireDay; j++) {
                    if (expire[j] > 0) {
                        expire[j]--;
                        break;
                    }
                }

                res++;
            }

            if (expire[i] > 0) {
                store = Math.max(0, store - expire[i]);
                expire[i] = 0;
            }
        }

        for (int i = n; store > 0 && i <= maxExpireDay; i++) {
            store--;
            res++;

            for (int j = i; j <= maxExpireDay; j++) {
                if (expire[j] > 0) {
                    expire[j]--;
                    break;
                }
            }

            if (expire[i] > 0) {
                store = Math.max(0, store - expire[i]);
                expire[i] = 0;
            }
        }

        return res;
    }

}
