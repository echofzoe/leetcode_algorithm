package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 森林中的兔子
 * https://leetcode-cn.com/problems/rabbits-in-forest/
 *
 * @author echofzoe
 * @since 2021.4.5
 */
public class Lc_781_森林中的兔子 {

    public static void main(String[] args) {
        Lc_781_森林中的兔子 lc = new Lc_781_森林中的兔子();

        int[] answers = {10, 10, 10};

        System.out.println("输入: " + Arrays.toString(answers) + ", 输出: " + lc.numRabbits(answers));
    }

    public int numRabbits(int[] answers) {
        if (answers == null || answers.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;

        for (int x : answers) {
            if (!map.containsKey(x) || map.get(x) == 0) {
                res += x + 1;
                map.put(x, x);
            } else {
                map.put(x, map.get(x) - 1);
            }
        }

        return res;
    }

}
