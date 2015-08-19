package slicer;

import java.util.ArrayList;
import java.util.Arrays;

public class Triangle {
	public Vector[] corner = new Vector[3];
	public Vector normal;

	/**
	 * Represents a triangle.
	 * @param one first edge
	 * @param two second edge
	 * @param three third edge
	 * @param normal normal of the triangle
	 */
	public Triangle(Vector one, Vector two, Vector three, Vector normal) {
		super();
		this.corner[0] = one;
		this.corner[1] = two;
		this.corner[2] = three;
		this.normal = normal;
	}

	/**
	 * Subtracts a vector s from the triangles corners.
	 * @param s vector which will be subtracted
	 * @return new Triangle
	 */
	public Triangle sub(Vector s) {
		corner[0] = corner[0].sub(s);
		corner[1] = corner[1].sub(s);
		corner[2] = corner[2].sub(s);
		return this;
	}

	public boolean checkVector(Vector v) {
		if (v.z >= 0 && v.z <= 1) {
			return true;
		}
		return false;

	}

	/**
	 * Returns a new position vector of the cutting point with the plane.
	 * @param p slicing plane
	 * @param i corner of a triangle
	 * @param j corner of a triangle
	 * @return position vector of the cutting point on the plane
	 */
	public Vector combination(Plane p, int i, int j) {

		float distanceI = p.distanceToPoint(corner[i]);
		float distanceJ = p.distanceToPoint(corner[j]);

		if (distanceI * distanceJ < 0) {
			float factor = distanceI / (distanceI - distanceJ);
			//System.out.println(factor);
			Vector edge = Vector.sub(corner[j], corner[i]);
			return corner[i].add(edge.mul(factor));
		} else if (distanceI == 0) {
			return corner[i];
		} else if (distanceJ == 0) {
			return corner[j];
		}
		return null;
	}

	/**
	 * Cuts a triangle with a plane.
	 * @param p slicing plane
	 * @return intersection points with plane
	 */
	public ArrayList<Vector> intersectPlane(Plane p) {

		ArrayList<Vector> result = new ArrayList<Vector>();

		Vector[] tmp = new Vector[3];

		tmp[0] = combination(p, 0, 1);
		tmp[1] = combination(p, 0, 2);
		tmp[2] = combination(p, 1, 2);

		for (int i = 0; i < 3; i++) {
			if (tmp[i] != null) {
				result.add(tmp[i]);
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "Triangle [corner=" + Arrays.toString(corner) + ", normal="
				+ normal + "]";
	}

}
