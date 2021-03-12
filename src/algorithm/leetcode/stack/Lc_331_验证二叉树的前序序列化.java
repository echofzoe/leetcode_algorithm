package algorithm.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

public class Lc_331_验证二叉树的前序序列化 {

    // 验证二叉树的前序序列化
    // https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/

    public static void main(String[] args) {
        Lc_331_验证二叉树的前序序列化 lc = new Lc_331_验证二叉树的前序序列化();
        String preorder = "7,2,#,2,#,#,#,6,#";

        System.out.println("字符串\"" + preorder + "\"" + (lc.isValidSerialization3(preorder) ? "是" : "不是") + "一颗正确的二叉树前序序列化");
    }

    // 栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public boolean isValidSerializationStack(String preorder) {
        int n = preorder.length();
        char[] cs = preorder.toCharArray();

        int idx = 0;
        Deque<Integer> stack = new LinkedList<>();
        // base case
        stack.push(1);

        while (idx < n) {
            if (stack.isEmpty()) return false;

            if (cs[idx] == ',') {
                idx++;
            } else if (cs[idx] == '#') {
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }

                idx++;
            } else {
                while (idx < n && cs[idx++] != ',') ;

                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }

                stack.push(2);
            }
        }

        return stack.isEmpty();
    }

    // 计数 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean isValidSerializationCount(String preorder) {
        int n = preorder.length();
        char[] cs = preorder.toCharArray();

        int idx = 0, cnt = 1; // 槽位初始为1
        while (idx < n) {
            if (cnt == 0) return false;

            if (cs[idx] == ',') {
                idx++;
            } else if (cs[idx] == '#') {
                cnt--;
                idx++;
            } else {
                while (idx < n && cs[idx++] != ',') ;
                cnt++;  // cnt = cnt - 1 + 2;
            }
        }

        return cnt == 0;
    }
    
    // 模拟（找规律） - 时间复杂度 O(N) - 空间复杂度 O(1)
    // - 1. 任意二叉树的叶子结点数比度为2的非叶子节点数多1，故序列中，数字数量 = #号数量 − 1
    // - 2. 在最后一个位置之前，数字数量 >= #号数量数字数量
    // - 3. 由1和2可知，最后一个位置必为#号
    public boolean isValidSerialization3(String preorder) {
        int n = preorder.length();
        char[] cs = preorder.toCharArray();

        if ((cs[0] == '#' && n != 1) || cs[n - 1] != '#') return false;

        int num = 0, sharp = 0, idx = 0;
        while (idx < n) {
            if (cs[idx] == ',') {
                if (num < sharp) return false;
                idx++;
            } else if (cs[idx] == '#') {
                idx++;
                sharp++;
            } else {
                while (idx < n && cs[idx++] != ',') ;
                num++;
            }
        }

        return num == sharp - 1;
    }

}
