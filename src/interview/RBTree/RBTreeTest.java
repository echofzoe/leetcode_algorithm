package interview.RBTree;

import java.util.Scanner;

public class RBTreeTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 5 6 7 4 3 1 0 2

        RBTree<String, Object> rbt = new RBTree<>();

        while (true) {
            System.out.println("请输入key：");
            String key = scanner.next();
            System.out.println();

            rbt.insert(key, null);
            TreeOperation.show(rbt.getRoot());
        }
    }

}
