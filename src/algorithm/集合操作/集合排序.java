package algorithm.集合操作;

import org.junit.Test;

import java.util.*;

/**
 * Java 各集合类的排序操作合集
 *
 * @author echofzoe
 * @since 2021.7.10
 */
public class 集合排序 {

    @Test
    public void treeMapSort() {
        // 按 key 降序
        System.out.println("按 key 降序");
        Map<String, String> tm = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.compareTo(s1);
            }
        });

        tm.put("a", "d");
        tm.put("b", "e");
        tm.put("c", "f");
        Set<String> keySet = tm.keySet();
        for (String key : keySet) {
            System.out.println(key + ":" + tm.get(key));
        }

        // 按 value 降序
        System.out.println("按 value 降序");
        List<Map.Entry<String, String>> mappingList = new ArrayList<>(tm.entrySet());
        mappingList.sort(new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (Map.Entry<String, String> mapping : mappingList) {
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
        }
    }
}
