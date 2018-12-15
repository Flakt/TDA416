package Lab.Lab3;

import java.util.*;

public class CompDijkstraPath<E extends Edge> {
    private PriorityQueue<PqElement> pq = new PriorityQueue<>(); // The priority queue
    private List<Integer> visitedNodes = new ArrayList<>();      // List of all the visited nodes

    private final int from;
    private final int to;
    private final double weight;
    private LinkedList<E>[] edgeList;

    // Constructor
    CompDijkstraPath(int from, int to, double weight, LinkedList<E>[] edgeList){
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.edgeList = edgeList;
    }

    Iterator<E> getShortestPath(int startPoint, int endPoint){
        pq.add(new PqElement(startPoint, 0, new LinkedList<>())); // Add first element to the pq

        while (!pq.isEmpty()){                              // While pq != empty
            PqElement currentNode = pq.poll();              //   currentNode = first element in pq.
            if (!visitedNodes.contains(currentNode.node)){  //   if (node isn't visited)
                if (currentNode.node == endPoint){          //     if (node is end point)
                    return currentNode.path.iterator();     //       return path
                } else {                                    //     else
                    visitedNodes.add(currentNode.node);     //       mark node visited


                    // TODO for every node on EL (make a for-each loop)
                      if (!visitedNodes.contains(nodeOnEL)){ //         if (not visited)
                          pq.add(nodeOnEl);                  //           Add node to pq
                      }
                }
            }
        }
        return null;
    }


    class PqElement {
        private int node;           // The node arrived at
        private int totalWeight;    // The cost of getting to "node" from the start node
        LinkedList<E> path;         // The path from the start node

        // Constructor
        PqElement(int node, int totalWeight, LinkedList<E> path){
            this.node = node;
            this.totalWeight = totalWeight;
            this.path = path;
        }
    }


}
