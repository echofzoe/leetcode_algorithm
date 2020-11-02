package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lc_139_单词拆分 {

    // 单词拆分
    // https://leetcode-cn.com/problems/word-break/

    public static void main(String[] args) {
        Lc_139_单词拆分 lc = new Lc_139_单词拆分();
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");

        System.out.println(s + (lc.wordBreak(s, wordDict) ? "可以" : "不可以") + "被空格拆分为一个或多个在字典" + Arrays.toString(wordDict.toArray()) + "中出现的单词");
    }

    // dp - 时间复杂度 O(N^2) Java中为O(N^3), 因为substring方法 - 空间复杂度 O(N^2)
    public boolean wordBreak(String s, List<String> wordDict) {

        int len = s.length(), maxLengthInWordDict = 0;

        // dp[i] 表示字符串s的前i个字符组成的子串s.substring(0, i)能否被拆分成若干个字典中出现的单词
        // 状态转移方程 - dp[i] = dp[j] && check(s[j..i - 1])
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        Set<String> wordDictSet = new HashSet<>();
        for (String val : wordDict) {
            wordDictSet.add(val);
            maxLengthInWordDict = Math.max(maxLengthInWordDict, val.length());
        }

        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = i - 1; j >= 0 && j >= i - maxLengthInWordDict; j--) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

}
