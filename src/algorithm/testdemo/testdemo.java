package algorithm.testdemo;

import org.junit.jupiter.api.Test;

public class testdemo {

    @Test
    public void test1() {
        int a = -2;
        String bin_a = Integer.toBinaryString(a);
        System.out.println(bin_a);

        a = a << 31;
        bin_a = Integer.toBinaryString(a);
        System.out.println(bin_a);
    }

}
