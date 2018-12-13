import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;

public class GraphReaderNaive {
	public static String stdOrCustom;
	public static String weightedOrNot;
	public static int nbrElements;
	// TODO !
	public static void main(String[] args) {
		stdOrCustom = args.length > 0 ? args[0] : "std";
		weightedOrNot = args.length > 1 ? args[1] : "notWeighted";
		nbrElements = args.length > 2 ? Integer.parseInt(args[2]) : Integer.MAX_VALUE;
		
		System.out.println("Std or custom ? " + stdOrCustom);
		System.out.println("Weighted or not ? " + weightedOrNot);
		
		try {
			Node[] nodes= readGraph("./data/paris-vertices.txt", "./data/paris-edges.txt");
			System.err.println("returned " + nodes.length + " elts.");
			Plotter p = new Plotter(nodes);
			Date begin = new Date();
			p.plot(nodes);
			System.out.println("Took " + (new Date().getTime() - begin.getTime())/1000f + " sec to read");
			
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
	}
	
	private static Node[] readGraph(String verticesFileName, String edgesFileName) throws FileNotFoundException {
		
		int i = 0;
		
		Date begin = new Date();
		
		java.util.List<Node> nodes;
		
		nodes = GraphReaderNaive.stdOrCustom.compareTo("std") == 0 ? new ArrayList<Node>() : new SingleLinkedList<Node>();
		
		try {
			File verticesFile = new File(verticesFileName);
			File edgesFile = new File(edgesFileName);
			
			Scanner verticeScanner = new Scanner(verticesFile);
			Scanner edgeScanner = new Scanner(edgesFile);
			
			while (verticeScanner.hasNextLine() && i < GraphReaderNaive.nbrElements) {
				String line = verticeScanner.nextLine();
				String[] points = line.split(" ");
				Node n = new Node(Double.parseDouble(points[0]), Double.parseDouble(points[1]));
				nodes.add(n);
				i++;
			}
									
			while (edgeScanner.hasNextLine()) {
				String line = edgeScanner.nextLine();
				String[] data = line.split(" ");
				
				int fromIndex = GraphReaderNaive.stdOrCustom.compareTo("std") == 0 ? Integer.parseInt(data[0]) : nodes.size() - 2 - Integer.parseInt(data[0]);
				int toIndex = GraphReaderNaive.stdOrCustom.compareTo("std") == 0 ? Integer.parseInt(data[1]) : nodes.size() - 2 - Integer.parseInt(data[1]);

				if (fromIndex > GraphReaderNaive.nbrElements - 1 || toIndex > GraphReaderNaive.nbrElements - 1)
					continue;
				
				boolean bothDir = Integer.parseInt(data[2]) == 2;
				int distance = Integer.parseInt(data[3]);
				//System.out.println(fromIndex + "   " + toIndex);
				nodes.get(fromIndex).addNeighbor(nodes.get(toIndex), distance);
				if (bothDir) {
					nodes.get(toIndex).addNeighbor(nodes.get(fromIndex), distance);
				}
			}
			
		} catch (FileNotFoundException e) {
			throw e;
		}
		
		System.out.println("Took " + (new Date().getTime() - begin.getTime())/1000f + " sec to write");
		
		return nodes.toArray(new Node[nodes.size()]);

	}
}