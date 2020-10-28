package JavaSE_advanced.advanced19_Compare;

import org.junit.Test;

import java.util.Arrays;

public class CompareTest {

    /**
     * Comparable 接口
     */
    @Test
    public void testComparable() {
        String[] array = new String[]{"AA", "CC", "KK", "MM", "GG", "JJ", "DD"};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        Goods[] goods = new Goods[4];
        goods[0] = new Goods("lenovoMouse", 12);
        goods[1] = new Goods("dellMouse", 24);
        goods[2] = new Goods("appleMouse", 36);
        goods[3] = new Goods("microsoftMouse", 36);
        Arrays.sort(goods);
        System.out.println(Arrays.toString(goods));
    }

}
