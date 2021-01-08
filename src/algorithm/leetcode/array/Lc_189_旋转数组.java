package algorithm.leetcode.array;

import java.util.Arrays;

public class Lc_189_旋转数组 {

    // 旋转数组
    // https://leetcode-cn.com/problems/rotate-array/

    public static void main(String[] args) {
        Lc_189_旋转数组 lt = new Lc_189_旋转数组();

        int[] test1 = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;

        int[] test2 = new int[]{-1, -100, 3, 99};
        int k2 = 6;

        lt.rotate1(test1, k1);
        System.out.println(Arrays.toString(test1));

        lt.rotate3(test2, k2);
        System.out.println(Arrays.toString(test2));

    }

    // 1.暴力 - 时间复杂度 O(n*k) - 空间复杂度 O(1)
    public void rotate1(int[] nums, int k) {
        if (k == 0) return;
        int n = nums.length;

        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[n - 1];
            for (int j = 0; j < n; j++) {
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
        int n = nums.length;

        int[] extra = new int[nums.length];
        for (int i = 0; i < n; i++) {
            extra[(i + k) % n] = nums[i];
        }
        System.arraycopy(extra, 0, nums, 0, n);
    }

    // 3.反转数组
    public void rotate3(int[] nums, int k) {
        if (k == 0) return;
        int n = nums.length;

        // 旋转数组 k 次，则 k % n 个尾部元素会被移动到头部，剩下的元素将会后移
        k %= n;
        reverse(nums, 0, n);   // 先将数组整个反转，这样 k % n 个尾部元素会被移动到头部，但其顺序是颠倒的；另一部分元素亦然
        reverse(nums, 0, k);   // 再将颠倒的头部元素反转
        reverse(nums, k, n);
    }

    // 解法3辅助函数
    private void reverse(int[] nums, int start, int end) {
        while (start < end - 1) {
            int temp = nums[start];
            nums[start] = nums[end - 1];
            nums[end - 1] = temp;

            start++;
            end--;
        }
    }
}
