package algorithm.leetcode;

import java.util.Arrays;

public class Lc_189 {
    public static void main(String[] args) {
        Lc_189 lt = new Lc_189();

        int[] test1 = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;

        int[] test2 = new int[]{-1, -100, 3, 99};
        int k2 = 5;

        lt.rotate1(test1, k1);
        System.out.println(Arrays.toString(test1));

        lt.rotate3(test2, k2);
        System.out.println(Arrays.toString(test2));

    }

    // 1.暴力 - 时间复杂度 O(n*k) - 空间复杂度 O(1)
    public void rotate1(int[] nums, int k) {
        if (k == 0) return;
        int len = nums.length;

        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[len - 1];
            for (int j = 0; j < len; j++) {
                // 将每一个元素换成它的前一个元素，所以要使用 temp 临时保存前一个元素
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }

    // 2.使用额外的数组 - 时间复杂度 O(n) - 空间复杂度 O(n)
    public void rotate2(int[] nums, int k) {
        if (k == 0) return;
        int len = nums.length;

        int[] extra = new int[nums.length];
        for (int i = 0; i < len; i++) {
            extra[(i + k) % len] = nums[i];
        }
        for (int i = 0; i < len; i++) {
            nums[i] = extra[i];
        }
    }

    // 3.反转数组
    public void rotate3(int[] nums, int k) {
        if (k == 0) return;
        int len = nums.length;

        // 旋转数组 k 次，则 k % n 个尾部元素会被移动到头部，剩下的元素将会后移
        k %= len;
        reverse(nums, 0, len - 1);   // 先将数组整个反转，这样 k % n 个尾部元素会被移动到头部，但其顺序是颠倒的；另一部分元素亦然
        reverse(nums, 0, k - 1);   // 再将颠倒的头部元素反转
        reverse(nums, k, len - 1);
    }

    // 解法3辅助函数
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
