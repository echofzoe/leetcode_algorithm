package algorithm.leetcode.doublePoint;

public class Lc_633_平方数之和 {

    // 平方数之和
    // https://leetcode-cn.com/problems/sum-of-square-numbers/

    public static void main(String[] args) {
        Lc_633_平方数之和 lc = new Lc_633_平方数之和();
        int c = Integer.MAX_VALUE;

        System.out.println((lc.judgeSquareSumDoublePoint(c) ? "存在" : "不存在") + "两个整数 a 和 b，使得 a^2 + b^2 = " + c);
    }

    // 二分查找 - 时间复杂度 O(根号C*logC) 其中枚举a的时间复杂度为O(根号C)，二分查找的时间复杂度为O(logC) - 空间复杂度 O(logC)
    public boolean judgeSquareSumBinarySearch(int c) {
        long tmp = 0;
        for (long a = 0; (tmp = a * a) <= c; a++) {
            long b = c - tmp;

            if (binarySearch(0, b, b)) {
                return true;
            }
        }

        return false;
    }

    private boolean binarySearch(long lo, long hi, long target) {
        if (lo > hi) return false;

        long mid = lo + (hi - lo) / 2, square = mid * mid;
        if (square == target) {
            return true;
        } else if (square > target) {
            return binarySearch(lo, mid - 1, target);
        } else {
            return binarySearch(mid + 1, hi, target);
        }
    }

    // 双指针 - 时间复杂度 O() - 空间复杂度 O()
    public boolean judgeSquareSumDoublePoint(int c) {
        int lo = 0, hi = (int) Math.sqrt(c);

        while (lo <= hi) {
            int sum = lo * lo + hi * hi;
            if (sum == c) {
                return true;
            } else if (sum < c) {
                lo++;
            } else {
                hi--;
            }
        }

        return false;
    }

}
