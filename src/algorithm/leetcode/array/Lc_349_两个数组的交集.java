package algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Lc_349_两个数组的交集 {

    // 两个数组的交集
    // https://leetcode-cn.com/problems/intersection-of-two-arrays/

    public static void main(String[] args) {
        Lc_349_两个数组的交集 lc = new Lc_349_两个数组的交集();
        int[] nums1 = {4, 9, 5}, nums2 = {9, 4, 9, 8, 4};
        int[] nums3 = {1, 2, 2, 1}, nums4 = {2, 2};

        System.out.println("数组" + Arrays.toString(nums1) + "与" + Arrays.toString(nums2) + "的交集是" + Arrays.toString(lc.intersectionSortAndDoublePoint(nums1, nums2)));
        System.out.println("数组" + Arrays.toString(nums3) + "与" + Arrays.toString(nums4) + "的交集是" + Arrays.toString(lc.intersectionDoubleSet(nums3, nums4)));
    }

    // 排序 + 双指针
    // - 时间复杂度 O(mlogm+nlogn), 其中m和n分别是两个数组的长度. 对两个数组排序的时间复杂度分别是O(mlogm)和O(nlogn), 双指针寻找交集元素的时间复杂度是O(m+n)
    // - 空间复杂度 O(logm+logn)
    public int[] intersectionSortAndDoublePoint(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] res = new int[nums1.length + nums2.length];
        int i = 0, j = 0, index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                res[index++] = nums1[i];
                while (++i < nums1.length && nums1[i] == nums1[i - 1]) ;
                while (++j < nums2.length && nums2[j] == nums2[j - 1]) ;
            } else if (nums1[i] < nums2[j]) {
                while (++i < nums1.length && nums1[i] == nums1[i - 1]) ;
            } else {
                while (++j < nums2.length && nums2[j] == nums2[j - 1]) ;
            }
        }

        return Arrays.copyOfRange(res, 0, index);
    }

    // 双集合 - 时间复杂度 O(m+n), m和n分别是两个数组的长度 - 空间复杂度 O(m+n)
    public int[] intersectionDoubleSet(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            set2.add(num);
        }

        return getIntersection(set1, set2);
    }

    private int[] getIntersection(Set<Integer> set1, Set<Integer> set2) {
        if (set1.size() > set2.size()) return getIntersection(set2, set1);

        int[] res = new int[set2.size()];
        int index = 0;

        for (int num : set1) {
            if (set2.contains(num)) {
                res[index++] = num;
            }
        }

        return Arrays.copyOfRange(res, 0, index);
    }

}
