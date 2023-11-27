package datastructure;

/**
 * @Description: CS61B Lab7 BSTMap
 * @Author: whj
 * @Date: 2023-11-27 15:29
 */
public class BSTMap<K extends Comparable<K>, V> {

    private BSTNode root;
    private int size;
    private BSTNode cur;


    public void clear() {
        root = null;
        size = 0;
    }


    public boolean containsKey(K key) {
        //递归写法看下面的find
        cur = root;
        while (cur != null) {
            if (cur.key.compareTo(key) == 0) {
                return true;
            } else if (cur.key.compareTo(key) < 0) {
                cur = cur.rightChid;
            } else {
                cur = cur.leftChid;
            }
        }
        return false;
    }
    /*public static BST find(BST T, int L)
    {
        if (T == null || L == T.label)
            return T;
        else if (L < T.label)
            return find(T.left, L);
        else return find(T.right, L);
    }*/


    public V get(K key) {
        if (containsKey(key)) {
            return cur.value;
        }
        return null;
    }


    public int size() {
        return size;
    }


    public void put(K key, V value) {
        size++;
        if (root == null) {
            root = new BSTNode(key, value);
            return;
        }

        cur = root;
        BSTNode pre = root;
        while (cur != null) {
            if (cur.key.compareTo(key) == 0) {
                cur.value = value;
                return;
            } else if (cur.key.compareTo(key) < 0) {
                pre = cur;
                cur = cur.rightChid;
            } else {
                pre = cur;
                cur = cur.leftChid;
            }
        }
        //cur == null, need to add new Node
        if (pre.key.compareTo(key) < 0) {
            pre.rightChid = new BSTNode(key, value);
        } else {
            pre.leftChid = new BSTNode(key, value);
        }

    }

    //prints out your BSTMap in order of increasing Key
    public void printInOrder(BSTNode cur) {
        if (cur == null) {
            return;
        }
        printInOrder(cur.leftChid);
        System.out.println("key = " + cur.key + ", value = " + cur.value);
        printInOrder(cur.rightChid);
    }


    private class BSTNode {
        public BSTNode leftChid;
        public BSTNode rightChid;
        public K key;
        public V value;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
