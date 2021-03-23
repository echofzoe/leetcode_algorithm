package algorithm.leetcode.doublePoint;

public class Lc_680_II_验证回文字符串 {

    // 验证回文字符串 Ⅱ
    // https://leetcode-cn.com/problems/valid-palindrome-ii/description/

    public static void main(String[] args) {
        Lc_680_II_验证回文字符串 lc = new Lc_680_II_验证回文字符串();
        String s = "deeee";

        System.out.println("非空字符串\"" + s + "\"" + (lc.validPalindrome(s) ? "可以" : "不可以") + "通过只删除一个字符而成为回文字符串");
    }

    // 双指针 + 贪心 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean validPalindrome(String s) {
        char[] cs = s.toCharArray();
        int lo = 0, hi = s.length() - 1;

        while (lo < hi) {
            if (cs[lo] == cs[hi]) {
                lo++;
                hi--;
            } else {
                return validPalindrome(s, lo + 1, hi) || validPalindrome(s, lo, hi - 1);
            }
        }

        return true;
    }

    private boolean validPalindrome(String s, int lo, int hi) {
        char[] cs = s.toCharArray();

        while (lo < hi) {
            if (cs[lo++] != cs[hi--]) {
                return false;
            }
        }

        return true;
    }

}
