package Lab.Lab3;

import java.util.*;

public class DirectedGraph<E extends Edge> {

	private LinkedList <E>[] edgeList; // LinkedList of all the edges

	// Constructor that creates a list of all the edges
	public DirectedGraph(int noOfNodes) {
		edgeList = (LinkedList<E>[]) new LinkedList[noOfNodes];
	}

	public void addEdge(E edge) {
		;
	}

	/**
	 * Returns the shortest path from node A to node B.
	 *
	 * @param from The node we came from.
	 * @param to The node we arrived to.
	 * @return An iterator with the shortest path from node A to node B.
	 */
	public Iterator<E> shortestPath(int from, int to) {
		CompDijkstraPath compD = new CompDijkstraPath(from, to, 0, edgeList);

		return compD.getShortestPath(from, to);
	}

	/**
	 * Calculates the MST of the line traffic.
	 *
	 * @return the MST in form of an Iterator.
	 */
	public Iterator<E> minimumSpanningTree() {
		// Call CompKryskalEdge

		return null;
	}
}
