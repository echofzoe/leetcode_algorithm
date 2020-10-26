package algorithm.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class Jzo_20_表示数值的字符串 {

    // 表示数值的字符串
    // https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/

    public static void main(String[] args) {
        Jzo_20_表示数值的字符串 lc = new Jzo_20_表示数值的字符串();
        String[] input = {"5e2", "3.1416", "1a3.14", "12e", "12e+5.4"};

        for (String s : input) {
            System.out.println("字符串\"" + s + "\"" + (lc.isNumber(s) ? "能" : "不能") + "表示一个数值.");
        }
    }

    /*
    * 确定有限状态自动机 - 时间复杂度 O(N) - 空间复杂度 O(1)
    * - 确定字符类型
    * - 空格 [ ], 数字 [0-9], 符号位[+,-], 小数点 [.], 幂符号 [E,e]
    *
    * - 枚举出所有的状态
    * - 0. 起始的空格
    * - 1. 符号位
    * - 2. 小数点前的数字
    * - 3. 小数点、小数点后的数字
    * - 4. 当小数点前为空格时,小数点、小数点后的数字
    * - 5. 幂符号
    * - 6. 幂符号后的符号位
    * - 7. 幂符号后的数字
    * - 8. 结尾的空格
    *
    * - 合法的结束状态：2,3,7,8
    */
    public boolean isNumber(String s) {
        Map[] states = {
                new HashMap<>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
                new HashMap<>() {{ put('d', 2); put('.', 4); }},                           // 1.
                new HashMap<>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
                new HashMap<>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
                new HashMap<>() {{ put('d', 3); }},                                        // 4.
                new HashMap<>() {{ put('s', 6); put('d', 7); }},                           // 5.
                new HashMap<>() {{ put('d', 7); }},                                        // 6.
                new HashMap<>() {{ put('d', 7); put(' ', 8); }},                           // 7.
                new HashMap<>() {{ put(' ', 8); }}                                         // 8.
        };

        int p = 0;
        char t;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') t = 'd';    // digit
            else if (c == '+' || c == '-') t = 's';    // sign
            else if (c == 'e' || c == 'E') t = 'e';    // 幂符号
            else if (c == '.' || c == ' ') t = c;    // 小数点和空格
            else t = '?';

            if (!states[p].containsKey(t)) return false;
            p = (int) states[p].get(t);
        }

        return p == 2 || p == 3 || p == 7 || p == 8;
    }
}
