package algorithm.leetcode.hash;

import java.util.*;

/**
 * 点菜展示表
 * <P>https://leetcode-cn.com/problems/display-table-of-food-orders-in-a-restaurant/</P>
 *
 * @author echofzoe
 * @since 2021.7.6
 */
public class Lc_1418_点菜展示表 {

    public static void main(String[] args) {
        Lc_1418_点菜展示表 lc = new Lc_1418_点菜展示表();

        List<List<String>> orders = lc.listInitialized();

        System.out.println("给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说，orders[i] = [customerNamei, tableNumberi, foodItemi]，\n" +
                "其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。\n" +
                "请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。\n" +
                "接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。\n" +
                "注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。\n");
        System.out.println("输入：" + orders.toString());
        System.out.println("输出：" + lc.displayTable(orders).toString());
    }

    // hash
    // - 时间复杂度 O(T+NlogN+MlogM+MN) 其中T是数组orders的长度，N是数据表的列数（即餐品的数量），M是数据表的行数（即餐桌的数量）
    // -- 遍历orders需花费O(T)，对餐品名和餐桌号进行排序分别需要O(NlogN)和O(MlogM)，将数据填入表格，需要O(MN)
    // - 空间复杂度 O(T+N+M) 不计答案所需的空间
    public List<List<String>> displayTable(List<List<String>> orders) {
        // m 记录每张餐桌对应的菜品以及菜品对应的数量
        TreeMap<String, Map<String, Integer>> m = new TreeMap<>(Comparator.comparingInt(Integer::valueOf));
        // set 记录全部的无重复的菜品名称
        Set<String> set = new TreeSet<>();

        for (List<String> order : orders) {
            String tableNumber = order.get(1);
            String foodItem = order.get(2);

            Map<String, Integer> m1 = m.getOrDefault(tableNumber, new HashMap<>());
            m1.put(foodItem, m1.getOrDefault(foodItem, 0) + 1);
            m.put(tableNumber, m1);

            set.add(foodItem);
        }

        List<List<String>> res = new ArrayList<>();

        // 生成 点菜展示表 的标题行
        int n = set.size();
        List<String> title = new ArrayList<>(n + 1);
        title.add("Table");
        int idx = 1;
        Map<String, Integer> food2idx = new HashMap<>();  // 记录标题行中各菜品所在的下标（从1开始，因为位置0被"Table"占了）
        for (String s : set) {
            title.add(s);
            food2idx.put(s, idx++);
        }
        res.add(title);

        // 生成 点菜展示表 中各餐桌的数据
        for (Map.Entry<String, Map<String, Integer>> entry : m.entrySet()) {
            String tableNumber = entry.getKey();
            Map<String, Integer> m1 = entry.getValue();

            String[] ss = new String[n + 1];
            ss[0] = tableNumber;

            for (String foodItem : set) {
                int cnt = m1.getOrDefault(foodItem, 0);
                idx = food2idx.get(foodItem);
                ss[idx] = cnt == 0 ? "0" : String.valueOf(cnt);
            }

            res.add(Arrays.asList(ss));
        }

        return res;
    }

    private List<List<String>> listInitialized() {
        List<String> l1 = new ArrayList<>() {{
            add("David");
            add("3");
            add("Ceviche");
        }};
        List<String> l2 = new ArrayList<>() {{
            add("Corina");
            add("10");
            add("Beef Burrito");
        }};
        List<String> l3 = new ArrayList<>() {{
            add("David");
            add("3");
            add("Fried Chicken");
        }};
        List<String> l4 = new ArrayList<>() {{
            add("Carla");
            add("5");
            add("Water");
        }};
        List<String> l5 = new ArrayList<>() {{
            add("Carla");
            add("5");
            add("Ceviche");
        }};
        List<String> l6 = new ArrayList<>() {{
            add("Rous");
            add("3");
            add("Ceviche");
        }};
        return new ArrayList<>() {{
            add(l1);
            add(l2);
            add(l3);
            add(l4);
            add(l5);
            add(l6);
        }};
    }

}
