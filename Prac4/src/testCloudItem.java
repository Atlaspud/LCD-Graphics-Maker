
public class testCloudItem {

	public static void main(String[] args) {
		CloudItem item = new CloudItem("today");
		System.out.println("Test1 - expected: today (1) actual: " + item);
		item.increment();
		System.out.println("Test2 - expected: today (2) actual: " + item);
		System.out.println("Test3 - expected: today actual: " + item.getWord());
		System.out.println("Test4 - expected: 2 actual: " + item.getCount());
	}

}
