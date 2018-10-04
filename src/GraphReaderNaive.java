import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class GraphReaderNaive {
	// TODO !
	public static void main(String[] args) {
		//final String verticesFilename = args[0];
		//final String edgesFilename = args[1];
		try {
			//System.out.println(args[0]);
			Node[] nodes= readGraph("./data/paris-vertices.txt", "./data/paris-edges.txt");
			System.err.println("returned " + nodes.length + " elts.");
			Plotter p = new Plotter(nodes);
			p.plot(nodes);
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
	}
	
	private static Node[] readGraph(String verticesFileName, String edgesFileName) throws FileNotFoundException {
		
		SingleLinkedList<Node> nodes = new SingleLinkedList<Node>();
		
		try {
			File verticesFile = new File(verticesFileName);
			File edgesFile = new File(edgesFileName);
			
			Scanner verticeScanner = new Scanner(verticesFile);
			Scanner edgeScanner = new Scanner(edgesFile);
			
			while (verticeScanner.hasNextLine()) {
				String line = verticeScanner.nextLine();
				String[] points = line.split(" ");
				Node n = new Node(Double.parseDouble(points[0]), Double.parseDouble(points[1]));
				nodes.add(n); 
			}
			
			System.out.println(nodes.size());
						
			while (edgeScanner.hasNextLine()) {
				String line = edgeScanner.nextLine();
				String[] data = line.split(" ");
				int fromIndex = nodes.size() - 2  - Integer.parseInt(data[0]);
				int toIndex = nodes.size() - 2 - Integer.parseInt(data[1]);
				boolean bothDir = Integer.parseInt(data[2]) == 2;
				//int distance = Integer.parseInt(data[3]);
				System.out.println(fromIndex + "   " + toIndex);
				nodes.get(fromIndex).addNeighbor(nodes.get(toIndex));
				if (bothDir) {
					nodes.get(toIndex).addNeighbor(nodes.get(fromIndex));
				}			
			}
			
			
		} catch (FileNotFoundException e) {
			throw e;
		}
		
		return nodes.toArray(new Node[nodes.size()]);

	}
}