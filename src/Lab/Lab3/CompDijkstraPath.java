package Lab.Lab3;

import java.util.*;

/**
 * Class that implements the OH version of the Dijkstra algorithm, that finds the shortest route from one to ONE node.
 *
 * @param <E> A type that extends Edge.
 */
class CompDijkstraPath<E extends Edge> {
    private final int from;
    private final int to;
    private LinkedList<E>[] edgeList;

    /**
     * The constructor that gets the necessary variables to call getShortestPath
     *
     * @param from     The node we came from.
     * @param to       The node we arrived to.
     * @param edgeList A list of all the edges.
     */
    CompDijkstraPath(int from, int to, LinkedList<E>[] edgeList) {
        this.from = from;
        this.to = to;
        this.edgeList = edgeList;
    }

    /**
     * Calculates the shortest path from node A to node B.
     *
     * @param startPoint Where a node begins.
     * @param endPoint   Where a node travels to.
     * @return An iterator with the shortest path from node A to node B.
     */
    Iterator<E> getShortestPath(int startPoint, int endPoint) {
        List<Integer> visitedNodes = new ArrayList<>();                      // List of all the visited nodes
        PriorityQueue<PqElement> pq = new PriorityQueue<>();                 // The priority queue
        pq.add(new PqElement(startPoint, 0, new LinkedList<>())); // Add first element to the pq

        while (!pq.isEmpty()) {
            PqElement currentNode = pq.poll();
            if (!visitedNodes.contains(currentNode.node)) {
                if (currentNode.node == endPoint) {
                    return currentNode.path.iterator();
                } else {
                    visitedNodes.add(currentNode.node);
                    for (E nodeOnEL : edgeList[currentNode.node])
                        if (!visitedNodes.contains(nodeOnEL.to)) {
                            double totalWeight = currentNode.pathWeight + nodeOnEL.getWeight();
                            LinkedList<E> newPath = (LinkedList<E>) currentNode.path.clone();
                            newPath.add(nodeOnEL);
                            pq.add(new PqElement(nodeOnEL.to, totalWeight, newPath));
                        }
                }
            }
        }
        return null;
    }

    /**
     * Weighted path that is suited for the PriorityQueue.
     */
    class PqElement implements Comparable<PqElement> {
        private int node;           // The node arrived at
        private double pathWeight;  // The cost of getting to "node" from the start node
        LinkedList<E> path;         // The path from the start node

        // Constructor
        PqElement(int node, double pathWeight, LinkedList<E> path) {
            this.node = node;
            this.pathWeight = pathWeight;
            this.path = path;
        }

        @Override
        public int compareTo(PqElement elem) {
            if (this.pathWeight < elem.pathWeight) {
                return -1;
            } else if (this.pathWeight > elem.pathWeight) {
                return 1;
            } else return 0;
        }
    }
}
