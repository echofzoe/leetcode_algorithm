package algorithm.leetcode.stack;

public class Lc_316_去除重复字母 {

    // 去除重复字母
    // https://leetcode-cn.com/problems/remove-duplicate-letters/solution/qu-chu-zhong-fu-zi-mu-by-leetcode-soluti-vuso/

    public static void main(String[] args) {
        Lc_316_去除重复字母 lc = new Lc_316_去除重复字母();
        String s = "cbacdcbc";

        System.out.println("在字符串\"" + s + "\"中去除重复字符并使得返回结果的字典序最小的结果是" + lc.removeDuplicateLetters(s));
    }

    public String removeDuplicateLetters(String s) {
        boolean[] visited = new boolean[26];
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 97]++;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!visited[c - 97]) {

                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > c) {
                    if (count[sb.charAt(sb.length() - 1) - 97] > 0) {
                        visited[sb.charAt(sb.length() - 1) - 97] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }

                visited[c - 97] = true;
                sb.append(c);
            }

            count[c - 97]--;
        }

        return sb.toString();
    }

}
