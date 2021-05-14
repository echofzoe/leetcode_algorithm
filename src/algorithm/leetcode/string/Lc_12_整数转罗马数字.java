package algorithm.leetcode.string;

/**
 * 整数转罗马数字
 * <P>https://leetcode-cn.com/problems/integer-to-roman/</P>
 *
 * @author echofzoe
 * @since 2021.5.14
 */
public class Lc_12_整数转罗马数字 {

    public static void main(String[] args) {
        Lc_12_整数转罗马数字 lc = new Lc_12_整数转罗马数字();

        int num = 1994;

        System.out.println(num + "的罗马数字表示是 " + lc.intToRoman(num));
    }

    // 贪心 - 时间复杂度 O(1) - 空间复杂度 O(1)
    // 由大到小计算罗马数字的映射值
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            int v = values[i];
            String s = symbols[i];
            while (num >= v) {
                num -= v;
                res.append(s);
            }
            if (num == 0) break;
        }

        return res.toString();
    }

    // 硬编码 - 时间复杂度 O(1) - 空间复杂度 O(1)
    // 暴力编码每一种可能的情况
    public String intToRoman1(int num) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        StringBuilder res = new StringBuilder();
        res.append(thousands[num / 1000]);
        res.append(hundreds[num % 1000 / 100]);
        res.append(tens[num % 100 / 10]);
        res.append(ones[num % 10]);

        return res.toString();
    }

}
