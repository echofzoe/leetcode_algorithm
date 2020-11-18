package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_134_加油站 {

    // 加油站
    // https://leetcode-cn.com/problems/gas-station/

    public static void main(String[] args) {
        Lc_134_加油站 lc = new Lc_134_加油站();
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};

        System.out.print("在补油数组" + Arrays.toString(gas) + "和耗油数组" + Arrays.toString(cost) + "的情况下,");
        int tmp = lc.canCompleteCircuitPrefix(gas, cost);
        if (tmp == -1) {
            System.out.println("汽车不能环岛一周");
        } else {
            System.out.println("汽车从" + tmp + "号加油站出发可以环岛一周");
        }
    }

    // 暴力 - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    public int canCompleteCircuitBruteForce(int[] gas, int[] cost) {
        int cur = 0;
        int len = gas.length;

        for (int i = 0; i < len; i++) {

            int end = i + len;
            int index = i;
            int tmp;

            while (index < end) {
                if (index >= len) {
                    tmp = index % len;
                } else {
                    tmp = index;
                }

                cur = cur + gas[tmp] - cost[tmp];

                if (cur >= 0) {
                    index++;
                } else {
                    cur = 0;
                    break;
                }
            }

            if (index == end) return i;
        }

        return -1;
    }

    // 暴力搜索优化 一次遍历 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int canCompleteCircuitOnceTraversal(int[] gas, int[] cost) {
        int cur = 0;
        int len = gas.length;

        int i = 0;
        while (i < len) {

            int end = i + len;
            int index = i;
            int tmp;

            while (index < end) {
                if (index >= len) {
                    tmp = index % len;
                } else {
                    tmp = index;
                }

                cur = cur + gas[tmp] - cost[tmp];

                if (cur >= 0) {
                    index++;
                } else {
                    cur = 0;
                    break;
                }
            }

            if (index == end) return i;
            i = index + 1;
        }

        return -1;
    }
    
    // 后缀和 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int canCompleteCircuitSuffix(int[] gas, int[] cost) {
        int len = gas.length;
        int[] suffix = new int[len];
        int max = gas[len - 1] - cost[len - 1];
        suffix[len - 1] = max;

        int res = len - 1;
        for (int i = len - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + gas[i] - cost[i];
            if (max < suffix[i]) {
                max = suffix[i];
                res = i;
            }
        }

        if (suffix[0] < 0) return -1;
        return res;
    }

    // 前缀和 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int canCompleteCircuitPrefix(int[] gas, int[] cost) {
        int len = gas.length;
        int prefix = 0, res = 0, minPrefix = Integer.MAX_VALUE;

        for (int i = 0; i < len; i++) {
            prefix = prefix + gas[i] - cost[i];
            if (prefix < minPrefix) {
                minPrefix = prefix;
                res = i + 1;
            }
        }

        return prefix >= 0 ? res % len : -1;
    }

}
