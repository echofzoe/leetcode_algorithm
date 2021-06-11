package algorithm.leetcode.backtrack;

import java.util.Arrays;

/**
 * 解数独
 * <P>https://leetcode-cn.com/problems/sudoku-solver/</P>
 *
 * @author echofzoe
 * @since 2021.6.11
 */
public class Lc_37_解数独 {

    public static void main(String[] args) {
        Lc_37_解数独 lc = new Lc_37_解数独();

        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        System.out.println("编写一个程序，通过填充空格来解决数独问题。\n" +
                "数独的解法需 遵循如下规则：\n" +
                "  - 数字 1-9 在每一行只能出现一次。\n" +
                "  - 数字 1-9 在每一列只能出现一次。\n" +
                "  - 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）\n" +
                "数独部分空格内已填入了数字，空白格用 '.' 表示。\n");
        System.out.println("输入：board = " + Arrays.deepToString(board));
        lc.solveSudoku(board);
        System.out.println("输出：" + Arrays.deepToString(lc.board));
    }

    // 回溯 - 时间复杂度 O(1) - 空间复杂度 O(1) 因为9*9的格盘大小固定
    private boolean[][] row = new boolean[9][9];
    private boolean[][] col = new boolean[9][9];
    private boolean[][][] cell = new boolean[3][3][9];

    private char[][] board;

    public void solveSudoku(char[][] board) {
        this.board = board;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';

                    // 记录3*3九宫格内已有数字对其余空格位置的影响（即某行某列已有某个数字，不能再使用这个数字了）
                    row[i][num] = col[j][num] = cell[i / 3][j / 3][num] = true;
                }
            }
        }

        dfs(0, 0);
    }

    private boolean dfs(int x, int y) {
        // exit
        if (y == 9) return dfs(x + 1, 0);
        if (x == 9) return true;


        // dfs
        // - 当前格子已存在数字，横向往下一列遍历
        if (board[x][y] != '.') return dfs(x, y + 1);
        // - 当前格子不存在数字，遍历填入9个候选数字
        for (int i = 0; i < 9; i++) {
            board[x][y] = (char) (i + '1');
            row[x][i] = col[y][i] = cell[x / 3][y / 3][i] = true;

            if (dfs(x, y + 1)) {
                // - 往后递归能得到正确结果，中断当前候选数字的遍历
                break;
            } else {
                // - 回溯（往后递归不能得到正确结果）
                board[x][y] = '.';
                row[x][i] = col[y][i] = cell[x / 3][y / 3][i] = false;
            }
        }

        return board[x][y] != '.';
    }

}
