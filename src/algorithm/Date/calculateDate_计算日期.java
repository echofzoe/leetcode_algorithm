package algorithm.Date;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class calculateDate_计算日期 {

    // 计算日期
    // - 给定一个天数 x,和当前的年curY,月curM,日curD,计算当前日期加上天数 x 后的日期

    public static void main(String[] args) {
        calculateDate_计算日期 main = new calculateDate_计算日期();

        System.out.println("2020.2.21 加上10天后的日期是 " + main.calculateDate(10, 2020, 2, 21));
        System.out.println("2017.7.20 加上5天后的日期是 " + main.calculateDate(5, 2017, 7, 20));
        System.out.println("2008.2.8 加上25天后的日期是 " + main.calculateDate(25, 2008, 2, 8));
    }

    Set<Integer> monthOf31Days = new HashSet<>() {{
        add(1);
        add(3);
        add(5);
        add(7);
        add(8);
        add(10);
        add(12);
    }};

    String calculateDate(int x, int curY, int curM, int curD) {
        // 31天 / 月
        if (monthOf31Days.contains(curM)) {
            // 月份进位
            if (curD + x > 31) {
                // 年份进位
                if (curM + 1 > 12) {
                    curY++;
                    curM = 1;
                    curD = curD + x - 31;
                } else {
                    curM++;
                    curM = 1;
                    curD = curD + x - 31;
                }
            } else {
                curD += x;
            }
        }
        // 2 月
        else if (curM == 2) {
            // 闰年
            if ((curY % 4 == 0 && curY % 100 != 0) || curY % 400 != 0) {
                // 月份进位
                if (curD + x > 29) {
                    curM++;
                    curD = curD + x - 29;
                } else {
                    curD += x;
                }
            } else {
                // 月份进位
                if (curD + x > 28) {
                    curM++;
                    curD = curD + x - 28;
                } else {
                    curD += x;
                }
            }
        }
        // 30天 / 月
        else {
            // 月份进位
            if (curD + x > 30) {
                // 年份进位
                if (curM + 1 > 12) {
                    curY++;
                    curM = 1;
                    curD = curD + x - 30;
                } else {
                    curM++;
                    curD = curD + x - 30;
                }
            } else {
                curD += x;
            }
        }

        return curY + "." + curM + "." + curD;
    }
}
