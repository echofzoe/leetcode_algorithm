package JavaSE_basic.basic03;

import java.util.Arrays;

public class ArraysReferences {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 6, 13, 4, 15};
        int[] nums2 = new int[]{1, 2, 3, 4, 5};

        boolean isEqual = Arrays.equals(nums1, nums2);
        System.out.println("两数组是否相同？" + isEqual);

        System.out.println(Arrays.toString(nums1));

        Arrays.sort(nums1);
        for (int i : nums1) System.out.print(i + " ");
        System.out.println();

        Arrays.fill(nums1, 10);
        for (int i : nums1) System.out.print(i + " ");
        System.out.println();

        System.out.println("二分查找元素\'3\'的索引为：" + Arrays.binarySearch(nums2, 3));

        // Arrays常见异常
        // 1.越界异常：ArrayIndexOutOfBoundsException
        try {
            System.out.println(nums1[100]);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 无论异常否，都会执行的代码
        }

        // 2.越界异常：NullPointerException
        try {
            nums1 = null;
            System.out.println(nums1[100]);
        } catch (Exception e) {
            // 越界异常：ArrayIndexOutOfBoundsException
            e.printStackTrace();
        }
    }
}

