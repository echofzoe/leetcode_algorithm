package algorithm.leetcode.dfsAndBfs.bfs;

import java.util.*;

/**
 * 单词接龙
 * <P>https://leetcode-cn.com/problems/word-ladder/</P>
 *
 * @author echofzoe
 * @since 2021.6.28
 */
public class Lc_127_单词接龙 {

    public static void main(String[] args) {
        Lc_127_单词接龙 lc = new Lc_127_单词接龙();

        String beginWord = "hit", endWord = "cog";
        String[] tmp = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = Arrays.asList(tmp);

        System.out.println("字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：\n" +
                "  - 序列中第一个单词是 beginWord 。\n" +
                "  - 序列中最后一个单词是 endWord 。\n" +
                "  - 每次转换只能改变一个字母。\n" +
                "  - 转换过程中的中间单词必须是字典 wordList 中的单词。\n" +
                "给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。\n");
        System.out.println("输入：beginWord = " + "\"" + beginWord + "\", endWord = " + "\"" + endWord + "\", wordList = " + wordList);
        System.out.println("输出：" + lc.ladderLength(beginWord, endWord, wordList));  // 5
    }

    // 双向 bfs
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) return 0;

        Set<String> s = new HashSet<>();
        for (String str : wordList) s.add(str);

        if (!s.contains(endWord)) return 0;

        Queue<String> q1 = new LinkedList<>(), q2 = new LinkedList<>();
        q1.add(beginWord);
        q2.add(endWord);

        Map<String, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
        m1.put(beginWord, 0);
        m2.put(endWord, 0);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            int res = -1;
            if (q1.size() <= q2.size()) {
                res = update(q1, m1, m2, s);
            } else {
                res = update(q2, m2, m1, s);
            }
            if (res != -1) return res + 1;
        }

        return 0;
    }

    private int update(Queue<String> q, Map<String, Integer> m1, Map<String, Integer> m2, Set<String> s) {
        int size = q.size();

        for (int i = 0; i < size; i++) {
            String cur = q.poll();
            int n = cur.length();

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 26; k++) {
                    String replace = cur.substring(0, j) + String.valueOf((char) ('a' + k)) + cur.substring(j + 1);

                    if (s.contains(replace)) {
                        if (m1.containsKey(replace)) continue;

                        if (m2.containsKey(replace)) return m1.get(cur) + 1 + m2.get(replace);

                        q.add(replace);
                        m1.put(replace, m1.getOrDefault(cur, 0) + 1);
                    }
                }
            }
        }

        return -1;
    }

}
