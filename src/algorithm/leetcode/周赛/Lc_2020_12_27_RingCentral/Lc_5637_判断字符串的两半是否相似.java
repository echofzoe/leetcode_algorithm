package algorithm.leetcode.周赛.Lc_2020_12_27_RingCentral;

import java.util.HashSet;
import java.util.Set;

public class Lc_5637_判断字符串的两半是否相似 {

    // 判断字符串的两半是否相似
    // https://leetcode-cn.com/problems/determine-if-string-halves-are-alike/

    public static void main(String[] args) {
        Lc_5637_判断字符串的两半是否相似 lc = new Lc_5637_判断字符串的两半是否相似();
        String input1 = "AbCdEfGh";    // true
        String input2 = "MerryChristmas";    // false
        
        System.out.println("将偶数长度的字符串\"" + input1 + "\"拆分成长度相同的两半，他们" + (lc.halvesAreAlike(input1) ? "含有" : "不含有") + "相同数目的元音");
        System.out.println("将偶数长度的字符串\"" + input2 + "\"拆分成长度相同的两半，他们" + (lc.halvesAreAlike(input2) ? "含有" : "不含有") + "相同数目的元音");
    }

    public boolean halvesAreAlike(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');

        int count1 = 0, count2 = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (i < n / 2 && set.contains(c)) count1++;
            else if (i >= n / 2 && set.contains(c)) count2++;
        }

        return count1 == count2;
    }

}
