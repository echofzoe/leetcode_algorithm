package algorithm.leetcode.recursion;

public class Jzo_64_求n个数累加 {

    // 求1+2+…+n
    // https://leetcode-cn.com/problems/qiu-12n-lcof/

    public static void main(String[] args) {
        Jzo_64_求n个数累加 lc = new Jzo_64_求n个数累加();
        int n = 9;
        
        System.out.println("1+2+...+" + n + " 的结果为 " + lc.sumNums(n));
    }

    public int sumNums(int n) {
        if (n > 0) n += sumNums(n - 1);
        return n;
    }
}
