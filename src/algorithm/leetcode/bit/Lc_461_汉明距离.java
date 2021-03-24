package algorithm.leetcode.bit;

public class Lc_461_汉明距离 {

    // 汉明距离
    // https://leetcode-cn.com/problems/hamming-distance/

    public static void main(String[] args) {
        Lc_461_汉明距离 lc = new Lc_461_汉明距离();
        int x = 1, y = 2;

        System.out.println(x + "与" + y + "之间的汉明距离是" + lc.hammingDistance(x, y)); // 2
    }

    // 位运算 - 时间复杂度 O(1) Integer最多产生32次迭代 - 空间复杂度 O(1)
    public int hammingDistance(int x, int y) {
        int res = 0;

        int z = x ^ y;
        while (z != 0) {
            res++;
            z &= (z - 1);
        }

        return res;
    }

}
