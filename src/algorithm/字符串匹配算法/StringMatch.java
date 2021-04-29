package algorithm.字符串匹配算法;

public class StringMatch {

    public static void main(String[] args) {
        StringMatch sm = new StringMatch();

        String origin = "GTTATAGCTGGTAGCGGCGAA";
        String pattern = "GTAGCGGCG";


        System.out.println(" --- --- --- BF 算法 --- --- --- ");
        int idx1 = sm.bruteForce(origin, pattern);
        System.out.println("字符串\"" + origin + "\"中" + (idx1 == -1 ? "不包含" : "包含") + "模式串\"" + pattern + "\"");
        if (idx1 != -1) System.out.println("且模式串在字符串中的起始下标为 " + idx1);
    }

    public int bruteForce(String origin, String pattern) {
        if (origin == null || pattern == null) return -1;

        int n, m;
        if ((n = origin.length()) < (m = pattern.length())) return -1;

        int i = 0, j = 0;
        while (i < n && j < m) {
            if (origin.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        if (j == m) {
            return i - j;
        }

        return -1;
    }

}
