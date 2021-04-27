package algorithm.leetcode.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BluetoothUUID {

    private static final int OFFSET_START = 2, OFFSET_END = 6;

    private static final int BASE_START = 0, BASE_END = 8;

    private static final Map<Integer, Character> i2c = new HashMap<>() {{
        put(10, 'a');
        put(11, 'b');
        put(12, 'c');
        put(13, 'd');
        put(14, 'e');
    }};

    private static final Map<Character, Integer> c2i = new HashMap<>() {{
        put('a', 10);
        put('b', 11);
        put('c', 12);
        put('d', 13);
        put('e', 14);
    }};

    private static String getUUID(String base, String offset) {
        char[] cs1 = new StringBuilder(base.toLowerCase().substring(BASE_START, BASE_END)).reverse().toString().toCharArray();
        char[] cs2 = new StringBuilder(offset.toLowerCase().substring(OFFSET_START, OFFSET_END)).reverse().toString().toCharArray();

        String postfix = base.substring(8, 36);

        int idx = 0, carry = 0;
        while (idx < 4) {
            char c1 = cs1[idx], c2 = cs2[idx];
            int a1 = c2i.containsKey(c1) ? c2i.get(c1) : (c1 - 48), a2 = c2i.containsKey(c2) ? c2i.get(c2) : (c2 - 48);

            int a = a1 + a2 + carry, b = a / 16, c = a % 16;

            carry = Math.max(b, 0);

            cs1[idx++] = i2c.containsKey(c) ? i2c.get(c) : (char) (c + 48);
        }

        while (carry > 0) {
            char c1 = cs1[idx];
            int a1 = c2i.containsKey(c1) ? c2i.get(c1) : (c1 - 48);

            int a = a1 + carry, b = a / 16, c = a % 16;

            carry = Math.max(b, 0);

            cs1[idx++] = i2c.containsKey(c) ? i2c.get(c) : (char) (c + 48);
        }

        return new StringBuilder(String.valueOf(cs1)).reverse() + postfix;
    }

    public static String getUpperCaseUUID(String base, String offset) {
        return getUUID(base, offset).toUpperCase();
    }

    public static String getLowerCaseUUID(String base, String offset) {
        return getUUID(base, offset).toLowerCase();
    }

    @Test
    public void test() {
        String base = "8653000A-43E6-47B7-9CB0-5FC21D4AE340";
        String[] offset = {"0x2A19", "0x2902", "0x2A1C"};

        System.out.println(BluetoothUUID.getLowerCaseUUID(base, offset[0]));
        System.out.println(BluetoothUUID.getUpperCaseUUID(base, offset[0]));

        System.out.println(BluetoothUUID.getLowerCaseUUID(base, offset[1]));
        System.out.println(BluetoothUUID.getUpperCaseUUID(base, offset[1]));

        System.out.println(BluetoothUUID.getLowerCaseUUID(base, offset[2]));
        System.out.println(BluetoothUUID.getUpperCaseUUID(base, offset[2]));
    }

}
