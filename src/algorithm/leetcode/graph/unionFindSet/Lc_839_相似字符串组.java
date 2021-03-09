package algorithm.leetcode.graph.unionFindSet;

import algorithm.leetcode.utils.UnionFindSet;

import java.util.Arrays;

public class Lc_839_相似字符串组 {

    // 相似字符串组
    // https://leetcode-cn.com/problems/similar-string-groups/

    public static void main(String[] args) {
        Lc_839_相似字符串组 lc = new Lc_839_相似字符串组();
        String[] strs = {"tars", "rats", "arts", "star"};

        System.out.println(Arrays.toString(strs) + "中相似字符串组的个数是" + lc.numSimilarGroups(strs));
    }

    // 并查集 - 时间复杂度 O(N^2*M) M为单一字符串的长度 - 空间复杂度 O(N)
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;

        UnionFindSet ufs = new UnionFindSet(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    if (!ufs.isConnected(i, j)) {
                        ufs.union(i, j);
                    }
                }
            }
        }

        return ufs.getSetCount();
    }

    private boolean isSimilar(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;

        int cnt = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                cnt++;
            }

            if (cnt > 2) return false;
        }

        return true;
    }

}
