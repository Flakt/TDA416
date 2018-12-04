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

    private void addInTree(E newElem, Entry root) {
    }

    private boolean splay( E e ) {
        Entry soughtEntry = find(e, root);
        if (soughtEntry != null) {
            while (root.element != e) {
                
            }
            return true;
        } else {
            return false;
        }
    }


    private void leftThenRight ( Entry x ) {
        // First rotate left
        Entry y = x.left;
        Entry z = y.right;

        E temp = y.element;
        y.element = z.element;
        z.element = temp;
        y.right = z.right;
        if ( y.right != null ) {
            y.right.parent = z;
        }
        z.right = z.left;
        z.left = y.left;
        if ( z.left != null ) {
            z.left.parent = z;
        }
        y.left = z;

        // Now rotate right
        temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.left = y.left;
        if ( x.left != null ) {
            x.left.parent = x;
        }
        y.left = y.right;
        y.right = x.right;
        if ( y.right != null ) {
            y.right.parent = y;
        }
        x.right = y;
    }

    private void rightThenLeft ( Entry x ) {
        // First rotate right
        Entry y = x.right;
        Entry z = y.left;

        E temp = y.element;
        y.element = z.element;
        z.element = temp;
        y.left = z.left;
        if ( y.left != null ) {
            y.left.parent = z;
        }
        z.left = z.right;
        z.right = y.right;
        if ( z.right != null ) {
            z.right.parent = z;
        }
        y.right = z;

        // Now rotate left
        temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.right = y.right;
        if ( x.right != null ) {
            x.right.parent = x;
        }
        y.right = y.left;
        y.left = x.left;
        if ( y.left != null ) {
            y.left.parent = y;
        }
        x.left = y;

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
    private void doubleRotateRight( Entry x ) {
        Entry   y = x.left,
                z = x.left.right;
        E       e = x.element;
        x.element = z.element;
        z.element = e;
        y.right   = z.left;
        if ( y.right != null )
            y.right.parent = y;
        z.left    = z.right;
        z.right   = x.right;
        if ( z.right != null )
            z.right.parent = z;
        x.right   = z;
        z.parent  = x;
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
    private void doubleRotateLeft( Entry x ) {
        Entry  y  = x.right,
                z  = x.right.left;
        E      e  = x.element;
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
