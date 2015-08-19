package slicer;

public class Line {
	public Vector[] vec = new Vector[2];

	/**
	 * Intersection Line of Triangle with a Plane
	 */
	public Line(Vector start, Vector end) {
		super();
		this.vec[0] = start;
		this.vec[1] = end;
	}

}
