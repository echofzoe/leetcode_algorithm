package algorithm.leetcode.array;

import java.util.*;
import java.util.stream.Collectors;

public class Lc_977_有序数组的平方 {

    // 有序数组的平方
    // https://leetcode-cn.com/problems/squares-of-a-sorted-array/

    public static void main(String[] args) {
        Lc_977_有序数组的平方 lc = new Lc_977_有序数组的平方();
        int[] input1 = {-4, -1, 0, 3, 10};
        int[] input2 = {-7, -3, 2, 3, 11};

        System.out.println("数组 " + Arrays.toString(input1) + " 中每个数字的平方组成的新数组按非递减顺序排序后的结果为 " + Arrays.toString(lc.sortedSquares(input1)));
        System.out.println("数组 " + Arrays.toString(input2) + " 中每个数字的平方组成的新数组按非递减顺序排序后的结果为 " + Arrays.toString(lc.sortedSquares1(input2)));
    }

    // 双指针 - 时间复杂度 O(N) - 空间复杂度 O(1) 存放结果的数组除外
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int[] res = new int[n];

        for (int i = 0, j = n - 1, pos = n - 1; i <= j; ) {
            if (A[i] * A[i] > A[j] * A[j]) {
                res[pos] = A[i] * A[i];
                i++;
            } else {
                res[pos] = A[j] * A[j];
                j--;
            }
            pos--;
        }

        return res;
    }

    public int[] sortedSquares1(int[] A) {

        List<Integer> list = new LinkedList<>() {{
            for (int i : A) {
                add(i * i);
            }
        }};

        return list.stream().sorted().collect(Collectors.toList()).stream().mapToInt(Integer::valueOf).toArray();
    }

}
