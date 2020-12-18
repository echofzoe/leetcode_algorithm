package algorithm.leetcode.bit;

import java.util.HashMap;
import java.util.Map;

public class Lc_389_找不同 {

    // 找不同
    // https://leetcode-cn.com/problems/find-the-difference/

    public static void main(String[] args) {
        Lc_389_找不同 lc = new Lc_389_找不同();
        String s = "abcd";
        String t = "abcde";

        System.out.println("字符串\"" + t + "\"是由字符串\"" + s + "\"随机重排后在随机位置添加一个字母后的结果，添加的字母是" + lc.findTheDifferenceBit(s, t));
    }

    // 位运算 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public char findTheDifferenceBit(String s, String t) {
        int bitmask = 0;
        String tmp = s + t;
        for (char c : tmp.toCharArray()) {
            bitmask ^= c;
        }

        return (char) bitmask;
    }

    // 计数 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public char findTheDifferenceCount(String s, String t) {
        int a = 0, b = 0;

        for (char c : s.toCharArray()) a += c;
        for (char c : t.toCharArray()) b += c;

        return (char) (b - a);
    }

    // 哈希 + 二分 - 时间复杂度 O(N) 其中存入哈希表O(N) 二分查找O(logN) - 空间复杂度 O(N) 为哈希表所占空间
    public char findTheDifferenceHashAndBs(String s, String t) {
        Map<Character, Integer> count = new HashMap<>();

        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        char c = binarySearch(t, count, 0, t.length() - 1);

        return c;
    }

    private char binarySearch(String t, Map<Character, Integer> count, int left, int right) {
        int n = t.length();

        if (left > right) {
            return '#';
        }

        int mid = left + (right - left) / 2;

        char c = t.charAt(mid);
        if (count.containsKey(c) && count.get(c) > 0) {
            count.put(c, count.get(c) - 1);
        } else {
            return c;
        }

        char c1 = binarySearch(t, count, left, mid - 1);
        char c2 = binarySearch(t, count, mid + 1, right);

        if (c1 != '#' || c2 != '#') {
            return c1 != '#' ? c1 : c2;
        }

        return '#';
    }

}
