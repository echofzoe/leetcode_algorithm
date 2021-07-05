package algorithm.leetcode.stack;

import java.util.*;

/**
 * 原子的数量
 * <P>https://leetcode-cn.com/problems/number-of-atoms/</P>
 *
 * @author echofzoe
 * @since 2021.7.5
 */
public class Lc_726_原子的数量 {

    public static void main(String[] args) {
        Lc_726_原子的数量 lc = new Lc_726_原子的数量();

        String formula = "K4(ON(SO3)2)2";
        
        System.out.println("给定一个化学式formula（作为字符串），返回每种原子的数量。\n" +
                "原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。\n" +
                "如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。\n" +
                "两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。\n" +
                "一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。\n" +
                "给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。");
        System.out.println("输入：formula = \"" + formula + "\"");
        System.out.println("输出：" + lc.countOfAtoms(formula));  // "K4N2O14S4"
    }

    // 栈 + 哈希 - 时间复杂度 O() - 空间复杂度 O()
    private char[] cs;
    private int idx, n;

    public String countOfAtoms(String formula) {
        n = formula.length();
        cs = formula.toCharArray();

        Deque<Map<String, Integer>> stack = new LinkedList<>();
        stack.push(new HashMap<>());

        idx = 0;
        while (idx < n) {
            char c = cs[idx];

            if (c == '(') {
                idx++;
                stack.push(new HashMap<>());
            } else if (c == ')') {
                idx++;

                int num = parseNum();
                Map<String, Integer> popMap = stack.pop();
                Map<String, Integer> topMap = stack.peek();
                for (Map.Entry<String, Integer> entry : popMap.entrySet()) {
                    String atom = entry.getKey();
                    int v = entry.getValue();
                    topMap.put(atom, topMap.getOrDefault(atom, 0) + v * num);
                }
            } else {
                // ！下面两句有严格的先后关系！
                String atom = parseAtom();
                int num = parseNum();
                Map<String, Integer> topMap = stack.peek();
                topMap.put(atom, topMap.getOrDefault(atom, 0) + num);
            }
        }

        TreeMap<String, Integer> t = new TreeMap<>(stack.pop());

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : t.entrySet()) {
            String atom = entry.getKey();
            int v = entry.getValue();
            sb.append(atom);
            if (v > 1) sb.append(v);
        }

        return sb.toString();
    }

    private int parseNum() {
        if (idx == n || !Character.isDigit(cs[idx])) return 1;

        int num = 0;
        while (idx < n && Character.isDigit(cs[idx])) {
            num = num * 10 + cs[idx++] - '0';
        }

        return num;
    }

    private String parseAtom() {
        StringBuilder sb = new StringBuilder();
        sb.append(cs[idx++]);

        while (idx < n && Character.isLowerCase(cs[idx])) {
            sb.append(cs[idx++]);
        }

        return sb.toString();
    }

}
