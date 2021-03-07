package interview;

import java.util.Arrays;

public class 阿里巴巴_求非递减的最长拼接字符串长度 {

    // 求非递减的最长拼接字符串长度
    // - 给出一个整数n，代表字符串的数量，以及n个按字母序非递减的字符串。从中选择若干个字符串，求这些字符串拼接起来能得到的非递减的字符串的最大长度。
    // - 实例：
    // -- 输入 int n = 4, String[] input = {"aaa", "bcd", "zzz", "bcdfg"}
    // -- 输出 11
    // - 解释：
    // -- 把第1、3、4个字符串拼接起来，得到最长非递减拼接字符串"aaabcdfgzzz"，长度为11。

    public static void main(String[] args) {
        阿里巴巴_求非递减的最长拼接字符串长度 iv = new 阿里巴巴_求非递减的最长拼接字符串长度();
        String[] input = {"aaa", "bcd", "zzz", "bcdfg"};
        String[] input1 = {"abc", "bc", "bd", "zzz"};
        String[] input2 = {"az", "bbb", "bc"};

        System.out.println("输入" + Arrays.toString(input) + "\n输出：" + iv.longestNonDecreasingSplicingStr(input));
        System.out.println("输入" + Arrays.toString(input1) + "\n输出：" + iv.longestNonDecreasingSplicingStr(input1));
        System.out.println("输入" + Arrays.toString(input2) + "\n输出：" + iv.longestNonDecreasingSplicingStr(input2));
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int longestNonDecreasingSplicingStr(String[] input) {
        int n = input.length;

        Arrays.sort(input, (x, y) -> {
            int n1 = x.length(), n2 = y.length();

            if (x.charAt(n1 - 1) == y.charAt(n2 - 1)) return n2 - n1;
            return x.charAt(n1 - 1) - y.charAt(n2 - 1);
        });

        int[] dp = new int[n];
        // base case
        dp[0] = input[0].length();

        for (int i = 1; i < n; i++) {
            dp[i] = input[i].length();
            String cur = input[i];
            for (int j = 0; j < i; j++) {
                String pre = input[j];

                // 可以拼接
                if (pre.charAt(pre.length() - 1) < cur.charAt(0)) {
                    dp[i] = Math.max(dp[i], dp[j] + cur.length());
                }
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }

}
