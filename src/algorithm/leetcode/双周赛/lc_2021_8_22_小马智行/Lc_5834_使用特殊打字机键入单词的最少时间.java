package algorithm.leetcode.双周赛.lc_2021_8_22_小马智行;

/**
 * 使用特殊打字机键入单词的最少时间
 * <P>https://leetcode-cn.com/problems/minimum-time-to-type-word-using-special-typewriter/</P>
 *
 * @author echofzoe
 * @since 2021.8.22
 */
public class Lc_5834_使用特殊打字机键入单词的最少时间 {

    public static void main(String[] args) {
        Lc_5834_使用特殊打字机键入单词的最少时间 lc = new Lc_5834_使用特殊打字机键入单词的最少时间();

        String word = "zjpc";

        System.out.println("有一个特殊打字机，它由一个 圆盘 和一个 指针 组成， 圆盘上标有小写英文字母 'a' 到 'z'。只有 当指针指向某个字母时，它才能被键入。指针 初始时 指向字符 'a' 。\n" +
                "每一秒钟，你可以执行以下操作之一：\n" +
                "  - 将指针 顺时针 或者 逆时针 移动一个字符。\n" +
                "  - 键入指针 当前 指向的字符。\n" +
                "给你一个字符串 word ，请你返回键入 word 所表示单词的 最少 秒数 。\n");
        System.out.println("输入：word = " + word);
        System.out.println("输出：" + lc.minTimeToType(word));
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int minTimeToType(String word) {
        int pre = 0, res = 0;

        for (char c : word.toCharArray()) {
            int cur = c - 'a';

            int a = (pre - cur + 26) % 26, b = (cur - pre + 26) % 26;
            res += Math.min(a, b);

            pre = cur;
            res++;
        }

        return res;
    }
}
