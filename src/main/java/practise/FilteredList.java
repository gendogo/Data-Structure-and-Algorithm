package practise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @Description: dis05 exam 01
 * @Author: whj
 * @Date: 2023-09-29 20:28
 */
public class FilteredList<T> implements Iterable<T> {
    List<T> list;
    Predicate<T> pred;

    public FilteredList(List<T> L, Predicate<T> filter) {
        this.list = L;
        this.pred = filter;
    }

    public Iterator<T> iterator() {
        return new FilteredListIterator();
    }

    private class FilteredListIterator implements Iterator<T> {
        int index;

        public FilteredListIterator() {
            index = 0;
            moveIndex();
        }

        @Override
        public boolean hasNext() {
            return index < list.size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T answer = list.get(index);
            index += 1;
            moveIndex();
            return answer;
        }

        private void moveIndex() {
            while (hasNext() && !pred.test(list.get(index))) {
                index += 1;
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        FilteredList<Integer> filteredList = new FilteredList<>(objects, new Predicate<Integer>() {
            @Override
            public boolean test(Integer x) {
                return false;
            }
        });
    }

}
