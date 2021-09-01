package algorithm.leetcode.string;

/**
 * 比较版本号
 * <P>https://leetcode-cn.com/problems/compare-version-numbers/</P>
 *
 * @author echofzoe
 * @since 2021.9.1
 */
public class Lc_165_比较版本号 {

    public static void main(String[] args) {
        Lc_165_比较版本号 lc = new Lc_165_比较版本号();

        String version1 = "1.01", version2 = "1.001";

        System.out.println("给你两个版本号 version1 和 version2 ，请你比较它们。\n" +
                "版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。\n" +
                "比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。\n" +
                "返回规则如下：\n" +
                "  - 如果 version1 > version2 返回 1，\n" +
                "  - 如果 version1 < version2 返回 -1，\n" +
                "  - 除此之外返回 0。\n");
        System.out.println("输入：version1 = " + version1 + ", version2 = " + version2);
        System.out.println("输出：" + lc.compareVersion(version1, version2));  // 0
    }

    // 模拟 - 时间复杂度 O(N + M) - 空间复杂度 O(N + M) N为version1的长度，M为version2的长度
    public int compareVersion(String version1, String version2) {
        String[] ss1 = version1.split("\\."), ss2 = version2.split("\\.");
        int n1 = ss1.length, n2 = ss2.length;

        for (int i = 0; i < n1 || i < n2; i++) {
            int a = 0, b = 0;
            if (i < n1) a = Integer.parseInt(ss1[i]);
            if (i < n2) b = Integer.parseInt(ss2[i]);

            if (a > b) return 1;
            if (a < b) return -1;
        }

        return 0;
    }

    // 双指针 - 时间复杂度 O(N + M) - 空间复杂度 O(1)
    public int compareVersion1(String version1, String version2) {
        int n1 = version1.length(), n2 = version2.length();

        int i = 0, j = 0;
        while (i < n1 || j < n2) {
            int a = 0, b = 0;

            for ( ; i < n1 && version1.charAt(i) != '.'; i++) {
                a = a * 10 + version1.charAt(i) - '0';
            }
            i++;

            for ( ; j < n2 && version2.charAt(j) != '.'; j++) {
                b = b * 10 + version2.charAt(j) - '0';
            }
            j++;

            if (a != b) {
                return a > b ? 1 : -1;
            }
        }

        return 0;
    }

}
