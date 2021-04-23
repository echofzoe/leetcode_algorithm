package algorithm.leetcode.string.kmp;

import java.util.*;

/**
 * 阿里巴巴2021 4月2日面试题
 * <P>评测题目: 用你最熟悉的语言实现以下方法，不能使用正则</P>
 *
 * @author echofzoe
 * @since 2021.4.23
 */
public class ali_interview_21_4_2 {

    public static void main(String[] args) {
        ali_interview_21_4_2 ali = new ali_interview_21_4_2();

        String s = "abcdcdd", p1 = "abc", p2 = "cd";

        System.out.println("在源字符串\"" + s + "\"中，以\"" + p1 + "\"开头，\"" + p2 + "\"结尾的子串有" + ali.findSubString(s, p1, p2));
    }

    /**
     * @param source  源字符串
     * @param prefix  开头字符串
     * @param postfix 结尾字符串
     * @return 在source中找出所有以prefix开头并且以postfix结尾的子串
     */
    public List<String> findSubString(String source, String prefix, String postfix) {
        if (source == null || prefix == null || prefix.length() == 0 || postfix == null || postfix.length() == 0)
            return null;
        final int n = source.length(), m1 = prefix.length(), m2 = postfix.length();

        List<String> res = new ArrayList<>();
        if (n == 0) return res;

        // 1.计算候选答案的下标
        int idx = 0;
        List<Integer> preIdx = new ArrayList<>(), postIdx = new ArrayList<>();
        while (idx < n - m1) {
            int lo = source.indexOf(prefix, idx);
            if (lo < 0) break;

            preIdx.add(lo);
            idx = lo + 1;
        }

        idx = 0;
        while (idx < n - m2) {
            int hi = source.indexOf(postfix, idx);
            if (hi < 0) break;

            postIdx.add(hi);
            idx = hi + 1;
        }

        // 2.整理答案
        for (int lo : preIdx) {
            for (int hi : postIdx) {
                if (lo <= hi) {
                    res.add(source.substring(lo, hi + m2));
                }
            }
        }

        return res;
    }

}
