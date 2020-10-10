package algorithm.leetcode.recursion;

import java.util.HashMap;

public class Lc_292_NimGame {

    // Nim Game
    // https://leetcode-cn.com/problems/nim-game/

    public static void main(String[] args) {
        Lc_292_NimGame lc = new Lc_292_NimGame();
        int n = 100;

        System.out.print("如果堆中有 " + n + " 块石头，那么你永远");
        if (lc.canWinNim_3(n)) {
            System.out.print("会");
        } else {
            System.out.print("不会");
        }
        System.out.println("赢得比赛");
    }

    // brutal force - 时间复杂度太高
    private boolean canWinNim_1(int n) {
        if (n < 4) return true;
        return !canWinNim_1(n - 1) || !canWinNim_1(n - 2) || !canWinNim_1(n - 3);
    }

    // brutal force with memorandum
    private HashMap<Integer, Boolean> map = new HashMap<>();
    private boolean canWinNim_2(int n) {
        if (n < 4) return true;

        if (map.containsKey(n)) return map.get(n);
        map.put(1, true);
        map.put(2, true);
        map.put(3, true);

        boolean a = map.containsKey(n - 1) ? !map.get(n - 1) : !canWinNim_2(n - 1);
        boolean b = map.containsKey(n - 2) ? !map.get(n - 2) : !canWinNim_2(n - 2);
        boolean c = map.containsKey(n - 3) ? !map.get(n - 3) : !canWinNim_2(n - 3);

        map.put(n, a || b || c);
        return map.get(n);
    }

    // dp with O(n) space
    private boolean canWinNim_3(int n) {
        if (n < 4) return true;

        boolean[] canWin = new boolean[n + 1];    // 让出 index0
        canWin[1] = canWin[2] = canWin[3] = true;
        for (int i = 4; i <= n; i++) {
            canWin[i] = !canWin[i - 1] || !canWin[i - 2] || !canWin[i - 3];
        }
        return canWin[n];
    }

    // dp with O(1) space
    private boolean canWinNim_4(int n) {
        if (n < 4) return true;

        boolean canWin = false;
        boolean a = true;
        boolean b = true;
        boolean c = true;

        for (int i = 4; i <= n; i++) {
            canWin = !a || !b || !c;
            a = b;
            b = c;
            c = canWin;
        }
        return canWin;
    }

    // dp from end to start
    private boolean check(boolean[] array, int i) {
        if (i >= array.length) return false;
        return array[i];
    }

    private boolean canWinNim(int n) {
        if (n < 4) return true;

        boolean[] array = new boolean[n + 1];    // 让出 index0
        array[n] = true;    // 从后往前推，最后一个是自己拿的时候，必赢
        for (int i = n - 1; i > 0; i--) {
            if (!check(array, i + 1) || !check(array, i + 2) || !check(array, i + 3)) {
                array[i] = true;
            }
        }
        return array[1];
    }

}
