package algorithm.leetcode.bit;

/**
 * 将二进制表示减到 1 的步骤数
 * <P>https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/</P>
 *
 * @author echofzoe
 * @since 2021.6.21
 */
public class Lc_1404_将二进制表示减到1的步骤数 {

    public static void main(String[] args) {
        Lc_1404_将二进制表示减到1的步骤数 lc = new Lc_1404_将二进制表示减到1的步骤数();

        String s = "1101";

        System.out.println("给你一个以二进制形式表示的数字 s 。请你返回按下述规则将其减少到 1 所需要的步骤数：\n" +
                "  - 如果当前数字为偶数，则将其除以 2 。\n" +
                "  - 如果当前数字为奇数，则将其加上 1 。\n" +
                "题目保证你总是可以按上述规则将测试用例变为 1 。\n");
        System.out.println("输入：s = " + "\"" + s + "\"");
        System.out.println("输出：" + lc.numSteps(s));  // 6
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int numSteps(String s) {
        int n = s.length();

        int res = 0;
        boolean meet1 = false;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c == '0') {
                // 1. 当前是0，并且之前已出现过1，则当前0被之前1的进位改写成了1，需要进行一次+1操作和一次除2操作
                // 2. 当前是0，并且之前未出现过1，则当前是最末尾的0，只需进行一次除2操作即可
                res += meet1 ? 2 : 1;
            } else {
                // 1. 当前是1，并且之前已出现过1，则当前1被之前1的进位改写成了0，需要进行一次除2操作
                // 2. 当前是1，并且之前未出现过1，则当前1需要进行一次+1操作和一次除2操作
                if (meet1) {
                    res++;
                } else {
                    if (i != 0) res += 2;
                    meet1 = true;
                }
            }
        }

        return res;
    }

}
