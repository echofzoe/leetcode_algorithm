package algorithm.leetcode.string;

public class Lc_925_长按键入 {

    // 长按键入
    // https://leetcode-cn.com/problems/long-pressed-name/

    public static void main(String[] args) {
        Lc_925_长按键入 lc = new Lc_925_长按键入();
        String name = "alex", typed = "aaleex";

        System.out.println("typed:\"" + typed + "\" " + (lc.isLongPressedName(name, typed) ? "是" : "不是") + " name:\"" + name + "\" 长按键入的结果.");
    }

    public boolean isLongPressedName(String name, String typed) {
        if (name.length() > typed.length() || name.charAt(0) != typed.charAt(0)) return false;

        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }

        return i == name.length();
    }
}
