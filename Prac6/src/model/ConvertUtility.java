package model;

public class ConvertUtility {
	
	public static double fahrenheitToCelsius(double fahrenheit) {
		return (fahrenheit - 32) * 5/9;
	}
	
	public static double celciusToFahrenheit(double celcius) {
		return celcius * 9/5 + 32;
	}
	
	public static double inchesToMetres(double inches) {
		return inches*0.00254;
	}
	
	public static double metresToInches(double metres) {
		return metres/0.00254;
	}
				

}
