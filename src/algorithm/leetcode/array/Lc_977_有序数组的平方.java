package algorithm.leetcode.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 有序数组的平方
 * <P>https://leetcode-cn.com/problems/squares-of-a-sorted-array/</P>
 *
 * @author echofzoe
 * @since unknown
 * @updated 2021.7.27
 */
public class Lc_977_有序数组的平方 {

    public static void main(String[] args) {
        Lc_977_有序数组的平方 lc = new Lc_977_有序数组的平方();
        
        int[] A = {-4, -1, 0, 3, 10};

        System.out.println("给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。");
        System.out.println("输入：A = " + Arrays.toString(A));
        System.out.println("输出：" + Arrays.toString(lc.sortedSquares(A)));
    }

    // 双指针 - 时间复杂度 O(N) - 空间复杂度 O(1) 存放结果的数组除外
    public int[] sortedSquares(int[] A) {
        int n = A.length;

        int[] res = new int[n];

        int i = 0, j = n - 1, pos = n - 1;
        while (i <= j) {
            if (A[i] * A[i] > A[j] * A[j]) {
                res[pos--] = A[i] * A[i++];
            } else {
                res[pos--] = A[j] * A[j--];
            }
        }

        return res;
    }

    // 排序 - 时间复杂度 O(NlogN) - 空间复杂度 O(logN)
    public int[] sortedSquares1(int[] A) {
        List<Integer> list = new LinkedList<>() {{
            for (int i : A) {
                add(i * i);
            }
        }};

        return list.stream().sorted().mapToInt(Integer::valueOf).toArray();
    }

}
