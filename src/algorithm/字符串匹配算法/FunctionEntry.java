package algorithm.字符串匹配算法;

public class FunctionEntry {
    public static void main(String[] args) {
        String mainString = "GTTATAGCTGGTAGCGGCGAA";
        String patternString = "GTAGCGGCG";


        System.out.println(" --- --- --- BF 算法 --- --- --- ");
        int index1 = BruteForce.bruteForce(mainString, patternString);
        System.out.println("主串 \"" + mainString + (index1 == -1 ? "\" 不包含" : " 包含") + "模式串 \"" + patternString + "\",");
        System.out.println("且模式串从主串的下标 " + index1 + " 开始出现");
    }
}
