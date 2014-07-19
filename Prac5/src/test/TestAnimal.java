package test;

import java.util.ArrayList;

import animals.Animal;
import animals.Bird;
import animals.Fish;
import animals.Mammal;

public class TestAnimal {

	public static void main(String[] args) {
		// Test 1
		Animal animal = new Animal("Emu", "Australia");
		System.out.println("Test 1 - contructor/toString, expected: Emu (Austalia), Actual: " + animal);
		
		// Test 2
		ArrayList<Animal> animalArray = new ArrayList<Animal>();
		animalArray.add(new Animal("Beaver","Canada"));
		animalArray.add(new Animal("Condor","Chile"));
		animalArray.add(new Animal("Panda","China"));
		animalArray.add(new Animal("Lion","Ethiopia"));
		animalArray.add(new Animal("Brown Bear","Finland"));
		animalArray.add(new Animal("Jaguar","Guyana"));
		
		StringBuilder builder = new StringBuilder();
		for (Animal string: animalArray) {
			builder.append(string + "\n");
		}
		System.out.println("Test 2 - arrayList of Animals\n" + builder.toString());
		
		// Test 3
		Mammal dolphin = new Mammal("Dolphin", "Greece", false);
		System.out.println("Test 3 - Mammal class, expected: "
				+ "Dolphin (Greece) [Water-dwelling], actual: "
				+ dolphin);
		
		// Test 4
		Fish clownFish = new Fish("Clownfish", "Australia", true);
		System.out.println("Test 4 - Fish class, expected: "
				+ "Clownfish (Australia) [Bony], actual: "
				+ clownFish);
		
		// Test 5
		Bird kiwi = new Bird("Kiwi", "New Zealand", false);
		System.out.println("Test 4 - Bird class, expected: "
				+ "Kiwi (New Zealand) [Would be awesome if "
				+ "it could fly], actual: " + kiwi);
	}

}
