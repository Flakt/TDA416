package Lab.Lab3;

import java.util.*;

/**
 * Class that implements the OH version of the Dijkstra algorithm, that finds the shortest route from one to ONE node.
 *
 * @param <E> A type that extends Edge.
 */
public class CompDijkstraPath<E extends Edge> {
    private final int from;
    private final int to;
    private final double weight; // TODO NEEDED?
    private LinkedList<E>[] edgeList;

    /**
     * The constructor that gets the necessary variables to call getShortestPath
     *
     * @param from The node we came from.
     * @param to The node we arrived to.
     * @param weight The weight of the edge.
     * @param edgeList A list of all the edges.
     */
    CompDijkstraPath(int from, int to, double weight, LinkedList<E>[] edgeList){
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.edgeList = edgeList;
    }

    /**
     * Calculates the shortest path from node A to node B.
     *
     * @param startPoint Where a node begins.
     * @param endPoint Where a node travels to.
     * @return An iterator with the shortest path from node A to node B.
     */
    Iterator<E> getShortestPath(int startPoint, int endPoint) {
        List<Integer> visitedNodes = new ArrayList<>();                      // List of all the visited nodes
        PriorityQueue<PqElement> pq = new PriorityQueue<>();                 // The priority queue
        pq.add(new PqElement(startPoint, 0, new LinkedList<>())); // Add first element to the pq

        while (!pq.isEmpty()) {                                             // While pq != empty
            PqElement currentNode = pq.poll();                              //   currentNode = first element in pq.
            if (!visitedNodes.contains(currentNode.node)) {                 //   if (node isn't visited)
                if (currentNode.node == endPoint) {                         //     if (node is end point)
                    return currentNode.path.iterator();                     //       return path
                } else {                                                    //     else
                    visitedNodes.add(currentNode.node);                     //       mark node visited
                    for (E nodeOnEL : edgeList[currentNode.node])           //       for every node on edgeList
                        if (!visitedNodes.contains(nodeOnEL.to)) {          //         if (not visited)
                            double totalWeight = currentNode.pathWeight + nodeOnEL.getWeight(); // Add weight to path
                            LinkedList<E> newPath = currentNode.path;                           // Create a new path
                            newPath.add(nodeOnEL);                                              // Add new node to path
                            pq.add(new PqElement(nodeOnEL.to, totalWeight, newPath));           // Add elem to pq
                        }
                }
            }
        }
        return null;
    }


    /**
     * Weighted path that is suited for the PriorityQueue.
     */
    class PqElement {
        private int node;           // The node arrived at
        private double pathWeight;  // The cost of getting to "node" from the start node
        LinkedList<E> path;         // The path from the start node

        // Constructor
        PqElement(int node, double pathWeight, LinkedList<E> path){
            this.node = node;
            this.pathWeight = pathWeight;
            this.path = path;
        }

        // TODO Implement comparable and override compareTo?
    }
}
