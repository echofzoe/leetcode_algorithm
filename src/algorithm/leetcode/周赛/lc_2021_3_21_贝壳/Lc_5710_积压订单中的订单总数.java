package algorithm.leetcode.周赛.lc_2021_3_21_贝壳;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Lc_5710_积压订单中的订单总数 {

    // 积压订单中的订单总数
    // https://leetcode-cn.com/problems/number-of-orders-in-the-backlog/

    public static void main(String[] args) {
        Lc_5710_积压订单中的订单总数 lc = new Lc_5710_积压订单中的订单总数();
        int[][] orders = {{7, 1000000000, 1}, {15, 3, 0}, {5, 999999995, 0}, {5, 1, 1}};

        System.out.println("订单" + Arrays.deepToString(orders) + "经题意匹配后所积压的订单数是" + lc.getNumberOfBacklogOrders(orders));
    }

    // 优先队列 - 时间复杂度 O(NlogN) - 空间复杂度 O(N)
    public int getNumberOfBacklogOrders(int[][] orders) {
        Queue<long[]> buy = new PriorityQueue<>((x, y) -> Long.compare(y[0], x[0])); // 大根堆
        Queue<long[]> sell = new PriorityQueue<>((x, y) -> Long.compare(x[0], y[0])); // 小根堆

        for (int[] order : orders) {
            long price = order[0], amount = order[1], type = order[2];

            if (type == 0) {
                while (amount > 0 && !sell.isEmpty()) {
                    if (sell.peek()[0] <= price) {
                        // sell 价格 <= buy 价格
                        if (sell.peek()[1] <= amount) {
                            // sell 数量 <= buy 数量
                            amount -= sell.peek()[1];
                            sell.poll();
                        } else {
                            // sell 数量 > buy 数量
                            long t1 = sell.peek()[0];
                            long t2 = sell.peek()[1] - amount;
                            amount = 0;
                            sell.poll();
                            sell.offer(new long[]{t1, t2});
                        }
                    } else {
                        // sell 价格 > buy 价格，当前 buy 入堆
                        buy.offer(new long[]{price, amount});
                        amount = 0;
                        break;
                    }
                }

                // 当前 buy 匹配后仍有剩余，将剩余入堆
                if (amount > 0) {
                    buy.offer(new long[]{price, amount});
                }
            } else {
                // 代码逻辑同上
                while (amount > 0 && !buy.isEmpty()) {
                    if (buy.peek()[0] >= price) {
                        if (buy.peek()[1] <= amount) {
                            amount -= buy.peek()[1];
                            buy.poll();
                        } else {
                            long t1 = buy.peek()[0];
                            long t2 = buy.peek()[1] - amount;
                            amount = 0;
                            buy.poll();
                            buy.offer(new long[]{t1, t2});
                        }
                    } else {
                        sell.offer(new long[]{price, amount});
                        amount = 0;
                        break;
                    }
                }

                if (amount > 0) {
                    sell.offer(new long[]{price, amount});
                }
            }
        }

        // 统计结果，注意对中间过程取余
        long res = 0, MOD = 1000000007;
        while (!buy.isEmpty()) {
            res = (res + buy.poll()[1]) % MOD;
        }

        while (!sell.isEmpty()) {
            res = (res + sell.poll()[1]) % MOD;
        }

        return (int) (res % MOD);
    }

}
