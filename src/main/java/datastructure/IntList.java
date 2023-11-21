package datastructure;

/**
 * @Description: com from chapter 2.2 of ebook
 * @Author: whj
 * @Date: 2023-09-11 21:29
 */
public class IntList<T> {
    public T first;
    public IntList rest;

    public IntList() {
    }

    public IntList(T f, IntList r) {
        this.first = f;
        this.rest = r;
    }

    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    //return the ith item in the list
    public Object get(int i) {
        if (i == 0) {
            return first;
        }
        return this.rest.get(i - 1);
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L = new IntList(10, L);
        L = new IntList(15, L);
        System.out.println(String.format("The size of L is %d ,ith item is %d", L.size(), L.get(2)));
    }


}


