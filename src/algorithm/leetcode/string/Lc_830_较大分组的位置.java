package algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc_830_较大分组的位置 {

    // 较大分组的位置
    // https://leetcode-cn.com/problems/positions-of-large-groups/

    public static void main(String[] args) {
        Lc_830_较大分组的位置 lc = new Lc_830_较大分组的位置();
        String s = "abcdddeeeeaabbbcd";
        
        System.out.println("字符串\"" + s + "\"按题意得到的每一个连续的较大分组的区间是" + lc.largeGroupPositions(s));
    }

    // 一趟遍历 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        int n = s.length();
        if (n < 3) return res;

        int count = 1;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                if (count >= 3) res.add(Arrays.asList(i - count + 1, i));
                count = 1;
            } else {
                count++;
            }
        }

        return res;
    }

    // 一趟遍历 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public List<List<Integer>> largeGroupPositions1(String s) {
        List<List<Integer>> res = new ArrayList<>();
        int n = s.length();
        if (n < 3) return res;

        int start = 0, end = 0;
        while (end < n) {
            for (end = start + 1; end < n; end++) {
                if (s.charAt(end) != s.charAt(start) && end - start >= 3) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(start);
                    tmp.add(end - 1);
                    res.add(tmp);

                    start = end;
                } else if (s.charAt(end) != s.charAt(start) && end - start < 3) {
                    start = end;
                    break;
                }
            }
        }

        if (end == n && end - start >= 3) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(start);
            tmp.add(end - 1);
            res.add(tmp);
            start = end;
        }

        return res;
    }

}
