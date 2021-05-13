package algorithm.leetcode.array.树状数组;

import java.util.Arrays;

/**
 * 区域和检索 - 数组可修改
 * <P>https://leetcode-cn.com/problems/range-sum-query-mutable/</P>
 *
 * @author echofzoe
 * @since 2021.5.13
 */
public class Lc_307_区域和检索_数组可修改 {

    public static void main(String[] args) {
        int[] input = {1, 3, 5};
        NumArray numArray = new NumArray(input);
        System.out.println("数组 " + Arrays.toString(input) + " 中下标 0 到 2 之间的区域和是 " + numArray.sumRange(0, 2));  // 9
        System.out.print("数组 " + Arrays.toString(input) + " 中将下标 1 的元素更新为 2 的结果是 ");
        numArray.update(1, 2);
        System.out.println(Arrays.toString(input));
        System.out.println("现在，数组 " + Arrays.toString(input) + " 中下标 0 到 2 之间的区域和是 " + numArray.sumRange(0, 2));  // 8
    }

}

// 树状数组 - 时间复杂度 O(NlogN) add操作和query操作的复杂度都是O(logN),因此构建数组的复杂度为O(NlogN) - 空间复杂度 O(N)
class NumArray {

    int[] tree;

    int lowBit(int x) {
        return x & -x;
    }

    // 查找前缀和的方法
    int query(int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowBit(i)) res += tree[i];
        return res;
    }

    // 在树状数组的 idx 位置中增加值 x
    void add(int idx, int x) {
        for (int i = idx; i <= n; i += lowBit(i)) tree[i] += x;
    }

    int[] nums;
    int n;

    public NumArray(int[] _nums) {
        nums = _nums;
        n = nums.length;
        tree = new int[n + 1];
        for (int i = 0; i < n; i++) add(i + 1, nums[i]);
    }

    public void update(int idx, int val) {
        add(idx + 1, val - nums[idx]);
        nums[idx] = val;
    }

    public int sumRange(int lo, int hi) {
        return query(hi + 1) - query(lo);
    }
}
