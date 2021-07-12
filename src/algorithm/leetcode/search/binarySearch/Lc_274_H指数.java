package algorithm.leetcode.search.binarySearch;

import java.util.Arrays;

/**
 * H 指数
 * <P>https://leetcode-cn.com/problems/h-index/</P>
 *
 * @author echofzoe
 * @since 2021.7.11
 */
public class Lc_274_H指数 {

    public static void main(String[] args) {
        Lc_274_H指数 lc = new Lc_274_H指数();

//        int[] citations = {2, 0, 6, 1, 5};
        int[] citations = {11, 15};

        System.out.println("给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。\n" +
                "h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。且其余的 N - h 篇论文每篇被引用次数 不超过 h 次。\n" +
                "例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。\n");
        System.out.println("输入：citations = " + Arrays.toString(citations));
        System.out.println("输出：" + lc.hIndex1(citations));  // 2
    }

    // 排序 - 时间复杂度 O(NlogN) 为排序时间复杂度 - 空间复杂度 O(logN) 为排序额外的内存消耗
    // - 首先我们可以将初始的 H 指数 h 设为 0，然后将引用次数排序，并且对排序后的数组从大到小遍历。
    // - 根据 H 指数的定义，如果当前 H 指数为 h 并且在遍历过程中找到当前值 citations[i] > h，则说明我们找到了一篇被引用了至少 h + 1 次的论文，所以将现有的 h 值加 1
    // - 继续遍历直到 h 无法继续增大。最后返回 h 作为最终答案
    public int hIndex(int[] citations) {
        int n = citations.length;

        Arrays.sort(citations);

        int h = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (citations[i] > h) h++;
        }

        return h;
    }
    
    // 模拟 + 二分 - 时间复杂度 O(NlogN) - 空间复杂度 O(1)
    public int hIndex1(int[] citations) {
        int n;
        if (citations == null || (n = citations.length) == 0) return 0;

        Arrays.sort(citations);
        if (citations[n - 1] == 0) return 0;

        // 二分至少被引用次数
        int l = 0, m, r = n - 1;
        while (l < r) {
            m = l + (r - l) / 2;

            if (citations[m] < n - m) l = m + 1;
            else r = m;
        }

        return n - l;
    }

}
