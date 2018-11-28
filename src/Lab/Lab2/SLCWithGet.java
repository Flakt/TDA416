package Lab.Lab2;

public class SLCWithGet<E extends Comparable<? super E>>
                        extends LinkedCollection<E>
                        implements CollectionWithGet<E> {

    SLCWithGet() {
        super();
    }

    public boolean add(E element) {
        if (head == null) {
            head = new Entry(element, null);
            return true;
        } else {
            return addObject(head, element);
        }
    }

    /**
     * Recursively checks if an Entries element is higher lexicographicaly
     * than the element to be inserted. It then creates a new Entry and links
     * it appropriately.
     *
     * @param current the entry to be compared.
     * @param element the object to be inserted.
     * @return true if the object has been added to the list.
     */
    private boolean addObject(Entry current, E element) {
        if (current.next == null) {
            current.next = new Entry(element, null);
            return true;
        }
        if (current.element.toString().compareTo(element.toString()) >= 0) {
            Entry addedEntry = new Entry(element, current);
            updateEntryBefore(addedEntry, current, head);
            return true;
        } else {
            addObject(current.next, element);
        }
        return false;
    }

    /**
     * Iterates through the linked list by recursion and 
     * finds the element linked to endEntry and links it to the
     * newly inserted Entry.
     * 
     * @param addedEntry the Entry to be linked to
     * @param endEntry the Entry to stop at
     * @param itr the current entry to be checked
     */

    private void updateEntryBefore(Entry addedEntry, Entry endEntry, Entry itr) {
        if (itr.next == endEntry) {
            itr.next = addedEntry;
        } else {
            updateEntryBefore(addedEntry, endEntry, itr.next);
        }
    }

    /**
     * Fetches the Entry that contains the value that is sought.
     *
     * @param element The element we want to find.
     * @return The Entry that matches the searched value.
     */
    public E get(E element) {
        return get(element, head);
    }

    // Helper method for "get" that adds an argument which allows us to do a recursive call
    private E get(E element, Entry head){
        if(head == null){
            return null;
        } else if(head.element == element) {
            return head.element;
        } else {
            get(element, head.next);
        }
        return head.element; // This will never happen
    }
}
