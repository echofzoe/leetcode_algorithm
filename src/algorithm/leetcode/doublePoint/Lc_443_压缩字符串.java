package algorithm.leetcode.doublePoint;

import java.util.Arrays;

/**
 * 压缩字符串
 * <P>https://leetcode-cn.com/problems/string-compression/</P>
 *
 * @author echofzoe
 * @since 2021.8.22
 */
public class Lc_443_压缩字符串 {

    public static void main(String[] args) {
        Lc_443_压缩字符串 lc = new Lc_443_压缩字符串();

        char[] cs = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};

        System.out.println("给你一个字符数组 chars ，请使用下述算法压缩：\n" +
                "从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：\n" +
                "  - 如果这一组长度为 1 ，则将字符追加到 s 中。\n" +
                "  - 否则，需要向 s 追加字符，后跟这一组的长度。\n" +
                "压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。\n" +
                "请在 修改完输入数组后 ，返回该数组的新长度。\n" +
                "你必须设计并实现一个只使用常量额外空间的算法来解决此问题。\n");
        System.out.println("输入：chars = " + Arrays.toString(cs));
        System.out.println("输出：" + lc.compress(cs));  // 6
    }

    // 双指针 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int compress(char[] cs) {
        int n = cs.length;

        int write = 0, left = 0;

        for (int read = 0; read < n; read++) {
            if (read == n - 1 || cs[read] != cs[read + 1]) {
                cs[write++] = cs[read];

                int num = read - left + 1;
                if (num > 1) {
                    int start = write;

                    while (num > 0) {
                        cs[write++] = (char) (num % 10 + '0');
                        num /= 10;
                    }

                    reverse(cs, start, write - 1);
                }

                left = read + 1;
            }
        }

        return write;
    }

    private void reverse(char[] cs, int l, int r) {
        while (l < r) {
            char c = cs[l];
            cs[l] = cs[r];
            cs[r] = c;

            l++;
            r--;
        }
    }

}
