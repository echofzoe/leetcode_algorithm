package algorithm.leetcode.周赛.lc_2021_7_11_商汤;

import java.util.HashSet;
import java.util.Set;

/**
 * 长度为 3 的不同回文子序列
 * <P>https://leetcode-cn.com/problems/unique-length-3-palindromic-subsequences/</P>
 *
 * @author echofzoe
 * @since 2021.7.11
 */
public class Lc_5809_长度为3的不同回文子序列 {

    public static void main(String[] args) {
        Lc_5809_长度为3的不同回文子序列 lc = new Lc_5809_长度为3的不同回文子序列();

        String s = "bbcbaba";

        System.out.println("给你一个字符串 s ，返回 s 中 长度为 3 的不同回文子序列 的个数。\n" +
                "即便存在多种方法来构建相同的子序列，但相同的子序列只计数一次。\n" +
                "回文 是正着读和反着读一样的字符串。\n" +
                "子序列 是由原字符串删除其中部分字符（也可以不删除）且不改变剩余字符之间相对顺序形成的一个新字符串。\n" +
                "  - 例如，\"ace\" 是 \"abcde\" 的一个子序列。\n" +
                "注：\n" +
                "  - 3 <= s.length <= 105\n" +
                "  - b. s 仅由小写英文字母组成\n");
        System.out.println("输入：s = " + "\"" + s + "\"");
        System.out.println("输出：" + lc.countPalindromicSubsequence1(s));  // 4
    }

    // 位运算模拟 - 时间复杂度 O(N) N为s的长度，遍历一遍s，每一次遍历时再遍历26个字母，总计O(26N)=O(N) - 空间复杂度 O(1)
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();

        int[] l = new int[n], r = new int[n];
        for (int i = 0; i < n; i++) {
            int mask = 1 << cs[i] - 'a';
            l[i] |= i == 0 ? mask : l[i - 1] | mask;
        }
        for (int i = n - 1; i >= 0; i--) {
            int mask = 1 << cs[i] - 'a';
            r[i] |= i == n - 1 ? mask : r[i + 1] | mask;
        }

        Set<String> set = new HashSet<>();
        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j < 26; j++) {
                if (((l[i - 1] >> j) & 1) == 1 && ((r[i + 1] >> j) & 1) == 1) {
                    set.add("" + j + cs[i]);
                }
            }
        }

        return set.size();
    }

    // 模拟 - 时间复杂度 O() - 空间复杂度 O()
    public int countPalindromicSubsequence1(String s) {
        char[] cs = s.toCharArray();

        Set<String> set = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 'a');
            int l = s.indexOf(c), r = s.lastIndexOf(c);

            if (r > l) {
                for (int j = l + 1; j < r; j++) {
                    set.add("" + c + cs[j]);
                }
            }
        }

        return set.size();
    }

}
