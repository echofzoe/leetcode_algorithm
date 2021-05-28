package algorithm.剑指offer.dp和贪心;

/**
 * 把数字翻译成字符串
 * <P>https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/</P>
 *
 * @author echofzoe
 * @since 2021.5.28
 */
public class Jzo_46_把数字翻译成字符串 {

    public static void main(String[] args) {
        Jzo_46_把数字翻译成字符串 jz = new Jzo_46_把数字翻译成字符串();

        int num = 12258;

        System.out.println("给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。");
        System.out.println("输入：" + num);
        System.out.println("输出：" + jz.translateNum(num));
    }

    // DP - 时间复杂度 O() - 空间复杂度 O()
    /*
        dp[i] 表示以 num 的第 i 位为结尾的数字的翻译方案数，xi 表示数字 num 的第 i 位
        状态转移方程: {
            dp[i] = dp[i - 2] + dp[i - 1], xi-
            dp[i] = dp[i - 1],
        }
     */
    public int translateNum(int num) {
        char[] cs = String.valueOf(num).toCharArray();
        int n = cs.length;

        int[] dp = new int[n + 1];
        // base case
        dp[0] = dp[1] = 1;



        return dp[n];
    }

}
