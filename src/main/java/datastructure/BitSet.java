package datastructure;

import java.util.HashSet;

/**
 * @Description: non negative number
 * @Author: whj
 * @Date: 2023-12-02 17:49
 */
public class BitSet {
    private int[] set;

    public BitSet(int n) {
        set = new int[(n + 31) / 32];
    }

    public void add(int num) {
        set[num / 32] |= 1 << (num % 32);
    }

    public void remove(int num) {
        set[num / 32] &= ~(1 << (num % 32));
    }

    public void reverse(int num) {
        set[num / 32] ^= 1 << (num % 32);
    }

    public boolean contains(int num) {
        return ((set[num / 32] >> (num % 32)) & 1) == 1;
    }

    // 对数器测试
    public static void main(String[] args) {
        int n = 1000;
        int testTimes = 10000;
        System.out.println("test start");
        // 实现的位图结构
        BitSet bitSet = new BitSet(n);
        // 直接用HashSet做对比测试
        HashSet<Integer> hashSet = new HashSet<>();
        System.out.println("testing api");
        for (int i = 0; i < testTimes; i++) {
            double decide = Math.random();
            // number -> 0 ~ n-1，等概率得到
            int number = (int) (Math.random() * n);
            if (decide < 0.333) {
                bitSet.add(number);
                hashSet.add(number);
            } else if (decide < 0.666) {
                bitSet.remove(number);
                hashSet.remove(number);
            } else {
                bitSet.reverse(number);
                if (hashSet.contains(number)) {
                    hashSet.remove(number);
                } else {
                    hashSet.add(number);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (bitSet.contains(i) != hashSet.contains(i)) {
                System.out.println("wrong!");
            }
        }
        System.out.println("test over");
    }

}
