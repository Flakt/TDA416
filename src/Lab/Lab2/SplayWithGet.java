package Lab.Lab2;

public class SplayWithGet<E extends Comparable<? super E>>
                                   extends BinarySearchTree<E>
                                   implements CollectionWithGet<E>{

    SplayWithGet() {
        super();
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

    @Override
    public boolean add (E elem) {
        if (root == null) {
            root = new Entry(elem, null);
        } else {
            addInTree(elem, root);
        }
        size++;
        return true;
    }

    // TODO: Consider how this method really should work
    private void addInTree(E newElem, Entry entry) {
        if (newElem.compareTo(entry.element) < 0) {
            if (entry.left == null) {
                entry.left = new Entry(newElem, entry);
            } else {
                addInTree(newElem, entry.left);
            }
        } else {
            if (entry.right == null) {
                entry.right = new Entry(newElem, entry);
            } else {
                addInTree(newElem, entry.right);
            }
        }
    }

    // TODO: Optimize?
    private boolean splay( E e ) {
        Entry soughtEntry = find(e, root);
        if (soughtEntry != null) {
            while (root.element != e) {
                if (soughtEntry.parent == root) {
                    zigOrZag(soughtEntry);
                }
                else if (soughtEntry == soughtEntry.parent.left) {
                    zagZagOrZagZig(soughtEntry);
                }
                else if (soughtEntry == soughtEntry.parent.right) {
                    zigZigOrZigZag(soughtEntry);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private void zigZigOrZigZag(Entry entry) {
        if (entry.parent.parent.left == entry.parent) {
            rightThenRight(entry.parent.parent);
        }
        else {
            rightThenLeft(entry.parent.parent);
        }
    }


    private void zagZagOrZagZig(Entry entry) {
        if (entry.parent.parent.left == entry.parent) {
            leftThenRight(entry.parent.parent);
        }
        else {
            leftThenleft(entry.parent.parent);
        }
    }

    private void zigOrZag(Entry entry) {
        if (entry == entry.parent.left) {
            rotateRight(entry.parent);
        } else {
            rotateLeft(entry.parent);
        }
    }


    private void leftThenleft ( Entry x ) {
        // First rotate left
        Entry y = x.right, z = x.right.right;

        E temp = y.element;
        y.element = z.element;
        z.element = temp;
        y.right = z.right;
        if (y.right != null) {
            y.right.parent = y;
        }
        z.right = z.left;
        z.left = y.left;
        if (z.left != null) {
            z.left.parent = z;
        }
        y.left = z;

        // Then left again
        temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.right = y.right;
        if (x.right != null) {
            x.right.parent = x;
        }
        y.right = y.left;
        y.left = x.left;
        if (y.left != null) {
            y.left.parent = y;
        }
        x.left = y;

    }

    private void rightThenRight( Entry x ) {
        // First rotate right
        Entry y = x.left, z = x.left.left;

        E temp = y.element;
        y.element = z.element;
        z.element = temp;
        y.left = z.left;
        if (y.left != null) {
            y.left.parent = y;
        }
        z.left = z.right;
        z.right = y.right;
        if (z.right != null) {
            z.right.parent = z;
        }
        y.right = z;

        // Then right again
        temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.left = y.left;
        if (x.left != null) {
            x.left.parent = x;
        }
        y.left = y.right;
        y.right = x.right;
        if (y.right != null) {
            y.right.parent = y;
        }
        x.right = y;
    }

    // Code below copied from AVL_Tree

    /* Rotera 1 steg i hogervarv, dvs
             x'                 y'
            / \                / \
           y'  C   -->        A   x'
          / \                    / \
         A   B                  B   C
   */
    private void rotateRight( Entry x ) {
        Entry y = x.left;
        E temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.left = y.left;
        if ( x.left != null ) {
            x.left.parent = x;
        }
        y.left    = y.right;
        y.right   = x.right;
        if ( y.right != null ) {
            y.right.parent = y;
        }
        x.right   = y;
    } //   rotateRight
    // ========== ========== ========== ==========

    /* Rotera 1 steg i vanstervarv, dvs
              x'                 y'
             / \                / \
            A   y'  -->        x'  C
               / \            / \
              B   C          A   B
    */
    private void rotateLeft( Entry x ) {
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

    /* Rotera 2 steg i hogervarv, dvs
              x'                  z'
             / \                /   \
            y'  D   -->        y'    x'
           / \                / \   / \
          A   z'             A   B C   D
             / \
            B   C
    */
    private void leftThenRight( Entry x ) {
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
    }  //  doubleRotateRight
    // ========== ========== ========== ==========

    /* Rotera 2 steg i vanstervarv, dvs
               x'                  z'
              / \                /   \
             A   y'   -->       x'    y'
                / \            / \   / \
               z   D          A   B C   D
              / \
             B   C
     */
    private void rightThenLeft( Entry x ) {
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
    } //  doubleRotateLeft
}
