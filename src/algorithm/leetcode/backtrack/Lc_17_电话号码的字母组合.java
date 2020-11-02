package algorithm.leetcode.backtrack;

import java.util.*;

public class Lc_17_电话号码的字母组合 {

    // 电话号码的字母组合
    // https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/

    public static void main(String[] args) {
        Lc_17_电话号码的字母组合 lc = new Lc_17_电话号码的字母组合();
        String digits1 = "23";
        String digits2 = "68";

        System.out.println("字符串\"" + digits1 + "\"所能表示的所有字母组合是" + lc.letterCombinationsBacktrack(digits1).toString());
        System.out.println("字符串\"" + digits2 + "\"所能表示的所有字母组合是" + lc.letterCombinationsQueue(digits2).toString());
    }

    // 回溯 - 时间复杂度 O(3^m * 4^n) 其中m是输入中对应3个字母的数字个数, n是输入中对应4个字母的数字个数 - 空间复杂度 O(M+N)
    public List<String> letterCombinationsBacktrack(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        backtrack(res, phoneMap, digits, 0, new StringBuilder());
        return res;
    }

    private void backtrack(List<String> res, Map<Character, String> phoneMap, String digits, int index, StringBuilder sb) {
        if (index == digits.length()) res.add(sb.toString());

        else {
            String letters = phoneMap.get(digits.charAt(index));
            for (int i = 0; i < letters.length(); i++) {
                sb.append(letters.charAt(i));
                backtrack(res, phoneMap, digits, index + 1, sb);
                sb.deleteCharAt(index);
            }
        }
    }

    // 队列 - 时间复杂度 O(3^m * 4^n) 其中m是输入中对应3个字母的数字个数, n是输入中对应4个字母的数字个数 - 空间复杂度 O(3^m * 4^n) 为总结果数字
    public List<String> letterCombinationsQueue(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        Queue<String> queue = new LinkedList<>();
        String letters = phoneMap.get(digits.charAt(0));
        for (int i = 0; i < letters.length(); i++) {
            queue.offer(letters.substring(i, i + 1));
        }

        for (int i = 1; i < digits.length(); i++) {
            letters = phoneMap.get(digits.charAt(i));
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < letters.length(); k++) {
                    queue.offer(queue.peek() + letters.charAt(k));
                }
                queue.poll();
            }
        }

        int size = queue.size();
        for (int i = 0; i < size; i++) {
            res.add(queue.poll());
        }

        return res;
    }

    Map<Character, String> phoneMap = new HashMap<>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};
}
