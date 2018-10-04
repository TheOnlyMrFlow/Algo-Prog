class Plotter {
	private final Projector2D proj;
	static {
		StdDraw.enableDoubleBuffering();
	}

	Plotter(Node[] nodes) {
		proj = new Projector2D(nodes);
	}

	public void plot(Node n) {
		bufferedPlot(n);
		StdDraw.show();
	}

	public void plot(Node[] n) {
		for (int i = 0; i != n.length; ++i) {
			bufferedPlot(n[i]);
		}
		StdDraw.show();
	}

	private void bufferedPlot(Node n) {
		double[] coords = proj.project(n);
		Node[] neighbors = n.getNeighbors();
		for (int i = 0; i != neighbors.length; ++i) {
			double[] tmp = proj.project(neighbors[i]);
			StdDraw.line(coords[0], coords[1], tmp[0], tmp[1]);
		}
	}
}