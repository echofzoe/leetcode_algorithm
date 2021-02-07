package algorithm.leetcode.slidingWindow;

import java.util.*;

public class Lc_480_滑动窗口中位数 {

    // 滑动窗口中位数
    // https://leetcode-cn.com/problems/sliding-window-median/

    public static void main(String[] args) {
        Lc_480_滑动窗口中位数 lc = new Lc_480_滑动窗口中位数();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        System.out.println(Arrays.toString(nums) + "中长度为" + k + "的滑动窗口的中位数组成的数组是" + Arrays.toString(lc.medianSlidingWindow(nums, k)));
    }

    // 双优先队列 + 延迟删除 - 时间复杂度 O(NlogN) - 空间复杂度 O(N)
    // 由于「延迟删除」的存在，small 比 large 在最坏情况下可能包含所有的 n 个元素，即没有一个元素被真正删除了。因此优先队列的大小是 O(n) 而不是 O(k) 的，其中 n 是数组 nums 的长度
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        DualHeap dh = new DualHeap(k);
        for (int i = 0; i < k; i++) {
            dh.insert(nums[i]);
        }

        double[] res = new double[n - k + 1];
        res[0] = dh.getMedian();
        for (int i = k; i < n; i++) {
            dh.insert(nums[i]);
            dh.earse(nums[i - k]);

            res[i - k + 1] = dh.getMedian();
        }

        return res;
    }

}

class DualHeap {
    // 大根堆，维护较小的一半元素
    private PriorityQueue<Integer> small;
    // 小根堆，维护较大的一半元素
    private PriorityQueue<Integer> large;
    // 哈希表，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
    private Map<Integer, Integer> delayed;

    private int k;
    // small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
    private int smallSize, largeSize;

    public DualHeap(int k) {
        this.small = new PriorityQueue<>((x, y) -> y.compareTo(x));    // 比较时调用 compareTo 方法，防止 int 溢出
        this.large = new PriorityQueue<>((x, y) -> x.compareTo(y));
        this.delayed = new HashMap<>();
        this.k = k;
        this.smallSize = 0;
        this.largeSize = 0;
    }

    public double getMedian() {
        return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
    }

    public void insert(int x) {
        if (small.isEmpty() || x <= small.peek()) {
            small.offer(x);
            smallSize++;
        } else {
            large.offer(x);
            largeSize++;
        }

        maleBalance();
    }

    public void earse(int x) {
        delayed.put(x, delayed.getOrDefault(x, 0) + 1);

        if (x <= small.peek()) {
            smallSize--;

            if (x == small.peek()) {
                prune(small);
            }
        } else {
            largeSize--;

            if (x == large.peek()) {
                prune(large);
            }
        }

        maleBalance();
    }

    // 调整 small 和 large 中的元素个数，使得二者的元素个数满足要求
    private void maleBalance() {
        if (smallSize > largeSize + 1) {
            // small 比 large 元素多 2 个
            large.offer(small.poll());
            smallSize--;
            largeSize++;

            // small 堆顶元素被移除，需要进行 prune
            prune(small);
        } else if (smallSize < largeSize) {
            // large 比 small 元素多 1 个
            small.offer(large.poll());
            smallSize++;
            largeSize--;

            // large 堆顶元素被移除，需要进行 prune
            prune(large);
        }
    }

    // 不断地弹出 heap 的堆顶元素，并且更新哈希表
    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int x = heap.peek();

            if (delayed.containsKey(x)) {
                delayed.put(x, delayed.get(x) - 1);

                if (delayed.get(x) == 0) {
                    delayed.remove(x);
                }

                heap.poll();
            } else {
                break;
            }
        }
    }

}
