package algorithm;


import java.util.*;

/**
 * @Description:
 * @Author: whj
 * @Date: 2023-10-11 17:04
 */
public class LeetCode {
    public static void sort(Stack<Integer> stack) {
        int depth = getDepth(stack);
        while (depth > 0) {
            int maxValue = getValue(stack, depth);
            int times = getTimes(stack, depth, maxValue);
            downMaxValue(stack, depth, maxValue, times);
            depth -= times;
        }
    }

    private static void downMaxValue(Stack<Integer> stack, int depth, int maxValue, int times) {
        if (depth == 0) {
            for (int i = 0; i < times; i++) {
                stack.push(maxValue);
            }
        } else {
            int value = stack.pop();
            downMaxValue(stack, depth - 1, maxValue, times);
            if (value != maxValue) {
                stack.push(value);
            }
        }
    }

    private static int getTimes(Stack<Integer> stack, int depth, int maxValue) {
        if (depth == 0) {
            return 0;
        }
        int value = stack.pop();
//        int times1 = getTimes(stack, depth - 1, maxValue);
        int times = getTimes(stack, depth - 1, maxValue)  + (value == maxValue ? 1 : 0);
        stack.push(value);
        return times;
    }

    private static int getValue(Stack<Integer> stack, int depth) {
        if (depth == 0) {
            return Integer.MIN_VALUE;
        }
        int value = stack.pop();
        int max1 = getValue(stack, depth - 1);
        int max = Math.max(value, max1);
        stack.push(value);
        return max;
    }

    private static int getDepth(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        Integer value = stack.pop();
        int depth = 1 + getDepth(stack);
        stack.push(value);
        return depth;
    }

    // 为了测试
    // 生成随机栈
    public static Stack<Integer> randomStack(int n, int v) {
        Stack<Integer> ans = new Stack<Integer>();
        for (int i = 0; i < n; i++) {
            ans.add((int) (Math.random() * v));
        }
        return ans;
    }

    // 为了测试
    // 检测栈是不是从顶到底依次有序
    public static boolean isSorted(Stack<Integer> stack) {
        int step = Integer.MIN_VALUE;
        while (!stack.isEmpty()) {
            if (step > stack.peek()) {
                return false;
            }
            step = stack.pop();
        }
        return true;
    }


    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.add(1);
        test.add(5);
        test.add(4);
        test.add(5);
        test.add(3);
        test.add(2);
        test.add(3);
        test.add(1);
        test.add(4);
        test.add(2);
        sort(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

        // 随机测试
        int N = 20;
        int V = 20;
        int testTimes = 20000;
        System.out.println("start");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N);
            Stack<Integer> stack = randomStack(n, V);
            sort(stack);
            if (!isSorted(stack)) {
                System.out.println("wrong");
                break;
            }
        }
        System.out.println("end");
    }


}
