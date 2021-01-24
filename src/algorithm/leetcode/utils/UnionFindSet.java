package algorithm.leetcode.utils;

public class UnionFindSet {

    private int[] parent;
    private int n;

    public UnionFindSet(int n) {
        this.n = n;
        this.parent = new int[n];

        // 初始化 - 自己与自己联通
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    // 找到x的根节点
    public int find(int x) {
        if (parent[x] == x) return x;

        // 路径压缩
        parent[x] = find(parent[x]);
        return parent[x];
    }

    // 联通
    public void union(int x, int y) {
        int index1 = find(x), index2 = find(y);

        if (index1 == index2) return;
        parent[index1] = index2;
    }

    // 判断是否联通
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public int[] getParent() {
        return parent;
    }

    public int getN() {
        return n;
    }

}
