package algorithm.leetcode.周赛.lc_2021_4_5_LCCUP21_春季赛_个人赛;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 魔塔游戏
 * <p>小扣当前位于魔塔游戏第一层，共有 N 个房间，编号为 0 ~ N-1。每个房间的补血道具/怪物对于血量影响记于数组 nums，其中正数表示道具补血数值，即血量增加对应数值；负数表示怪物造成伤害值，即血量减少对应数值；0 表示房间对血量无影响</p>
 * <p>小扣初始血量为 1，且无上限。假定小扣原计划按房间编号升序访问所有房间补血/打怪，为保证血量始终为正值，小扣需对房间访问顺序进行调整，每次仅能将一个怪物房间（负数的房间）调整至访问顺序末尾。请返回小扣最少需要调整几次，才能顺利访问所有房间。若调整顺序也无法访问完全部房间，请返回 -1</p>
 */
public class lccup_03_魔塔游戏 {

    public static void main(String[] args) {
        lccup_03_魔塔游戏 lc = new lccup_03_魔塔游戏();

        int[] nums = {100, 100, 100, -250, -60, -140, -50, -50, 100, 150};

        System.out.println("对关卡房间调整" + lc.magicTower(nums) + "次后，才能通关");
    }

    public int magicTower(int[] nums) {
        long sum = 0;
        for (int i : nums) sum += i;
        if (sum < 0) return -1;

        sum = 0;
        Queue<Integer> neg = new PriorityQueue<>();

        int res = 0;
        for (int i : nums) {
            sum += i;

            if (i < 0) {
                neg.offer(i);

                while (sum < 0) {
                    sum -= neg.poll();
                    res += 1;
                }
            }
        }

        return res;
    }

}
