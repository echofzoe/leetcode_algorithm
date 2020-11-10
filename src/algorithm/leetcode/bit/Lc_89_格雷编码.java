package algorithm.leetcode.bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc_89_格雷编码 {

    // 格雷编码
    // https://leetcode-cn.com/problems/gray-code/

    public static void main(String[] args) {
        Lc_89_格雷编码 lc = new Lc_89_格雷编码();
        int n = 2;

        System.out.println(n + "的格雷编码序列为" + Arrays.toString(lc.grayCode(n).toArray()));
    }

    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>() {{
            add(0);
        }};

        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(head + res.get(j));
            }
            head <<= 1;
        }

        return res;
    }

}
