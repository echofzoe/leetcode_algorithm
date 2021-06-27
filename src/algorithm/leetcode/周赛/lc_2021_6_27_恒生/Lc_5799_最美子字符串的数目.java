package algorithm.leetcode.周赛.lc_2021_6_27_恒生;

/**
 * 最美子字符串的数目
 * <P>https://leetcode-cn.com/problems/number-of-wonderful-substrings/</P>
 * 
 * @author echofzoe 
 * @since 2021.6.27
 */
public class Lc_5799_最美子字符串的数目 {

    public static void main(String[] args) {
        Lc_5799_最美子字符串的数目 lc = new Lc_5799_最美子字符串的数目();

        String word = "aabb";
        
        System.out.println("如果某个字符串中 至多一个 字母出现 奇数 次，则称其为 最美 字符串。\n" +
                "  - 例如，\"ccjjc\" 和 \"abab\" 都是最美字符串，但 \"ab\" 不是。\n" +
                "给你一个字符串 word ，该字符串由前十个小写英文字母组成（'a' 到 'j'）。请你返回 word 中 最美非空子字符串 的数目。如果同样的子字符串在 word 中出现多次，那么应当对 每次出现 分别计数。\n" +
                "子字符串 是字符串中的一个连续字符序列。\n");
        System.out.println("输入：word = " + word);
        System.out.println("输出：" + lc.wonderfulSubstrings(word));
    }

    public long wonderfulSubstrings(String word) {
        long res = 0;
        int mask = 0;

        long[] freq = new long[1 << 10];
        // base case
        freq[0] = 1;

        for (char c : word.toCharArray()) {
            int idx = c - 'a';

            mask ^= (1 << idx);
            res += freq[mask];
            for (int i = 0; i < 10; i++) {
                res += freq[mask ^ (1 << i)];
            }

            freq[mask]++;
        }

        return res;
    }

}
