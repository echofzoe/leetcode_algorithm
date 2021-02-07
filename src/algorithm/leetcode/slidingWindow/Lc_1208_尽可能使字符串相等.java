package algorithm.leetcode.slidingWindow;

public class Lc_1208_尽可能使字符串相等 {

    // 尽可能使字符串相等
    // https://leetcode-cn.com/problems/get-equal-substrings-within-budget/

    public static void main(String[] args) {
        Lc_1208_尽可能使字符串相等 lc = new Lc_1208_尽可能使字符串相等();
        String s = "abcd", t = "bcdf";
        int cost = 3;

        System.out.println("\"" + s + "\"和" + "\"" + t + "\"" + "在最大开销" + cost + "内能够完成转化的最长子串长度为" + lc.equalSubstring(s, t, cost));
    }

    // 滑动窗口（双指针） - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();

        int[] diff = new int[n];
        for (int i = 0; i < n; i++) diff[i] = Math.abs(s.charAt(i) - t.charAt(i));

        int left = 0, right = 0, cur = 0, res = 0;
        while (right < n) {
            cur += diff[right];

            while (cur > maxCost) {
                cur -= diff[left];
                left++;
            }

            res = Math.max(res, right - left + 1);
            right++;
        }

        return res;
    }

}
