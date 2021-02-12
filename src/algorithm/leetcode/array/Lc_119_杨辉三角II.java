package algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Lc_119_杨辉三角II {

    // 杨辉三角 II
    // https://leetcode-cn.com/problems/pascals-triangle-ii/

    public static void main(String[] args) {
        Lc_119_杨辉三角II lc = new Lc_119_杨辉三角II();
        int rowIndex = 3;

        System.out.println("杨辉三角的第" + rowIndex + "行是" + lc.getRow2(rowIndex));
    }

    // 递推 - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    public List<Integer> getRow1(int rowIndex) {
        Integer[][] res = new Integer[rowIndex + 1][rowIndex + 1];
        for (Integer[] e : res) Arrays.fill(e, 0);

        for (int i = 0; i <= rowIndex; i++) {
            res[i][0] = 1;

            for (int j = 1; j <= i; j++) {
                res[i][j] = res[i - 1][j - 1] + res[i - 1][j];
            }
        }

        return Arrays.asList(res[rowIndex]);
    }

    // 滚动数组(递推优化) - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        
        res.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            res.add(0);

            for (int j = i; j > 0; j--) {
                res.set(j, res.get(j - 1) + res.get(j));
            }
        }

        return res;
    }

}
