package algorithm.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lc_406_根据身高重建队列 {

    // 根据身高重建队列
    // https://leetcode-cn.com/problems/queue-reconstruction-by-height/

    public static void main(String[] args) {
        Lc_406_根据身高重建队列 lc = new Lc_406_根据身高重建队列();
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};

        System.out.println("将打乱顺序的一群人站成一个队列" + Arrays.deepToString(people) + "，每个人由一个整数对(h,k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数");
        System.out.println("重建这个队列的结果是" + Arrays.deepToString(lc.reconstructQueue(people)));
    }

    // 自定义排序 - 时间复杂度O(N^2) N*logN为排序时间复杂度,随后需要N^2的时间复杂度遍历每个人并放入队列 - 空间复杂度(logN) 为排序栈所需空间
    public int[][] reconstructQueue(int[][] people) {
        // 按h降序,k升序排序
        Arrays.sort(people, (x, y) -> {
            if (x[0] != y[0]) {
                return y[0] - x[0];
            } else {
                return x[1] - y[1];
            }
        });

        // 此时,h前的所有人的身高都大于等于h,k即为h在数组中要插入的位置
        List<int[]> res = new ArrayList<>();
        for (int[] person : people) {
            res.add(person[1], person);
        }

        return res.toArray(new int[res.size()][]);
    }

}
