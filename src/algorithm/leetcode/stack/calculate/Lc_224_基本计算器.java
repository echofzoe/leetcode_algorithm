package algorithm.leetcode.stack.calculate;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Lc_224_基本计算器 {

    // 基本计算器
    // https://leetcode-cn.com/problems/basic-calculator/

    public static void main(String[] args) {
        Lc_224_基本计算器 lc = new Lc_224_基本计算器();
        String s = "(1+(4+5+2)-3)+(6+8)";
        String s1 = "2-4-(8+2-6+(8+4-(1)+8-10))";

        System.out.println("字符串表达式\"" + s + "\"在经过计算器计算后得到的结果是" + lc.calculate(s));
        System.out.println("字符串表达式\"" + s1 + "\"在经过计算器计算后得到的结果是" + lc.calculate1(s1));
    }

    // 符号栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int calculate(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();

        int res = 0, curSign = 1;

        Deque<Integer> sign = new LinkedList<>();
        sign.offerLast(curSign);

        for (int i = 0; i < n; i++) {
            if (cs[i] == ' ') continue;

            switch (cs[i]) {
                case '+' -> curSign = sign.peekLast();
                case '-' -> curSign = -sign.peekLast();
                case '(' -> sign.offerLast(curSign);
                case ')' -> sign.pollLast();
                default -> {
                    int nums = 0;
                    while (i < n && cs[i] >= '0' && cs[i] <= '9') {
                        nums = nums * 10 + (cs[i++] - 48);
                    }
                    i--;
                    res += curSign * nums;
                }
            }
        }

        return res;
    }

    // 双栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int calculate1(String s) {
        Deque<Integer> nums = new ArrayDeque<>();
        Deque<Character> opes = new ArrayDeque<>();

        // 预处理字符串
        nums.offerLast(0); // 避免第一个数字是负数
        s = s.replaceAll(" ", "");
        s = s.replaceAll("\\(-", "(0-"); // 避免左括号内第一个数字是负数
        s = s.replaceAll("\\(\\+", "(0+"); // 避免左括号内第一个正数前有加号

        char[] cs = s.toCharArray();
        int n = cs.length;

        for (int i = 0; i < n; i++) {
            char c = cs[i];

            switch (c) {
                case '(' -> opes.offerLast(c);
                case ')' -> {
                    while (!opes.isEmpty()) {
                        if (opes.peekLast() != '(') {
                            calc(nums, opes);
                        } else {
                            opes.pollLast();
                            break;
                        }
                    }
                }
                default -> {
                    if (Character.isDigit(c)) {
                        int a = 0, idx = i;
                        while (idx < n && Character.isDigit(cs[idx])) {
                            a = a * 10 + (cs[idx++] - '0');
                        }

                        i = idx - 1;
                        nums.offerLast(a);
                    } else {
                        while (!opes.isEmpty() && opes.peekLast() != '(') {
                            calc(nums, opes);
                        }

                        opes.offerLast(c);
                    }
                }
            }
        }

        while (!opes.isEmpty()) {
            calc(nums, opes);
        }

        return nums.peekLast();
    }

    private void calc(Deque<Integer> nums, Deque<Character> opes) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (opes.isEmpty()) return;

        int b = nums.pollLast(), a = nums.pollLast();
        char c = opes.pollLast();
        nums.offerLast(c == '+' ? a + b : a - b);
    }

}
