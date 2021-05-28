package algorithm.剑指offer.queue;

import java.util.*;

public class Jzo_9_用两个栈实现队列 {

    // 用两个栈实现队列
    // https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/

    public static void main(String[] args) {
        Jzo_9_用两个栈实现队列 lc = new Jzo_9_用两个栈实现队列();

        // 输入 - input 和应得的输出 - output
        String[] input1 = new String[]{"CQueue", "appendTail", "deleteHead", "deleteHead"};
        Integer[] input2 = new Integer[]{null, 3, null, null};
        Integer[] output = new Integer[]{null, null, 3, -1};

        // 算法的输出 - res
        List<Integer> res = new ArrayList<>();
        CQueue obj = null;

        for (int i = 0; i < input1.length; i++) {
            switch (input1[i]) {
                case "CQueue":
                    obj = new CQueue();
                    res.add(null);
                    break;
                case "appendTail":
                    obj.appendTail(input2[i]);
                    res.add(null);
                    break;
                case "deleteHead":
                    res.add(obj.deleteHead());
                    break;
                default:
                    break;
            }
        }

        // 比较答案
        boolean flag = true;
        for (int i = 0; i < output.length; i++) {

            if (output[i] == null && res.get(i) == null) continue;

            if (output[i] == null || res.get(i) == null || !output[i].equals(res.get(i))) {
                flag = false;
                break;
            }
        }

        // 结论
        System.out.println(flag ? "Accepted!" : "Failed!");
    }
}

class CQueue {

    Stack<Integer> A, B;

    public CQueue() {
        A = new Stack<>();
        B = new Stack<>();
    }

    public void appendTail(int value) {
        A.push(value);
    }

    public int deleteHead() {
        if (B.isEmpty()) {
            if (A.isEmpty()) {
                return -1;
            } else {
                while (!A.isEmpty()) {
                    B.push(A.pop());
                }
            }
        }

        return B.pop();
    }
}
