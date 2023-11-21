package datastructure;

/**
 * @Description: Array based list
 * @Author: whj
 * @Date: 2023-09-26 14:21
 */
public class AList<T> {
    public static final int Factor = 2;
    private int size;
    private T[] items;

    /**
     * Creates an empty list.
     */
    public AList() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /**
     * Inserts X into the back of the list.
     */
    public void addLast(T x) {
        if (size == items.length) {
            resize(Factor * size);
        }
        items[size] = x;
        size++;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;
    }

    /**
     * Returns the item from the back of the list.
     */
    public Object getLast() {
        return items[size - 1];
    }

    /**
     * Gets the ith item in the list (0 is the front).
     */
    public Object get(int i) {
        return items[i];
    }

    /**
     * Returns the number of items in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Deletes item from back of the list and
     * returns deleted item.
     */
    public Object removeLast() {
        Object x = getLast();
        items[size - 1] = null;
        size--;
        return x;
    }
}
