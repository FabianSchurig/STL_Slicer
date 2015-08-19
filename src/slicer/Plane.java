package slicer;

public class Plane {
	public float height; // height to origin
	public Vector normal;

	/**
	 * Represents a Cutting Plane
	 * @param normal Normal of the plane. Most times in z direction.
	 */
	public Plane(Vector normal) {
		super();
		this.height = 0;
		this.normal = normal;
	}

	/**
	 * returns a support vector u of the plane
	 * @return
	 */
	public Vector getU() {
		Vector u = new Vector(-normal.y, normal.x, 0);
		if (u.isNull()) {
			u = new Vector(-normal.z, 0, normal.x);
		}
		return u;
	}

	/**
	 * returns a support vector v of the plane
	 * @return
	 */
	public Vector getV() {
		Vector v = new Vector(0, -normal.z, normal.y);
		if (v.isNull()) {
			v = new Vector(-normal.z, 0, normal.x);
		}
		return v;
	}

	public float getDistance() {
		return height;
	}

	public void setDistance(float distance) {
		this.height = distance;
	}

	public Vector getNormal() {
		return normal;
	}

	public void setNormal(Vector normal) {
		this.normal = normal;
	}

	/**
	 * returns the distance of a vertex to the plane
	 * @param vertex
	 * @return distance
	 */
	public float distanceToPoint(Vector vertex) {
		return vertex.scalarProduct(normal) - height;
	}

}
