package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description: https://leetcode.cn/problems/implement-queue-using-stacks/
 * @Author: whj
 * @Date: 2023-11-28 20:36
 */
public class MyQueue {

    public Stack<Integer> in;
    public Stack<Integer> out;

    public MyQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    public void push(int x) {
        in.push(x);
        inToOut();
    }

    // 倒数据
    // 从in栈，把数据倒入out栈
    // 1) out空了，才能倒数据
    // 2) 如果倒数据，in必须倒完
    private void inToOut() {
        if (out.empty()) {
            while (!in.empty()) {
                out.push(in.pop());
            }
        }
    }

    public int pop() {
        inToOut();
        return out.pop();
    }

    public int peek() {
        inToOut();
        return out.peek();
    }

    public boolean empty() {
        return in.empty() && out.isEmpty();
    }
}

//https://leetcode.cn/problems/implement-stack-using-queues/
class MyStack {

    public Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList();
    }

    public void push(int x) {
        int n = queue.size();
        queue.offer(x);
        for (int i = 0; i < n; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
