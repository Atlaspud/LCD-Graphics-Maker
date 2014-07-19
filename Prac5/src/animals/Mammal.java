package animals;

public class Mammal extends Animal {
	private boolean livesOnLand;
	
	public Mammal(String commonName, String country, boolean livesOnLand) {
		super(commonName, country);
		this.livesOnLand = livesOnLand;
	}
	
	@Override
	public String toString() {
		return String.format("%s [%s]",super.toString(),
				livesOnLand ? "Land-Dwelling" : "Water-Dwelling");
	}

}
