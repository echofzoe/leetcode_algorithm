package algorithm.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

public class Lc_290_单词规律 {

    // 单词规律
    // https://leetcode-cn.com/problems/word-pattern/

    public static void main(String[] args) {
        Lc_290_单词规律 lc = new Lc_290_单词规律();
        String pattern1 = "abba", str1 = "dog dog dog dog";    // false
        String pattern2 = "abba", str2 = "dog cat cat dog";    // true

        System.out.println("字符串\"" + str1 + "\"" + (lc.wordPattern(pattern1, str1) ? "遵循\"" : "不遵循\"") + pattern1 + "\"规律");
        System.out.println("字符串\"" + str2 + "\"" + (lc.wordPattern(pattern2, str2) ? "遵循\"" : "不遵循\"") + pattern2 + "\"规律");
    }

    // 哈希 - 时间复杂度 O(n + m) - 空间复杂度 O(n + m)
    public boolean wordPattern(String pattern, String s) {
        String[] arr = s.split(" ");

        int n = pattern.length();
        if (n != arr.length) return false;

        Map<Character, String> p2s = new HashMap<>();
        Map<String, Character> s2p = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char c = pattern.charAt(i);
            String tmp = arr[i];

            if (p2s.containsKey(c) && !p2s.get(c).equals(tmp) || s2p.containsKey(tmp) && s2p.get(tmp) != c) return false;

            p2s.put(c, tmp);
            s2p.put(tmp, c);
        }

        return true;
    }

}
