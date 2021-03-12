package algorithm.leetcode.dpAndGreedy;

import java.util.*;

public class Lc_659_分割数组为连续子序列 {

    // 分割数组为连续子序列
    // https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences/

    public static void main(String[] args) {
        Lc_659_分割数组为连续子序列 lc = new Lc_659_分割数组为连续子序列();
        int[] input1 = {1, 2, 3, 3, 4, 4, 5, 5};
        int[] input2 = {1, 2, 3, 4, 4, 5};

        boolean b1 = lc.isPossibleGreedy(input1);
        boolean b2 = lc.isPossibleGreedy(input2);
        System.out.println("按升序排序的整数数组" + Arrays.toString(input1) + (b1 ? "能" : "不能") + "分割成一个或多个子序列（子序列长度大于3）");
        if (b1) System.out.println("所有子序列的集合是" + lc.isPossibleGreedyPrint(input1));

        System.out.println("按升序排序的整数数组" + Arrays.toString(input2) + (b2 ? "能" : "不能") + "分割成一个或多个子序列");
        if (b2) System.out.println("所有子序列的集合是" + lc.isPossibleGreedyPrint(input2));
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public boolean isPossibleGreedy(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> freq = new HashMap<>();  // 统计数字出现频次，帮助判断当前数字是否能够开头
        Map<Integer, Integer> need = new HashMap<>();  // 帮助判断当前数字是否可以结尾

        for (int v : nums) freq.put(v, freq.getOrDefault(v, 0) + 1);

        for (int v : nums) {
            // 当前数字已经被用到其他子序列中
            if (freq.get(v) == 0) continue;

            if (need.containsKey(v) && need.get(v) > 0) {
                // 当前数字需要放入其他已有的子序列后面
                freq.put(v, freq.get(v) - 1);
                need.put(v, need.get(v) - 1);

                need.put(v + 1, need.getOrDefault(v + 1, 0) + 1);
            } else if (freq.get(v) > 0 && freq.getOrDefault(v + 1, 0) > 0 && freq.getOrDefault(v + 2, 0) > 0) {
                // 当前数字不需要放入其他已有子序列后面，但是可以作为首位元素新开一个子序列
                freq.put(v, freq.get(v) - 1);
                freq.put(v + 1, freq.get(v + 1) - 1);
                freq.put(v + 2, freq.get(v + 2) - 1);

                need.put(v + 3, need.getOrDefault(v + 3, 0) + 1);
            } else {
                // 以上两个条件都不满足，则无法分配当前数字
                return false;
            }
        }

        return true;
    }

    // 进阶 - 若返回true则打印出所有子序列 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public List<List<Integer>> isPossibleGreedyPrint(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> freq = new HashMap<>();  // 统计数字出现频次，帮助判断当前数字是否能够开头
        Map<Integer, List<List<Integer>>> need = new HashMap<>();  // 帮助判断当前数字是否可以结尾

        for (int v : nums) freq.put(v, freq.getOrDefault(v, 0) + 1);

        for (int v : nums) {
            // 当前数字已经被用到其他子序列中
            if (freq.get(v) == 0) continue;

            if (need.containsKey(v) && need.get(v).size() > 0) {
                // 当前数字需要放入其他已有的子序列后面
                freq.put(v, freq.get(v) - 1);
                // 随便取一个需要当前数字的子序列
                List<Integer> seq = need.get(v).get(0);
                need.get(v).remove(0);
                seq.add(v);

                // 更新需求子序列
                need.getOrDefault(v + 1, new ArrayList<>()).add(seq);
            } else if (freq.get(v) > 0 && freq.getOrDefault(v + 1, 0) > 0 && freq.getOrDefault(v + 2, 0) > 0) {
                // 当前数字不需要放入其他已有子序列后面，但是可以作为首位元素新开一个子序列
                freq.put(v, freq.get(v) - 1);
                freq.put(v + 1, freq.get(v + 1) - 1);
                freq.put(v + 2, freq.get(v + 2) - 1);

                List<List<Integer>> tmp = new ArrayList<>() {{
                    new ArrayList<>() {{
                        add(v);
                        add(v + 1);
                        add(v + 2);
                    }};
                }};
                need.put(v + 3, tmp);
            } else {
                // 以上两个条件都不满足，则无法分配当前数字
                return null;
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for (List<List<Integer>> tmp : need.values()) {
            res.addAll(tmp);
        }
        return res;
    }

}
