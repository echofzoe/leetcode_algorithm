package algorithm.leetcode.周赛.lc_2020_11_22_商汤;

public class Lc_5606_具有给定数值的最小字符串 {

    // 具有给定数值的最小字符串
    // https://leetcode-cn.com/problems/smallest-string-with-a-given-numeric-value/

    public static void main(String[] args) {
        Lc_5606_具有给定数值的最小字符串 lc = new Lc_5606_具有给定数值的最小字符串();
        int n = 5, k = 73;

        System.out.println(n + "个字符组成的数值为" + k + "的具有最小字典序的字符串是" + lc.getSmallestString(n, k));
    }

    public String getSmallestString(int n, int k) {
        char[] res = new char[n];

        while (n > 0) {
            int tmp = k - (n - 1);
            if (tmp < 26) {
                res[--n] = (char) ('a' + (tmp - 1));
                k -= tmp;
            } else {
                res[--n] = 'z';
                k -= ('z' - (97 - 1));
            }
        }

        return new String(res);
    }
}
