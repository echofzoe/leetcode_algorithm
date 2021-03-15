package algorithm.leetcode.array;

import java.util.Arrays;

public class Jzo_17_打印从1到最大的n位数 {

    // 打印从1到最大的n位数
    // https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/

    public static void main(String[] args) {
        Jzo_17_打印从1到最大的n位数 lc = new Jzo_17_打印从1到最大的n位数();
        int n = 2;

        System.out.println("从1到最大的" + n + "位数的数组为: " + Arrays.toString(lc.printNumbers_BigNumberPrint(n)));
    }

    // 暴力 无法解决大数问题 - 时间复杂度 O(10^N) - 空间复杂度 O(10^N)
    public int[] printNumbers_BruteForce(int n) {
        if (n == 0) return new int[0];

        int end = (int) (Math.pow(10, n) - 1);
        int[] res = new int[end];

        for (int i = 0; i < end; i++) {
            res[i] = i + 1;
        }

        return res;
    }

    int[] res;    // 结果集
    /**
     * n - 数位
     * start - 非0数字的起始位置
     * nine - 当前字符串中9的个数
     * count - 结果集记数位,结果集大小为 [1, 10^n]
     */
    int n, count, nonZeroBit, nine;
    char[] cur;    // 当前字符串
    char[] loop;    // 取值范围

    // DFS - 时间复杂度 O(10^N) - 空间复杂度 O(10^N)
    public int[] printNumbers_BigNumberPrint(int n) {
        if (n == 0) return new int[0];

        this.res = new int[(int) Math.pow(10, n) - 1];
        this.n = n;
        this.cur = new char[n];
        this.loop = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        this.count = 0;
        this.nonZeroBit = n - 1;
        this.nine = 0;

        dfs(0);

        return res;
    }

    private void dfs(int b) {
        if (b == n) {
            String s = String.valueOf(cur).substring(nonZeroBit);
            if (!s.equals("0")) res[count++] = Integer.parseInt(s);

            if (n - nonZeroBit == nine) nonZeroBit--;

            return;
        }

        for (char c : loop) {
            if (c == '9') nine++;
            cur[b] = c;

            // 往下dfs
            dfs(b + 1);
        }

        nine--;
    }
}
