package algorithm.leetcode.slidingWindow;

public class Lc_424_替换后的最长重复字符 {

    // 替换后的最长重复字符
    // https://leetcode-cn.com/problems/longest-repeating-character-replacement/

    public static void main(String[] args) {
        Lc_424_替换后的最长重复字符 lc = new Lc_424_替换后的最长重复字符();
        String s = "AABABBA";
        int k = 1;

        System.out.println("在字符串\"" + s + "\"中任意替换" + k + "个字符能够得到的包含重复字符的最长子串的长度为 " + lc.characterReplacement(s, k));
    }

    // 滑动窗口（双指针） - 时间复杂度 O(N) - 空间复杂度 O(1) 为长度为26的数组空间
    public int characterReplacement(String s, int k) {
        int n = s.length();

        int[] count = new int[26];
        int left = 0, right = 0, historyCountMax = 0;
        while (right < n) {
            count[s.charAt(right) - 'A']++;

            historyCountMax = Math.max(historyCountMax, count[s.charAt(right) - 'A']);

            if (right - left + 1 > historyCountMax + k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            right++;
        }

        return right - left;
    }

}
