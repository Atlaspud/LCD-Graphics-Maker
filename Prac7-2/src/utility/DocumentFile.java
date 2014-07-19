package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DocumentFile {
	public static void save(String filename, String text) {
		try {
			PrintWriter writer = new PrintWriter(filename);
			writer.print(text);
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static String load(String filename) throws FileNotFoundException {
		StringBuilder textBuild = new StringBuilder();
		
		Scanner scanner = new Scanner(new File(filename));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (!line.isEmpty()) {
				textBuild.append(line + "\n");
			}
		}
		scanner.close();
		return textBuild.toString();
	}
}
