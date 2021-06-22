package algorithm.leetcode.bit;

import java.util.ArrayList;
import java.util.List;

/**
 * 二进制手表
 * <P>https://leetcode-cn.com/problems/binary-watch/</P>
 *
 * @author echofzoe
 * @since 2021.6.21
 */
public class Lc_401_二进制手表 {

    public static void main(String[] args) {
        Lc_401_二进制手表 lc = new Lc_401_二进制手表();

        int turnedOn = 1;

        System.out.println("二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。\n" +
                "给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。\n" +
                "小时不会以零开头：\n" +
                "  - 例如，\"01:00\" 是无效的时间，正确的写法应该是 \"1:00\" 。\n" +
                "分钟必须由两位数组成，可能会以零开头：\n" +
                "  - 例如，\"10:2\" 是无效的时间，正确的写法应该是 \"10:02\" 。\n");
        System.out.println("输入：");
        System.out.println("输出：" + lc.readBinaryWatch(turnedOn));
    }

    // 枚举时分中二进制的1 - 时间复杂度 O(1) - 空间复杂度 O(1)
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        if (turnedOn > 8) return res;

        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    res.add(h + ":" + (m < 10 ? "0" : "") + m);
                }
            }
        }

        return res;
    }

    // 枚举所有二进制 - 时间复杂度 O(1) - 空间复杂度 O(1)
    public List<String> readBinaryWatch1(int turnedOn) {
        List<String> res = new ArrayList<>();
        if (turnedOn > 8) return res;

        // 共10个二进制位，表示的最大整数为2^10=1024
        for (int i = 0; i < 1024; i++) {
            int h = i >> 6, m = i & ((int) Math.pow(2, 6) - 1);

            if (h < 12 && m < 60 && Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                res.add(h + ":" + (m < 10 ? "0" : "") + m);
            }
        }

        return res;
    }

}
