package algorithm.leetcode.周赛.lc_2021_6_6_恒生;

/**
 * 使二进制字符串字符交替的最少反转次数
 * <P>https://leetcode-cn.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/</P>
 *
 * @author echofzoe
 * @since 2021.6.6
 */
public class Lc_5778_使二进制字符串字符交替的最少反转次数 {

    public static void main(String[] args) {
        Lc_5778_使二进制字符串字符交替的最少反转次数 lc = new Lc_5778_使二进制字符串字符交替的最少反转次数();

        String s = "01001001101";

        System.out.println("给你一个二进制字符串 s 。你可以按任意顺序执行以下两种操作任意次：\n" +
                "  - 类型 1 ：删除 字符串 s 的第一个字符并将它 添加 到字符串结尾。\n" +
                "  - 类型 2 ：选择 字符串 s 中任意一个字符并将该字符 反转 ，也就是如果值为 '0' ，则反转得到 '1' ，反之亦然。\n" +
                "请你返回使 s 变成 交替 字符串的前提下， 类型 2 的 最少 操作次数 。\n" +
                "我们称一个字符串是 交替 的，需要满足任意相邻字符都不同。\n" +
                "  - 比方说，字符串 \"010\" 和 \"1010\" 都是交替的，但是字符串 \"0100\" 不是。");
        System.out.println("输入：s = \"" + s + "\"");
        System.out.println("输出：" + lc.minFlips(s));  // 2
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int minFlips(String s) {
        int n = s.length();

        int res = 0;

        // 记录将s通过类型2变成"010101..."这种形式所需的操作数
        char[] f = {'0', '1'};
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != f[i % 2]) res++;
        }

        int head0 = res, head1 = n - res;
        res = Math.min(head0, head1);
        if ((n & 1) == 0) return res;  // 长度为偶数时，类型1的操作不影响类型2的操作，故直接返回类型2的操作数

        // 长度为奇数时
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int t = head0 - (c == '0' ? 0 : 1);
            t += c == '0' ? 1 : 0;

            head1 = t;
            head0 = n - t;

            res = Math.min(res, Math.min(head0, head1));

            if (res == 0) break;
        }

        return res;
    }

}
