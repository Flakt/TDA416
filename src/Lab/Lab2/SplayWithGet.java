package Lab.Lab2;

public class SplayWithGet<E extends Comparable<? super E>>
                          extends BinarySearchTree<E>
                          implements CollectionWithGet<E> {

    // Constructor
    SplayWithGet() {
        super();
    }

    @Override
    public boolean add(E elemToAdd) {
        if (root == null) {
            root = new Entry(elemToAdd, null);
        } else {
            addInTree(elemToAdd, root);
        }
        size++;
        return true;
    }

    // TODO: Consider how this method really should work
    private void addInTree(E elemToAdd, Entry entry) {
        if (elemToAdd.compareTo(entry.element) < 0) {
            if (entry.left == null) {
                entry.left = new Entry(elemToAdd, entry);
            } else {
                addInTree(elemToAdd, entry.left);
            }
        } else {
            if (entry.right == null) {
                entry.right = new Entry(elemToAdd, entry);
            } else {
                addInTree(elemToAdd, entry.right);
            }
        }
    }

    /**
     * Checks if an occurence of the argument exists in the collection,
     * "splays" it (moving the sought entry to the root of the tree while balancing accordingly).
     * and then returns the content after being moved to the root.
     *
     * @param e The dummy element to compare to.
     * @return An element  <tt>e'</tt> in the collection
     *         satisfying <tt>e.compareTo(e') == 0</tt>.
     *         If no element is found, <tt>null</tt> is returned
     */
    @Override
    public E get(E e) {
        if (splay(e)) {
            return root.element;
        } else {
            return null;
        }
    }

    // TODO: Optimize? No!
    private boolean splay(E e) {
        Entry soughtEntry = find(e, root);

        if (soughtEntry != null) {
            while (root.element != e) {
                if (soughtEntry.parent == root) {
                    zigOrZag(soughtEntry);
                }
                else if (soughtEntry == soughtEntry.parent.left) {
                    zigZigOrZigZag(soughtEntry);
                }
                else { zagZagOrZagZig(soughtEntry); }
            }
            return true;
        } else {
            return false;
        }
    }

    // Decides if we should rotate right twice, or rotate right and left
    private void zigZigOrZigZag(Entry entry) {
        if (entry.parent.parent.left == entry.parent) {
            zigZig(entry.parent.parent);
        }
        else {
            zigZag(entry.parent.parent);
        }
    }

    // Decides if we should rotate left twice, or rotate left and right
    private void zagZagOrZagZig(Entry entry) {
        if (entry.parent.parent.right == entry.parent) {
            zagZag(entry.parent.parent);
        }
        else {
            zagZig(entry.parent.parent);
        }
    }

    // Decides if we should rotate right or left
    private void zigOrZag(Entry entry) {
        if (entry == entry.parent.left) {
            zig(entry.parent);
        } else {
            zag(entry.parent);
        }
    }

    /* Rotate left twice
           x                     z
          / \                   / \
         A   y                 y   D
            / \      -->      / \
           B   z             x   C
              / \           / \
             C   D         A   B
    */
    private void zagZag(Entry x) {
        // Rotate left
        Entry y = x.right;
        Entry z = x.right.right;

        E temp = y.element;
        y.element = z.element;
        z.element = temp;
        y.right = z.right;
        if (y.right != null)
            y.right.parent = y;
        z.right = z.left;
        z.left = y.left;
        if (z.left != null)
            z.left.parent = z;
        y.left = z;

        // Rotate left again
        temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.right = y.right;
        if (x.right != null)
            x.right.parent = x;
        y.right = y.left;
        y.left = x.left;
        if (y.left != null)
            y.left.parent = y;
        x.left = y;
    }

    /* Rotate right twice:
              x               z
             / \             / \
            y   D    -->    A   y
           / \                 / \
          z   C               B   x
         / \                     / \
        A   B                   C   D
    */
    private void zigZig(Entry x) {
        // Rotate right
        Entry y = x.left;
        Entry z = x.left.left;

        E temp = y.element;
        y.element = z.element;
        z.element = temp;
        y.left = z.left;
        if (y.left != null)
            y.left.parent = y;
        z.left = z.right;
        z.right = y.right;
        if (z.right != null)
            z.right.parent = z;
        y.right = z;

        // Rotate right again
        temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.left = y.left;
        if (x.left != null)
            x.left.parent = x;
        y.left = y.right;
        y.right = x.right;
        if (y.right != null)
            y.right.parent = y;
        x.right = y;
    }

    // Code below copied from AVL_Tree

    /* Rotate right:
             x'                 y'
            / \                / \
           y'  C   -->        A   x'
          / \                    / \
         A   B                  B   C
    */
    private void zig(Entry x) {
        Entry y = x.left;
        E temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.left = y.left;
        if ( x.left != null )
            x.left.parent = x;
        y.left    = y.right;
        y.right   = x.right;
        if ( y.right != null )
            y.right.parent = y;
        x.right   = y;
    } //   rotateRight
    // ========== ========== ========== ==========

    /* Rotate left:
              x'                 y'
             / \                / \
            A   y'  -->        x'  C
               / \            / \
              B   C          A   B
    */
    private void zag(Entry x) {
        Entry  y  = x.right;
        E temp    = x.element;
        x.element = y.element;
        y.element = temp;
        x.right   = y.right;
        if ( x.right != null )
            x.right.parent  = x;
        y.right   = y.left;
        y.left    = x.left;
        if ( y.left != null )
            y.left.parent   = y;
        x.left    = y;
    } //   rotateLeft
    // ========== ========== ========== ==========

    /* Rotate left, then right:
              x'                  z'
             / \                /   \
            y'  D   -->        y'    x'
           / \                / \   / \
          A   z'             A   B C   D
             / \
            B   C
    */
    private void zagZig(Entry x) {
        Entry y = x.left, z = x.left.right;
        E e = x.element;
        x.element = z.element;
        z.element = e;
        y.right = z.left;
        if ( y.right != null )
            y.right.parent = y;
        z.left = z.right;
        z.right = x.right;
        if ( z.right != null )
            z.right.parent = z;
        x.right = z;
        z.parent = x;
    }  //  doubleRotateRight, aka. fakeNews
    // ========== ========== ========== ==========

    /* Rotate right, then left:
               x'                  z'
              / \                /   \
             A   y'   -->       x'    y'
                / \            / \   / \
               z   D          A   B C   D
              / \
             B   C
     */
    private void zigZag(Entry x) {
        Entry y = x.right, z = x.right.left;
        E e  = x.element;
        x.element = z.element;
        z.element = e;
        y.left    = z.right;
        if ( y.left != null )
            y.left.parent = y;
        z.right   = z.left;
        z.left    = x.left;
        if ( z.left != null )
            z.left.parent = z;
        x.left    = z;
        z.parent  = x;
    } //  doubleRotateLeft, aka. fakeNews
}
