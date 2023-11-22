package datastructure;

/**
 * @Description: https://leetcode.cn/problems/design-circular-queue/
 * @Author: whj
 * @Date: 2023-11-22 15:22
 */
public class MyCircularQueue {
    private int size, head, tail, limit;
    public int[] queue;

    public MyCircularQueue(int k) {
        limit = k;
        size = head = tail = 0;
        queue = new int[limit];
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        size++;
        queue[tail] = value;
        tail = tail == limit - 1 ? 0 : tail + 1;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        size--;
        head = head == limit - 1 ? 0 : head + 1;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        //head不需要像下面的tail一样特判，因为加操作都是只对tail，deque处理了head
        return queue[head];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        //如果tail现在0位置，说明现在最后一个元素就是在数组最后一个位置
        int last = tail == 0 ? (limit - 1) : (tail - 1);
        return queue[last];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == limit;
    }
}
