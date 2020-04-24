

public class Tanker extends Vehicle
{
	private int liquidCapacity;
	private String liquidType;
	private String name;

	public Tanker(String make, String model, Integer weight, String regNo, String depot, Integer liquidCapacity, String liquidType) {
		super(make, model, weight, regNo, depot);

		this.liquidType = liquidType;
		this.liquidCapacity = liquidCapacity;
		this.name = "Tanker";
	}


	public String toString() {
		return this.getClass().getSimpleName() + " " +
			name;
	}
}
