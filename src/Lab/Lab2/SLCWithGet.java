package Lab.Lab2;

public class SLCWithGet<E extends Comparable<? super E>>
                        extends LinkedCollection<E>
                        implements CollectionWithGet<E> {

    /**
     * Adds element to the linked list.
     *
     * @param element the object to add into the list.
     * @return True if the element was added.
     */
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
     * @param currentEntry the entry to be compared.
     * @param elementToAdd the object to be inserted.
     * @return true if the object has been added to the list.
     */
    private boolean addObject(Entry currentEntry, E elementToAdd) {
        if (currentEntry.next == null) {
            currentEntry.next = new Entry(elementToAdd, null);
            return true;
        }
        if (currentEntry.element.compareTo(elementToAdd) >= 0) {
            Entry addedEntry = new Entry(elementToAdd, currentEntry.next);
            updateEntryLinks(head, addedEntry, addedEntry.next);
            return true;
        } else {
            addObject(currentEntry.next, elementToAdd);
        }
        return false;
    }

    /**
     * Iterates through the linked list by recursion and 
     * finds the element linked to endEntry and links it to the
     * newly inserted Entry.
     * 
     * @param iterator The current entry we use to iterate the linked list.
     * @param addedEntry the Entry the iterator.next need to be connected to.
     * @param nextEntry The Entry we use to know which Entry that should link to addedEntry.
     */
    private void updateEntryLinks(Entry iterator, Entry addedEntry, Entry nextEntry) {
        if (iterator.next == nextEntry){
            iterator.next = addedEntry;
        } else {
         updateEntryLinks(iterator.next, addedEntry, nextEntry);
        }
    }

    /**
     * Fetches the Entry that contains the value that is sought.
     *
     * @param searchedElement The element we want to find.
     * @return the searched element if it was found.
     */
    public E get(E searchedElement) {
        if (head == null) {
            return null;
        }

        Entry currentEntry = head;
        int compare = searchedElement.compareTo(currentEntry.element);

        while (compare != 0) {
            if(currentEntry.next == null) {
                return null;
            }
            currentEntry = currentEntry.next;
            compare = searchedElement.compareTo(currentEntry.element);
        }

        return currentEntry.element;
    }
}
