package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_605_种花问题 {

    // 种花问题
    // https://leetcode-cn.com/problems/can-place-flowers/

    public static void main(String[] args) {
        Lc_605_种花问题 lc = new Lc_605_种花问题();
        int[] flowerbed1 = {1, 0, 0, 0, 0, 1};
        int[] flowerbed2 = {0, 1, 0};
        int n1 = 2, n2 = 1;

        System.out.println("在花坛" + Arrays.toString(flowerbed1) + "上" + (lc.canPlaceFlowers(flowerbed1, n1) ? "能" : "不能") + "种入" + n1 + "朵花");
        System.out.println("在花坛" + Arrays.toString(flowerbed2) + "上" + (lc.canPlaceFlowers(flowerbed2, n2) ? "能" : "不能") + "种入" + n2 + "朵花");
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        if ((len + 1) / 2 < n) return false;

        for (int i = 0; i < len && n > 0; i += 2) {
            if (flowerbed[i] == 0) {
                if (i == len - 1 || flowerbed[i + 1] == 0) {
                    n--;
                } else {
                    i++;
                }
            }
        }

        return n == 0;
    }

}
