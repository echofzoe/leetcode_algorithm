package algorithm.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc_1002_查找常用字符 {

    // 查找常用字符
    // - 入参仅有小写字母组成
    // https://leetcode-cn.com/problems/find-common-characters/

    public static void main(String[] args) {
        Lc_1002_查找常用字符 lc = new Lc_1002_查找常用字符();
        String[] A = new String[]{"bella", "label", "roller"};

        System.out.println("列表 " + Arrays.toString(A) + " 中的每个字符串中都显示的全部字符（包括重复字符）组成的列表为 " + Arrays.toString(lc.commonChars(A).toArray()));
    }

    public List<String> commonChars(String[] A) {
        int[] minfrep = new int[26];
        Arrays.fill(minfrep, Integer.MAX_VALUE);

        for (String word : A) {
            int[] freq = new int[26];

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                ++freq[ch - 'a'];
            }
            for (int i = 0; i < 26; i++) {
                minfrep[i] = Math.min(minfrep[i], freq[i]);
            }
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < minfrep[i]; j++) {
                res.add(String.valueOf((char) (i + 'a')));
            }
        }

        return res;
    }
}
