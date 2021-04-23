package interview.programming;

import java.util.Arrays;

public class 阿里巴巴_求打完扑克牌的最少次数 {

    // 求打完扑克牌的最少次数
    // - 扑克牌的类型有0，1，2，3，4，5，6，7，8，9共10种类型，每种类型的牌的数量记为Ai，0<=Ai<=4，且保证至少有一张牌。扑克牌的打法有以下几种：
    // -- 单张牌：把任意一种类型的单张扑克牌打出
    // -- 对子：把两张相同类型的扑克牌一起打出
    // -- 顺子：把5张连续的扑克牌打出，如01234，34567等
    // -- 连对：把3对连续的对子一起打出，如112233，445566等
    // - 实例：
    // -- 输入是10个整数，分别代表每种扑克牌的数量；输出是1个整数，求打完所有的扑克牌所需的最少次数。
    // -- 输入 int[] nums = {1,1,1,2,2,2,2,2,1,1}
    // -- 输出 3
    // - 解释：
    // -- 分别打出01234、34567、56789，总共需要3次打完。

    public static void main(String[] args) {
        阿里巴巴_求打完扑克牌的最少次数 iv = new 阿里巴巴_求打完扑克牌的最少次数();
        int[] nums = {1, 1, 1, 2, 2, 2, 2, 2, 1, 1};

        System.out.println("输入：" + Arrays.toString(nums));
//        System.out.println("输出：" + iv.minimumTimes(nums));
    }

//    public int minimumTimes(int[] nums) {
//
//    }

}
