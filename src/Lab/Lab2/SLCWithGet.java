package Lab.Lab2;

import java.util.Comparator;

public class SLCWithGet<E> extends LinkedCollection implements Comparable {

    SLCWithGet() {
        super();
    }

    @Override
    public int compareTo(Object otherO) {
        int result = Comparator.compare(this, otherO);
        return 0;
    }


    @Override
    public boolean add(Object element) {
        if (head == null) {
            head = new Entry(element, null);
            return true;
        } else {
            if (element.toString().compareTo(head.element.toString()) > 0) {
                Entry entryToAdd = new Entry(element, head.next);
                addObject(entryToAdd, head.next.element);
            }
            head = head.next;
        }
        return true;
    }

    private boolean addObject(Entry entry, Object element) {
        return true;
    }

    public E get(E e) {
        Entry t = find(e);
        return t;
        //return t == null ? null : t.element;



        return element:
    }


}
