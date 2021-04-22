public class Planet{
	/*Its current x position.*/
	public double xxPos;

	/*Its current y position.*/
	public double yyPos;

	/*Its current velocity in the x direction.*/
	public double xxVel;

	/*Its current velocity in the y direction.*/
	public double yyVel;

	/*Its mass.*/
	public double mass;
	/* The name of the file that corresponds to the image that depicts the planet*/
	public String imgFileName;

	/*Gravitational constant G. */
	public static final double GCONSTANT= 6.67E-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/* The second constructor should take in a Planet object and initialize an identical Planet object.*/
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p2){
		double distance = 0;
		double distanceX = 0;
		double distanceY = 0;
		distanceX = p2.xxPos - this.xxPos;
		distanceY = p2.yyPos - this.yyPos;
		distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
		return distance;
	}

	public double calcForceExertedBy(Planet p2){
		double force = 0;
		double distance = this.calcDistance(p2);
		force = GCONSTANT * this.mass * p2.mass /(distance * distance);
		return force;
	}

	public double calcForceExertedByX(Planet p2){
		double force = 0;
		double forceX = 0;
		double distance = 0;
		double distanceX = 0;
		force = this.calcForceExertedBy(p2);
		distance = this.calcDistance(p2);
		distanceX = p2.xxPos - this.xxPos;
		forceX = (force * distanceX)/distance;
		return forceX;
	}

	public double calcForceExertedByY(Planet p2){
		double force = 0;
		double forceY = 0;
		double distance = 0;
		double distanceY = 0;
		force = this.calcForceExertedBy(p2);
		distance = this.calcDistance(p2);
		distanceY = p2.yyPos - this.yyPos;
		forceY = (force * distanceY)/distance;
		return forceY;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double forceNetX = 0;
		for (int i =0; i < allPlanets.length; i++){
			if (allPlanets[i].equals(this) == false){
				forceNetX += this.calcForceExertedByX(allPlanets[i]);
			}
		}
		return forceNetX;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets){
		double forceNetY = 0;
		for(int i = 0; i < allPlanets.length; i++){
			if (allPlanets[i].equals(this) == false){
				forceNetY += this.calcForceExertedByY(allPlanets[i]);
			}
		}
		return forceNetY;
	}

	public void update(double dt, double fX, double fY){
		double aNetX = 0;
		double aNetY = 0;
		aNetX = fX / this.mass;
		aNetY = fY / this.mass;
		this.xxVel = this.xxVel + dt * aNetX;
		this.yyVel = this.yyVel + dt * aNetY;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

	public void draw(){
		String imgStar = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imgStar);
	}

}