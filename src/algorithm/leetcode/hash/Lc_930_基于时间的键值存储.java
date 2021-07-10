package algorithm.leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基于时间的键值存储
 * <P>https://leetcode-cn.com/problems/time-based-key-value-store/</P>
 *
 * @author echofzoe
 * @since 2021.7.10
 */
public class Lc_930_基于时间的键值存储 {

    public static void main(String[] args) {
        Lc_930_基于时间的键值存储 lc = new Lc_930_基于时间的键值存储();

        System.out.println("创建一个基于时间的键值存储类 TimeMap，它支持下面两个操作：\n" +
                "1. set(string key, string value, int timestamp)\n" +
                "  - 存储键 key、值 value，以及给定的时间戳 timestamp。\n" +
                "2. get(string key, int timestamp)\n" +
                "  - 返回先前调用 set(key, value, timestamp_prev) 所存储的值，其中 timestamp_prev <= timestamp。\n" +
                "  - 如果有多个这样的值，则返回对应最大的  timestamp_prev 的那个值。\n" +
                "  - 如果没有值，则返回空字符串（\"\"）。\n" +
                "注：所有 TimeMap.set 操作中的时间戳 timestamps 都是严格递增的\n");

        TimeMap kv = new TimeMap();
        kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1
        System.out.println(kv.get("foo", 1));  // 输出 "bar"
        System.out.println(kv.get("foo", 3)); // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"）
        kv.set("foo", "bar2", 4);
        System.out.println(kv.get("foo", 4)); // 输出 "bar2"
        System.out.println(kv.get("foo", 5)); // 输出 "bar2"
    }

}

class TimeMap {

    class Pair implements Comparable<Pair> {
        int timestamp;
        String v;

        public Pair(int t, String v) {
            this.timestamp = t;
            this.v = v;
        }

        public int hashCode() {
            return timestamp + v.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof Pair) {
                Pair p2 = (Pair) obj;
                return this.timestamp == p2.timestamp && this.v.equals(p2.v);
            }
            return false;
        }

        public int compareTo(Pair p2) {
            return this.timestamp == p2.timestamp ? this.v.compareTo(p2.v) : this.timestamp - p2.timestamp;
        }
    }

    Map<String, List<Pair>> m;

    /** Initialize your data structure here. */
    public TimeMap() {
        m = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        m.computeIfAbsent(key, k -> new ArrayList<>()).add(new Pair(timestamp, value));
    }

    public String get(String key, int timestamp) {
        List<Pair> pairs = m.getOrDefault(key, new ArrayList<>());
        if (pairs.isEmpty()) return "";

        int n = pairs.size();
        int l = 0, m, r = n - 1;
        while (l < r) {
            m = l + (r - l + 1) / 2;
            if (pairs.get(m).timestamp > timestamp) r = m - 1;
            else l = m;
        }

        return pairs.get(l).timestamp <= timestamp ? pairs.get(l).v : "";
    }
}
