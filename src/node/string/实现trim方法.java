package interview.string;

/**
 * 实现 String 类中的 trim 方法，此方法能够去掉字符串两端的空白字符
 * @author echofzoe
 * @since 2021.4.8
 */
public class 实现trim方法 {

    public static void main(String[] args) {
        实现trim方法 main = new 实现trim方法();

        String s = "   abc   ", tmp = "";
        System.out.println("s = " + s + ", len = " + s.length() +  "; s.trim() = " + (tmp = s.trim()) + ", len = " + tmp.length());
        System.out.println("s = " + s + ", len = " + s.length() + ", s.myTrim() = " + (tmp = main.myTrim(s)) + ", len = " + tmp.length());
    }

    private String myTrim(String s) {
        int p = 0, n = s.length();

        char[] cs = s.toCharArray();
        while (p < n && cs[p] <= ' ') p++;
        while (p < n && cs[n - 1] <= ' ') n--;

        return p > 0 || n < s.length() ? s.substring(p, n) : s;
    }

}
