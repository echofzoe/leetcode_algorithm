package algorithm.leetcode.dfsAndBfs.bfs;

import java.util.*;

/**
 * 打开转盘锁
 * <P>https://leetcode-cn.com/problems/open-the-lock/</P>
 *
 * @author echofzoe
 * @since 2021.6.28
 */
public class Lc_752_打开转盘锁 {

    public static void main(String[] args) {
        Lc_752_打开转盘锁 lc = new Lc_752_打开转盘锁();

        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";

        System.out.println("你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。\n" +
                "锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。\n" +
                "列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。\n" +
                "字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。\n");
        System.out.println("输入：deadends = " + Arrays.toString(deadends) + ", target = " + target);
        System.out.println("输出：" + lc.openLock1(deadends, target));  // 6
    }

    // bfs
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) return 0;

        Set<String> d = new HashSet<>();
        Collections.addAll(d, deadends);

        if (d.contains("0000")) return -1;

        Set<String> s = new HashSet<>() {{
            add("0000");
        }};

        Queue<String> q = new LinkedList<>() {{
            add("0000");
        }};

        int res = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            res++;

            for (int i = 0; i < size; i++) {
                String cur = q.poll();

                for (String next : getNext(cur)) {
                    if (!d.contains(next) && !s.contains(next)) {
                        if (next.equals(target)) return res;

                        q.add(next);
                        s.add(next);
                    }
                }
            }
        }

        return -1;
    }

    private List<String> getNext(String cur) {
        List<String> res = new ArrayList<>();
        char[] cs = cur.toCharArray();

        for (int i = 0; i < 4; i++) {
            char num = cs[i];

            // num - 1
            cs[i] = preNum(num);
            res.add(new String(cs));

            // num + 1
            cs[i] = nextNum(num);
            res.add(new String(cs));

            // backtrace
            cs[i] = num;
        }

        return res;
    }

    private char preNum(char num) {
        return num == '0' ? '9' : (char) (num - 1);
    }

    private char nextNum(char num) {
        return num == '9' ? '0' : (char) (num + 1);
    }

    // 双向 bfs
    public int openLock1(String[] deadends, String target) {
        if (target.equals("0000")) return 0;

        String start = "0000", end = target;

        Queue<String> q1 = new LinkedList<>();  // 正向队列
        Queue<String> q2 = new LinkedList<>();  // 反向队列
        q1.add(start);
        q2.add(end);

        Set<String> d = new HashSet<>();
        Collections.addAll(d, deadends);

        Map<String, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
        m1.put(start, 0);
        m2.put(end, 0);

        if (d.contains(start)) return -1;

        while (!q1.isEmpty() && !q2.isEmpty()) {
            int res = -1;

            res = q1.size() < q2.size() ? update(q1, m1, m2, d) : update(q2, m2, m1, d);

            if (res != -1) return res;
        }

        return -1;
    }

    private int update(Queue<String> q, Map<String, Integer> m1, Map<String, Integer> m2, Set<String> d) {
        int size = q.size();

        for (int i = 0; i < size; i++) {
            String cur = q.poll();

            for (int j = 0; j < 4; j++) {
                for (String next : getNext(cur)) {
                    if (m1.containsKey(next) || d.contains(next)) continue;

                    if (m2.containsKey(next)) return m1.get(cur) + 1 + m2.get(next);

                    q.add(next);
                    m1.put(next, m1.getOrDefault(cur, 0) + 1);
                }
            }
        }

        return -1;
    }

}
