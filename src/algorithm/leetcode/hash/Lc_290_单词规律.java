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
        Map<Character, String> c2str = new HashMap<>();
        Map<String, Character> str2c = new HashMap<>();

        int index = 0, n = s.length();
        for (char c : pattern.toCharArray()) {
            if (index >= n) return false;

            int end = index;
            while (end < n && s.charAt(end) != ' ') end++;
            String tmp = s.substring(index, end);

            if (c2str.containsKey(c) && !c2str.get(c).equals(tmp)) return false;

            if (str2c.containsKey(tmp) && str2c.get(tmp) != c) return false;

            c2str.put(c, tmp);
            str2c.put(tmp, c);

            index = end + 1;
        }

        return index >= n;
    }

}
