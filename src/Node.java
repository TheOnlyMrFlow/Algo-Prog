import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Node {
	public final double lonRadians;
	public final double latRadians;
	private List<Node> neighbors;

	private static double toRadians(double degrees) {
		return degrees * Math.PI / 180;
	}

	public Node(double lon, double lat) { // (node-cstor)
		lonRadians = toRadians(lon);
		latRadians = toRadians(lat);
		neighbors = new SingleLinkedList<Node>();
	}

	public void addNeighbor(Node other) {
		neighbors.add(other);
	}

	public Node[] getNeighbors() {
		return neighbors.toArray(new Node[neighbors.size()]);
	}
}






