package algorithm.leetcode.graph;

import algorithm.leetcode.utils.NumsUtils;
import algorithm.leetcode.utils.UnionFindSet;

import java.util.Arrays;

public class Lc_765_情侣牵手 {

    // 情侣牵手
    // https://leetcode-cn.com/problems/couples-holding-hands/

    public static void main(String[] args) {
        Lc_765_情侣牵手 lc = new Lc_765_情侣牵手();
        int[] row = {0, 2, 1, 3, 4, 6, 7, 5};

        System.out.println(Arrays.toString(row) + "排列的情侣，按题意需要交换" + lc.minSwapsCouplesGreedy2(row) + "次座位，才能让所有的情侣手牵手");
    }

    // 并查集 - 时间复杂度 O(NlogN) - 空间复杂度 O(N)
    public int minSwapsCouplesUfs(int[] row) {
        int n = row.length;

        UnionFindSet ufs = new UnionFindSet(n / 2);
        for (int i = 0; i < n; i += 2) {
            ufs.union(row[i] / 2, (row[i + 1]) / 2);
        }

        return ufs.getN() - ufs.getSetCount();
    }

    // 贪心 - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    public int minSwapsCouplesGreedy(int[] row) {
        int n = row.length;

        int res = 0;
        for (int i = 0; i < n; i += 2) {
            int pair = row[i] ^ 1;
            if (row[i + 1] == pair) continue;

            for (int j = i + 1; j < n; j++) {
                if (row[j] == pair) {
                    NumsUtils.swap(row, i + 1, j);

                    res++;
                    break;
                }
            }
        }

        return res;
    }

    // 原数组哈希 + 贪心 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int minSwapsCouplesGreedy2(int[] row) {
        int n = row.length;

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            // 每个人对应的位置
            pos[row[i]] = i;
        }

        int res = 0;
        for (int i = 0; i < n; i += 2) {
            int pair = row[i] ^ 1;
            if (row[i + 1] == pair) continue;

            int rightNextIdx = pos[pair];

            NumsUtils.swap(row, i + 1, rightNextIdx);
            NumsUtils.swap(pos, row[i + 1], row[rightNextIdx]);

            res++;
        }

        return res;
    }

}
