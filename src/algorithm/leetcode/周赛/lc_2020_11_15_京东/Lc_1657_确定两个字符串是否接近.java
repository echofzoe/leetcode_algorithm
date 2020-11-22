package algorithm.leetcode.周赛.lc_2020_11_15_京东;

import java.util.*;

public class Lc_1657_确定两个字符串是否接近 {

    // 确定两个字符串是否接近
    // https://leetcode-cn.com/problems/determine-if-two-strings-are-close/

    public static void main(String[] args) {
        Lc_1657_确定两个字符串是否接近 lc = new Lc_1657_确定两个字符串是否接近();
        String word1 = "abc", word2 = "bca";

        System.out.println("字符串\"" + word1 + "\"和\"" + word2 + "\"" + (lc.closeStrings(word1,word2) ? "接近" : "不接近"));
    }

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;

        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();

        for (char c : word1.toCharArray()) {
            if (m1.containsKey(c)) {
                m1.put(c, m1.get(c) + 1);
            } else {
                m1.put(c, 1);
            }
        }

        for (char c : word2.toCharArray()) {
            if (m2.containsKey(c)) {
                m2.put(c, m2.get(c) + 1);
            } else {
                m2.put(c, 1);
            }
        }

        Queue<Integer> q1 = new PriorityQueue<>();
        Queue<Integer> q2 = new PriorityQueue<>();

        for (char c : word1.toCharArray()) {
            if (!m2.containsKey(c)) return false;

            if (!m2.get(c).equals(m1.get(c))) {
                if (!q1.contains(m1.get(c))) q1.add(m1.get(c));
                if (!q2.contains(m2.get(c))) q2.add(m2.get(c));;
            }
        }

        while (!q1.isEmpty()) {
            if (!q1.poll().equals(q2.poll())) return false;
        }

        return true;
    }

}


