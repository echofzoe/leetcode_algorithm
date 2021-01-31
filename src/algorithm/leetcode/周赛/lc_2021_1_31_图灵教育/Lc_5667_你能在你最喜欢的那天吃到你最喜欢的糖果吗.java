package algorithm.leetcode.周赛.lc_2021_1_31_图灵教育;

import java.util.Arrays;

public class Lc_5667_你能在你最喜欢的那天吃到你最喜欢的糖果吗 {

    // 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
    // https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/

    public static void main(String[] args) {
        Lc_5667_你能在你最喜欢的那天吃到你最喜欢的糖果吗 lc = new Lc_5667_你能在你最喜欢的那天吃到你最喜欢的糖果吗();
        int[] candiesCount = {7, 4, 5, 3, 8};
        int[][] queries = {{0, 2, 2}, {4, 2, 4}, {2, 13, 1000000000}};

        System.out.println("结果是" + Arrays.toString(lc.canEat(candiesCount, queries)));
    }

    // 前缀和 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int typeCnt = candiesCount.length, n = queries.length;

        long[] preSum = new long[typeCnt];
        preSum[0] = candiesCount[0];
        for (int i = 1; i < typeCnt; i++) preSum[i] = preSum[i - 1] + candiesCount[i];

        boolean[] res = new boolean[n];
        for (int i = 0; i < n; i++) {
            int type = queries[i][0], day = queries[i][1], cap = queries[i][2];

            // [x, y] -> [每天吃一颗糖, 每天吃最大数量的糖]
            long x = day + 1, y = (long)(day + 1) * cap;

            // [a, b] -> [type 只被吃了一颗, type 被吃完了]
            long a = preSum[type] - candiesCount[type] + 1, b = preSum[type];

            if (!(x > b || y < a)) res[i] = true;
        }

        return res;
    }

}
