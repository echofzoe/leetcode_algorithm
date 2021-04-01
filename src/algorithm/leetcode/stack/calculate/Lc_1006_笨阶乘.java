package algorithm.leetcode.stack.calculate;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Lc_1006_笨阶乘 {

    // 笨阶乘
    // https://leetcode-cn.com/problems/clumsy-factorial/

    public static void main(String[] args) {
        Lc_1006_笨阶乘 lc = new Lc_1006_笨阶乘();
        int N = 10;

        System.out.println(N + "的笨阶乘是" + lc.clumsy(N));
    }

    // 双栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    private Map<Character, Integer> map = new HashMap<>() {{
        put('*', 2);
        put('/', 2);
        put('+', 1);
        put('-', 1);
    }};

    public int clumsy(int N) {
        if (N < 1) return 0;

        StringBuilder sb = new StringBuilder();
        char[] cs = {'*', '/', '+', '-'};
        int idx = 0;
        for (int i = N; i > 0; i--) {
            sb.append(i).append(cs[idx++ % 4]);
        }
        sb.deleteCharAt(sb.length() - 1);

        return calculate(sb.toString());
    }

    private int calculate(String s) {

        Deque<Integer> nums = new LinkedList<>();
        Deque<Character> opes = new LinkedList<>();

        char[] cs = s.toCharArray();
        int n = cs.length, idx = 0;
        while (idx < n) {

            char c = cs[idx];

            if (isNum(c)) {
                int d = 0;
                char c1;
                while (idx < n && isNum(c1 = cs[idx])) {
                    d = d * 10 + (c1 - '0');
                    idx++;
                }
                nums.offerLast(d);
            } else {
                while (!opes.isEmpty() && nums.size() >= 2 && map.get(opes.peekLast()) >= map.get(c)) {
                    calc(nums, opes);
                }

                opes.offerLast(c);
                idx++;
            }
        }

        while (!opes.isEmpty() && nums.size() >= 2) {
            calc(nums, opes);
        }

        return nums.peekLast();
    }

    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    private void calc(Deque<Integer> nums, Deque<Character> opes) {
        if (nums.size() < 2 || opes.isEmpty()) return;

        int b = nums.pollLast(), a = nums.pollLast();
        char ope = opes.pollLast();

        int d = 0;
        switch(ope) {
            case '*' -> d = a * b;
            case '/' -> d = a / b;
            case '+' -> d = a + b;
            default -> d = a - b;
        }

        nums.offerLast(d);
    }

}
