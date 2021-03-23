package algorithm.leetcode.doublePoint;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Lc_345_反转字符串中的元音字母 {

    // 反转字符串中的元音字母
    // https://leetcode-cn.com/problems/reverse-vowels-of-a-string/description/

    public static void main(String[] args) {
        Lc_345_反转字符串中的元音字母 lc = new Lc_345_反转字符串中的元音字母();
        String s = "leetcode";

        System.out.println("反转字符串\"" + s + "\"中的所有元音字母所得到的结果是" + lc.reverseVowels(s));
    }

    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        int lo = 0, hi = s.length() - 1;
        char[] cs = s.toCharArray();

        while (lo < hi) {
            char c1 = cs[lo], c2 = cs[hi];

            if (set.contains(c1) && set.contains(c2)) {
                cs[lo++] = c2;
                cs[hi--] = c1;
            } else if (!set.contains(c1)) {
                lo++;
            } else if (!set.contains(c2)) {
                hi--;
            }
        }

        return new String(cs);
    }

}
