package algorithm.字符串匹配算法;

public class BruteForce {
    public static int bruteForce(String mainString, String patternString) {
        if (mainString == null && patternString == null) return 0;
        if (mainString == null || patternString == null) return -1;
        if (patternString.length() > mainString.length()) return -1;

        int mLen = mainString.length(), pLen = patternString.length(), i = 0, j = 0;

        while (i < mLen && j < pLen) {
            if (mainString.charAt(i) == patternString.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        if (j == pLen) {
            return i - j;
        }

        return -1;
    }
}
