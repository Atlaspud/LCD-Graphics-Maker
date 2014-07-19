package animals;

public class Bird extends Animal {
	private boolean canFly;
	
	public Bird(String commonName, String country, boolean canFly) {
		super(commonName,country);
		this.canFly = canFly;
	}
	
	@Override
	public String toString() {
		return String.format("%s [%s]",super.toString(),
				canFly ? "Can Fly" : "Would be awesome if it could fly");
	}

}
