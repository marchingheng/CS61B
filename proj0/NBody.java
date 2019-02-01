public class NBody{
	/** get the radius of the universe */
	public static double readRadius(String dir){
		In in = new In(dir);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}
	/** get a list of planets */
	public static Planet [] readPlanets(String dir){
		In in = new In(dir);
		int numberOfPlanets = in.readInt();
		Planet [] planets = new Planet[numberOfPlanets];
		double radius = in.readDouble();
		for (int i = 0; i < numberOfPlanets; i++){
		double xxPos = in.readDouble();
		double yyPos = in.readDouble();
		double xxVel = in.readDouble();
		double yyVel = in.readDouble();
		double mass = in.readDouble();
		String img = in.readString();
		planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
		}
		return planets;
	}

	public static void main(String[] args) {
		/** read in all neccessary data */
		double T = new Double(args[0]);
		double dt = new Double(args[1]);
		String fileName = args[2];
		double radius = readRadius(fileName);
		Planet [] planets = readPlanets(fileName);

		/** draw background */
		String imageToShow = "./images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToShow);
		for (Planet planet:planets){planet.draw();}
		StdDraw.show();
		StdDraw.enableDoubleBuffering();
		int t = 0;
		while (t < T){
			double [] xForces = new double[planets.length];
			double [] yForces = new double[planets.length]; 
			int number = 0;
			for (Planet planet:planets){
				xForces[number] = planet.calcNetForceExertedByX(planets);
				yForces[number] = planet.calcNetForceExertedByY(planets);
				number+=1;
			}
			number = 0;
			for (Planet planet:planets){
				planet.update(dt, xForces[number], yForces[number]);
				number += 1;
			}

			/** drawing at each time step */
			StdDraw.picture(0, 0, imageToShow);
			for (Planet planet:planets){planet.draw();}
			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
			}

	}


	
}