package algorithm.leetcode.周赛.lc_2021_1_24_图灵教育;

public class Lc_5661_替换隐藏数字得到的最晚时间 {

    // 替换隐藏数字得到的最晚时间
    // https://leetcode-cn.com/problems/latest-time-by-replacing-hidden-digits/

    public static void main(String[] args) {
        Lc_5661_替换隐藏数字得到的最晚时间 lc = new Lc_5661_替换隐藏数字得到的最晚时间();
        String time = "1?:22";

        System.out.println(time + "中替换隐藏数字得到的最晚时间是" + lc.maximumTime(time));
    }

    // 模拟 - 时间复杂度 O(1) - 空间复杂度 O(1)
    public String maximumTime(String time) {
        char[] arr = time.toCharArray();

        if (arr[0] == '?' && arr[1] == '?') {
            arr[0] = '2';
            arr[1] = '3';
        } else if (arr[0] == '?') {
            if (arr[1] <= '3') arr[0] = '2';
            else arr[0] = '1';
        } else if (arr[1] == '?') {
            if (arr[0] <= '1') arr[1] = '9';
            else arr[1] = '3';
        }

        if (arr[3] == '?') arr[3] = '5';
        if (arr[4] == '?') arr[4] = '9';

        return new String(arr);
    }

}
