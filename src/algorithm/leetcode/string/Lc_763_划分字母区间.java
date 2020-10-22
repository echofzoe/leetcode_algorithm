package algorithm.leetcode.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc_763_划分字母区间 {

    // 划分字母区间
    // https://leetcode-cn.com/problems/partition-labels/

    public static void main(String[] args) {
        Lc_763_划分字母区间 lc = new Lc_763_划分字母区间();
        String S = "ababcbacadefegdehijhklij";
        
        System.out.println("把字符串 " + S + " 划分为尽可能多的片段,同一个字母只会出现在其中的一个片段,划分后每个字符串片段的长度为: " + lc.partitionLabels(S).toString());
    }

    public List<Integer> partitionLabels(String S) {
        if (S.length() == 0) return new ArrayList<>();

        int[] last = new int[26];
        for (int i = 0; i < S.length(); i++) {
            last[S.charAt(i) - 'a'] = i;
        }

        List<Integer> partition = new ArrayList<>();

        int start = 0, end = 0;
        for (int i = 0; i < S.length(); i++) {
            end = Math.max(end, last[S.charAt(i) - 'a']);

            if (i == end) {
                partition.add(end - start + 1);    // 索引+1即长度
                start = end + 1;
            }
        }

        return partition;
    }
}
