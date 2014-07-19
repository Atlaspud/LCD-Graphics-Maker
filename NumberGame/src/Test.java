import java.util.ArrayList;


public class Test {
	static ArrayList<ArrayList<Integer>> cardNumbers = new ArrayList<ArrayList<Integer>>();
	static ArrayList<Integer> num = new ArrayList<Integer>();
	
	static int CARDS = 0;
	static int range = 31;
	
	public static void main(String[] args) {
		// BINARY
		while (true) {
			if (range > (int) Math.pow(2,CARDS)) {
				ArrayList<Integer> newArray = new ArrayList<Integer>();
				newArray.add((int) Math.pow(2,CARDS));
				cardNumbers.add(newArray);
				CARDS++;
			} else {
				break;
			}
		}
		// FIBONACCI
//		int currentFib = 1;
//		int previousFib = 1;
//		int testNumber = 1;
//		while (true) {
//			System.out.println("" + testNumber + " and " + currentFib);
//			ArrayList<Integer> newArray = new ArrayList<Integer>();
//			newArray.add(currentFib);
//			cardNumbers.add(newArray);
//			CARDS++;
//			if (range < testNumber) {
//				break;
//			}
//			int newFib = currentFib + previousFib;
//			previousFib = currentFib;
//			currentFib = newFib;
//			testNumber += newFib;
//		}
		
		for (int i = 1; i <= range; i++) {
			int testNum = i;
			for (int j = CARDS - 1; j >= 0; j--) {
				ArrayList<Integer> cardList = cardNumbers.get(j);
				if (testNum % cardList.get(0) != testNum) {
					testNum -= cardList.get(0);
					if (!cardList.contains(i)) {
						cardList.add(i);
					}
				}
				if (testNum == 0) {
					break;
				}
			}
		}
		
		for (int i = 0; i < CARDS; i++) {
			System.out.println(cardNumbers.get(i).toString());
		}
	}
}
