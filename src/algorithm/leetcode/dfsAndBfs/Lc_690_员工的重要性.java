package algorithm.leetcode.dfsAndBfs;

import java.util.*;

/**
 * 员工的重要性
 * <P>https://leetcode-cn.com/problems/employee-importance/</P>
 *
 * @author echofzoe
 * @since 2021.5.14
 */
public class Lc_690_员工的重要性 {

    public static void main(String[] args) {
        Lc_690_员工的重要性 lc = new Lc_690_员工的重要性();

        Employee e1 = new Employee(1, 5);
        e1.subordinates = new ArrayList<>() {{
            add(2);
            add(3);
        }};
        Employee e2 = new Employee(2, 3);
        Employee e3 = new Employee(3, 3);

        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);

        System.out.println("给定一个保存员工信息的数据结构 Employee，它包含了员工 唯一的 id ，重要度 和 直系下属的 id 。\n" +
                "比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。那么员工 1 的数据结构是 [1, 15, [2]] ，员工 2的 数据结构是 [2, 10, [3]] ，员工 3 的数据结构是 [3, 5, []] 。注意虽然员工 3 也是员工 1 的一个下属，但是由于 并不是直系 下属，因此没有体现在员工 1 的数据结构中。\n" +
                "现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。");
        System.out.println("输入：" + employees);
        System.out.println("输出：" + lc.getImportanceBFS(employees, 1));  // 11
    }

    // DFS - 时间复杂度 O(N) - 空间复杂度 O(N)
    private Map<Integer, Employee> map;
    private int res;

    public int getImportanceDFS(List<Employee> employees, int id) {
        map = new HashMap<>();
        res = 0;

        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }

        dfs(id);

        return res;
    }

    private void dfs(int id) {
        Employee e = map.get(id);
        res += e.importance;

        if (e.subordinates == null || e.subordinates.size() == 0) return;

        for (int eid : e.subordinates) {
            dfs(eid);
        }
    }

    // BFS - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int getImportanceBFS(List<Employee> employees, int id) {
        map = new HashMap<>();
        res = 0;

        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }

        Deque<Employee> d = new LinkedList<>();
        d.offerLast(map.get(id));

        while (!d.isEmpty()) {
            Employee poll = d.pollFirst();
            res += poll.importance;

            if (poll.subordinates == null || poll.subordinates.size() == 0) continue;
            for (int pid : poll.subordinates) {
                d.offerLast(map.get(pid));
            }
        }

        return res;
    }

}

// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;

    public Employee(int id, int importance) {
        this.id = id;
        this.importance = importance;
    }

    @Override
    public String toString() {
        return "[" + id + ", " + importance + ", " + subordinates + "]";
    }
};
