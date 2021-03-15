package algorithm.leetcode.dpAndGreedy.跳跃游戏;

import java.util.Arrays;

public class Lc_1306_跳跃游戏III {

    // 跳跃游戏 III
    // https://leetcode-cn.com/problems/jump-game-iii/

    public static void main(String[] args) {
        Lc_1306_跳跃游戏III lc = new Lc_1306_跳跃游戏III();
        int[] input1 = {4, 2, 3, 0, 3, 1, 2};
        int[] input2 = {3, 0, 2, 1, 2};
        int start1 = 5, start2 = 2;

        System.out.println("数组" + Arrays.toString(input1) + "在跳跃游戏III中得到的结果是 " + lc.canReach(input1, start1));
        System.out.println("数组" + Arrays.toString(input2) + "在跳跃游戏III中得到的结果是 " + lc.canReach(input2, start2));
    }

    boolean flag;
    boolean[] visited;

    public boolean canReach(int[] arr, int start) {
        int n = arr.length;

        this.flag = false;
        this.visited = new boolean[n];

        dfs(arr, start);

        return flag;
    }

    private void dfs(int[] arr, int start) {
        if (arr[start] == 0) flag = true;

        if (flag) return;

        if (visited[start]) return;

        visited[start] = true;

        if (start - arr[start] >= 0) {
            dfs(arr, start - arr[start]);
        }

        if (start + arr[start] < arr.length) {
            dfs(arr, start + arr[start]);
        }
    }

}
