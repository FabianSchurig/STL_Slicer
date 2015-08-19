package slicer;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ImportSTL {
	public ArrayList<Triangle> triangleMesh;
	public String fileName;
	public String solid;
	private Scanner sc;
	private Triangle tri;

	public ImportSTL(String fileName) {
		super();
		this.fileName = fileName;
		triangleMesh = new ArrayList<Triangle>();
	}

	/**
	 * Reads an ASCII STL File
	 * @throws IOException
	 */
	public void readFile() throws IOException {
		sc = new Scanner(new FileReader(fileName));
		tri = null;
		Vector normal = null;

		while (sc.hasNext()) {
			String next = sc.next();
			// System.out.println(next);
			if (next.equals("solid") && sc.hasNext()) {
				solid = next;
			}
			if (next.equals("facet")) {
				tri = null;
			}
			if (next.equals("normal")) {
				float x = readNumber(sc.next());
				float y = readNumber(sc.next());
				float z = readNumber(sc.next());
				normal = new Vector(x, y, z);
				// System.out.println(normal.toString());
			}
			if (next.equals("vertex")) {
				float x = readNumber(sc.next());
				float y = readNumber(sc.next());
				float z = readNumber(sc.next());
				Vector vectorX = new Vector(x, y, z);
				// System.out.println(vectorX.toString());
				sc.next();
				x = readNumber(sc.next());
				y = readNumber(sc.next());
				z = readNumber(sc.next());
				Vector vectorY = new Vector(x, y, z);
				// System.out.println(vectorY.toString());
				sc.next();
				x = readNumber(sc.next());
				y = readNumber(sc.next());
				z = readNumber(sc.next());
				Vector vectorZ = new Vector(x, y, z);
				// System.out.println(vectorZ.toString());
				triangleMesh
						.add(new Triangle(vectorX, vectorY, vectorZ, normal));
				// System.out.println(triangleMesh.toString());
			}
			if (next.equals("endfacet")) {

			}

		}

		System.out.println(triangleMesh.toString());

	}

	/**
	 * reads the values of a string
	 * @param s string to get valueOf
	 * @return
	 */
	public float readNumber(String s) {
		return (float) (Double.valueOf(s)).doubleValue();
	}

}
