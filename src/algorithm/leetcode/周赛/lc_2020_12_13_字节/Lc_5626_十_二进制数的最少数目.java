package algorithm.leetcode.周赛.lc_2020_12_13_字节;

public class Lc_5626_十_二进制数的最少数目 {

    // 十-二进制数的最少数目
    // https://leetcode-cn.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/

    public static void main(String[] args) {
        Lc_5626_十_二进制数的最少数目 lc = new Lc_5626_十_二进制数的最少数目();
        String n1 = "82734";
        String n2 = "27346209830709182346";
        
        System.out.println("和为十进制数" + n1 + "的十-二进制数的最少数目是" + lc.minPartitions(n1));
        System.out.println("和为十进制数" + n2 + "的十-二进制数的最少数目是" + lc.minPartitions(n2));
    }

    public int minPartitions(String n) {
        int res = 0;

        for (char c : n.toCharArray()) {
            res = Math.max(res, c - 48);
            if (res == 9) return res;
        }

        return res;
    }

}
