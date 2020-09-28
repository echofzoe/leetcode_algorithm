package algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc_无重复字符串的排列组合 {

    // 无重复字符串的排列组合
    // - 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
    // https://leetcode-cn.com/problems/permutation-i-lcci/

    public static void main(String[] args) {
        Lc_无重复字符串的排列组合 lc = new Lc_无重复字符串的排列组合();
        String S = "qwe";
        System.out.println("字符串 " + S + " 的所有无重复字符串的排列组合为：" + Arrays.toString(lc.permutation(S)));
    }

    public String[] permutation(String S) {
        List<String> res = new ArrayList<>() {{
            add(S);
        }};

        for (int i = 0; i < S.length() - 1; i++) {
            int size = res.size();

            for (int j = i + 1; j < S.length(); j++) {

                for (int index = 0; index < size; index++) {
                    res.add(swap(res.get(index), i, j));
                }
            }
        }

        return res.toArray(new String[res.size()]);
    }

    private String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        chars[i] ^= chars[j];
        chars[j] ^= chars[i];
        chars[i] ^= chars[j];
        return new String(chars);
    }


}
