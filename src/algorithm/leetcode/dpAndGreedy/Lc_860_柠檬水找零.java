package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lc_860_柠檬水找零 {

    // 柠檬水找零
    // https://leetcode-cn.com/problems/lemonade-change/

    public static void main(String[] args) {
        Lc_860_柠檬水找零 lc = new Lc_860_柠檬水找零();
        int[] bills = {5, 5, 5, 10, 20};

        System.out.println("顾客按账单" + Arrays.toString(bills) + "排队购买你出售的5美元一杯的柠檬水，你" + (lc.lemonadeChange(bills) ? "能" : "不能") + "正确找零");
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;

        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) {
                    return false;
                } else {
                    five--;
                    ten++;
                }
            } else if (bill == 20) {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

}
