package algorithm.leetcode.slidingWindow;

import java.util.Arrays;

public class Lc_567_字符串的排列 {

    // 字符串的排列
    // https://leetcode-cn.com/problems/permutation-in-string/

    public static void main(String[] args) {
        Lc_567_字符串的排列 lc = new Lc_567_字符串的排列();
        String s1 = "ab", s2 = "eidbaooo";

        System.out.println("\"" + s2 + "\"" + (lc.checkInclusion(s1, s2) ? "包含" : "不包含") + "\"" + s1 + "\"的排列之一");
    }

    // 滑动窗口 - 时间复杂度 O(n1 + n2 * ∣Σ∣ + ∣Σ∣) 其中∣Σ∣是字符集的大小，取决于出现字符的最小ASCII值和最大ASCII值 - 空间复杂度 O(∣Σ∣)
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 > n2) return false;

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        for (int i = 0; i < n1; i++) {
            freq1[s1.charAt(i) - 'a']++;
            freq2[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(freq1, freq2)) return true;

        for (int i = n1; i < n2; i++) {
            freq2[s2.charAt(i) - 'a']++;
            freq2[s2.charAt(i - n1) - 'a']--;

            if (Arrays.equals(freq1, freq2)) return true;
        }

        return false;
    }

}
