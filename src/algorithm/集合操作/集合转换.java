package algorithm.集合操作;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 各集合与数组等其他数据类型之间的转换
 *
 * @author echofzoe
 * @since 2021.7.20
 */
public class 集合转换 {

    @Test
    public void list2other() {
        int[] data = {1, 2, 3, 4, 5};

        // int[] 转 List<Integer>
        // - 1.使用Arrays.stream将int[]转换成IntStream
        // - 2.使用IntStream中的boxed()装箱，将IntStream转换成Stream<Integer>
        // - 3.使用Stream的collect()，将Stream<T>转换成List<T>，因此正是List<Integer>
        List<Integer> list1 = Arrays.stream(data).boxed().collect(Collectors.toList());

        // int[] 转 Integer[]
        // - 1.使用Arrays.stream将int[]转换成IntStream
        // - 2.使用IntStream中的boxed()装箱，将IntStream转换成Stream<Integer>
        // - 3.使用Stream的toArray，传入IntFunction<T[]> generator，返回Integer[]，否则默认是Object[]
        Integer[] integers1 = Arrays.stream(data).boxed().toArray(Integer[]::new);

        // List<Integer> 转 Integer[]
        // - 调用toArray，传入参数T[] a
        Integer[] integers2 = list1.toArray(new Integer[0]);

        // List<Integer> 转 int[]
        // - 1.转换成int[]类型，就得先转成IntStream
        // - 2.通过mapToInt()把Stream<Integer>调用Integer::valueOf来转成IntStream
        // - 3.IntStream中默认toArray()转成int[]
        int[] arr1 = list1.stream().mapToInt(Integer::valueOf).toArray();

        // Integer[] 转 int[]
        // - 思路同上
        int[] arr2 = Arrays.stream(integers1).mapToInt(Integer::valueOf).toArray();

        // Integer[] 转 List<Integer>
        List<Integer> list2 = Arrays.asList(integers1);
    }

}
