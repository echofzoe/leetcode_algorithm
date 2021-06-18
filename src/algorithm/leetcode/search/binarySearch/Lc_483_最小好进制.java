package algorithm.leetcode.search.binarySearch;

/**
 * 最小好进制
 * <P>https://leetcode-cn.com/problems/smallest-good-base/</P>
 *
 * @author echofzoe
 * @since 2021.6.18
 */
public class Lc_483_最小好进制 {

    public static void main(String[] args) {
        Lc_483_最小好进制 lc = new Lc_483_最小好进制();

        String n = "13";

        System.out.println("对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。\n" +
                "以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。\n" +
                "  - n的取值范围是 [3, 10^18]。\n" +
                "  - 输入总是有效且没有前导 0。\n");
        System.out.println("输入：n = " + "\"" + n + "\"");
        System.out.println("输出：" + lc.smallestGoodBase(n));
    }

    public String smallestGoodBase(String n) {
        int num = Integer.parseInt(n), res = Integer.MAX_VALUE;

        // 10^18在二进制下最多有64个1，而进制最大不会超过n本身，故可以枚举1的个数
        for (int i = 1; i < 64; i++) {
            int l = 2, m, r = num;

            while (l < r) {
                m = l + (r - l) / 2;

                int t = check(m, i);
                if (t == num) {
                    res = Math.min(res, m);
                } else if (t < num) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }

        return String.valueOf(res);
    }

    private int check(int x, int m) {
        int res = 0;
        for (int i = 0; i <= m; i++) {
            res = res * x + 1;
        }
        return res;
    }

}
