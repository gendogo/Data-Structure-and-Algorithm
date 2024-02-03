package homework;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @Description: dis05 exam 02
 * @Author: whj
 * @Date: 2023-09-29 23:03
 */
public class IteratorOfIterators implements Iterator<Integer> {
    LinkedList<Iterator<Integer>> iterators;


    public IteratorOfIterators(List<Iterator<Integer>> a) {
        iterators = new LinkedList<>();
        for (Iterator<Integer> iterator : a) {
            if(iterator.hasNext()){
                iterators.add(iterator);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !iterators.isEmpty();
    }

    @Override
    public Integer next() {
        if(!hasNext()){
            throw new NoSuchElementException("no Iterator item");
        }
        Iterator<Integer> iterator = iterators.removeFirst();
        int ans = iterator.next();
        if(iterator.hasNext()){
            iterators.addLast(iterator);
        }

        return ans;
    }
}
