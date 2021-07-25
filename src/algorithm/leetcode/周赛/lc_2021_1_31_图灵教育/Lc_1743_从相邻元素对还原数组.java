package algorithm.leetcode.周赛.lc_2021_1_31_图灵教育;

import java.util.*;

/**
 * 从相邻元素对还原数组
 * <P>https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs/</P>
 *
 * @author echofzoe
 * @since 2021.1.31
 * @updated 2021.7.25
 */
public class Lc_1743_从相邻元素对还原数组 {

    public static void main(String[] args) {
        Lc_1743_从相邻元素对还原数组 lc = new Lc_1743_从相邻元素对还原数组();

        int[][] adjacentPairs = {{4, -2}, {1, 4}, {-3, 1}};

        System.out.println("存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。\n" +
                "给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。\n" +
                "题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。\n" +
                "返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。\n");
        System.out.println("输入：adjacentPairs = " + Arrays.deepToString(adjacentPairs));
        System.out.println("输出：" + Arrays.toString(lc.restoreArray(adjacentPairs)));  // [-2,4,1,-3]
    }

    // hash - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length;

        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int[] a : adjacentPairs) {
            m.computeIfAbsent(a[0], key -> new ArrayList<>()).add(a[1]);
            m.computeIfAbsent(a[1], key -> new ArrayList<>()).add(a[0]);
        }

        int[] res = new int[n + 1];
        for (Map.Entry<Integer, List<Integer>> entry : m.entrySet()) {
            if (entry.getValue().size() == 1) {
                res[0] = entry.getKey();
                res[1] = entry.getValue().get(0);

                for (int i = 2; i < n + 1; i++) {
                    List<Integer> next = m.get(res[i - 1]);

                    res[i] = next.get(0) != res[i - 2] ? next.get(0) : next.get(1);
                }

                break;
            }
        }

        return res;
    }

}
