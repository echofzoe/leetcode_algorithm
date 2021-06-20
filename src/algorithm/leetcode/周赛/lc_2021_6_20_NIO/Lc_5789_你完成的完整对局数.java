package algorithm.leetcode.周赛.lc_2021_6_20_NIO;

/**
 * 你完成的完整对局数
 * <P>https://leetcode-cn.com/problems/the-number-of-full-rounds-you-have-played/</P>
 *
 * @author echofzoe
 * @since 20021.6.20
 */
public class Lc_5789_你完成的完整对局数 {

    public static void main(String[] args) {
        Lc_5789_你完成的完整对局数 lc = new Lc_5789_你完成的完整对局数();

        String startTime = "20:01", finishTime = "06:44";

        System.out.println("一款新的在线电子游戏在近期发布，在该电子游戏中，以 刻钟 为周期规划若干时长为 15 分钟 的游戏对局。这意味着，在 HH:00、HH:15、HH:30 和 HH:45 ，将会开始一个新的对局，其中 HH 用一个从 00 到 23 的整数表示。游戏中使用 24 小时制的时钟 ，所以一天中最早的时间是 00:00 ，最晚的时间是 23:59 。\n" +
                "给你两个字符串 startTime 和 finishTime ，均符合 \"HH:MM\" 格式，分别表示你 进入 和 退出 游戏的确切时间，请计算在整个游戏会话期间，你完成的 完整对局的对局数 。\n" +
                "  - 例如，如果 startTime = \"05:20\" 且 finishTime = \"05:59\" ，这意味着你仅仅完成从 05:30 到 05:45 这一个完整对局。而你没有完成从 05:15 到 05:30 的完整对局，因为你是在对局开始后进入的游戏；同时，你也没有完成从 05:45 到 06:00 的完整对局，因为你是在对局结束前退出的游戏。\n" +
                "如果 finishTime 早于 startTime ，这表示你玩了个通宵（也就是从 startTime 到午夜，再从午夜到 finishTime）。\n" +
                "假设你是从 startTime 进入游戏，并在 finishTime 退出游戏，请计算并返回你完成的 完整对局的对局数 。\n");
        System.out.println("输入：startTime = " + "\"" + startTime + "\", finishTime = \"" + finishTime + "\"");
        System.out.println("输出：" + lc.numberOfRounds(startTime, finishTime));
    }

    // 数学（转化为分钟计算） - 时间复杂度 O(1) - 空间复杂度 O(1)
    public int numberOfRounds(String startTime, String finishTime) {
        int t0 = 60 * Integer.parseInt(startTime.substring(0, 2)) + Integer.parseInt(startTime.substring(3, 5));
        int t1 = 60 * Integer.parseInt(finishTime.substring(0, 2)) + Integer.parseInt(finishTime.substring(3, 5));

        if (t1 < t0) t1 += 1440;

        // 第一个小于等于 finishTime 的完整对局的结束时间
        t1 = t1 / 15 * 15;

        return (t1 - t0) / 15;
    }

    // 模拟（分情况讨论） - 时间复杂度 O(1) - 空间复杂度 O(1)
    public int numberOfRounds1(String startTime, String finishTime) {
        int startHour = Integer.parseInt(startTime.substring(0, 2)), startMin = Integer.parseInt(startTime.substring(3, 5));
        int endHour = Integer.parseInt(finishTime.substring(0, 2)), endMin = Integer.parseInt(finishTime.substring(3, 5));

        if (startHour > endHour || (startHour == endHour && startMin > endMin)) {
            // 玩了个通宵的情况，拆成两个正常情况计算
            return calc(startTime, "24:00") + calc("00:00", finishTime);
        } else {
            return calc(startTime, finishTime);
        }
    }

    private int calc(String startTime, String finishTime) {
        if (startTime.equals(finishTime)) return 0;

        int res = 0;

        int startHour = Integer.parseInt(startTime.substring(0, 2)), startMin = Integer.parseInt(startTime.substring(3, 5));
        int endHour = Integer.parseInt(finishTime.substring(0, 2)), endMin = Integer.parseInt(finishTime.substring(3, 5));

        // 完整的一个小时内，可以玩4轮游戏
        // a 表示游戏从第几轮开始（如果b>0，说明玩家没赶上第a轮游戏，所以玩家只能从a+1轮游戏开始玩）
        // c 表示从完整的一个小时内的0分钟开始玩，能玩多少轮游戏
        // 所以 c - a 就表示了，玩家在这个小时内玩了几轮游戏
        int a = startMin / 15, b = startMin % 15, c = endMin / 15;
        if (b > 0) a += 1;

        if (startHour == endHour) {
            // 游戏总时长在一个小时内，直接用上面分析的 c - a 即可表示游戏总轮数
            res += c - a;
        } else {
            // 游戏时长在两个小时内，则可拆分为 完整的一小时（4轮） + 不完整的一小时（c - a 轮）
            res += 4 - a + c;

            // 游戏时长 > 两小时，则在上式的基础上再加上多出来的完整的那几个小时的轮数
            if (endHour - startHour > 1) res += (endHour - startHour - 1) * 4;
        }

        return res;
    }

}
