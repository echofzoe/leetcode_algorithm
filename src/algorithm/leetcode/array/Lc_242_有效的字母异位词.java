package algorithm.leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class Lc_242_有效的字母异位词 {

    // 有效的字母异位词
    // https://leetcode-cn.com/problems/valid-anagram/

    public static void main(String[] args) {
        Lc_242_有效的字母异位词 lc = new Lc_242_有效的字母异位词();
        String s = "acca", t = "ccac";

        System.out.println("字符串\"" + s + "\"和\"" + t + "\"" + (lc.isAnagram(s,t) ? "是" : "不是") + "字母异位词");
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();

        for (char c : s.toCharArray()) {
            if (m1.containsKey(c)) {
                m1.put(c, m1.get(c) + 1);
            } else {
                m1.put(c, 1);
            }
        }

        for (char c : t.toCharArray()) {
            if (m2.containsKey(c)) {
                m2.put(c, m2.get(c) + 1);
            } else {
                m2.put(c, 1);
            }
        }

        for (char c : s.toCharArray()) {
            if (!m2.containsKey(c)) {
                return false;
            } else {
                if (!m1.get(c).equals(m2.get(c))) {
                    return false;
                }
            }
        }

        return true;
    }

}
