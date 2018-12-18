package Lab.Lab3;

import java.util.*;

public class CompKruskalEdge<E extends Edge> {
    private PriorityQueue<E> prioQ = new PriorityQueue<>();
    private List<E> [] connections;

    CompKruskalEdge(LinkedList <E>[] list) {
        insertEdgesIntoQueue(list);
        connections = new List[list.length];
    }

    private void insertEdgesIntoQueue(LinkedList<E> [] list) {
        for (LinkedList<E> edges : list) {
            ListIterator<E> itr = edges.listIterator();
            while (itr.hasNext()) {
                prioQ.offer(itr.next());
            }
        }
    }

    public Iterator<E> minimumSpanningTree() {
        while (!prioQ.isEmpty() && connections.length > 1) {
            E edge = prioQ.poll();

            int from = edge.from;
            int to = edge.to;

            if (connections[from] != connections[to]) {
                if (connections[from].size() > connections[to].size()) {
                    moveEdges(from, connections[to]);
                    connections[to] = connections[from];
                }
                else {
                    moveEdges(to,connections[from]);
                    connections[from] = connections[to];
                }
                connections[from].add(edge);
            }
        }
        return connections[0].iterator();
    }

/*
    public List compare(List<E> edges) {
        insertEdgesIntoQueue(edges);

        while (!(prioQ.isEmpty()) && connections.size() > 1) {
            Edge edge = prioQ.poll();

            if (connections.get(edge.from - 1).size() < connections.get(edge.to - 1).size()) {
                moveEdges(edge.to, connections.get(edge.from - 1));
                connections.get(edge.to).add((E) edge);
            }
            else {
                moveEdges(edge.from, connections.get(edge.to - 1));
                connections.get(edge.from).add((E) edge);
            }
        }

        return connections;
    }
*/

    private void moveEdges(int index, List<E> edges) {
        for (E edge : edges) {
            connections[index - 1].add(edge);
        }
    }

}
