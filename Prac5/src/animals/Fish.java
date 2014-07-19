package animals;

public class Fish extends Animal {
	private boolean bonySkeleton;
	
	public Fish(String commonName, String country, boolean bonySkeleton) {
		super(commonName,country);
		this.bonySkeleton = bonySkeleton;
	}
	
	@Override
	public String toString() {
		return String.format("%s [%s]",super.toString(),
				bonySkeleton ? "Bony" : "Not Bony");
	}

}
