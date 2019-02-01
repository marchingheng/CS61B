public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double N = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}


	/** calculate the distance between two planets */
	public double calcDistance(Planet p){
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		return Math.sqrt(dx*dx + dy*dy);
	}

	/** calculate the force between two planets */
	public double calcForceExertedBy(Planet p){
		double distance = this.calcDistance(p);
		return N*this.mass*p.mass/(distance*distance);
	}

	/** calculate the force between two planet in x-direction */
	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos - this.xxPos;
		double distance = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		return force/distance*dx;
	}

	/** calculate the force between two planet in y-direction */
	public double calcForceExertedByY(Planet p){
		double dy = p.yyPos - this.yyPos;
		double distance = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		return force/distance*dy;
	}

	/** take in an array of planets and calculate their force to current planet in x-direction */
	public double calcNetForceExertedByX(Planet [] planets){
		double accumulatedForceX = 0;
		for (Planet planet:planets){
			if (this != planet){
				accumulatedForceX += this.calcForceExertedByX(planet);
			}
		}
		return accumulatedForceX;
	}


	/** take in an array of planets and calculate their force to current planet in y-direction */
	public double calcNetForceExertedByY(Planet [] planets){
		double accumulatedForceY = 0;
		for (Planet planet:planets){
			if (this != planet){
				accumulatedForceY += this.calcForceExertedByY(planet);
			}
		}
		return accumulatedForceY;
	}

	/** update the position and velocity of a planet when it is pulled by force fX and fY for dt seconds. */
	public void update(double dt, double fX, double fY){
		double accelerationX = fX/this.mass;
		double accelerationY = fY/this.mass;
		xxVel += accelerationX*dt;
		yyVel += accelerationY*dt;
		xxPos += xxVel*dt;
		yyPos += yyVel*dt;
	}

	/** draw the planet */
	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "./images/"+this.imgFileName);
	}


}