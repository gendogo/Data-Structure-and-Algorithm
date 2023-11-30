package algorithm;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Description:
 * @Author: whj
 * @Date: 2023-11-30 17:43
 */
public class HeapSort {
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        heapSort1();
        System.out.println(Arrays.toString(arr));
    }

    // swim
    public static void swim(int i) {
        while (((i - 1) / 2) >= 0 && arr[i] > arr[(i - 1) / 2]) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    //sink
    public static void sink(int i, int size) {
        int l = i * 2 + 1;
        while (l < size) {
            int best = l + 1 < size && arr[l + 1] > arr[l] ? l + 1 : l;
            best = arr[i] > arr[best] ? i : best;
            if (i == best) {
                break;
            }
            swap(best, i);
            i = best;
            l = i * 2 + 1;
        }

    }

    public static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void heapSort1() {
        for (int i = arr.length - 1; i >= 0; i--) {
            swim(i);
        }
        int size = arr.length;
        while (size > 1) {
            // 堆顶和堆最后一个元素交换
            swap(0, --size);
            // 把堆顶元素sink
            sink(0, size);
        }

    }
}
