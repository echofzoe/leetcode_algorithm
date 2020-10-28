package algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jzo_21_调整数组顺序使奇数位于偶数前面 {

    // 调整数组顺序使奇数位于偶数前面
    // https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/

    public static void main(String[] args) {
        Jzo_21_调整数组顺序使奇数位于偶数前面 lc = new Jzo_21_调整数组顺序使奇数位于偶数前面();
        int[] input = {2, 16, 3, 5, 13, 1, 16, 1, 12, 18, 11, 8, 11, 11, 5, 1};

        System.out.println("调整数组" + Arrays.toString(input) + "中数字的顺序,使得所有奇数位于数组的前半部分,所有偶数位于数组的后半部分所得到的的结果是 " + Arrays.toString(lc.exchange_DoublePoint(input)));
    }

    // 列表插入 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int[] exchange_ListInsert(int[] nums) {
        if (nums.length == 0) return new int[0];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            if ((temp & 1) == 1) {
                list.add(0, temp);
            } else {
                list.add(temp);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // 双指针 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int[] exchange_DoublePoint(int[] nums) {
        if (nums.length == 0) return new int[0];

        int left = 0, right = nums.length - 1;
        while (left < right) {
            if ((nums[left] & 1) == 0 && (nums[right] & 1) == 1) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            } else {
                if (nums[left] % 2 != 0) left++;
                if (nums[right] % 2 == 0) right--;
            }
        }

        return nums;
    }
}
