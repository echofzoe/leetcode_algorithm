package algorithm.leetcode.dfsAndBfs;

import java.util.*;

public class Lc_127_单词接龙 {

    // 单词接龙
    // https://leetcode-cn.com/problems/word-ladder/

    public static void main(String[] args) {
        Lc_127_单词接龙 lc = new Lc_127_单词接龙();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}.clone());

        System.out.println("从" + beginWord + "到" + endWord + "的最短转换序列的长度是" + lc.ladderLength(beginWord, endWord, wordList));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 1.先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        wordSet.remove(beginWord);

        // 2.图的广度优先遍历，必须使用队列和表示是否访问过的 visited 哈希表
        Queue<String> queue = new LinkedList<>() {{
            offer(beginWord);
        }};
        Set<String> visited = new HashSet<>() {{
            add(beginWord);
        }};

        // 3.开始广度优先遍历，包含起点，因此初始化的时候步数为 1
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            // 遍历当前队列中的单词
            for (int i = 0; i < size; i++) {
                String curWord = queue.poll();
                // 如果curWord经过修改一个字符能够和endWord相同，则step+1
                if (changeWordEveryoneLetter(curWord, endWord, wordSet, queue, visited)) {
                    return ++step;
                }
            }

            step++;
        }
        return 0;
    }

    private boolean changeWordEveryoneLetter(String curWord, String endWord, Set<String> wordSet, Queue<String> queue, Set<String> visited) {
        char[] chars = curWord.toCharArray();
        for (int i = 0; i < endWord.length(); i++) {
            char tmp = chars[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (j == tmp) {
                    continue;
                }
                chars[i] = j;
                String nextWord = String.valueOf(chars);
                if (wordSet.contains(nextWord)) {
                    if (nextWord.equals(endWord)) {
                        return true;
                    }
                    if (!visited.contains(nextWord)) {
                        queue.add(nextWord);
                        visited.add(nextWord);
                    }
                }
            }
            chars[i] = tmp;
        }
        return false;
    }

}
