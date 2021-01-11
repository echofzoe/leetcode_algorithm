package algorithm.leetcode.graph;

import algorithm.leetcode.utils.UnionFindSet;

import java.util.*;

public class Lc_1202_交换字符串中的元素 {

    // 交换字符串中的元素
    // https://leetcode-cn.com/problems/smallest-string-with-swaps/

    public static void main(String[] args) {
        Lc_1202_交换字符串中的元素 lc = new Lc_1202_交换字符串中的元素();
        String s = "dcab";
        List<List<Integer>> pairs = new ArrayList<>() {{
            add(new ArrayList<>(Arrays.asList(0, 3)));
            add(new ArrayList<>(Arrays.asList(1, 2)));
            add(new ArrayList<>(Arrays.asList(0, 2)));
        }};

        System.out.println("字符串\"" + s + "\"在索引对" + pairs.toString() + "中经过任意次交换后得到的字典序最小的字符串是\"" + lc.smallestStringWithSwaps(s, pairs) + "\"");
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();

        UnionFindSet ufs = new UnionFindSet(n);

        // 联通
        for (List<Integer> pair : pairs) {
            ufs.union(pair.get(0), pair.get(1));
        }

        // 映射 代表元 和 同属于一个代表元的字符列表
        Map<Integer, List<Character>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int parent = ufs.find(i);

            if (!map.containsKey(parent)) {
                map.put(parent, new ArrayList<>());
            }

            map.get(parent).add(s.charAt(i));
        }

        for (Map.Entry<Integer, List<Character>> entry : map.entrySet()) {
            entry.getValue().sort(Comparator.comparingInt(x -> x));
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            int cur = ufs.find(i);
            List<Character> list = map.get(cur);
            sb.append(list.remove(list.size() - 1));
        }

        return sb.toString();
    }

}
