package algorithm.leetcode.stack;

public class Lc_1047_删除字符串中的所有相邻重复项 {

    // 删除字符串中的所有相邻重复项
    // https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/

    public static void main(String[] args) {
        Lc_1047_删除字符串中的所有相邻重复项 lc = new Lc_1047_删除字符串中的所有相邻重复项();
        String S = "abbaca";

        System.out.println("在字符串\"" + S + "\"上反复执行重复项删除操作得到的最终结果是" + lc.removeDuplicates1(S));
    }

    // 栈 - 时间复杂度 O(N) - 空间复杂度 O(1) 使用语言本身提供的字符串类模拟栈操作，可以节省重开一个栈所需的O(N)空间
    public String removeDuplicates1(String S) {
        int n = S.length();

        StringBuilder stack = new StringBuilder();

        int top = -1;
        for (int i = 0; i < n; i++) {
            char c = S.charAt(i);
            if (top >= 0 && stack.charAt(top) == c) {
                stack.deleteCharAt(top);
                top--;
            } else {
                stack.append(c);
                top++;
            }
        }

        return stack.toString();
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public String removeDuplicates2(String S) {
        int n = S.length();

        StringBuilder sb = new StringBuilder(S);

        int idx = 0;
        while (idx < sb.length() - 1) {
            if (sb.charAt(idx) == sb.charAt(idx + 1)) {
                sb.deleteCharAt(idx + 1);
                sb.deleteCharAt(idx);

                if (idx > 0) idx--;

                continue;
            }

            idx++;
        }

        return sb.toString();
    }

}
