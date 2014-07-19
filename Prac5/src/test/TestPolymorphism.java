package test;

import java.util.ArrayList;

import animals.Animal;
import animals.Bird;
import animals.Fish;
import animals.Mammal;

public class TestPolymorphism {

	public static void main(String[] args) {
		String[] country = {"Japan", "America", "Australia", "Finland", "England", "China"};
		String[] fish = {"Tuna","Salmon","Nemo","Shark"};
		boolean[] fishInfo = {true, true, true, false};
		String[] bird = {"Big Bird","Sunbird","Emu", "Kiwi"};
		boolean[] birdInfo = {false, true, false, false};
		String[] mammal = {"Koala", "Wallaby", "Kangaroo", "Cow"};

		ArrayList<Animal> animalArray = new ArrayList<Animal>();
		
		for (int i = 0; i < randInt(10,21) ; i++) {
			
			int animalType = randInt(1,3 + 1);
			int index = 0;
			
			switch (animalType) {
			
			case 1:
				index = randInt(4);
				animalArray.add(new Fish(fish[index], country[randInt(6)], fishInfo[index]));
				break;
			case 2:
				index = randInt(4);
				animalArray.add(new Mammal(mammal[index], country[randInt(6)], randBool()));
				break;
			case 3:
				index = randInt(4);
				animalArray.add(new Bird(bird[index], country[randInt(6)], birdInfo[index]));
			}
				
		}
		
		StringBuilder builder = new StringBuilder();
		for (Animal string: animalArray) {
			builder.append(string + "\n");
		}
		System.out.println("Test 1 - Polymorphism\n" + builder.toString());
		
	}
	
	public static int randInt(int max) {
		return (int) (Math.random() * max);
	}
	
	public static int randInt(int min, int max) {
		return min + (int) ((Math.random() * (max - min)));
	}
	
	public static boolean randBool() {
		return randInt(2) == 1;
	}

}
