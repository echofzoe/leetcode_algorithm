package algorithm.leetcode.search.binarySearch;

import java.util.Arrays;

/**
 * H 指数 II
 * <P>https://leetcode-cn.com/problems/h-index-ii/</P>
 *
 * @author echofzoe
 * @since 2021.7.12
 */
public class Lc_275_H指数_II {

    public static void main(String[] args) {
        Lc_275_H指数_II lc = new Lc_275_H指数_II();

        int[] citations = {0,1,3,5,6};

        System.out.println("给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照 升序排列 。编写一个方法，计算出研究者的 h 指数。\n" +
                "h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）\"\n");
        System.out.println("输入：citations = " + Arrays.toString(citations));
        System.out.println("输出：" + lc.hIndex(citations));  // 3
    }

    // 二分 - 时间复杂度 O(logN) - 空间复杂度 O(1)
    public int hIndex(int[] citations) {
        int n;
        if (citations == null || (n = citations.length) == 0 || citations[n - 1] == 0) return 0;

        // 二分最少被引用次数
        int l = 0, m, r = n - 1;
        while (l < r) {
            m = l + (r - l) / 2;

            if (citations[m] < n - m) l = m + 1;
            else r = m;
        }

        return n - l;
    }

}
