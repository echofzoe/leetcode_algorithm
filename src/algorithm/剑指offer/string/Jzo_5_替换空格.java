package algorithm.剑指offer.string;

public class Jzo_5_替换空格 {

    // 替换空格
    // https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/

    public static void main(String[] args) {
        Jzo_5_替换空格 lc = new Jzo_5_替换空格();
        String s = "We are happy.";
        
        System.out.println("字符串 \"" + s + "\" 经过替换空格处理后为" + " \"" + lc.replaceSpace(s) + "\"");
    }

    public String replaceSpace(String s) {
        char[] chars = new char[s.length() * 3];
        int size = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                chars[size++] = '%';
                chars[size++] = '2';
                chars[size++] = '0';
            }
            else {
                chars[size++] = c;
            }
        }

        return new String(chars, 0, size);

//        StringBuffer buf = new StringBuffer(s.length() * 3);
//        for (char c : s.toCharArray()) {
//            if (c == ' ') {
//                buf.append("%20");
//            }else {
//                buf.append(c);
//            }
//        }
//        return buf.toString();
    }
}
