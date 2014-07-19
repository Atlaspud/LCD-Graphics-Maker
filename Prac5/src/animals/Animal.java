package animals;

public class Animal {
	private String commonName;
	private String country;
	
	public Animal(String commonName, String country) {
		this.commonName = commonName;
		this.country = country;
	}
	public String toString() {
		return this.commonName + " (" + this.country + ")";
	}

}
