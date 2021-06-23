package algorithm.leetcode.bit;

/**
 * 最小好进制
 * <P>https://leetcode-cn.com/problems/smallest-good-base/</P>
 *
 * @author echofzoe
 * @since 2021.6.22
 */
public class Lc_483_最小好进制 {

    public static void main(String[] args) {
        Lc_483_最小好进制 lc = new Lc_483_最小好进制();

        String n = "14919921443713777";

        System.out.println("对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。\n" +
                "以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。\n");
        System.out.println("输入：n = " + "\"" + n + "\"");
        System.out.println("输出：" + lc.smallestGoodBase(n));  // "496"
    }

    // 二分 - 时间复杂度 O(logN) N为字符串n表示的数值 - 空间复杂度 O(1)
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        int len = (int) (Math.log(num) / Math.log(2) + 1);

        // 枚举全1字符串长度
        for (int i = len; i > 2; i--) {
            long l = 2, m, r = num - 1;

            // 枚举进制
            while (l < r) {
                m = l + (r - l) / 2;

                long t = check(i, m);
                if (t == num) {
                    return String.valueOf(m);
                } else if (t == -1 || t > num) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
        }

        return String.valueOf(num - 1);
    }

    // 计算k进制下全为1且长度为len的字符串表示的数值
    private long check(int len, long k) {
        long sum = 1, cur = 1;
        for (int i = 1; i < len; i++) {
            if (cur > Long.MAX_VALUE / k) return -1;
            cur *= k;
            if (sum > Long.MAX_VALUE - cur) return -1;
            sum += cur;
        }
        return sum;
    }

}
