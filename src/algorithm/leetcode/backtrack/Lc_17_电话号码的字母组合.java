package algorithm.leetcode.backtrack;

import java.util.*;

/**
 * 电话号码的字母组合
 * <P>https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/</P>
 *
 * @author echofzoe
 * @since unknown
 * @updated 2021.6.2
 */
public class Lc_17_电话号码的字母组合 {

    public static void main(String[] args) {
        Lc_17_电话号码的字母组合 lc = new Lc_17_电话号码的字母组合();
        String digits1 = "23";
        String digits2 = "68";

        System.out.println("字符串\"" + digits1 + "\"所能表示的所有字母组合是" + lc.letterCombinations(digits1).toString());
        System.out.println("字符串\"" + digits2 + "\"所能表示的所有字母组合是" + lc.letterCombinations1(digits2).toString());
    }

    // 回溯 - 时间复杂度 O(3^m * 4^n) 其中m是输入中对应3个字母的数字个数, n是输入中对应4个字母的数字个数 - 空间复杂度 O(M+N)
    private final Map<Character, String> phoneMap = new HashMap<>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    private List<String> res;
    private int n;

    public List<String> letterCombinations(String digits) {
        this.res = new ArrayList<>();
        if (digits == null || (this.n = digits.length()) == 0) return res;

        dfs(digits, 0, new StringBuilder());

        return res;
    }

    private void dfs(String digits, int idx, StringBuilder sb) {
        if (idx == n) {
            res.add(sb.toString());
            return;
        }

        char c = digits.charAt(idx);
        String letters = phoneMap.get(c);
        for (char cc : letters.toCharArray()) {
            sb.append(cc);
            dfs(digits, idx + 1, sb);
            // backtrace
            sb.deleteCharAt(idx);
        }
    }

    // 队列 - 时间复杂度 O(3^m * 4^n) 其中m是输入中对应3个字母的数字个数, n是输入中对应4个字母的数字个数 - 空间复杂度 O(3^m * 4^n) 为总结果数字
    public List<String> letterCombinations1(String digits) {
        this.res = new ArrayList<>();
        if (digits == null || (this.n = digits.length()) == 0) return res;

        char[] cs = digits.toCharArray();

        Deque<String> dq = new LinkedList<>();
        String letters = phoneMap.get(cs[0]);
        for (char c : letters.toCharArray()) dq.offerLast(String.valueOf(c));

        for (int i = 1; i < n; i++) {
            letters = phoneMap.get(cs[i]);

            int size = dq.size();
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < letters.length(); k++) {
                    dq.offerLast(dq.peek() + letters.charAt(k));
                }
                dq.pollFirst();
            }
        }

        while (!dq.isEmpty()) {
            res.add(dq.pollFirst());
        }

        return res;
    }

}
