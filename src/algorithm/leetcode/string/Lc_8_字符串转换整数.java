package algorithm.leetcode.string;

public class Lc_8_字符串转换整数 {

    // 字符串转换整数 (atoi)
    // https://leetcode-cn.com/problems/string-to-integer-atoi/

    public static void main(String[] args) {
        Lc_8_字符串转换整数 lc = new Lc_8_字符串转换整数();
        String s = "-48";

        System.out.println("字符串 " + s + " 转换成整数后结果为 " + lc.myAtoi(s));
    }

    public int myAtoi(String s) {
        s = s.trim();    // 去除所有空格
        if (s.length() == 0) return 0;    // 判空
        if (!Character.isDigit(s.charAt(0)) && s.charAt(0) != '+' && s.charAt(0) != '-') return 0;    // 判断首个非空字母

        int res = 0;
        boolean negative = s.charAt(0) == '-';    // 是否负数
        int i = !Character.isDigit(s.charAt(0)) ? 1 : 0;    // 数字从索引 0 or 1 开始

        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            // -2147483648
            // -214748364
            int tmp = ((negative ? Integer.MIN_VALUE : Integer.MIN_VALUE + 1) + (s.charAt(i) - '0')) / 10;
            if (tmp > res) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            res = res * 10 - (s.charAt(i++) - '0');
        }

        return negative ? res : -res;
    }
}
