package algorithm.leetcode.graph.unionFindSet;

import algorithm.leetcode.utils.UnionFindSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Lc_1319_连通网络的操作次数 {

    // 连通网络的操作次数
    // https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected/

    public static void main(String[] args) {
        Lc_1319_连通网络的操作次数 lc = new Lc_1319_连通网络的操作次数();
        int[][] connections = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}};
        int n = 6;

        System.out.println(n + "台计算机在初始布线" + Arrays.deepToString(connections) + "的情况下若想全部联通所需的最小操作次数是 " + lc.makeConnected(n, connections));
    }

    public int makeConnected(int n, int[][] connections) {
        int len = connections.length;
        if (len < n - 1) return -1;

        UFS1319 ufs = new UFS1319(n);
        for (int[] connection : connections) {
            ufs.union(connection[0], connection[1]);
        }

        return ufs.getConnectedComponent() - 1;
    }

}

class UFS1319 extends UnionFindSet {

    public UFS1319(int n) {
        super(n);
    }

    public int getConnectedComponent() {
        int res = 0;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < getN(); i++) {
            int tmp = find(i);
            if (!set.contains(tmp)) {
                res++;
                set.add(tmp);
            }
        }

        return res;
    }

}
