package algorithm.leetcode.sort;

import java.util.*;

/**
 * 前K个高频单词
 * <P>https://leetcode-cn.com/problems/top-k-frequent-words/</P>
 *
 * @author echofzoe
 * @since 2021
 */
public class Lc_692_前K个高频单词 {

    public static void main(String[] args) {
        Lc_692_前K个高频单词 lc = new Lc_692_前K个高频单词();

        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k = 4;

        System.out.println("单词列表 " + Arrays.toString(words) + " 中，前 " + k + " 个出现次数最多的单词是 " + lc.topKFrequent1(words, k));  // ["the", "is", "sunny", "day"]
    }

    // hash + sort
    // - 时间复杂度 O(L×N+L×MlogM)，其中N表示给定字符串序列的长度，L表示字符串的平均长度，M表示实际字符串种类数。我们需要L*N的时间将字符串插入到哈希表中，以及L*MlogM的时间完成字符串比较（最坏情况下所有字符串出现频率都相同，我们需要将它们两两比较）
    // - 空间复杂度 O(L*M)，其中L表示字符串的平均长度，M表示实际字符串种类数。哈希表和生成的排序数组空间占用均为O(L*M)
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            res.add(entry.getKey());
        }

        res.sort((a, b) -> map.get(a).equals(map.get(b)) ? a.compareTo(b) : map.get(b) - map.get(a));

        return res.subList(0, k);
    }

    // 小根堆
    // - 时间复杂度 O(L*N + M*L*logK) 其中N表示给定字符串序列的长度，M表示实际字符串种类数，L表示字符串的平均长度。我们需要L*N的时间将字符串插入到哈希表中，以及每次插入元素到优先队列中都需要L*logK的时间，共需要插入M次
    // - 空间复杂度 O(L*(M+K)) 其中L表示字符串的平均长度，M表示实际字符串种类数。哈希表空间占用为O(L*M)，优先队列空间占用为O(L*K)
    public List<String> topKFrequent1(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        Queue<String> pq = new PriorityQueue<>((a, b) -> map.get(a).equals(map.get(b)) ? b.compareTo(a) : map.get(a) - map.get(b));

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry.getKey());
            if (pq.size() > k) pq.poll();
        }

        List<String> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(0, pq.poll());
        }

        return res;
    }

}
