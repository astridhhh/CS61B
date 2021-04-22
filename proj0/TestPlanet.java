public class TestPlanet{
	public static void main(String[] args){
		Planet Saturn = new Planet(2.3E+12, 9.5E+11, 0, 0, 6E+26, null);
		Planet Sun  = new Planet(1E+12, 2E+11, 0, 0, 2E+30, null);
		double pairwiseForce = 0;
		pairwiseForce = Saturn.calcForceExertedBy(Sun);
		System.out.println(pairwiseForce + "N");
	}
}