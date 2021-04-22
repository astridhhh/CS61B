public class NBody{
	public static double readRadius(String fileName){
			In in = new In(fileName);
			in.readLine();
			double radius = in.readDouble();	
			return radius;
	}

	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int n = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[n];
		for(int i = 0; i < n ; i++){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xP, yP, xV, yV, m, img);
		}
		return planets;
	}
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] p = readPlanets(filename);
		String imgBkg = "images/starfield.jpg";
		String audio = "audio/2001.mid";
		/** Set up the universe. */
		StdDraw.setScale(-radius, radius);

		/** Clears the drawing window. */
		StdDraw.clear();

		/** Draw backgroud. */
		StdDraw.picture(0, 0, imgBkg);

		for(int i = 0; i < p.length; i++){
			p[i].draw();
		}

		StdDraw.enableDoubleBuffering();
		double time = 0;
		while (time < T){
			StdDraw.clear();
			double[] xForces = new double[p.length];
			double[] yForeces = new double[p.length];
 			for(int i =0; i < p.length; i++){
 				xForces[i] = p[i].calcNetForceExertedByX(p);
 				yForeces[i] = p[i].calcNetForceExertedByY(p);	
 			}

 			for(int i = 0; i < p.length; i++){
 				p[i].update(dt, xForces[i], yForeces[i]);
 			}

 			StdDraw.picture(0, 0, imgBkg);
 			for (int i = 0; i < p.length; i++){
 				p[i].draw();
 			}
 			StdDraw.show();
 			StdDraw.pause(10);
			time += dt;
		}
		StdOut.printf("%d\n", p.length);
		StdOut.printf("%.2e\n", radius);
		for(int i = 0; i < p.length; i++){
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", p[i].xxPos,
				p[i].yyPos, p[i].xxVel, p[i].yyVel, p[i].mass, p[i].imgFileName);
		}
	}
	
}














