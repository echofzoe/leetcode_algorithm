package algorithm.leetcode.numericalOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc_204_计数质数 {

    // 计数质数
    // https://leetcode-cn.com/problems/count-primes/

    public static void main(String[] args) {
        Lc_204_计数质数 lc = new Lc_204_计数质数();
        int n1 = 10;
        int n2 = 50;
        int n3 = 100;

        System.out.println("小于" + n1 + "的质数一共有" + lc.countPrimesEnum(n1) + "个");
        System.out.println("小于" + n2 + "的质数一共有" + lc.countPrimes埃氏筛(n2) + "个");
    }

    // 枚举 - 时间复杂度 O(N√N) 单个数检查需要O(√N) 一共检查N个数 - 空间复杂度 O(1)
    public int countPrimesEnum(int n) {
        int res = 0;

        for (int i = 2; i < n; i++) {
            res += isPrime(i) ? 1 : 0;
        }

        return res;
    }

    private boolean isPrime(int i) {
        for (int j = 2; j * j <= i; j++) {
            if (i % j == 0) {
                return false;
            }
        }

        return true;
    }

    // 埃氏筛 - 时间复杂度 O(NloglogN) - 空间复杂度 O(N)
    public int countPrimes埃氏筛(int n) {
        int res = 0;

        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);

        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 1) {
                res += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }

        return res;
    }

    // 线性筛 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int countPrimes线性筛(int n) {
        List<Integer> primes = new ArrayList<>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);

        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; j++) {
                isPrime[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0) break;
            }
        }

        return primes.size();
    }

}
