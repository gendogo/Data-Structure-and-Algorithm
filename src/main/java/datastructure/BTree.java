package datastructure;

import java.lang.module.FindException;
import java.util.Arrays;

/**
 * @Description:
 * @Author: whj
 * @Date: 2024-03-10 17:49
 */
public class BTree {
    static class Node {
        int[] keys;
        Node[] children;
        int keyNumber; //The number of keys in this node.
        boolean leaf = true;
        int t; //degree number

        public Node(int t) { //t >= 2
            this.t = t;
            this.keys = new int[2 * t - 1];
            this.children = new Node[2 * t];
        }

        Node get(int key) {
            int i = 0;
            while (i < keyNumber) {
                if (keys[i] == key) {
                    return this;
                }
                if (keys[i] > key) {
                    break;
                }
                i++;
            }
            // i == keyNumber or keys[i] > key
            if (leaf) {
                return null;
            }
            // current node is not leaf node
            return children[i].get(key);
        }

        // insert a new key to the right of the index
        void insertKey(int key, int index) {
            for (int i = keyNumber - 1; i >= index; i--) {
                keys[i + 1] = keys[i];
            }
            keys[index] = key;
            keyNumber++;

        }

        void insertChild(Node child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;
        }

        //Remove the key at the index.
        int removeKey(int index) {
            int t = keys[index];
            System.arraycopy(keys, index + 1, keys, index, --keyNumber - index);
            return t;
        }

        int removeLeftmostKey() {
            return removeKey(0);
        }

        int removeRightmostKey() {
            return removeKey(keyNumber - 1);
        }

        Node removeChild(int index) {
            Node t = children[index];
            System.arraycopy(children, index + 1, children, index, keyNumber + 1 - index);
            return t;
        }

        Node removeLeftmostChild() {
            return removeChild(0);
        }

        Node removeRightmostChild() {
            return removeChild(keyNumber);
        }

        Node childLeftSibling(int index) {
            return index > 0 ? children[index - 1] : null;
        }

        Node childRightSibling(int index) {
            return index == keyNumber ? null : children[index + 1];
        }

        //copy Current Node to target Node
        void moveToTargetNode(Node target) {
            int start = target.keyNumber;
            if (!leaf) {
                for (int i = 0; i <= keyNumber; i++) {
                    target.children[start + i] = children[i];
                }
            }
            for (int i = 0; i < keyNumber; i++) {
                target.keys[target.keyNumber++] = keys[i];
            }
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }
    }

    Node root;
    int t; //minimal degree of B Tree
    int MIN_KEY_NUMBER;
    int MAX_KEY_NUMBER;

    public BTree() {
        t = 2;
    }

    public BTree(int t) {
        this.t = t;
        root = new Node(t);
        //According to the definition of a B-tree.
        MAX_KEY_NUMBER = 2 * t - 1;
        MIN_KEY_NUMBER = t - 1;
    }

    public boolean contains(int key) {
        return root.get(key) != null;
    }

    public void put(int key) {
        putHelper(root, key, null, 0);
    }

    private void putHelper(Node node, int key, Node parent, int index) {
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] == key) {
                return; // update
            }
            if (node.keys[i] > key) {
                break;
            }
            i++;
        }
        //the new key should be inserted to child node
        if (node.leaf) {
            node.insertKey(key, i);
        } else {
            putHelper(node.children[i], key, node, i);
        }
        if (node.keyNumber == MAX_KEY_NUMBER) {
            split(node, parent, index);
        }
    }

    //Index means the nth child Note need to be split
    private void split(Node left, Node parent, int index) {
        if (parent == null) { //current node is root node
            Node newRoot = new Node(t);
            newRoot.leaf = false;
            newRoot.insertChild(left, 0);
            this.root = newRoot;
            parent = newRoot;
        }
        //crete right node
        Node right = new Node(t);
        right.leaf = left.leaf;
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);
        if (!left.leaf) {
            System.arraycopy(left.children, t, right.children, 0, t);
        }
        right.keyNumber = t - 1;
        left.keyNumber = t - 1;

        int mid = left.keys[t - 1];
        parent.insertKey(mid, index);
        //inset right node
        parent.insertChild(right, index + 1);
    }

    public void remove(int key) {
        removeHelper(root, key, null, 0);
    }

    private void removeHelper(Node node, int key, Node parent, int index) {
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] >= key) {
                break;
            }
            i++;
        }
        if (node.leaf) {
            if (!found(node, key, i)) {
                return;
            } else {
                node.removeKey(i);
            }
        } else {
            if (!found(node, key, i)) {
                removeHelper(node.children[i], key, node, i);
            } else {
                Node s = node.children[i + 1];
                while (!s.leaf) {
                    s = s.children[0];
                }
                int skey = s.keys[0];
                node.keys[i] = skey;
                removeHelper(node.children[i + 1], skey, node, i + 1);
            }
        }
        if (node.keyNumber < MIN_KEY_NUMBER) { //BTree is not balanced now
            balance(parent, node, index);
        }

    }

    private void balance(Node parent, Node x, int i) {
        if (x == root) {
            if (root.keyNumber == 0 && root.children[0] != null) {
                root = root.children[0];
            }
            return;
        }
        Node left = parent.childLeftSibling(i);
        Node right = parent.childRightSibling(i);
        if (left != null && left.keyNumber > MIN_KEY_NUMBER) {
            //Rotating left node
            x.insertKey(parent.keys[i - 1], 0);
            if (!left.leaf) {
                x.insertChild(left.removeRightmostChild(), 0);
            }
            parent.keys[i - 1] = left.removeRightmostKey();
            return;
        }
        if (right != null && right.keyNumber > MIN_KEY_NUMBER) {
            //Rotating right node
            x.insertKey(parent.keys[i], x.keyNumber);
            if (!right.leaf) {
                x.insertChild(right.removeLeftmostChild(), x.keyNumber + 1);
            }
            parent.keys[i] = right.removeLeftmostKey();
            return;
        }
        if (left != null) { //left bro does not have enough key, need to combine
            parent.removeChild(i);
            left.insertKey(parent.removeKey(i - 1), left.keyNumber);
            x.moveToTargetNode(left);
        } else {
            //no left bro
            parent.removeChild(i + 1);
            x.insertKey(parent.removeKey(i), x.keyNumber);
            right.moveToTargetNode(x);
        }

    }

    private boolean found(Node node, int key, int i) {
        return i < node.keyNumber && node.keys[i] == key;
    }

}
