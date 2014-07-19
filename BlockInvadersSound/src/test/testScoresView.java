package test;

import java.util.ArrayList;
import java.util.List;

import au.edu.jcu.view.ScoresView;

public class testScoresView {
	public static void main(String[] args) {
		List<String> testList = new ArrayList<String>();
		testList.add("Hey");
		testList.add("yo");
		ScoresView view = new ScoresView(testList);
		view.setVisible(true);
	}

}
