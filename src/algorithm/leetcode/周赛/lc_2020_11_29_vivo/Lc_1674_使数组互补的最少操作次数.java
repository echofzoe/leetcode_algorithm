package algorithm.leetcode.周赛.lc_2020_11_29_vivo;

import java.util.Arrays;

public class Lc_1674_使数组互补的最少操作次数 {

    // 使数组互补的最少操作次数
    // https://leetcode-cn.com/problems/minimum-moves-to-make-array-complementary/

    public static void main(String[] args) {
        Lc_1674_使数组互补的最少操作次数 lc = new Lc_1674_使数组互补的最少操作次数();
        int[] input = {1, 2, 4, 3};
        int limit = 4;

        System.out.println("使数组" + Arrays.toString(input) + "在限定条件limit=" + limit + "下成为互补数组的最少操作次数为" + lc.minMoves(input, limit));
    }

    // 差分数组 - 时间复杂度 O(N) - 空间复杂度 O()

    // - res[x] 表示的是，nums[i] + nums[n - 1 - i] 为 x 的时候，需要多少次操作;
    // - 只需要计算出所有的 x 对应的 res[x], 取最小值;
    // - 根据题意，nums[i] + nums[n - 1 - i] 最小是 2，即将两个数都修改为 1；最大是 2 * limit，即将两个数都修改成 limit;
    // - 所以，res[x] 中 x 的取值范围是 [2, 2 * limit].

    // - 假设 nums[i] 为 A；nums[n - 1 - i] 为 B:
    // -- 如果修改后两个数字的和是 A + B，我们使用的操作数是 0 (没有修改);
    // -- 否则的话，如果修改后两个数字和在 [1 + min(A, B), limit + max(A, B)] 的范围，我们使用的操作数是 1 (只需要修改 A 或者 B 就好);
    // -- 否则的话，如果修改后两个数字和在 [2, 2 * limit] 的范围，我们使用的操作数是 2 (两个数字都要修改)).

    // - 遍历每一组 nums[i] 和 nums[n - 1 - i]，然后:
    // -- 先将 [2, 2 * limit] 的范围需要的操作数 +2;
    // -- 之后，将 [1 + min(A, B), limit + max(A, B)] 的范围需要的操作数 -1（即 2 - 1 = 1，操作 1 次）
    // -- 之后，将 [A + B] 位置的值再 -1（即 1 - 1 = 0，操作 0 次）.
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] diff = new int[2 + 2 * limit];

        for (int i = 0; i < n / 2; i++) {
            int a = nums[i], b = nums[n - 1 - i];

            // [2, 2 * limit] 范围 +2
            int left = 2, right = 2 * limit;
            diff[left] += 2;
            diff[right + 1] -= 2;

            // [1 + min(A, B), limit + max(A, B)] 范围 -1
            left = 1 + Math.min(a, b);
            right = limit + Math.max(a, b);
            diff[left] += -1;
            diff[right + 1] -= -1;

            // [A + B] 位置 -1
            left = right = a + b;
            diff[left] += -1;
            diff[right + 1] -= -1;
        }

        // 依次求和，得到最终互补的数字和 i 的时候，需要的操作数 sum，取最小值
        int res = n, sum = 0;
        for (int i = 2; i <= 2 * limit; i++) {
            sum += diff[i];
            res = Math.min(res, sum);
        }

        return res;
    }

}
