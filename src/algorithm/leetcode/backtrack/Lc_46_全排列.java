package algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Lc_46_全排列 {

    // 全排列
    // https://leetcode-cn.com/problems/permutations/

    public static void main(String[] args) {
        Lc_46_全排列 lc = new Lc_46_全排列();
        int[] input = {1, 2, 3};

        System.out.println("没有重复数字的序列" + Arrays.toString(input) + "的所有可能的全排列有" + Arrays.deepToString(lc.permute(input).toArray()));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }

        backtrack(nums.length, output, res, 0);
        return res;
    }

    private void backtrack(int length, List<Integer> output, List<List<Integer>> res, int first) {
        if (first == length) {
            res.add(new ArrayList<>(output));
        }

        for (int i = first; i < length; i++) {
            Collections.swap(output, first, i);    // 当前操作
            backtrack(length, output, res, first + 1);    // 继续递归填下一个数
            Collections.swap(output, first, i);    // 撤销当前操作
        }
    }

}
