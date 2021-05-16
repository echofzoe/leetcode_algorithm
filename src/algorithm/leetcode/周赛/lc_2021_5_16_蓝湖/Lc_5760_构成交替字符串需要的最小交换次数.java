package algorithm.leetcode.周赛.lc_2021_5_16_蓝湖;

/**
 * 构成交替字符串需要的最小交换次数
 * <P>https://leetcode-cn.com/problems/minimum-number-of-swaps-to-make-the-binary-string-alternating/</P>
 *
 * @author echofzoe
 * @since 2021.5.16
 */
public class Lc_5760_构成交替字符串需要的最小交换次数 {

    public static void main(String[] args) {
        Lc_5760_构成交替字符串需要的最小交换次数 lc = new Lc_5760_构成交替字符串需要的最小交换次数();

        String s = "111000";

        System.out.println("给你一个二进制字符串 s，现需要将其转化为一个 交替字符串 。请你计算并返回转化所需的 最小 字符交换次数，如果无法完成转化，返回 -1 。\n" +
                "交替字符串 是指：相邻字符之间不存在相等情况的字符串。例如，字符串 \"010\" 和 \"1010\" 属于交替字符串，但 \"0100\" 不是。\n" +
                "任意两个字符都可以进行交换，不必相邻。");
        System.out.println("修改字符串\"" + s + "\"以构成交替字符串需要的最小交换次数是 " + lc.minSwaps(s));  // 1
    }

    public int minSwaps(String s) {
        int n = s.length();
        if (n == 1) return 0;

        /*
          a - start0 的偶数位为 0 的个数
          b - start1 的奇数位为 1 的个数
          c - start0 的奇数位为 1 的个数
          d - start1 的偶数位为 0 的个数
         */
        int a = 0, b = 0, c = 0, d = 0;

        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            // 偶数位
            if (i % 2 == 0) {
                if (cs[i] == '0') a++;  // 对于 start0 来说，这个位必须是 0
                else b++;               // 对于 start1 来说，这个位必须是 1
            }
            // 奇数位
            else {
                if (cs[i] == '1') c++;  // 对于 start0 来说，这个位必须是 1
                else d++;               // 对于 start1 来说，这个位必须是 0
            }
        }

        if (a != c && b != d) return -1;
        if (a == c && b != d) return a;
        if (a != c && b == d) return b;
        // if (a == c && b == d)
        return Math.min(a, d);
    }

}
