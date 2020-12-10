package algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Lc_842_将数组拆分成斐波那契序列 {

    // 将数组拆分成斐波那契序列
    // https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence/

    public static void main(String[] args) {
        Lc_842_将数组拆分成斐波那契序列 lc = new Lc_842_将数组拆分成斐波那契序列();
        String S = "11235813";

        System.out.println("将字符串\"" + S + "\"拆分成斐波那契序列的结果是" + lc.splitIntoFibonacci(S));
    }

    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();

        if (S.length() < 3) return res;

        backtrace(res, S, 0);
        return res;
    }

    private boolean backtrace(List<Integer> res, String S, int index) {
        int n = S.length();

        // boundary
        if (index == n && res.size() >= 3) {
            return true;
        }

        for (int i = index; i < n; i++) {
            if (S.charAt(index) == '0' && i > index) break;

            long num = Long.parseLong(S.substring(index, i + 1));

            if (num > Integer.MAX_VALUE) break;

            int size = res.size();
            if (size >= 2 && num > res.get(size - 1) + res.get(size - 2)) break;

            if (size <= 1 || num == res.get(size - 1) + res.get(size - 2)) {
                res.add((int) num);

                if (backtrace(res, S, i + 1)) {
                    return true;
                }

                res.remove(res.size() - 1);
            }
        }

        return false;
    }

}
