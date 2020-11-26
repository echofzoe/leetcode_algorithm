package algorithm.leetcode.string;

public class Lc_1370_上升下降字符串 {

    // 上升下降字符串
    // https://leetcode-cn.com/problems/increasing-decreasing-string/

    public static void main(String[] args) {
        Lc_1370_上升下降字符串 lc = new Lc_1370_上升下降字符串();
        String s = "aaaabbbbcccc";

        System.out.println("\"" + s + "\"按上升下降重新排序后的结果字符串是\"" + lc.sortString(s) + "\"");
    }

    public String sortString(String s) {
        int[] bucket = new int[26];
        for (int i = 0; i < s.length(); i++) {
            bucket[s.charAt(i) - 97]++;
        }

        StringBuilder res = new StringBuilder();
        while (res.length() < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (bucket[i] > 0) {
                    res.append((char) (i + 97));
                    bucket[i]--;
                }
            }

            for (int i = 25; i >= 0; i--) {
                if (bucket[i] > 0) {
                    res.append((char) (i + 97));
                    bucket[i]--;
                }
            }
        }

        return res.toString();
    }

}
