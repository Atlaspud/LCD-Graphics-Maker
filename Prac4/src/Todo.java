import java.util.ArrayList;


public class Todo {
	private ArrayList<String> items;
	private ArrayList<String> completed;
	
	Todo() {
		items = new ArrayList<String>();
		completed = new ArrayList<String>();
	}
	
	void add(String item) {
		this.items.add(item);
	}
	
	void clearAll() {
		this.items.clear();
	}
	
	int count() {
		return this.items.size();
	}

	void markCompleted(String item) {
		if (this.items.contains(item)) {
			this.items.remove(item);
			this.completed.add(item);
		}
	}
	
	String getCompleted() {
		return this.completed.toString();
	}
}
