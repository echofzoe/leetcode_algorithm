package algorithm.leetcode.dfsAndBfs;

import java.util.HashSet;
import java.util.Set;

/**
 * 优美的排列
 * <P>https://leetcode-cn.com/problems/beautiful-arrangement/</P>
 *
 * @author echofzoe
 * @since 2021.8.16
 */
public class Lc_526_优美的排列 {

    public static void main(String[] args) {
        Lc_526_优美的排列 lc = new Lc_526_优美的排列();

        int n = 15;

        System.out.println("假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：\n" +
                "  - 第 i 位的数字能被 i 整除\n" +
                "  - i 能被第 i 位上的数字整除\n" +
                "现在给定一个整数 N，请问可以构造多少个优美的排列？\n");
        System.out.println("输入：n = " + n);
        System.out.println("输出：" + lc.countArrangement1(n));  // 24679
    }

    // dfs - 时间复杂度 O(N!) - 空间复杂度 O(N)
    private int n;
    private Set<Integer> set;

    public int countArrangement(int n) {
        this.n = n;
        set = new HashSet<>();

        return dfs(1);
    }

    private int dfs(int pos) {
        // exit
        if (pos > n) return 1;

        // dfs
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            // pruning
            if (set.contains(i)) continue;

            if (pos % i == 0 || i % pos == 0) {
                set.add(i);
                ans += dfs(pos + 1);

                // backtrace
                set.remove(i);
            }
        }

        return ans;
    }

    // 状态压缩 dfs - 时间复杂度 O(N!) - 空间复杂度 O(N)
    private int[] mem;

    public int countArrangement1(int n) {
        this.n = n;
        mem = new int[(int) Math.pow(2, 16)];

        return dfs1(1, 0);
    }

    private int dfs1(int pos, int mask) {
        // exit
        if (pos > n) return 1;

        if (mem[mask] > 0) return mem[mask];

        // dfs
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (((mask >> i) & 1) == 0 && (pos % i == 0 || i % pos == 0)) {
                ans += dfs1(pos + 1, mask + (1 << i));
            }
        }

        // memorize & return
        return mem[mask] = ans;
    }

}
