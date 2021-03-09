package algorithm.leetcode.graph.unionFindSet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Lc_547_省份数量 {

    // 省份数量
    // https://leetcode-cn.com/problems/number-of-provinces/

    public static void main(String[] args) {
        Lc_547_省份数量 lc = new Lc_547_省份数量();
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] isConnected2 = {{1, 0, 0, 1}, {0, 1, 1, 1}, {0, 1, 1, 0}, {1, 1, 0, 1}};

        System.out.println("图的邻接矩阵:");
        for (int[] ic : isConnected) System.out.println(Arrays.toString(ic));
        System.out.println("中，省份数量是" + lc.findCircleNumDfs(isConnected) + "\n");

        System.out.println("图的邻接矩阵:");
        for (int[] ic : isConnected2) System.out.println(Arrays.toString(ic));
        System.out.println("中，省份数量是" + lc.findCircleNum(isConnected2));
    }

    // dfs - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public int findCircleNumDfs(int[][] isConnected) {
        int cities = isConnected.length;
        if (cities == 0) return 0;

        int res = 0;
        boolean[] visited = new boolean[cities];

        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, cities, i);
                res++;
            }
        }

        return res;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int cities, int i) {
        for (int j = 0; j < cities; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, cities, j);
            }
        }
    }

    // bfs - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public int findCircleNumBfs(int[][] isConnected) {
        int cities = isConnected.length;
        if (cities == 0) return 0;

        int res = 0;
        boolean[] visited = new boolean[cities];

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    visited[cur] = true;
                    for (int j = 0; j < cities; j++) {
                        if (isConnected[cur][j] == 1 && !visited[j]) {
                            queue.offer(j);
                        }
                    }
                }

                res++;
            }
        }

        return res;
    }
    
    // 并查集 - 时间复杂度 O(N^2*logN) - 空间复杂度 O(N)
    public int findCircleNum(int[][] isConnected) {
        int cities = isConnected.length;
        if (cities == 0) return 0;

        int[] parent = new int[cities];
        for (int i = 0; i < cities; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < cities; i++) {
            for (int j = i + 1; j < cities; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }

        int res = 0;

        for (int i = 0; i < cities; i++) {
            if (parent[i] == i) {
                res++;
            }
        }

        return res;
    }

    private void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    private int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }

        return parent[index];
    }

}
