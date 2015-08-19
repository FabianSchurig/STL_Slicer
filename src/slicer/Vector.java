package slicer;

public class Vector {
	public float x, y, z;

	/**
	 * Represents a 3 dimensional Vector.
	 * @param x coordinate
	 * @param y coordinate
	 * @param z coordinate
	 */
	public Vector(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Calculates the scalar with a vector p
	 * @param p
	 * @return scalarProduct
	 */
	public float scalarProduct(Vector p) {
		return this.x * p.x + this.y * p.y + this.z * p.z;
	}

	/**
	 * length of a vector
	 * @return
	 */
	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * checks if it is a null vector
	 * @return
	 */
	public boolean isNull() {
		if (this.x == 0 && this.y == 0 && this.z == 0) {
			return true;
		}
		return false;
	}

	/**
	 * vector multiplication with a factor
	 * @param factor 
	 * @return
	 */
	public Vector mul(float factor) {
		return new Vector(this.x * factor, this.y * factor, this.z * factor);
	}

	/**
	 * vector division with a divisor
	 * @param divisor
	 * @return
	 */
	public Vector div(float divisor) {
		return new Vector(this.x / divisor, this.y / divisor, this.z / divisor);
	}

	/**
	 * vector addition with another vector
	 * @param v
	 * @return
	 */
	public Vector add(Vector v) {
		return new Vector(this.x + v.x, this.y + v.y, this.z + v.z);
	}

	/**
	 * vector subtraction with another vector
	 * @param v
	 * @return
	 */
	public Vector sub(Vector v) {
		return new Vector(this.x - v.x, this.y - v.y, this.z - v.z);
	}

	/**
	 * static vector addition of two vectors a and b
	 * @param a vector 
	 * @param b vector
	 * @return new vector
	 */
	public static Vector add(Vector a, Vector b) {
		return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
	}

	/**
	 * static vector subtraction of two vectors a and b
	 * @param a vector
	 * @param b vector
	 * @return
	 */
	public static Vector sub(Vector a, Vector b) {
		return new Vector(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

}
