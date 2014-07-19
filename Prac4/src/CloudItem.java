
public class CloudItem {
	private String word;
	private int count;
	
	public CloudItem(String word) {
		this.word = word;
		this.count = 1;
	}
	
	public String toString() {
		return word + " (" + count + ")";
	}
	
	public int getCount() {
		return this.count;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public void increment() {
		this.count++;
	}
}
