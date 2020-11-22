package algorithm.leetcode.周赛.lc_2020_11_22_商汤;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lc_5607_生成平衡数组的方案数 {

    // 生成平衡数组的方案数
    // https://leetcode-cn.com/problems/ways-to-make-a-fair-array/

    public static void main(String[] args) {
        Lc_5607_生成平衡数组的方案数 lc = new Lc_5607_生成平衡数组的方案数();
        int[] nums = {2, 1, 6, 4};
        
        System.out.println("从数组" + Arrays.toString(nums) + "中选择恰好一个从0开始的下标并删除对应的元素后满足奇数下标元素的和与偶数下标元素的和相等的方案数有" + lc.waysToMakeFair(nums) + "个");
    }

    public int waysToMakeFair(int[] nums) {
        int res = 0, n = nums.length;

        Map<Integer, int[]> prefis = new HashMap<>();    // 记录前缀和
        Map<Integer, int[]> suffis = new HashMap<>();    // 记录后缀和

        int oddSumP = 0, evenSumP = 0;    // 计算前缀和时的临时变量
        int oddSumS = 0, evenSumS = 0;    // 计算后缀和时的临时变量

        // 一趟遍历 -- 计算所有下标的前缀和与后缀和
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 1) {
                oddSumP += nums[i];
            } else {
                evenSumP += nums[i];
            }

            prefis.put(i, new int[]{evenSumP, oddSumP});

            int tmp = n - 1 - i;
            if ((tmp & 1) == 1) {
                oddSumS += nums[tmp];
            } else {
                evenSumS += nums[tmp];
            }

            suffis.put(tmp, new int[]{evenSumS, oddSumS});
        }

        // 一趟遍历 -- 计算结果
        for (int i = 0; i < n; i++) {
            evenSumP = prefis.get(i)[0];
            oddSumP = prefis.get(i)[1];

            evenSumS = suffis.get(i)[0];
            oddSumS = suffis.get(i)[1];

            // 偶数下标
            if ((i & 1) == 0) {
                // 删去当前下标元素后的偶数前缀和为 evenSumP - nums[i], 偶数后缀和为 oddSumS
                // 删去当前下标元素后的奇数前缀和为 oddSumP, 奇数后缀和为 evenSumS - nums[i]
                if (evenSumP - nums[i] + oddSumS == oddSumP + evenSumS - nums[i]) {
                    res++;
                }
            }

            // 奇数下标
            else {
                // 删去当前下标元素后的偶数前缀和为 evenSumP, 偶数后缀和为 oddSumS - nums[i]
                // 删去当前下标元素后的奇数前缀和为 oddSumP - nums[i], 奇数后缀和为 evenSumS
                if (evenSumP + oddSumS - nums[i] == oddSumP - nums[i] + evenSumS) {
                    res++;
                }
            }
        }

        return res;
    }

}


