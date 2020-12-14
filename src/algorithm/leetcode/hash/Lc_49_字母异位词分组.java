package algorithm.leetcode.hash;

import java.util.*;

public class Lc_49_字母异位词分组 {

    // 字母异位词分组
    // https://leetcode-cn.com/problems/group-anagrams/

    public static void main(String[] args) {
        Lc_49_字母异位词分组 lc = new Lc_49_字母异位词分组();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println("字符串数组" + Arrays.toString(strs) + "按字母异位词分组的结果是" + lc.groupAnagramsHash(strs));
    }

    // 哈希 - 时间复杂度 O(N*K*logK)，其中N是strs中的字符串的数量，K是strs中的字符串的的最大长度 - 空间复杂度 O(N*K)
    public List<List<String>> groupAnagramsHash(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);

            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());

            list.add(str);
            map.put(key, list);
        }

        for (Map.Entry<String, List<String>> item : map.entrySet()) {
            res.add(item.getValue());
        }

        return res;
    }

    // 计数 - 时间复杂度 O() - 空间复杂度 O()
    public List<List<String>> groupAnagramsCount(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            int[] counts = new int[26];
            int n = str.length();
            for (int i = 0; i < n; i++) {
                counts[str.charAt(i) - 97]++;
            }

            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append(i + 97);
                    sb.append(counts[i]);
                }
            }

            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());

            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }

}
