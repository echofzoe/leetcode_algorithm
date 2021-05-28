package algorithm.剑指offer.math;

/**
 * 数字序列中某一位的数字
 * <P>https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/</P>
 *
 * @author echofzoe
 * @since 2021.5.27
 */
public class Jzo_44_数字序列中某一位的数字 {

    public static void main(String[] args) {
        Jzo_44_数字序列中某一位的数字 lc = new Jzo_44_数字序列中某一位的数字();

        int n = 15;

        System.out.println("数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。\n" +
                "请写一个函数，求任意第n位对应的数字。");
        System.out.println("输入：n = " + n);
        System.out.println("输入：" + lc.findNthDigit(n));  // 2
    }

    // 找规律 - 时间复杂度 O(logN) - 空间复杂度 O(logN)
    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1, cnt = 9;

        while (n > cnt) {
            n -= cnt;
            digit++;
            start *= 10;
            cnt = start * digit * 9;
        }

        long num = start + (n - 1) / digit;

        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }

}
