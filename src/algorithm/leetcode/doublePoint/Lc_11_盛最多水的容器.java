package algorithm.leetcode.doublePoint;

import java.util.Arrays;

public class Lc_11_盛最多水的容器 {

    // 盛最多水的容器
    // https://leetcode-cn.com/problems/container-with-most-water/

    public static void main(String[] args) {
        Lc_11_盛最多水的容器 lc = new Lc_11_盛最多水的容器();
        int[] input = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        System.out.println("容器" + Arrays.toString(input) + "中最大的盛水面积是" + lc.maxArea(input));
    }

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;

        while (i < j) {
            res = height[i] < height[j] ? Math.max((j - i) * height[i++], res) : Math.max((j - i) * height[j--], res);
        }

        return res;
    }

}
