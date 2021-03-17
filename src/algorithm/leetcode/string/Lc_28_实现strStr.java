package algorithm.leetcode.string;

public class Lc_28_实现strStr {

    // 实现 strStr()
    // https://leetcode-cn.com/problems/implement-strstr/

    public static void main(String[] args) {
        Lc_28_实现strStr lc = new Lc_28_实现strStr();
        String haystack = "hello", needle = "ll";

        System.out.println("在字符串\"" + haystack + "\"中, \"" + needle + "\"出现的第一个位置是" + lc.strStrSlidingWindow(haystack, needle));
    }

    // 滑动窗口
    // - 时间复杂度 最坏O((N-L)L) 最优O(N) 其中N为haystack的长度，L为needle的长度。内循环中比较字符串的复杂度为L，最坏情况下总共需要比较(N-L)次
    // - 空间复杂度 O(1)
    public int strStrSlidingWindow(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        int m = haystack.length(), n = needle.length();

        for (int i = 0; i < m - n + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0) && haystack.startsWith(needle, i)) {
                return i;
            }
        }

        return -1;
    }

    // 双指针 - 时间复杂度 最坏O((N-L)L) 最优O(N) - 空间复杂度 O(1)
    public int strStrDoublePoint(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        int m = haystack.length(), n = needle.length();

        int neeIdx = 0, hayIdx = 0;
        while (hayIdx < m) {
            if (neeIdx < n && haystack.charAt(hayIdx) == needle.charAt(neeIdx)) {
                neeIdx++;
                hayIdx++;
            } else if (neeIdx == n) {
                break;
            } else {
                // backtrack
                hayIdx = hayIdx - neeIdx + 1;
                neeIdx = 0;
            }
        }

        if (neeIdx == n) return hayIdx - neeIdx;
        return -1;
    }

}
