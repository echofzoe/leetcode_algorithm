package algorithm.leetcode.周赛.lc_2021_4_25_发发奇;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 所有元音按顺序排布的最长子字符串
 * <P>https://leetcode-cn.com/problems/longest-substring-of-all-vowels-in-order/</P>
 * <P>题解：https://leetcode-cn.com/problems/longest-substring-of-all-vowels-in-order/solution/java-hua-dong-chuang-kou-da-gen-dui-by-e-mon8/</P>
 *
 * @author echofzoe
 * @since 2021.4.25
 */
public class Lc_5740_所有元音按顺序排布的最长子字符串 {

    public static void main(String[] args) {
        Lc_5740_所有元音按顺序排布的最长子字符串 lc = new Lc_5740_所有元音按顺序排布的最长子字符串();

        String word = "aeiaaioaaaaeiiiiouuuooaauuaeiu";

        System.out.println("当一个字符串满足如下条件时，我们称它是 美丽的 ：\n" +
                "  1.所有 5 个英文元音字母（'a' ，'e' ，'i' ，'o' ，'u'）都必须 至少 出现一次。\n" +
                "  2.这些元音字母的顺序都必须按照 字典序 升序排布（也就是说所有的 'a' 都在 'e' 前面，所有的 'e' 都在 'i' 前面，以此类推）\n");

        System.out.println("则字符串\"" + word + "\"中的最长美丽子字符串的长度是" + lc.longestBeautifulSubstring(word));  // 13
    }

    // 滑动窗口 + 大根堆 - 时间复杂度 O() - 空间复杂度 O()
    public int longestBeautifulSubstring(String word) {
        int n = word.length();

        char[] cs = word.toCharArray();
        Queue<Character> pq = new PriorityQueue<>((a, b) -> b - a);

        Map<Character, Character> pre = new HashMap<>() {{
            put('e', 'a');
            put('i', 'e');
            put('o', 'i');
            put('u', 'o');
        }};

        // 核心逻辑
        int lo = 0, hi = 0, res = 0;
        while (hi < n) {
            char c = cs[hi];
            if (pq.isEmpty() || c == pq.peek() || (c > pq.peek() && pq.peek() == pre.get(c))) {
                pq.offer(c);
                hi++;
            } else if (hi - lo > 4 && cs[lo] == 'a' && cs[hi - 1] == 'u') {
                res = Math.max(res, hi - lo);
                lo = hi;
            } else {
                pq.clear();
                lo = hi;
            }
        }

        // 结尾还需比较一次
        if (hi - lo > 4 && cs[lo] == 'a' && cs[hi - 1] == 'u') {
            res = Math.max(res, hi - lo);
        }

        return res;
    }

}
