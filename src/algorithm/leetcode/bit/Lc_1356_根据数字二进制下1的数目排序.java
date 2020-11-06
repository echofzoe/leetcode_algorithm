package algorithm.leetcode.bit;

import java.util.*;

public class Lc_1356_根据数字二进制下1的数目排序 {

    // 根据数字二进制下 1 的数目排序
    // https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits/

    public static void main(String[] args) {
        Lc_1356_根据数字二进制下1的数目排序 lc = new Lc_1356_根据数字二进制下1的数目排序();
        int[] arr = {1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};

        System.out.println("数组" + Arrays.toString(arr) + "按照二进制下1的数目排序的结果为" + Arrays.toString(lc.sortByBits(arr)));
    }

    public int[] sortByBits(int[] arr) {

        int[] bit = new int[10001];
        List<Integer> list = new ArrayList<>();
        for (int x : arr) {
            list.add(x);
            bit[x] = count1(x);
        }

        list.sort((o1, o2) -> {
            if (bit[o1] != bit[o2]) return bit[o1] - bit[o2];
            return o1 - o2;
        });

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    private int count1(int x) {
        int cnt = 0;
        while (x > 0) {
            if ((x & 1) == 1) cnt++;
            x >>>= 1;
        }
        return cnt;
    }

}
