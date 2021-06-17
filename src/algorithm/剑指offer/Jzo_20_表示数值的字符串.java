package algorithm.剑指offer;

/**
 * 剑指 Offer 20. 表示数值的字符串
 * https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
 *
 * @author echofzoe
 * @since 2021.4.8
 */
public class Jzo_20_表示数值的字符串 {

    public static void main(String[] args) {
        Jzo_20_表示数值的字符串 jz = new Jzo_20_表示数值的字符串();

        String s1 = "-1E-16", s2 = "12e+5.4";

        System.out.println(s1 + (jz.isNumber(s1) ? "是" : "不是") + "表示数值的字符串");
        System.out.println(s2 + (jz.isNumber(s2) ? "是" : "不是") + "表示数值的字符串");
    }

    // BF - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean isNumber(String s) {
        int n;
        if (s == null || (n = s.length()) == 0) return false;

        char[] cs = s.toCharArray();

        boolean isNum = false, isDot = false, isE = false;

        for (int i = 0; i < n; i++) {
            char c = cs[i];

            if (c == 'e' || c == 'E') {
                // 'e'和'E'前面必须有整数，且前面不能有小数点和重复的'e'或'E'
                if (!isNum || isE) return false;

                isE = true;
                isNum = false;  // 重置整数标识，因为'e'或'E'后面必须接上整数
            } else if (c == '+' || c == '-') {
                // 正负号只能出现在第一个位置，或者紧跟在'e'或'E'的后面
                if (i != 0 && cs[i - 1] != 'e' && cs[i - 1] != 'E') return false;
            } else if (c == '.') {
                // 小数点前可以没有数字，但不能有重复的小数点和'e'或'E'
                if (isDot || isE) return false;

                isDot = true;
            } else if (c >= '0' && c <= '9') {
                isNum = true;
            } else {
                // 其他情况均非法
                return false;
            }
        }

        return isNum;
    }

}
