package algorithm.leetcode.array;

import java.util.*;

public class Lc_1122_数组的相对排序 {

    // 数组的相对排序
    // https://leetcode-cn.com/problems/relative-sort-array/

    public static void main(String[] args) {
        Lc_1122_数组的相对排序 lc = new Lc_1122_数组的相对排序();
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};

        System.out.println("数组" + Arrays.toString(arr1) + "按照" + Arrays.toString(arr2) + "中出现元素的相对位置排序，未出现的按升序排放在末尾的结果是" + Arrays.toString(lc.relativeSortArrayCount(arr1, arr2)));
    }

    // 自定义排序 - 时间复杂度 O(N*logN) N为两数组最大长度,N*logN为N个数排序的时间复杂度 - 空间复杂度 O(m + n + logN) m,n为两数组构建的集合长度,logN为排序需要的栈空间
    public int[] relativeSortArrayMap(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int num : arr1) {
            list.add(num);
        }
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }

        list.sort((x, y) -> {
            if (map.containsKey(x) || map.containsKey(y)) {
                return map.getOrDefault(x, 1001) - map.getOrDefault(y, 1001);
            }
            return x - y;
        });

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = list.get(i);
        }

        return arr1;
    }

    // 计数排序
    public int[] relativeSortArrayCount(int[] arr1, int[] arr2) {
        int max = 0;
        for (int num : arr1) {
            max = Math.max(max, num);
        }

        int[] freq = new int[max + 1];
        for (int num : arr1) {
            freq[num]++;
        }

        int[] res = new int[arr1.length];
        int index = 0;
        for (int num : arr2) {
            for (int i = 0; i < freq[num]; i++) {
                res[index++] = num;
            }
            freq[num] = 0;
        }

        for (int i = 0; i <= max; i++) {
            for (int j = 0; j < freq[i]; j++) {
                res[index++] = i;
            }
        }

        return res;
    }

}
