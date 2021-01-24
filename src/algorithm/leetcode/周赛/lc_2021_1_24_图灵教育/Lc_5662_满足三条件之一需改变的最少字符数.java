package algorithm.leetcode.周赛.lc_2021_1_24_图灵教育;

public class Lc_5662_满足三条件之一需改变的最少字符数 {

    // 满足三条件之一需改变的最少字符数
    // https://leetcode-cn.com/problems/change-minimum-characters-to-satisfy-one-of-three-conditions/

    public static void main(String[] args) {
        Lc_5662_满足三条件之一需改变的最少字符数 lc = new Lc_5662_满足三条件之一需改变的最少字符数();
        String a = "azzzz", b = "bzzzz";

        System.out.println("字符串\"" + a + "\"" + "和\"" + b + "\"在满足以下三个条件：");
        System.out.println("- a 中的每个字母在字母表中 严格小于 b 中的每个字母");
        System.out.println("- b 中的每个字母在字母表中 严格小于 a 中的每个字母");
        System.out.println("- a 和 b 都由同一个字母组成");
        System.out.println("之一所需要的最少操作次数为 " + lc.minCharacters(a, b));
    }

    // 模拟 + 计数 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int minCharacters(String a, String b) {
        int n1 = a.length(), n2 = b.length();

        char[] c1 = new char[26], c2 = new char[26];

        for (char c : a.toCharArray()) c1[c - 'a']++;
        for (char c : b.toCharArray()) c2[c - 'a']++;

        int res = Integer.MAX_VALUE, c1Sum = 0, c2Sum = 0;
        for (int i = 0; i < 25; i++) {
            c1Sum += c1[i];
            c2Sum += c2[i];

            // 满足条件1&2（不包含'z'）
            int condition12 = Math.min(n1 - c1Sum + c2Sum, n2 - c2Sum + c1Sum);
            // 满足条件3
            int condition3 = n1 + n2 - c1[i] - c2[i];
            res = Math.min(res, Math.min(condition12, condition3));
        }

        return Math.min(res, n1 + n2 - c1[25] - c2[25]);
    }

}
