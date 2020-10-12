package algorithm.leetcode.array;

import java.util.Arrays;

public class Jzo_11_旋转数组的最小数字 {

    // 旋转数组的最小数字
    // https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/

    public static void main(String[] args) {
        Jzo_11_旋转数组的最小数字 lc = new Jzo_11_旋转数组的最小数字();
        int[] numbers = new int[]{2, 2, 2, 0, 1};

        System.out.println("旋转数组 " + Arrays.toString(numbers) + " 的最小数字为 " + lc.minArray(numbers));
    }

    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] < numbers[right]){
                right = mid;
            } else right--;
        }

        return numbers[left];
    }
}
