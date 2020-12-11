package algorithm.leetcode.dpAndGreedy;

import java.util.Deque;
import java.util.LinkedList;

public class Lc_649_Dota2参议院 {

    // Dota2 参议院
    // https://leetcode-cn.com/problems/dota2-senate/

    public static void main(String[] args) {
        Lc_649_Dota2参议院 lc = new Lc_649_Dota2参议院();
        String senate = "RDD";

        System.out.println("以轮为基础的投票过程\"" + senate + "\"结束后，获胜的是\"" + lc.predictPartyVictory(senate) + "\"");
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public String predictPartyVictory(String senate) {
        int n = senate.length();

        Deque<Integer> radiant = new LinkedList<>();
        Deque<Integer> dire = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') radiant.offerFirst(i);
            else dire.offerFirst(i);
        }

        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int radiantIndex = radiant.poll(), direIndex = dire.poll();
            if (radiantIndex < direIndex) radiant.offerLast(radiantIndex + n);
            else dire.offerLast(direIndex + n);
        }

        return radiant.isEmpty() ? "Dire" : "Radiant";
    }

}
