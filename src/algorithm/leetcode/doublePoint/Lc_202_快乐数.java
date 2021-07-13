package algorithm.leetcode.doublePoint;

import java.util.HashSet;
import java.util.Set;

/**
 * 快乐数
 * <P>https://leetcode-cn.com/problems/happy-number/</P>
 *
 * @author echofzoe
 * @since 2021.7.13
 */
public class Lc_202_快乐数 {

    public static void main(String[] args) {
        Lc_202_快乐数 lc = new Lc_202_快乐数();

        int n1 = 19, n2 = 2;
        
        System.out.println("编写一个算法来判断一个数 n 是不是快乐数。\n" +
                "「快乐数」定义为：\n" +
                "  - 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。\n" +
                "  - 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。\n" +
                "  - 如果 可以变为  1，那么这个数就是快乐数。\n" +
                "如果 n 是快乐数就返回 true ；不是，则返回 false 。\n");
        System.out.println("输入：n = " + n1);
        System.out.println("输出：" + lc.isHappy(n1));
        System.out.println("输入：n = " + n2);
        System.out.println("输出：" + lc.isHappy1(n2));
    }

    // 快慢双指针 - 时间复杂度 O(logN) - 空间复杂度 O(1)
    public boolean isHappy(int n) {
        int slow = n, fast = getNext(n);

        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }

        return fast == 1;
    }

    // hash - 时间复杂度 O(logN) - 空间复杂度 O(logN)
    public boolean isHappy1(int n) {
        Set<Integer> s = new HashSet<>();

        while (s.add(n)) {
            if (n == 1) return true;
            n = getNext(n);
        }

        return false;
    }

    private int getNext(int x) {
        int res = 0;

        while (x > 0) {
            res += Math.pow(x % 10, 2);
            x /= 10;
        }

        return res;
    }

}
