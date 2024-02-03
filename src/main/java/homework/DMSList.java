package homework;

/**
 * @Description: Dicussion4 exam NO.2
 * @Author: whj
 * @Date: 2023-09-28 13:03
 */
public class DMSList {
    private IntNode sentinel;

    public DMSList() {
        sentinel = new IntNode(-1000, new LastNode());
    }

    public class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode h) {
            item = i;
            next = h;
        }

        public int max() {
            //很明显这里是递归，递归的终止条件是next为null
            return Math.max(item, next.max());
        }
    }

    public class LastNode extends IntNode {
        public LastNode() {
            //new 的LastNode对象的item=0，next = null,因为继承了这两个属性
            super(0, null);
        }

        @Override
        public int max(){
            return 0;
        }

    }

    /* Returns 0 if list is empty. Otherwise, returns the max element. */
    public int max() {
        return sentinel.next.max();
    }

    public void insertFront(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
    }
}

