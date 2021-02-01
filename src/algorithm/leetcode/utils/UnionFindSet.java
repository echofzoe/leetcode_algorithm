package algorithm.leetcode.utils;


/**
 * @ClassName UnionFindSet
 * @Description 并查集模板
 * @Author echofzoe
 * @Version v3.0
 * @Date 2021/2/1 15:38
 */
public class UnionFindSet {

    // 大小
    private final int n;

    // 集合的代表元素 parent 数组
    private int[] parent;

    // 集合的节点个数 rank 数组
    // 秩 - 优化查询时间
    private int[] rank;

    // 连通分量数目
    private int setCount;

    public UnionFindSet(int n) {
        this.n = n;
        this.parent = new int[n];
        this.rank = new int[n];
        this.setCount = n;

        // 初始化
        // - 初始时每个集合的代表元素就是自身
        // - 初始时每个集合只有一个元素
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 找到x的根节点
    public int find(int x) {
        // 路径压缩
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    // 连通
    public void union(int x, int y) {
        int idx1 = find(x), idx2 = find(y);

        if (idx1 == idx2) return;

        if (rank[idx1] < rank[idx2]) {
            // y 所在集合比 x 所在集合节点数多
            parent[idx1] = idx2;
            rank[idx2] += rank[idx1];
        } else {
            // y 所在集合不比 x 所在集合节点数多（小于 or 等于）
            parent[idx2] = idx1;
            rank[idx1] += rank[idx2];
        }

        setCount--;
    }

    // 判断是否联通
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public int getN() {
        return n;
    }

    public int getSetCount() {
        return setCount;
    }
}
