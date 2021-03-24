package algorithm.leetcode.doublePoint;

import java.util.*;

public class Lc_524_通过删除字母匹配到字典里最长单词 {

    // 通过删除字母匹配到字典里最长单词
    // https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/description/

    public static void main(String[] args) {
        Lc_524_通过删除字母匹配到字典里最长单词 lc = new Lc_524_通过删除字母匹配到字典里最长单词();
        String s = "abpcplea";
        List<String> dictionary = new ArrayList<>(Arrays.asList("ale", "apple", "monkey", "plea"));

        System.out.println("字典" + dictionary.toString() + "中能够通过在\"" + s + "\"中删除某些字符得到的长度最长且字典序最小的字符串是" + lc.findLongestWord(s, dictionary));
    }

    // 双指针 - 时间复杂度 O(NL) L为dictionary中字符串的平均长度 - 空间复杂度 O(1) 声明char[] cs是为了在寻址时得到优于charAt()方法的性能，可以直接使用charAt()方法来节省char[]的空间
    public String findLongestWord(String s, List<String> dictionary) {
        int n = s.length();
        char[] cs = s.toCharArray();

        String res = "";
        for (String dic : dictionary) {
            int i = 0, j = 0;
            while (i < n && j < dic.length()) {
                if (cs[i] == dic.charAt(j)) {
                    j++;
                }

                if (j == dic.length()) {
                    if (dic.length() > res.length() || (dic.length() == res.length() && dic.compareTo(res) < 0)) {
                        res = dic;
                    }
                }

                i++;
            }
        }

        return res;
    }

    // 排序 + 双指针，排序后找到符合的候选答案后可直接返回 - 时间复杂度 O(LNlogN + LN) L为dictionary中字符串的平均长度，排序需要花费O(NlogN) - 空间复杂度 O(logN) 为排序产生的额外空间复杂度
    public String findLongestWord1(String s, List<String> dictionary) {
        int n = s.length();
        dictionary.sort((x, y) -> x.length() != y.length() ? y.length() - x.length() : x.compareTo(y));
        char[] cs = s.toCharArray();

        for (String dic : dictionary) {
            int i = 0, j = 0;
            while (i < n && j < dic.length()) {
                if (cs[i] == dic.charAt(j)) {
                    j++;
                }

                if (j == dic.length()) {
                    return dic;
                }

                i++;
            }
        }

        return "";
    }

}
