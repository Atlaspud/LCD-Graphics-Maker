
public class TestLogicalOps {

	static void testPrintOps(boolean p, boolean q){
		System.out.println("Test printOps():");
		System.out.println("When p == " + p + " and q == " + q + ":");
		System.out.printf("\tActual output:\t");
		LogicalOps.printOps(p, q);
	}
	
	public static void main(String[] args) {
		testPrintOps(false, false);
		testPrintOps(false, true);
		testPrintOps(true, false);
		testPrintOps(true, true);
	}

}
