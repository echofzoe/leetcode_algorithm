package algorithm.leetcode.周赛.lc_2021_4_25_发发奇;

/**
 * K 进制表示下的各位数字总和
 * <P>https://leetcode-cn.com/problems/sum-of-digits-in-base-k/</P>
 *
 * @author echofzoe
 * @since 2021.4.25
 */
public class Lc_5738_K进制表示下的各位数字总和 {

    public static void main(String[] args) {
        Lc_5738_K进制表示下的各位数字总和 lc = new Lc_5738_K进制表示下的各位数字总和();

        int n = 34, k = 6;  // 5 + 4 = 9
        
        System.out.println("十进制的" + n + "转换成" + k + "进制的x，则x各个数位上的数字总和是" + lc.sumBase(n, k));
    }

    // 模拟 - 时间复杂度 O(logN) 循环的次数与n在k进制下的位数相同 - 空间复杂度 O(1)
    public int sumBase(int n, int k) {
        int res = 0;
        
        while (n > 0) {
            res += n % k;
            n /= k;
        }
        
        return res;
    }

}
