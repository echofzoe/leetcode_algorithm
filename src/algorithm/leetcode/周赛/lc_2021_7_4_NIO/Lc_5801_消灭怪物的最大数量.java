package algorithm.leetcode.周赛.lc_2021_7_4_NIO;

import java.util.Arrays;

/**
 * 消灭怪物的最大数量
 * <P>https://leetcode-cn.com/problems/eliminate-maximum-number-of-monsters/</P>
 *
 * @author echofzoe
 * @since 2021/7/4
 */
public class Lc_5801_消灭怪物的最大数量 {

    public static void main(String[] args) {
        Lc_5801_消灭怪物的最大数量 lc = new Lc_5801_消灭怪物的最大数量();

        int[] dist = {4, 2, 3}, speed = {2, 1, 1};

        System.out.println("你正在玩一款电子游戏，在游戏中你需要保护城市免受怪物侵袭。给你一个 下标从 0 开始 且长度为 n 的整数数组 dist ，其中 dist[i] 是第 i 个怪物与城市的 初始距离（单位：米）。\n" +
                "怪物以 恒定 的速度走向城市。给你一个长度为 n 的整数数组 speed 表示每个怪物的速度，其中 speed[i] 是第 i 个怪物的速度（单位：米/分）。\n" +
                "怪物从 第 0 分钟 时开始移动。你有一把武器，并可以 选择 在每一分钟的开始时使用，包括第 0 分钟。但是你无法在一分钟的中间使用武器。这种武器威力惊人，一次可以消灭任一还活着的怪物。\n" +
                "一旦任一怪物到达城市，你就输掉了这场游戏。如果某个怪物 恰 在某一分钟开始时到达城市，这会被视为 输掉 游戏，在你可以使用武器之前，游戏就会结束。\n" +
                "返回在你输掉游戏前可以消灭的怪物的 最大 数量。如果你可以在所有怪物到达城市前将它们全部消灭，返回 n 。\n");
        System.out.println("输入：");
        System.out.println("输出：" + lc.eliminateMaximum(dist, speed));
    }

    // 排序 + 贪心 - 时间复杂度 O(NlogN) - 空间复杂度 O(logN) 为排序的空间开销
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;

        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            end[i] = (int) Math.ceil((double) dist[i] / speed[i]);
        }

        Arrays.sort(end);

        int res = 1, minute = 0;
        for (int i = 1; i < n; i++) {
            minute++;
            if (end[i] - minute <= 0) break;
            res++;
        }

        return res;
    }

}
