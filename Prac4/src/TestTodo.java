
public class TestTodo {

	public static void main(String[] args) {
		Todo todo = new Todo();
		System.out.println("Size after creating (expected value: 0), actual: " + todo.count());
		
		todo.add("buy milk");
		todo.add("mow the lawn");
		todo.add("buy an oculus rift");
		
		System.out.println("Size after creating (expected value: 3), actual: " + todo.count());
		
		todo.markCompleted("buy an oculus rift");
		System.out.println("Size after creating (expected value: 2), actual: " + todo.count());
		
		System.out.println("Currently completed items (expected [buy an oculus rift]"
				+ " actual: " + todo.getCompleted());
	}

}
