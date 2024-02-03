package homework;

import java.util.Iterator;

/**
 * @Description: disc05 No.2
 * @Author: whj
 * @Date: 2023-09-29 18:47
 */
public class OHQueue implements Iterable<OHRequest> {
    private OHRequest first;

    public OHQueue(OHRequest queue) {
        this.first = queue;
    }

    @Override
    public Iterator<OHRequest> iterator() {
        return new TYIterator(first);
    }

    public static void main(String[] args) {
        OHRequest s5 = new OHRequest("I deleted all of my files", "Allyson", null);
        OHRequest s4 = new OHRequest("conceptual: what is Java", "Omar", s5);
        OHRequest s3 = new OHRequest("git: I never did lab 1", "Connor", s4);
        OHRequest s2 = new OHRequest("help", "Hug", s3);
        OHRequest s1 = new OHRequest("no I haven't tried stepping through", "Itai", s2);
        OHQueue queue = new OHQueue(s1);
        for (OHRequest item : queue) { //两个接口泛型必须指定OHRequest类型，否则需要强转
            System.out.println(item.name + " : " + item.description);
        }
    }
}