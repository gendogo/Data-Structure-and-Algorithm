package datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description:
 * @Author: whj
 * @Date: 2023-09-27 21:16
 */
public class ArraySet<T> implements Iterable<T> {
    private T[] items;
    private int size; // the next item to be added will be at position size

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map. */
    public void add(T x) {
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size += 1;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
//        StringBuilder returnSB = new StringBuilder("{");
//        for (int i = 0; i < size - 1; i += 1) {
//            returnSB.append(items[i].toString());
//            returnSB.append(", ");
//        }
//        returnSB.append(items[size - 1]);
//        returnSB.append("}");
//        return returnSB.toString();

        List<String> list = new ArrayList<>();
        for (T item : this) {
            //这里this很容易错写为items，items可没有实现Iterable接口，会报错
            list.add(item.toString());
        }
        return String.join(", ", list);

    }

    public static <G> ArraySet<G> of(G... stuff) {
        //因为静态方法无法访问add方法，所以这里需要新建一个ArraySet对象
        ArraySet<G> returnSet = new ArraySet<>();
        for (G item : stuff) {
            returnSet.add(item);
        }
        return returnSet;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArraySet<T> o = (ArraySet<T>) other;
        if (o.size() != this.size()) {
            return false;
        }
        for (T item : this) {
            if (!o.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator<>(this);
    }

    public static class ArraySetIterator<T> implements Iterator<T> {
        private int wizPos;
        private ArraySet<T> arr;

        public ArraySetIterator(ArraySet<T> arr) {
            this.arr = arr;
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < arr.size();
        }

        @Override
        public T next() {
            T returnItem = arr.items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }


}
