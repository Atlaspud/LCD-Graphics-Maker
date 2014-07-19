
public class TestCloudItemsBuilder {

	public static void main(String[] args) {
		CloudItemsBuilder builder = new CloudItemsBuilder();
		if (!builder.parseTextFile("billGatesSpeech.txt")) {
			System.out.println("ERROR: file not found...");
		}
		System.out.println(builder);
	}

}
