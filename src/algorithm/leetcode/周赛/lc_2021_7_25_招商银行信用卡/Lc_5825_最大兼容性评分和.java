package algorithm.leetcode.周赛.lc_2021_7_25_招商银行信用卡;

import java.util.Arrays;

/**
 * 最大兼容性评分和
 * <P>https://leetcode-cn.com/problems/maximum-compatibility-score-sum/</P>
 *
 * @author echofzoe
 * @since 2021.7.25
 */
public class Lc_5825_最大兼容性评分和 {

    public static void main(String[] args) {
        Lc_5825_最大兼容性评分和 lc = new Lc_5825_最大兼容性评分和();

        int[][] students = {{1, 1, 1}, {0, 0, 1}, {0, 0, 1}, {0, 1, 0}}, mentors = {{1, 0, 1}, {0, 1, 1}, {0, 1, 0}, {1, 1, 0}};

        System.out.println("有一份由 n 个问题组成的调查问卷，每个问题的答案要么是 0（no，否），要么是 1（yes，是）。\n" +
                "这份调查问卷被分发给 m 名学生和 m 名导师，学生和导师的编号都是从 0 到 m - 1 。学生的答案用一个二维整数数组 students 表示，其中 students[i] 是一个整数数组，包含第 i 名学生对调查问卷给出的答案（下标从 0 开始）。导师的答案用一个二维整数数组 mentors 表示，其中 mentors[j] 是一个整数数组，包含第 j 名导师对调查问卷给出的答案（下标从 0 开始）。\n" +
                "每个学生都会被分配给 一名 导师，而每位导师也会分配到 一名 学生。配对的学生与导师之间的兼容性评分等于学生和导师答案相同的次数。\n" +
                "  - 例如，学生答案为[1, 0, 1] 而导师答案为 [0, 0, 1] ，那么他们的兼容性评分为 2 ，因为只有第二个和第三个答案相同。\n" +
                "请你找出最优的学生与导师的配对方案，以 最大程度上 提高 兼容性评分和 。\n" +
                "给你 students 和 mentors ，返回可以得到的 最大兼容性评分和 。\n");
        System.out.println("输入：students = " + Arrays.deepToString(students) + ", mentors = " + Arrays.deepToString(mentors));
        System.out.println("输出：");
    }

    // dfs - 时间复杂度 O(M^2 + MN) - 空间复杂度 O(M^2)
    private int m, n, res;
    private int[] ss, ms;
    private int[][] similar;

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        m = students.length;
        n = students[0].length;
        res = 0;
        this.ss = new int[m];
        this.ms = new int[m];
        similar = new int[m][m];

        // 状态压缩
        for (int i = 0; i < m; i++) {
            int a = 0, b = 0;
            for (int j = 0; j < n; j++) {
                a = (a << 1) | students[i][j];
                b = (b << 1) | mentors[i][j];
            }

            ss[i] = a;
            ms[i] = b;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                similar[i][j] = n - Integer.bitCount(ss[i] ^ ms[j]);
            }
        }

        dfs(0, 0);

        return res;
    }

    private void dfs(int sid, int sum) {
        if (sid == m) {
            res = Math.max(res, sum);
            return;
        }

        for (int mid = 0; mid < m; mid++) {
            if (ms[mid] == -1) continue;

            int tmp = ms[sid];
            ms[mid] = -1;

            // dfs
            dfs(sid + 1, sum + similar[sid][mid]);

            // backtrace
            ms[mid] = tmp;
        }
    }

}
