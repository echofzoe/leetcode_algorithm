package algorithm.leetcode.双周赛.lc_2021_7_24_恒生;

import java.util.*;

/**
 * 描述绘画结果
 * <P>https://leetcode-cn.com/problems/describe-the-painting/</P>
 *
 * @author echofzoe
 * @since 2021.7.25
 */
public class Lc_5806_描述绘画结果 {

    public static void main(String[] args) {
        Lc_5806_描述绘画结果 lc = new Lc_5806_描述绘画结果();

        int[][] segments = {{1, 4, 5}, {4, 7, 7}, {1, 7, 9}};

        System.out.println("给你一个细长的画，用数轴表示。这幅画由若干有重叠的线段表示，每个线段有 独一无二 的颜色。给你二维整数数组 segments ，其中 segments[i] = [starti, endi, colori] 表示线段为 半开区间 [starti, endi) 且颜色为 colori 。\n" +
                "线段间重叠部分的颜色会被 混合 。如果有两种或者更多颜色混合时，它们会形成一种新的颜色，用一个 集合 表示这个混合颜色。\n" +
                "    比方说，如果颜色 2 ，4 和 6 被混合，那么结果颜色为 {2,4,6} 。\n" +
                "为了简化题目，你不需要输出整个集合，只需要用集合中所有元素的 和 来表示颜色集合。\n" +
                "你想要用 最少数目 不重叠 半开区间 来 表示 这幅混合颜色的画。这些线段可以用二维数组 painting 表示，其中 painting[j] = [leftj, rightj, mixj] 表示一个 半开区间[leftj, rightj) 的颜色 和 为 mixj 。\n" +
                "   - 比方说，这幅画由 segments = [[1,4,5],[1,7,7]] 组成，那么它可以表示为 painting = [[1,4,12],[4,7,7]] ，因为：\n" +
                "       - [1,4) 由颜色 {5,7} 组成（和为 12），分别来自第一个线段和第二个线段。\n" +
                "       - [4,7) 由颜色 {7} 组成，来自第二个线段。\n" +
                "请你返回二维数组 painting ，它表示最终绘画的结果（没有 被涂色的部分不出现在结果中）。你可以按 任意顺序 返回最终数组的结果。\n" +
                "半开区间 [a, b) 是数轴上点 a 和点 b 之间的部分，包含 点 a 且 不包含 点 b 。\n");
        System.out.println("输入：segments = " + Arrays.deepToString(segments));
        System.out.println("输出：" + lc.splitPainting(segments));  // [[1,4,14],[4,7,16]]
    }

    // DIFF - 时间复杂度 O(N) - 空间复杂度 O(N)
    public List<List<Long>> splitPainting(int[][] segments) {
        List<List<Long>> res = new ArrayList<>();

        long[] diff = new long[100001];
        Set<Integer> start = new HashSet<>();
        for (int[] s : segments) {
            diff[s[0]] += s[2];
            diff[s[1]] -= s[2];
            start.add(s[0]);
        }

        long[] sum = new long[100001];
        for (int i = 1; i < diff.length; i++) {
            sum[i] += sum[i - 1] + diff[i];
        }

        for (int i = 1; i < sum.length; i++) {
            if (sum[i] != 0) {
                List<Long> tmp = new ArrayList<>();

                tmp.add((long) i);
                long color = sum[i];

                while (sum[i + 1] == color && !start.contains(i + 1)) i++;
                tmp.add((long) i + 1);

                tmp.add(color);

                res.add(tmp);
            }
        }

        return res;
    }

}
