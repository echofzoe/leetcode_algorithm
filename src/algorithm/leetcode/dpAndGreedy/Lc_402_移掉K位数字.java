package algorithm.leetcode.dpAndGreedy;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Lc_402_移掉K位数字 {

    // 移掉K位数字
    // - 题解: https://leetcode-cn.com/problems/remove-k-digits/solution/yi-zhao-chi-bian-li-kou-si-dao-ti-ma-ma-zai-ye-b-5/
    // https://leetcode-cn.com/problems/remove-k-digits/

    public static void main(String[] args) {
        Lc_402_移掉K位数字 lc = new Lc_402_移掉K位数字();
        String num = "10200";
        int k = 1;
        
        System.out.println("移除数字" + num + "中的" + k + "位数字，使得剩下的数字最小的结果是" + lc.removeKdigits(num, k));
    }

    // 贪心 + 单调栈  - 时间复杂度O() - 空间复杂度()
    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new LinkedList<>();

        for (int i = 0; i < num.length(); i++) {
            while (k > 0 && !stack.isEmpty() && num.charAt(i) < stack.peekLast()) {
                stack.pollLast();
                k--;
            }
            stack.offerLast(num.charAt(i));
        }

        for (int i = 0; i < k; i++) {
            stack.pollLast();
        }

        StringBuilder res = new StringBuilder();
        boolean leadingZero = true;
        while (!stack.isEmpty()) {
            char c = stack.pollFirst();
            if (leadingZero && c == '0') continue;
            leadingZero = false;
            res.append(c);
        }

        return res.length() == 0 ? "0" : res.toString();
    }

}
