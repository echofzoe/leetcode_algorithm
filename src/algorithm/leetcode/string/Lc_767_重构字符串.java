package algorithm.leetcode.string;

import java.util.PriorityQueue;

public class Lc_767_重构字符串 {

    // 重构字符串
    // https://leetcode-cn.com/problems/reorganize-string/

    public static void main(String[] args) {
        Lc_767_重构字符串 lc = new Lc_767_重构字符串();
        String s = "aab";

        System.out.print("字符串\"" + s + "\"");
        String output = lc.reorganizeStringHeap(s);
        if (output.equals("")) {
            System.out.println("不能通过重新排布其中的字母，使得两相邻的字符不同");
        } else {
            System.out.println("可以通过重新排布其中的字母，使得两相邻的字符不同，结果是" + output);
        }
    }

    // 基于大根堆的贪心 - 时间复杂度 O(nlog∣Σ∣+∣Σ∣) - 空间复杂度 O(∣Σ∣) Σ为
    public String reorganizeStringHeap(String S) {
        int n = S.length();
        if (n < 2) return S;

        int[] counts = new int[26];
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            char c = S.charAt(i);
            counts[c - 97]++;
            maxCount = Math.max(maxCount, counts[c - 97]);
        }

        // 边界值：
        // - n是奇数时，由于共有(n+1)/2个偶数下标，因此每个字母的出现次数都不能超过(n+1)/2次，否则出现次数最多的字母一定会出现相邻
        // - n是偶数时，在整数除法下满足n/2和(n+1)/2相等，因此可以合并n是偶数与n是奇数的情况：如果可以重新排布成相邻的字母都不相同的字符串，每个字母最多出现(n+1)/2次
        if (maxCount > (int) Math.ceil(n / 2.0)) return "";

        PriorityQueue<Character> queue = new PriorityQueue<>((o1, o2) -> counts[o2 - 'a'] - counts[o1 - 'a']);
        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) {
                queue.offer(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (queue.size() > 1) {
            char c1 = queue.poll();
            char c2 = queue.poll();
            sb.append(c1).append(c2);

            int idx1 = c1 - 'a', idx2 = c2 - 'a';
            counts[idx1]--;
            counts[idx2]--;
            if (counts[idx1] > 0) queue.offer(c1);
            if (counts[idx2] > 0) queue.offer(c2);
        }

        if (queue.size() > 0) sb.append(queue.poll());

        return sb.toString();
    }

    // 基于计数的贪心 - 时间复杂度 O(n+∣Σ∣) - 空间复杂度 O(∣Σ∣) Σ为字符串的长度
    public String reorganizeStringCount(String S) {
        int n = S.length();
        if (n < 2) return S;

        int[] counts = new int[26];
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            char c = S.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }

        if (maxCount > (n + 1) / 2) return "";

        char[] res = new char[n];
        int evenIdx = 0, oddIdx = 1;
        int half = n / 2;
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            while (counts[i] > 0 && counts[i] <= half && oddIdx < n) {
                res[oddIdx] = c;
                counts[i]--;
                oddIdx += 2;
            }
            while (counts[i] > 0) {
                res[evenIdx] = c;
                counts[i]--;
                evenIdx += 2;
            }
        }

        return new String(res);
    }

}
