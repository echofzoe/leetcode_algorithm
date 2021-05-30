package algorithm.leetcode.周赛.lc_2021_5_30_触宝;

/**
 * 插入后的最大值
 * <P>https://leetcode-cn.com/problems/maximum-value-after-insertion/</P>
 *
 * @author echofzoe
 * @since 2021.5.30
 */
public class Lc_5773_插入后的最大值 {

    public static void main(String[] args) {
        Lc_5773_插入后的最大值 lc = new Lc_5773_插入后的最大值();

        String n = "-13";
        int x = 2;

        System.out.println("给你一个非常大的整数 n 和一个整数数字 x ，大整数 n 用一个字符串表示。n 中每一位数字和数字 x 都处于闭区间 [1, 9] 中，且 n 可能表示一个 负数 。\n" +
                "你打算通过在 n 的十进制表示的任意位置插入 x 来 最大化 n 的 数值。但 不能 在负号的左边插入 x 。\n" +
                "  - 例如，如果 n = 73 且 x = 6 ，那么最佳方案是将 6 插入 7 和 3 之间，使 n = 763 。\n" +
                "  - 如果 n = -55 且 x = 2 ，那么最佳方案是将 2 插在第一个 5 之前，使 n = -255 。\n" +
                "返回插入操作后，用字符串表示的 n 的最大值。");
        System.out.println("输入：n = \"" + n + "\", x = " + x);
        System.out.println("输出：" + lc.maxValue(n, x));
    }

    // 贪心 - 时间复杂度 O(L) L为字符串n的长度 - 空间复杂度 O(1)
    public String maxValue(String n, int x) {
        int L = n.length();
        char[] cs = n.toCharArray();

        if (cs[0] == '-') {
            // 从前往后找第一个比 x 大的
            for (int i = 1; i < L; i++) {
                if (cs[i] - '0' > x) {
                    return new StringBuilder(n).insert(i, (char) (x + '0')).toString();
                }
            }
        } else {
            // 从前往后找第一个比 x 小的
            for (int i = 0; i < L; i++) {
                if (cs[i] - '0' < x) {
                    return new StringBuilder(n).insert(i, (char) (x + '0')).toString();
                }
            }
        }

        return n + (char) (x + '0');
    }

}
