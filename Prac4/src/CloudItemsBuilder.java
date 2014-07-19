import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class CloudItemsBuilder {
	private ArrayList<CloudItem> items;

	public CloudItemsBuilder() {
		items = new ArrayList<CloudItem>() ;
	}
	
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		
		for (int i = 0; i < items.size(); i++) {
			string.append(items.get(i)+ "\n");
		}
		return string.toString();
	}


	public boolean parseTextFile(String filename) {
		try {
			Scanner scanner = new Scanner(new File(filename));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (!line.isEmpty()) {
					line = line.replaceAll("[.,():$?;]", "");
					String[] elements = line.split(" ");
					for (String element : elements) {
						if (element.length() > 4) {
							int count = find(element);
							if (count == -1) {
								items.add(new CloudItem(element));
							} else {
								items.get(count).increment();
							}
						}
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}
	
	private int find(String word) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getWord().equals(word)) return i;
		}
		return -1;
	}
}
