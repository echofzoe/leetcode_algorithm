package algorithm.剑指offer.math;

public class Jzo_14_I_剪绳子 {

    // 剪绳子
    // https://leetcode-cn.com/problems/jian-sheng-zi-lcof/

    public static void main(String[] args) {
        Jzo_14_I_剪绳子 lc = new Jzo_14_I_剪绳子();
        int n = 10;

        System.out.println("按题意剪长度为" + n + "的绳子能得到的最大乘积是 " + lc.cuttingRope(n));
    }

    // 模拟 - 时间复杂度 O(1) - 空间复杂度 O(1)
    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;

        int a = n / 3, b = n % 3;
        if (b == 0) return (int) Math.pow(3, a);
        if (b == 1) return (int) (Math.pow(3, a - 1) * 4);
        return (int) (Math.pow(3, a) * 2);
    }

}
