package algorithm.leetcode.pathfinding;

import java.util.Arrays;

public class Jzo_12_矩阵中的路径 {

    // 矩阵中的路径
    // https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/

    public static void main(String[] args) {
        Jzo_12_矩阵中的路径 lc = new Jzo_12_矩阵中的路径();
        // input
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";

        System.out.println("矩阵" + Arrays.deepToString(board) + "中" + (lc.exist(board, word) ? " 存在 " : " 不存在 ") + "包含字符串\"" + word + "\"的路径");
    }

    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    /**
     * 深度优先搜索
     *
     * @param board - 矩阵
     * @param words - 目标字符串
     * @param i     - 行索引
     * @param j     - 列索引
     * @param k     - 目标字符在 word 中的索引
     * @return
     */
    private boolean dfs(char[][] board, char[] words, int i, int j, int k) {
        // 边界条件
        if (i > board.length - 1 || i < 0 || j > board[0].length - 1 || j < 0 || board[i][j] != words[k]) return false;

        // 结束条件
        if (k == words.length - 1) return true;

        board[i][j] = '/';    // 设置访问位

        // dfs 方向为: 下 上 右 左
        boolean res = dfs(board, words, i + 1, j, k + 1) || dfs(board, words, i - 1, j, k + 1) ||
                dfs(board, words, i, j + 1, k + 1) || dfs(board, words, i, j - 1, k + 1);

        board[i][j] = words[k];    // 还原访问位
        return res;
    }
}
