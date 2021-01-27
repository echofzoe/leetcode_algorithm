package algorithm.leetcode.utils;

public class UnionFindSet {

    // 大小
    private int n;
    // 根节点数组
    private int[] parent;
    // 连通分量数目
    private int setCount;

    public UnionFindSet(int n) {
        this.n = n;
        this.parent = new int[n];
        this.setCount = n;

        // 初始化 - 自己与自己连通
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    // 找到x的根节点
    public int find(int x) {
        // 路径压缩
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    // 连通
    public void union(int x, int y) {
        int index1 = find(x), index2 = find(y);

        if (index1 == index2) return;
        parent[index1] = index2;

        setCount--;
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

    public int getSetCount() {
        return setCount;
    }
}
