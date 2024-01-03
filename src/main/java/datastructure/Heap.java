package datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * @Description:
 * @Author: whj
 * @Date: 2024-01-03 13:44
 */
public class Heap<T> {
    private ArrayList<HeapNode<T>> heap;
    private HashMap<T, Integer> indexOfItem;

    private static class HeapNode<T> {
        private T item;
        private double priority;

        HeapNode(T i, double p) {
            item = i;
            priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double p) {
            priority = p;
        }
    }

    public Heap() {
        heap = new ArrayList<>();
        indexOfItem = new HashMap<>();
    }


    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Item already exists in the priority queue.");
        }
        heap.add(new HeapNode<>(item, priority));
        indexOfItem.put(item, size() - 1);
        swimUp(size() - 1);
    }


    public boolean contains(T item) {
        return indexOfItem.containsKey(item);
    }


    public T getSmallest() {
        if (isEmpty()) {
            throw new NoSuchElementException("The priority queue is empty.");
        }
        return heap.get(0).getItem();
    }


    public T removeSmallest() {
        if (isEmpty()) {
            throw new NoSuchElementException("The priority queue is empty.");
        }
        T smallestItem = heap.get(0).getItem();
        heap.set(0, heap.get(size() - 1));
        indexOfItem.put(heap.get(0).getItem(), 0);
        heap.remove(size() - 1);
        indexOfItem.remove(smallestItem);
        if (!isEmpty()) {
            swimDown(0);
        }
        return smallestItem;
    }


    public int size() {
        return heap.size();
    }


    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("Item doesn't exist in the priority queue.");
        }
        int index = indexOfItem.get(item);
        HeapNode<T> node = heap.get(index);
        if (priority < node.getPriority()) {
            node.setPriority(priority);
            swimUp(index);
        } else {
            node.setPriority(priority);
            swimDown(index);
        }
    }

    private boolean isEmpty() {
        return size() == 0;
    }

    private void swap(int x, int y) {
        HeapNode<T> nx = heap.get(x);
        HeapNode<T> ny = heap.get(y);
        indexOfItem.put(nx.getItem(), y);
        indexOfItem.put(ny.getItem(), x);
        heap.set(x, ny);
        heap.set(y, nx);
    }

    private void swimUp(int index) {
        int parent = parent(index);
        if (heap.get(index).getPriority() < heap.get(parent).getPriority()) {
            swap(index, parent);
            swimUp(parent);
        }
    }

    private void swimDown(int index) {
        int smallerChild = smallerChild(index);
        while (heap.get(index).getPriority() > heap.get(smallerChild).getPriority()) {
            swap(index, smallerChild);
            index = smallerChild;
            smallerChild = smallerChild(index);
        }
    }

    private int parent(int index) {
        if (index == 0) {
            return 0;
        }
        return (index - 1) / 2;
    }

    private int smallerChild(int index) {
        int leftChild = index * 2 + 1;
        leftChild = leftChild < size() ? leftChild : index;
        int rightChild = index * 2 + 2;
        rightChild = rightChild < size() ? rightChild : leftChild;
        return heap.get(leftChild).getPriority() < heap.get(rightChild).getPriority()
                ? leftChild : rightChild;
    }
}
