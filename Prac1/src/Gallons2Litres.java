
public class Gallons2Litres {
	
	public final static double LITRES_TO_ONE_GALLON = 3.78541;

	public static void main(String[] args) {
		for (int gallon = 1; gallon < 101 ; gallon++) {
			System.out.println("Litres = " + String.format("%.2f", gallon * LITRES_TO_ONE_GALLON));
		}
	}

}
