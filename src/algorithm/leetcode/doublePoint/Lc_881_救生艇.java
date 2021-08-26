package algorithm.leetcode.doublePoint;

import java.util.Arrays;

/**
 * 救生艇
 * <P>https://leetcode-cn.com/problems/boats-to-save-people/</P>
 *
 * @author echofzoe
 * @since 2021.08.26
 */
public class Lc_881_救生艇 {

    public static void main(String[] args) {
        Lc_881_救生艇 lc = new Lc_881_救生艇();

        int[] people = {3, 5, 3, 4};
        int limit = 5;

        System.out.println("第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。\n" +
                "每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。\n" +
                "返回载到每一个人所需的最小船数。(保证每个人都能被船载)。\n");
        System.out.println("输入：people = " + Arrays.toString(people) + ", limit = " + limit);
        System.out.println("输出：" + lc.numRescueBoats(people, limit));  // 4
    }

    // 双指针 - 时间复杂度 O(NlogN) 为排序时间复杂度 - 空间复杂度 O(logN) 为排序所需额外空间
    public int numRescueBoats(int[] people, int limit) {
        int n = people.length;

        Arrays.sort(people);

        int l = 0, r = n - 1, res = 0;
        while (l < r) {
            if (people[l] + people[r] <= limit) {
                l++;
                r--;
            } else {
                r--;
            }

            res++;
        }

        return res + (l == r ? 1 : 0);
    }

}
