package Lab.Lab3;

import java.util.*;

/**
 * Class that implements the OH version of Kruskals algorithm, which given a tree finds the minimum spanning tree.
 *
 * @param <E> A type that extends Edge.
 */
public class CompKruskalEdge<E extends Edge> implements Comparator<E>{
    private PriorityQueue<E> pq;
    private List<E> [] cc;
    private LinkedList<E> [] edgeList;

    /**
     * The constructor which takes the lists of edges required for the algorithm
     *
     * @param edgeList the list which contains linked lists containing edges
     */
    CompKruskalEdge(LinkedList<E>[] edgeList) {
        this.edgeList = edgeList;
        cc = new List[edgeList.length];
        for (int i = 0; i < cc.length; i++) {
            cc[i] = new ArrayList<>();
        }
    }

    /**
     * Creates a minimum spanning tree through a modified version of Kruskal's algorithm.
     *
     * @return An iterator containing all edges which makes up the MST
     */
    Iterator<E> minimumSpanningTree() {
        insertEdgesIntoQueue();

        while (!pq.isEmpty() && cc.length > 1) {
            E edge = pq.poll();

            int from = edge.from;
            int to = edge.to;

            if (cc[from] != cc[to]) {
                if (cc[from].size() > cc[to].size()) {
                    moveEdges(from, cc[to]);
                    cc[to] = cc[from];
                }
                else {
                    moveEdges(to, cc[from]);
                    cc[from] = cc[to];
                }
                cc[from].add(edge);
            }
        }
        return cc[0].iterator();
    }

    /**
     * Inserts all edges within edgeList into a priority queue.
     */
    private void insertEdgesIntoQueue() {
        pq = new PriorityQueue<>(edgeList.length, new CompKruskalEdge<>(edgeList));

        for (LinkedList<E> edges : edgeList) {
            pq.addAll(edges);
        }
    }

    /**
     * Moves all edges from the list to the destination list in cc.
     *
     * @param dest  The index of cc to put the edges in
     * @param edges The edges to be moved to cc[dest]
     */
    private void moveEdges(int dest, List<E> edges) {
        for (E edge : edges) {
            cc[dest].add(edge);
            cc[edge.from] = cc[dest];
            cc[edge.to] = cc[dest];
        }
    }

    /**
     * Overridden method for comparing edges which is used to correctly instance
     * an ordered priority queue.
     *
     * @param o1 an edge to be compared
     * @param o2 an edge to be compared
     * @return an int that shows how the compare went
     */
    @Override
    public int compare(E o1, E o2) {
        if (o1.getWeight() < o2.getWeight()) {
            return -1;
        }
        else if (o1.getWeight() == o2.getWeight()) {
            return 0;
        }
        return 1;
    }
}
