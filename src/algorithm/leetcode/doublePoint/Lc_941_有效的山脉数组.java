package algorithm.leetcode.doublePoint;

import java.util.Arrays;

public class Lc_941_有效的山脉数组 {

    // 有效的山脉数组
    // https://leetcode-cn.com/problems/valid-mountain-array/

    public static void main(String[] args) {
        Lc_941_有效的山脉数组 lc = new Lc_941_有效的山脉数组();
        int[] A = {0, 3, 2, 1};

        System.out.println("数组" + Arrays.toString(A) + (lc.validMountainArrayDoublePoint(A) ? "是" : "不是") + "一个有效的山脉数组");
    }

    // 双指针 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean validMountainArrayDoublePoint(int[] A) {
        if (A.length < 3) return false;

        int len = A.length, left = 0, right = len - 1;
        while (left + 1 < len && A[left] < A[left + 1]) left++;
        while (right - 1 >= 0 && A[right] < A[right - 1]) right--;
        

        return left > 0 && right < len - 1 && left == right;
    }

    // 线性查找 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean validMountainArrayLinear(int[] A) {
        if (A.length < 3) return false;

        int len = A.length, index = 0;
        while (index + 1 < len && A[index] < A[index + 1]) index++;

        if (index == 0 || index == len - 1) return false;
        while (index + 1 < len && A[index] > A[index + 1]) index++;

        return index == len - 1;
    }

}
