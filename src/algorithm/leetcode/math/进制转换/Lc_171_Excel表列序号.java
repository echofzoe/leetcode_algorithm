package algorithm.leetcode.math.进制转换;

/**
 * Excel表列序号
 * <P>https://leetcode-cn.com/problems/excel-sheet-column-number/</P>
 *
 * @author echofzoe
 * @since 2021.7.30
 */
public class Lc_171_Excel表列序号 {

    public static void main(String[] args) {
        Lc_171_Excel表列序号 lc = new Lc_171_Excel表列序号();

        String columnTitle = "ZY";

        System.out.println("给定一个Excel表格中的列名称，返回其相应的列序号。");
        System.out.println("输入：columnTitle = " + "\"" + columnTitle + "\"");
        System.out.println("输出：" + lc.titleToNumber(columnTitle));  // 701
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int titleToNumber(String columnTitle) {
        int n;
        if (columnTitle == null || (n = columnTitle.length()) == 0) return 0;

        int res = 0;
        for (int i = 0; i < n; i++) {
            char c = columnTitle.charAt(i);

            res = res * 26 + (c - 'A' + 1);
        }

        return res;
    }

}
