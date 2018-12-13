import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class Node implements Comparable {
	public final double lonRadians;
	public final double latRadians;
	//private List<Node> neighbors;
	private Object neighbors;

	private static double toRadians(double degrees) {
		return degrees * Math.PI / 180;
	}

	public Node(double lon, double lat) { // (node-cstor)
		lonRadians = toRadians(lon);
		latRadians = toRadians(lat);
		
		if (GraphReaderNaive.weightedOrNot.compareTo("notWeighted") == 0) {
			
			neighbors = GraphReaderNaive.stdOrCustom.compareTo("std") == 0 ? new ArrayList<Node>() : new SingleLinkedList<Node>();
		}
		
		else {
			neighbors = GraphReaderNaive.stdOrCustom.compareTo("std") == 0 ? new TreeMap<Node, Integer>() : new ListMap<Node, Integer>();
		}
		
	}

	public void addNeighbor(Node other, int distance) {
		if (GraphReaderNaive.weightedOrNot.compareTo("notWeighted") == 0) {
			((List)neighbors).add(other);
		}
		else {
			((Map)neighbors).put(other, distance);
		}
	}

	public Node[] getNeighbors() {
				
		if (GraphReaderNaive.weightedOrNot.compareTo("notWeighted") == 0) {
			return ((List<Node>)neighbors).toArray(new Node[((List)neighbors).size()]);
		}
		else {
			return (Node[]) ((Map<Node, Integer>)neighbors).keySet().toArray(new Node[((Map)neighbors).size()]);
		}
		
	}

	@Override
	public int compareTo(Object other) {
		return this.equals(other) ? 0 : 1;
	}
}






