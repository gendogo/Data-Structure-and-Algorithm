package algorithm;

/**
 * @Description:
 * @Author: whj
 * @Date: 2023-11-29 22:13
 */
public class QuickSort {
    public static int first;
    public static int last;
    public static int[] arr;

    private static void quicksort(int l, int r) {
        if (l >= r) {
            return;
        }
        //不能写成(Math.random() * arr.length),必须要在当前分区选择x，而不是整体
        int x = arr[l + (int) (Math.random() * (r - l + 1))];
        partition(l, x, r);
        int left = first - 1;
        int right = last + 1;
        quicksort(l, left);
        quicksort(right, r);
    }

    private static void partition(int l, int x, int r) {
        first = l;
        int i = l;
        last = r;
        while (i <= last) {
            if (arr[i] == x) {
                i++;
            } else if (arr[i] < x) {
                swap(first++, i++);
            } else {
                swap(last--, i);
            }
        }
    }

    private static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
