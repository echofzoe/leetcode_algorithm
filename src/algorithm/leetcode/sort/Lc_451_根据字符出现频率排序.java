package algorithm.leetcode.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 根据字符出现频率排序
 * <P>https://leetcode-cn.com/problems/sort-characters-by-frequency/</P>
 *
 * @author echofzoe
 * @since 2021.7.3
 */
public class Lc_451_根据字符出现频率排序 {

    public static void main(String[] args) {
        Lc_451_根据字符出现频率排序 lc = new Lc_451_根据字符出现频率排序();

        String s = "Aabb";

        System.out.println("给定一个字符串，请将字符串里的字符按照出现的频率降序排列。\n");
        System.out.println("输入：s = \"" + s + "\"");
        System.out.println("输出：" + lc.frequencySort(s));  // bbaA or bbAa
    }

    // 哈希 + 大根堆 - 时间复杂度 O(N + K*logK) K为字符串包含的不同字符的个数，将字符按出现频率排序需要O(K*logK)的时间  - 空间复杂度 O(N + K)
    public String frequencySort(String s) {
        Map<Character, Integer> m = new HashMap<>();
        for (char c : s.toCharArray()) {
            m.put(c, m.getOrDefault(c, 0) + 1);
        }

        Queue<Character> q = new PriorityQueue<>((a, b) -> m.get(b) - m.get(a));

        for (char c : s.toCharArray()) {
            q.offer(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            sb.append(q.poll());
        }

        return sb.toString();
    }

    // 桶排序 - 时间复杂度 O() - 空间复杂度 O()
    public String frequencySort1(String s) {
        int maxFreq = 0;
        Map<Character, Integer> m = new HashMap<>();
        for (char c : s.toCharArray()) {
            int curFreq = m.getOrDefault(c, 0) + 1;
            m.put(c, curFreq);
            maxFreq = Math.max(maxFreq, curFreq);
        }

        StringBuilder[] bucket = new StringBuilder[maxFreq + 1];
        for (int i = 0; i <= maxFreq; i++) {
            bucket[i] = new StringBuilder();
        }

        for (Map.Entry<Character, Integer> e : m.entrySet()) {
            char c = e.getKey();
            int freq = e.getValue();
            bucket[freq].append(c);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = maxFreq; i > 0; i--) {
            StringBuilder b = bucket[i];
            int size = b.length();
            for (int j = 0; j < size; j++) {
                char c = b.charAt(j);
                sb.append(String.valueOf(c).repeat(i));
            }
        }

        return sb.toString();
    }

}
