package algorithm.leetcode.周赛.lc_2021_4_11_万得;

import java.util.LinkedList;
import java.util.List;

/**
 * 找出游戏的获胜者
 * https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/
 *
 * @author echofzoe
 * @since 2021.4.11
 */
public class Lc_5727_找出游戏的获胜者 {

    public static void main(String[] args) {
        Lc_5727_找出游戏的获胜者 lc = new Lc_5727_找出游戏的获胜者();

        int n = 6, k = 5;

        System.out.println("容量为" + n + "的约瑟夫环在步长为" + k + "消除元素的情况下最终剩余的元素是" + lc.findTheWinner2(n, k));
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int findTheWinner1(int n, int k) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) list.add(i);

        int idx = 0;
        while (n > 1) {
            idx = (idx + k - 1) % n;
            list.remove(idx);
            n--;
        }

        return list.get(0) + 1;
    }

    // 递归（DP） - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int findTheWinner2(int n, int k) {
        return f(n, k) + 1;
    }

    private int f(int n, int k) {
        if (n == 1) return 0;
        return (f(n - 1, k) + k) % n;
    }

}
