package Lab.Lab3;

import java.util.*;

public class DirectedGraph<E extends Edge> {
	private LinkedList<E>[] edgeList; // LinkedList of all the Edges

	// Constructor that creates a list of all the Edges
	public DirectedGraph(int noOfNodes) {
		edgeList = (LinkedList<E>[]) new LinkedList[noOfNodes];

		for (int i = 0; i < edgeList.length; i++) {
			edgeList[i] = new LinkedList<>();
		}
	}

	/**
	 * Adds an edge to the edgeList.
	 *
	 * @param edge The edge to add.
	 */
	public void addEdge(E edge) {
		if (edge != null) {
			edgeList[edge.getSource()].add(edge);
		}
	}

	/**
	 * Returns the shortest path from node A to node B.
	 *
	 * @param from The node we are traveling from.
	 * @param to   The node we want to arrive at.
	 * @return An iterator with the shortest path from node A to node B.
	 */
	public Iterator<E> shortestPath(int from, int to) {
		CompDijkstraPath compD = new CompDijkstraPath(edgeList);

		return compD.getShortestPath(from, to);
	}

	/**
	 * Calculates The MST of the line traffic.
	 *
	 * @return The MST in form of an Iterator.
	 */
	public Iterator<E> minimumSpanningTree() {
		CompKruskalEdge compE = new CompKruskalEdge(edgeList);

		return compE.minimumSpanningTree();
	}
}
