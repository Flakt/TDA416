package Lab.Lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class CompKruskalEdge<E extends Edge> {
    private PriorityQueue<E> prioQ = new PriorityQueue<>();
    private List<List<E>> connections = new ArrayList<>();

    CompKruskalEdge() {

    }

    private void insertEdgesIntoQueue(List<E> edges) {
        for (E edge : edges) {
            prioQ.offer(edge);
        }
    }


    private void insertEdgeIntoCc(E edge, int index) {
        connections.get(index).add(edge);
    }

    public List compare(List<E> edges) {
        insertEdgesIntoQueue(edges);

        while (!(prioQ.isEmpty()) && connections.size() > 1) {
            Edge edge = prioQ.poll();

            if (connections.get(edge.from - 1).size() < connections.get(edge.to - 1).size()) {
                moveEdges(edge.to, connections.get(edge.from - 1));
                insertEdgeIntoCc((E) edge, edge.to);
            }
            else {
                moveEdges(edge.from, connections.get(edge.to - 1));
                insertEdgeIntoCc((E) edge, edge.from);
            }
        }

        return connections;
    }


    private void moveEdges(int index, List<E> edges) {
        for (E edge : edges) {
            connections.get(index - 1).add(edge);
        }
    }
    
}
