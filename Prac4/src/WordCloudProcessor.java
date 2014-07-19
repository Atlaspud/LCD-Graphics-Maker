import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class WordCloudProcessor {
	
	public static void main(String[] args) {
		CloudItemsBuilder builder = new CloudItemsBuilder();
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file","txt");
		chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(null);
		if (result != JFileChooser.APPROVE_OPTION) return;
		String path = chooser.getSelectedFile().getAbsolutePath();
		builder.parseTextFile(path);
		
		try {
			String newPath = chooser.getSelectedFile().getParent()
					+ "/test.cld";
			PrintWriter writer = new PrintWriter(newPath);
			writer.print(builder);
			System.out.println(builder);
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
