package algorithm.leetcode.string;

public class Lc_28_实现strStr {

    // 实现 strStr()
    // https://leetcode-cn.com/problems/implement-strstr/

    public static void main(String[] args) {
        Lc_28_实现strStr lc = new Lc_28_实现strStr();
        String haystack = "hello", needle = "ll";

        System.out.println("在字符串\"" + haystack + "\"中, \"" + needle + "\"出现的第一个位置是" + lc.strStr(haystack, needle));
    }

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;

        int index = 0, i = 0;
        while (i < haystack.length()) {
            if (index < needle.length() && haystack.charAt(i) == needle.charAt(index)) {
                index++;
                i++;
            } else if (index == needle.length()) {
                break;
            } else {
                i = i - index + 1;
                index = 0;
            }
        }

        if (index == needle.length()) return i - index;

        return -1;
    }

}
