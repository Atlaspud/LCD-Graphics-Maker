package test;

import java.util.ArrayList;
import java.util.List;

import au.edu.jcu.view.ScoresView;

public class testScoresView {
	public static void main(String[] args) {
		List<String> testList = new ArrayList<String>();
		testList.add("Test 1");
		testList.add("Test 2");
		ScoresView view = new ScoresView(testList);
		view.setVisible(true);
	}

}
