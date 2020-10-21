package algorithm.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

public class Jzo_17_打印从1到最大的n位数 {

    // 打印从1到最大的n位数
    // https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/

    public static void main(String[] args) {
        Jzo_17_打印从1到最大的n位数 lc = new Jzo_17_打印从1到最大的n位数();
        int n = 2;

        System.out.println("从1到最大的" + n + "位数的数组为: " + Arrays.toString(lc.printNumbers_BigNumberPrint(n)));
    }

    public int[] printNumbers_BruteForce(int n) {
        if (n == 0) return new int[0];

        int end = (int) (Math.pow(10, n) - 1);
        int[] res = new int[end];

        for (int i = 0; i < end; i++) {
            res[i] = i + 1;
        }

        return res;
    }

    int n;
    int start;    // 非0数字起始位置
    int nine = 0;    // 9的个数
    int count = 0;    // 结果集记数位,结果集大小为 [1, 10^n]

    char[] loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};    // 每一位可能的数字
    char[] num;    // 当前数字的字符数组

    int res[];    // 结果集

    public int[] printNumbers_BigNumberPrint(int n) {
        // 特判
        if (n == 0) return new int[0];

        // 初始化
        this.n = n;
        res = new int[(int) Math.pow(10, n) - 1];
        num = new char[n];
        start = n - 1;

        dfs(0);    // 从第0位开始递归至第n位

        return res;
    }

    private void dfs(int x) {
        if (x == n) {
            String s = String.valueOf(num).substring(start);
            if (!s.equals("0")) res[count++] = Integer.parseInt(s);
            if (n - start == nine) start--;
            return;
        }

        for (char c : loop) {
            if (c == '9') nine++;
            num[x] = c;
            dfs(x + 1);
        }

        nine--;
    }
}
