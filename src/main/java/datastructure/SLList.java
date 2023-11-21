package datastructure;

/**
 * @Description:这只是丐版链表，对尾部操作很慢，project1会对这个进行改进
 * @Author: whj
 * @Date: 2023-09-25 21:05
 */
public class SLList {

    //if you don't use any instance members of the outer class,
    // make the nested class static,this saves a bit of memory.
    public static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public SLList() {
        sentinel = new IntNode(63, null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x) {
        //哨兵结点永远在第一个
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    public void addLast(int x) {
//迭代写法
//        IntNode p = first;
//        while (p.next != null) {
//            p = p.next;
//        }
//        p.next = new IntNode(x, null);
//        size += 1;
        sentinel = addLastHelper(sentinel, x);
        size += 1;
    }

    private IntNode addLastHelper(IntNode p, int x) {
        if (p == null) {
            return new IntNode(x, null);
        }
        p.next = addLastHelper(p.next, x);
        return p;
    }

    public int size() {
        return size;
    }


    public int getFirst() {
        return sentinel.next.item;
    }

    public int getLast() {
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        return p.item;
    }


}
