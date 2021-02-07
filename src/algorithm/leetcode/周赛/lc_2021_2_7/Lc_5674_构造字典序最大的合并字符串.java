package algorithm.leetcode.周赛.lc_2021_2_7;

public class Lc_5674_构造字典序最大的合并字符串 {

    // 构造字典序最大的合并字符串
    // https://leetcode-cn.com/problems/largest-merge-of-two-strings/

    public static void main(String[] args) {
        Lc_5674_构造字典序最大的合并字符串 lc = new Lc_5674_构造字典序最大的合并字符串();
        String word1 = "cabaa", word2 = "bcaaa";

        System.out.println("\"" + word1 + "\"和\"" + word2 + "\"按照最大字典序构造的合并字符串是" + lc.largestMerge(word1, word2));
    }

    // 模拟 - 时间复杂度 O(M + N) - 空间复杂度 O(M + N)
    public String largestMerge(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();

        StringBuilder res = new StringBuilder(n1 + n2);
        int a = 0, b = 0, idx = 0;
        while (a < n1 || b < n2) {
            if (a == n1) res.append(word2.charAt(b++));
            else if (b == n2) res.append(word1.charAt(a++));
            else if (word1.charAt(a) < word2.charAt(b)) res.append(word2.charAt(b++));
            else if (word1.charAt(a) > word2.charAt(b)) res.append(word1.charAt(a++));
            else {
                if (word1.substring(a).compareTo(word2.substring(b)) < 0) res.append(word2.charAt(b++));
                else res.append(word1.charAt(a++));
            }
        }

        return res.toString();
    }

}
