package homework;

/**
 * @Description: Dicussion3 exam No.3
 * @Author: whj
 * @Date: 2023-09-26 16:23
 */

/*For instance, if lst is defined as IntList.list(0, 3, 1, 4, 2, 5),
evenOdd(lst) would modify lst to be IntList.list(0, 1, 2, 3, 4, 5).*/
public class IntList  {
    public int first;

    public IntList rest;


    public IntList(int f, IntList r) {
        this.first = f;
        this.rest = r;
    }

    public static IntList reverse(IntList lst) {
        IntList reversedFirstNode = lst;
        while (reversedFirstNode.rest != null) {
            reversedFirstNode = reversedFirstNode.rest;
        }
        reverseHelper(lst, null).rest = null;
        return reversedFirstNode;
    }

    private static IntList reverseHelper(IntList cur, IntList pre) {
        if (cur == null) {
            return cur;
        }
        if (cur.rest == null) {
            cur.rest = pre;
            return pre;
        }
        IntList node = reverseHelper(cur.rest, cur);
        node.rest = cur;
        return cur;
    }


    public static void evenOdd(IntList lst) {
        if (lst == null || lst.rest == null) {
            return;
        }

        IntList origin = lst.rest;
        IntList oddNode = lst.rest;

        while (oddNode.rest != null) {
            lst.rest = lst.rest.rest;
            oddNode.rest = oddNode.rest.rest;
            lst = lst.rest;
            if (oddNode.rest != null) {
                oddNode = oddNode.rest;
            }
        }
        lst.rest = origin;

    }

    public static IntList[] partition(IntList lst, int k) {
        IntList[] array = new IntList[k];
        int index = 0;
        IntList L = reverse(lst);
        while (L != null) {
            IntList prevAtIndex = array[index];
            IntList next = L.rest;
            array[index] = L;
            array[index].rest = prevAtIndex;
            L = next;
            index = (index + 1) % array.length;
        }
        return array;
    }



}

