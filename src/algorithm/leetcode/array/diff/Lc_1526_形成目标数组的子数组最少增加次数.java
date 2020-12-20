package algorithm.leetcode.array.diff;

import java.util.Arrays;

public class Lc_1526_形成目标数组的子数组最少增加次数 {

    // 形成目标数组的子数组最少增加次数
    // https://leetcode-cn.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/

    public static void main(String[] args) {
        Lc_1526_形成目标数组的子数组最少增加次数 lc = new Lc_1526_形成目标数组的子数组最少增加次数();
        // initial数组为与target数组等长的元素全为0的数组
        int[] target1 = {1, 2, 3, 2, 1};
        int[] target2 = {3, 1, 5, 4, 2};

        System.out.println("由initial数组得到" + Arrays.toString(target1) + "所需的最小操作次数为" + lc.minNumberOperations(target1));
        System.out.println("由initial数组得到" + Arrays.toString(target2) + "所需的最小操作次数为" + lc.minNumberOperations(target2));
    }

    public int minNumberOperations(int[] target) {
        int n = target.length, res = target[0];

        for (int i = 1; i < n; i++) {
            res += Math.max(target[i] - target[i - 1], 0);
        }

        return res;
    }

}
