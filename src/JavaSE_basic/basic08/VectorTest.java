package JavaSE_basic.basic08;

import java.util.Scanner;
import java.util.Vector;

public class VectorTest {
    public static void main(String[] args) {
        // 1. 实例化 ，用于从键盘获取学生成绩
        Scanner scan = new Scanner(System.in);

        // 2. 用 Vector 代替数组
        Vector v = new Vector();

        // 3. 添加信息 并 获取最大值
        int max = 0;
        while (true) {
            System.out.println("请输入学生的成绩（输入负数表示结束）");
            int score = scan.nextInt();
            if (score < 0) {
                break;
            }
            if (score > 100) {
                System.out.println("越界（范围[0, 100]），请重新输入！");
                continue;
            } else {
                // JDK 5.0 之前。采用多态
//                Integer iScore = new Integer(score);
//                v.addElement(iScore);

                // JDK 5.0 之前。采用自动装箱
                v.addElement(score);

                // 更新最大值
                max = max > score ? max : score;
            }
        }

        char level;
        // 4. 学生成绩分级
        for (int i = 0; i < v.size(); i++) {
            Object obj = v.elementAt(i);

            // JDK 5.0 之前，先转成基本数据类型再比较
//            Integer iObj = (Integer) obj;
//            int score = iObj.intValue();

            // JDK 5.0 之后，自动拆箱后直接比较
//            int score = (int)obj;
            int score = (Integer) obj;

            if (max - score <= 10)
                level = 'A';
            else if (max - score <= 20)
                level = 'B';
            else if (max - score <= 30)
                level = 'C';
            else
                level = 'D';
            System.out.println("Student-" + i + "`s socre is " + score + ", level is " + level);
        }
    }
}
