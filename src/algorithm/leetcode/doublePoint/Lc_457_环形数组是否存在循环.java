package algorithm.leetcode.doublePoint;

import java.util.Arrays;

/**
 * 环形数组是否存在循环
 * <P>https://leetcode-cn.com/problems/circular-array-loop/</P>
 *
 * @author echofzoe
 * @since 2021.8.7
 */
public class Lc_457_环形数组是否存在循环 {

    public static void main(String[] args) {
        Lc_457_环形数组是否存在循环 lc = new Lc_457_环形数组是否存在循环();

        int[] nums = {-1, 2};

        System.out.println("存在一个不含 0 的 环形 数组 nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：\n" +
                "  - 如果 nums[i] 是正数，向前 移动 nums[i] 步\n" +
                "  - 如果 nums[i] 是负数，向后 移动 nums[i] 步\n" +
                "因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。\n" +
                "数组中的 循环 由长度为 k 的下标序列 seq ：\n" +
                "  - 遵循上述移动规则将导致重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...\n" +
                "  - 所有 nums[seq[j]] 应当不是 全正 就是 全负\n" +
                "  - k > 1\n" +
                "如果 nums 中存在循环，返回 true ；否则，返回 false 。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.circularArrayLoop(nums));  // true
    }

    // 双指针 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        boolean[] vis = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (vis[i]) continue;

            int slow = i, fast = next(nums, i);

            while (nums[slow] * nums[fast] > 0 && nums[fast] * nums[next(nums, fast)] > 0) {
                if (slow == fast) {
                    if (slow != next(nums, slow)) return true;
                    else break;
                }

                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }

            // 记忆化
            int cur = i;
            while (!vis[cur] && nums[cur] * nums[next(nums, cur)] > 0) {
                int tmp = cur;
                cur = next(nums, cur);
                vis[tmp] = true;
            }
        }

        return false;
    }

    private int next(int[] nums, int cur) {
        int n = nums.length;
        return ((cur + nums[cur]) % n + n) % n;
    }

}
