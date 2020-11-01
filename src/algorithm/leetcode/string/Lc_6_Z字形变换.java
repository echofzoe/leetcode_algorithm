package algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class Lc_6_Z字形变换 {

    // Z 字形变换
    // https://leetcode-cn.com/problems/zigzag-conversion/

    public static void main(String[] args) {
        Lc_6_Z字形变换 lc = new Lc_6_Z字形变换();
        String input = "LEETCODEISHIRING";
        int numRows = 3;

        System.out.println("字符串\"" + input + "\"在行数为" + numRows + "时进行Z字形排列后的结果为\"" + lc.convert(input, numRows) + "\"");
    }

    // 时间复杂度 O(N) - 空间复杂度 O(N)
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) flag = -flag;
            i += flag;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }

        return res.toString();
    }

    public String convert2(String s, int numRows) {
        if (numRows == 1) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for(int i = 0; i < numRows; i++) rows[i] = new StringBuilder();
        int n = 2 * numRows - 2;
        for (int i = 0; i < s.length(); i++) {
            int x = i % n;
            rows[Math.min(x, n - x)].append(s.charAt(i));
        }

        return String.join("", rows);
    }

}
