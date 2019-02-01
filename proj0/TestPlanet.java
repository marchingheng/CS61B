public class TestPlanet{
	public static void main(String[] args) {
		checkPlanet();
	}

    private static void checkEquals(double actual, double expected, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }


	/** create two planet and check the calculation of their pairwise force */
	public static void checkPlanet(){
		Planet p1 = new Planet(1.00, 2.00, 3.00, 4.00, 100, "jupiter.gif");
		Planet p2 = new Planet(4.00, 6.00, 3.00, 4.00, 100, "jupiter.gif");
		double force = p1.calcForceExertedBy(p2);
		double expectedForce = Planet.N*100.00*100.00/(5.00*5.00);
		checkEquals(force, expectedForce, "Planet()", 0.01);

	}
}