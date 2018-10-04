class Projector2D {
	public final double[] meanFactorLon;
	public final double[] meanFactorLat;

	public Projector2D(Node[] nodes) {
		meanFactorLon = new double[2];
		meanFactorLat = new double[2];
		double minLon = Double.POSITIVE_INFINITY, maxLon = Double.NEGATIVE_INFINITY;
		double minLat = Double.POSITIVE_INFINITY, maxLat = Double.NEGATIVE_INFINITY;
		for (int i = 0; i != nodes.length; ++i) {
			minLon = Math.min(minLon, nodes[i].lonRadians);
			maxLon = Math.max(maxLon, nodes[i].lonRadians);
			minLat = Math.min(minLat, nodes[i].latRadians);
			maxLat = Math.max(maxLat, nodes[i].latRadians);
		}
		meanFactorLon[0] = (minLon + maxLon) / 2.;
		meanFactorLon[1] = Math.sin(maxLon - meanFactorLon[0]);
		meanFactorLat[0] = (minLat + maxLat) / 2.;
		meanFactorLat[1] = Math.sin(maxLat - meanFactorLat[0]);
	}

	private static double scale(double r, double[] meanFactor) {
		return Math.sin(r - meanFactor[0]) / meanFactor[1];
	}

	public double[] project(Node n) {
		return new double[] { scale(n.lonRadians, meanFactorLon), scale(n.latRadians, meanFactorLat) };
	}
}