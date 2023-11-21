package practise;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Description: disc05 No.2
 * @Author: whj
 * @Date: 2023-09-29 17:26
 */
public class OHIterator implements Iterator<OHRequest> {
    OHRequest curr;


    public OHIterator(OHRequest queue) {
        curr = queue;
    }

    public boolean isGood(String description) {
        return description != null && description.length() > 5;
    }

    @Override
    public boolean hasNext() {
        while (curr != null && !isGood(curr.description)) {
            curr = curr.next;
        }
        if (curr == null) {
            return false;
        }
        return true;
    }

    @Override
    public OHRequest next() {
        if (!hasNext()) {
            throw new NoSuchElementException("no element");
        }
        OHRequest item = curr;
        curr = curr.next;
        return item;
    }
}