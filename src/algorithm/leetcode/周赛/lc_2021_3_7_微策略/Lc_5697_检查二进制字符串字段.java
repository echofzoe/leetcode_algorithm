package algorithm.leetcode.周赛.lc_2021_3_7_微策略;

public class Lc_5697_检查二进制字符串字段 {

    // 检查二进制字符串字段
    // https://leetcode-cn.com/problems/check-if-binary-string-has-at-most-one-segment-of-ones/

    public static void main(String[] args) {
        Lc_5697_检查二进制字符串字段 lc = new Lc_5697_检查二进制字符串字段();
        String s1 = "1001";
        String s2 = "1001";

        System.out.println("\"" + s1 + "\"" + (lc.checkOnesSegment(s1) ? "满足" : "不满足") + "最多只包含一个由连续的'1'组成的字段");
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean checkOnesSegment(String s) {
        int i = 0, j = s.length() - 1;
        while (i <= j && s.charAt(i) == '0') i++;
        while (i <= j && s.charAt(j) == '0') j--;

        for (int k = i; k < j; k++) {
            if (s.charAt(k) == '0') return false;
        }

        return true;
    }

}
