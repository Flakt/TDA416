package Lab.Lab2;

public class SLCWithGet extends LinkedCollection {

    SLCWithGet(){
        super();
    }

    @Override
    public boolean add(Object element) {
        if (head == null) {
            head = new Entry(element, null);
            return true;
        } else {
            addObject(head, element);
        }
    }

    private boolean addObject(Entry entry, Object element) {
        if ()
            element.
    }




}
