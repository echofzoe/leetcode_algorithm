package algorithm.leetcode.双周赛.lc_2021_7_10_贝壳;

import java.util.TreeMap;

/**
 * 求和游戏
 * <P>https://leetcode-cn.com/problems/sum-game/</P>
 *
 * @author echofzoe
 * @since 2021.7.11
 */
public class Lc_5794_求和游戏 {

    public static void main(String[] args) {
        Lc_5794_求和游戏 lc = new Lc_5794_求和游戏();

        String num = "?3295???";

        System.out.println("Alice 和 Bob 玩一个游戏，两人轮流行动，Alice 先手 。\n" +
                "给你一个 偶数长度 的字符串 num ，每一个字符为数字字符或者 '?' 。每一次操作中，如果 num 中至少有一个 '?' ，那么玩家可以执行以下操作：\n" +
                "  - 选择一个下标 i 满足 num[i] == '?' 。\n" +
                "  - 将 num[i] 用 '0' 到 '9' 之间的一个数字字符替代。\n" +
                "当 num 中没有 '?' 时，游戏结束。\n" +
                "Bob 获胜的条件是 num 中前一半数字的和 等于 后一半数字的和。Alice 获胜的条件是前一半的和与后一半的和 不相等 。\n" +
                "  - 比方说，游戏结束时 num = \"243801\" ，那么 Bob 获胜，因为 2+4+3 = 8+0+1 。如果游戏结束时 num = \"243803\" ，那么 Alice 获胜，因为 2+4+3 != 8+0+3 。\n" +
                "在 Alice 和 Bob 都采取 最优 策略的前提下，如果 Alice 获胜，请返回 true ，如果 Bob 获胜，请返回 false 。\n" +
                "注意：\n" +
                "  - 2 <= num.length <= 105\n" +
                "  - num.length 是 偶数 。\n" +
                "  - num 只包含数字字符和 '?' 。\n");
        System.out.println("输入：num = " + "\"" + num + "\"");
        System.out.println("输出：" + lc.sumGame(num));  // false
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public boolean sumGame(String num) {
        int n = num.length();
        char[] cs = num.toCharArray();

        int cnt1 = 0, cnt2 = 0;
        int s1 = 0, s2 = 0;
        for (int i = 0; i < n; i++) {
            if (i < n / 2) {
                if (cs[i] == '?') cnt1++;
                else s1 += cs[i] - '0';
            } else {
                if (cs[i] == '?') cnt2++;
                else s2 += cs[i] - '0';
            }
        }

        int cnt = cnt1 + cnt2;
        if (cnt == 0) return s1 != s2;  // 无'?'的情况
        if ((cnt & 1) == 1) return true;  // '?'数量为奇数的情况

        // 当'?'数量为偶数时，分类讨论，因为 Alice 想让 s1 != s2，故分为以下两种情况讨论：
        // 1. s1 > s2
        // - Alice 希望到游戏结束时 s1 > s2，故 Alice 必须在左边的'?'中放'9'、右边的'?'中放'0'
        // - 为了应对这种情况，Bob 的操作相反
        // - 在左边，Alice 可以操作 ceil(cnt1 / 2) 次; 在右边，Bob 可以操作 cnt2 - [ceil(cnt / 2) - ceil(cnt1 / 2)]
        // 2. s1 < s2
        // - Alice 希望到游戏结束时 s1 < s2，故 Alice 必须在左边的'?'中放'0'、右边的'?'中放'9'
        // - 为了应对这种情况，Bob 的操作相反
        // - 在左边，Bob 可以操作 ceil(cnt1 / 2) 次; 在右边，Alice 可以操作 cnt2 - (cnt / 2 - ceil(cnt1 / 2))

        return s1 + ceil(cnt1) * 9 > s2 + (cnt2 - (ceil(cnt) - ceil(cnt1))) * 9
                || s1 + cnt1 / 2 * 9 < s2 + (cnt2 - (cnt / 2 - cnt1 / 2)) * 9;
    }

    private int ceil(int a) {
        return (int) Math.ceil((double) a / 2);
    }

}
