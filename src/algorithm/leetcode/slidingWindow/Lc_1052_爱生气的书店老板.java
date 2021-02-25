package algorithm.leetcode.slidingWindow;

import java.util.Arrays;

public class Lc_1052_爱生气的书店老板 {

    // 爱生气的书店老板
    // https://leetcode-cn.com/problems/grumpy-bookstore-owner/

    public static void main(String[] args) {
        Lc_1052_爱生气的书店老板 lc = new Lc_1052_爱生气的书店老板();
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        int X = 3;

        System.out.println("在顾客数组" + Arrays.toString(customers) + "和老板生气值数组" + Arrays.toString(grumpy) + "下，老板能让自己连续" + X + "分钟不生气，但只能这样做一次，则一天营业下来，最多有" + lc.maxSatisfied1(customers, grumpy, X) + "位客户能够感到满意");
    }

    // 滑动窗口 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int maxSatisfied1(int[] customers, int[] grumpy, int X) {
        int n = customers.length;

        int total = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                total += customers[i];
            }
        }

        int leftSum = 0, windowSum = 0, rightSum = total;
        for (int i = 0; i < X; i++) {
            if (grumpy[i] == 0) {
                rightSum -= customers[i];
            }

            windowSum += customers[i];
        }

        int res = 0;
        for (int i = X; i < n; i++) {
            res = Math.max(res, leftSum + windowSum + rightSum);

            if (grumpy[i] == 0) {
                rightSum -= customers[i];
            }

            if (grumpy[i - X] == 0) {
                leftSum += customers[i - X];
            }

            windowSum -= customers[i - X];
            windowSum += customers[i];
        }

        return  Math.max(res, leftSum + windowSum + rightSum);
    }

    // 滑动窗口 优化 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int maxSatisfied2(int[] customers, int[] grumpy, int X) {
        int n = customers.length;

        int total = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                total += customers[i];
            }
        }

        // 计算窗口内因生气而赶走顾客的最大值
        int increase = 0;
        for (int i = 0; i < X; i++) {
            increase += customers[i] * grumpy[i];
        }

        int maxIncrease = increase;
        for (int i = X; i < n; i++) {
            increase = increase - customers[i - X] * grumpy[i - X] + customers[i] * grumpy[i];
            maxIncrease = Math.max(maxIncrease, increase);
        }

        return total + maxIncrease;
    }
}
