package algorithm.leetcode.backtrack;

public class Lc_357_计算各个位数不同的数字个数 {

    // 计算各个位数不同的数字个数
    // https://leetcode-cn.com/problems/count-numbers-with-unique-digits/

    public static void main(String[] args) {
        Lc_357_计算各个位数不同的数字个数 lc = new Lc_357_计算各个位数不同的数字个数();
        int n = 2;

        System.out.println("所有的" + n + "位数中，各个位数不同的数字个数是 " + lc.countNumbersWithUniqueDigitsDfs(n));
    }

    // DFS - 时间复杂度 O(10^N) - 空间复杂度 O(logN) 为系统递归栈开销
    public int countNumbersWithUniqueDigitsDfs(int n) {
        if (n == 0) return 1;
        return dfs(Math.min(n, 10), 1, new boolean[10]);
    }

    private int dfs(int n, int idx, boolean[] visited) {
        int res = 0;

        if (idx <= n) {
            // 回溯0-9
            for (int i = 0; i < 10; i++) {
                // 剪枝
                if (i == 0 && idx == 2) continue;
                if (visited[i]) continue;

                // dfs
                visited[i] = true;
                res += dfs(n, idx + 1, visited) + 1;

                // 状态还原
                visited[i] = false;
            }
        }

        return res;
    }

    // DP - 时间复杂度 O(1) 最多遍历10次 - 空间复杂度 O(1)
    public int countNumbersWithUniqueDigitsDp(int n) {
        if (n == 0) return 1;
        int size = Math.min(10, n);

        // n 位数字排列组合：
        // - 第1位数字可取0~9共10种
        // - 第2位数字可取C(10,2)，且第1位不能为0，共9*9种
        // - 第3位数字可取C(10,3)，且第1位不能为0，共9*9*8种
        // - 第4位数字可取C(10,4)，且第1位不能为0，共9*9*8*7种
        // - ......
        // - 最后，总数 = 所有小于n的位数个数相加
        int a = 10, b = 9 * 9;
        for (int i = 2; i <= size; i++) {
            a += b;
            b *= 10 - i;
        }

        return a;
    }

}
