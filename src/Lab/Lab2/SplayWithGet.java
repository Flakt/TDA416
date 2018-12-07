package Lab.Lab2;

public class SplayWithGet<E extends Comparable<? super E>>
                          extends BinarySearchTree<E>
                          implements CollectionWithGet<E> {

    /**
     * Checks if an occurence of the argument exists in the collection, "splays" it
     * (moving the sought entry to the root of the tree while balancing accordingly)
     * and then returns the content after being moved to the root.
     *
     * @param e The dummy element to compare to.
     * @return An element  <tt>e'</tt> in the collection satisfying <tt>e.compareTo(e')
     *         == 0</tt>. If no element is found, <tt>null</tt> is returned
     */
    @Override
    public E get(E e) {
        if (splayFind(e, root)) {
            return root.element;
        } else {
            return null;
        }
    }

    // Searched for element in a splay tree
    private boolean splayFind(E elemToFind, Entry entry) {
        if (entry == null)
            return false;

        int cmp = elemToFind.compareTo(entry.element);
        if (cmp < 0) {
            if (entry.left == null){
                splay(entry);
                return false;
            } else {
                return splayFind(elemToFind, entry.left);
            }
        }
        else if (cmp > 0) {
            if (entry.right == null){
                splay(entry);
                return false;
            } else {
                return splayFind(elemToFind, entry.right);
            }
        }
        else {
            splay(entry);
            return true;
        }
    }

    // Rebalances the splay tree
    private void splay(Entry soughtEntry) {
        if (soughtEntry == root)
            return;

        if (soughtEntry != null) {
            Entry parentE = soughtEntry.parent;

            while (soughtEntry != root) {
                if (parentE == root) {
                    if (soughtEntry == parentE.left){
                        zig(root);
                    } else {
                        zag(root);
                    }
                    break;
                }
                else if (soughtEntry == parentE.left) {
                    if (parentE == parentE.parent.left){
                        zigZig(parentE.parent);
                    } else {
                        zigZag(parentE.parent);
                    }
                }
                else if (parentE == parentE.parent.right){
                    zagZag(parentE.parent);
                } else {
                    zagZig(parentE.parent);
                }
                soughtEntry = parentE.parent;
                parentE = soughtEntry.parent;
            }
        }
    }

    // Adds an element to a splay tree
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

    // Decides where in the splay tree to add the element
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

    // Rotate left twice
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

    // Rotate right twice
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
