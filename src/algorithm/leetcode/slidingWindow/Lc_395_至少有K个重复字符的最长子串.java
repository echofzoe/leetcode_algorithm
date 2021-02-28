package algorithm.leetcode.slidingWindow;

public class Lc_395_至少有K个重复字符的最长子串 {

    // 至少有 K 个重复字符的最长子串
    // https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/

    public static void main(String[] args) {
        Lc_395_至少有K个重复字符的最长子串 lc = new Lc_395_至少有K个重复字符的最长子串();
        String s = "ababbc";
        int k = 2;

        System.out.println("字符串\"" + s + "\"中每一字符出现次数都不少于" + k + "的最长字串的长度是" + lc.longestSubstring(s, k));
    }

    // 滑动窗口 - 时间复杂度 O(∣Σ∣ * N * ∣Σ∣ + ∣Σ∣^2) = O(∣Σ∣^2 * N + ∣Σ∣^2) = O(∣Σ∣^2 * (N + 1)) = O(∣Σ∣^2 * N) - 空间复杂度 O(∣Σ∣)
    // 其中 N 为字符串长度，Σ 为字符集
    public int longestSubstring(String s, int k) {
        int n = s.length();

        int res = 0;
        for (int charKind = 1; charKind <= 26; charKind++) {
            int[] cnt = new int[26];

            int lo = 0, hi = 0, curCharKind = 0;
            while (hi < n) {
                boolean flag = true;

                int idx = s.charAt(hi) - 'a';
                cnt[idx]++;
                if (cnt[idx] == 1) {
                    curCharKind++;
                }

                while (curCharKind > charKind) {
                    idx = s.charAt(lo) - 'a';
                    cnt[idx]--;
                    if (cnt[idx] == 0) {
                        curCharKind--;
                    }

                    lo++;
                }

                for (int i = 0; i < 26; i++) {
                    if (cnt[i] != 0 && cnt[i] < k) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    res = Math.max(res, hi - lo + 1);
                }
                
                hi++;
            }

        }

        return res;
    }

    // 滑动窗口 + 优化 - 时间复杂度 O(∣Σ∣ * N + ∣Σ∣^2) - 空间复杂度 O(∣Σ∣)
    public int longestSubstringOpti(String s, int k) {
        int n = s.length();

        int res = 0;
        for (int charKind = 1; charKind <= 26; charKind++) {
            int[] cnt = new int[26];

            // 当某个字符的出现次数从 0 增加到 1 时，将 less 加一
            // 当某个字符的出现次数从 k−1 增加到 k 时，将 less 减一
            int lo = 0, hi = 0, curCharKind = 0, less = 0;
            while (hi < n) {
                int idx = s.charAt(hi) - 'a';
                cnt[idx]++;
                if (cnt[idx] == 1) {
                    curCharKind++;
                    less++;
                }
                if (cnt[s.charAt(hi) - 'a'] == k) {
                    less--;
                }

                while (curCharKind > charKind) {
                    idx = s.charAt(lo) - 'a';
                    cnt[idx]--;
                    if (cnt[idx] == 0) {
                        curCharKind--;
                        less--;
                    }
                    if (cnt[idx] == k - 1) {
                        less++;
                    }

                    lo++;
                }

                if (less == 0) {
                    res = Math.max(res, hi - lo + 1);
                }

                hi++;
            }

        }

        return res;
    }

}
