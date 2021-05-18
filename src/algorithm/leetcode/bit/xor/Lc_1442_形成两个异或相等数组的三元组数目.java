package algorithm.leetcode.bit.xor;

import java.util.Arrays;

/**
 * 形成两个异或相等数组的三元组数目
 * <P>https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/</P>
 *
 * @author echofzoe
 * @since 2021.5.18
 */
public class Lc_1442_形成两个异或相等数组的三元组数目 {

    public static void main(String[] args) {
        Lc_1442_形成两个异或相等数组的三元组数目 lc = new Lc_1442_形成两个异或相等数组的三元组数目();

        int[] arr = {2, 3, 1, 6, 7}, arr1 = {1, 1, 1, 1, 1};

        System.out.println("给你一个整数数组 arr 。\n" +
                "现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。\n" +
                "a 和 b 定义如下：\n" +
                "    a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]\n" +
                "    b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]\n" +
                "注意：^ 表示 按位异或 操作。\n" +
                "请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。");
        System.out.println("输入：" + Arrays.toString(arr));
        System.out.println("输出：" + lc.countTriplets(arr));  // 4, 满足的下标三元组有(0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
        System.out.println("输入：" + Arrays.toString(arr1));
        System.out.println("输出：" + lc.countTriplets1(arr1));  // 10
    }

    // 前缀异或和 + 三重循环 - 时间复杂度 O(N^3) - 空间复杂度 O(N)
    public int countTriplets(int[] arr) {
        int n = arr.length;

        /* arr 中，区间 [i, j) 的异或和为：
            arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
            = (arr[0] ^ ... ^ arr[i - 1]) ^ (arr[0] ^ ... ^ arr[i - 1] ^ arr[i] ^ ... ^ arr[j - 1])
            = pre[i] ^ pre[j]
         */
        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] ^ arr[i - 1];
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j; k < n; k++) {
                    // 判断区间 [i, j) 与 [j, k] 的异或和是否相等
                    // if ((pre[i] ^ pre[j]) == (pre[j] ^ pre[k + 1])) 可简化为下式
                    if (pre[i] == pre[k + 1]) {
                        res++;
                    }
                }
            }
        }

        return res;
    }

    // 前缀异或和 + 二重循环 - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    // - 优化思路：当等式 pre[i] == pre[k + 1] 成立时，[i + 1, k] 范围内的任意 j 都符合要求，对应的三元组个数为 k - i
    public int countTriplets1(int[] arr) {
        int n = arr.length;

        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] ^ arr[i - 1];
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                if (pre[i] == pre[k + 1]) {
                    res += k - i;
                }
            }
        }

        return res;
    }

}
