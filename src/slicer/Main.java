package slicer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		// Import Ascii stl file, src= http://www.thingiverse.com/thing:39751
		ImportSTL stl = new ImportSTL("OpenRC_Truggy_Rim.stl"); 
		try {
			stl.readFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Plane p = new Plane(new Vector(0, 0, 1)); // z axis
		float objHeigth = 50; // in mm
		int counter = 0;

		// Trivial Algorithm
		for (float cutter = 0; cutter < objHeigth; cutter += 0.2f) {
			counter++;
			p.setDistance(cutter);

			ArrayList<Vector> intersectPoints = new ArrayList<Vector>();

			// Position Vector of the plane
			Vector e = p.normal.mul(p.height);
			
			// Draw new Image
			BufferedImage img = new BufferedImage(1000, 1000,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = img.createGraphics();
			
			for (int i = 0; i < stl.triangleMesh.size(); i++) {

				ArrayList<Vector> ints = stl.triangleMesh.get(i)
						.intersectPlane(p);

				float[] x = new float[ints.size()];
				float[] y = new float[ints.size()];

				for (int j = 0; j < ints.size(); j++) {
					// calc position vector of poins in plane via hessian
					Vector s = e.sub(ints.get(j));
					x[j] = p.getV().scalarProduct(s);
					y[j] = p.getU().scalarProduct(s);
				}

				// draw resulting line in image
				if (x.length > 1) {
					g2d.drawLine((int) (x[0] * 10 + img.getWidth() / 2),
							(int) (y[0] * 10 + img.getHeight() / 2),
							(int) (x[1] * 10 + img.getWidth() / 2),
							(int) (y[1] * 10 + img.getHeight() / 2));
				}
				intersectPoints.addAll(ints);
			}
			System.out.println("#Intersects = " + intersectPoints.size());
			g2d.dispose();
			// write output image in file
			try {
				ImageIO.write(img, "png",
						new File("render/" + counter + ".png"));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
