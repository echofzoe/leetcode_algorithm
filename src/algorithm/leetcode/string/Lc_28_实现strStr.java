package algorithm.leetcode.string;

/**
 * 实现 strStr()
 * <P>https://leetcode-cn.com/problems/implement-strstr/</P>
 *
 * @author echofzoe
 * @since 2021.4.20
 */
public class Lc_28_实现strStr {

    public static void main(String[] args) {
        Lc_28_实现strStr lc = new Lc_28_实现strStr();
        String haystack = "hello", needle = "ll";

        System.out.println("在字符串\"" + haystack + "\"中, \"" + needle + "\"出现的第一个位置是" + lc.strStrSlidingWindow(haystack, needle));
    }

    // 滑动窗口
    // - 时间复杂度 最坏O((N-M)*M) 最优O(N) 其中N为haystack的长度，M为needle的长度。内循环中比较字符串的复杂度为M，最坏情况下总共需要比较(N-M)次
    // - 空间复杂度 O(1)
    public int strStrSlidingWindow(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        int n = haystack.length(), m = needle.length();

        for (int i = 0; i < n - m + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0) && haystack.startsWith(needle, i)) {
                return i;
            }
        }

        return -1;
    }

    // KMP - 时间复杂度 O(N + M) - 空间复杂度 O(M)
    public int strStrSlidingWindowKMP(String ss, String pp) {
        int n = ss.length(), m = pp.length();

        // 增加哨兵
        ss = " " + ss;
        pp = " " + pp;

        char[] s = ss.toCharArray(), p = pp.toCharArray();

        // 构建 next 数组，数组长度为 pattern 串的长度
        int[] next = new int[m + 1];
        // 构建过程：从 i = 2, j = 0 开始，i 小于等于匹配串的长度
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功的话，j = next[j]
            while (j > 0 && p[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，j++
            if (p[i] == p[j + 1]) j++;
            // 更新 next[i]，结束本次循环，i++
            next[i] = j;
        }

        // 匹配过程：从 i = 1, j = 0 开始，i 小于等于原串长度
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功的话，j = next[j]
            while (j > 0 && s[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++，结束本次循环后再 i++
            if (s[i] == p[j + 1]) j++;
            // 匹配串整段匹配成功，直接返回下标
            if (j == m) return i - m;
        }

        return -1;
    }

}
