public class Truck extends Vehicle
{
	private int cargoCapacity;
	private String name;

	public Truck(String make, String model, Integer weight, String regNo, String depot, Integer cargoCapacity) {
		super(make, model, weight, regNo, depot);

		this.cargoCapacity = cargoCapacity;
		this.name = "Truck";
	}


	public String toString() {
		return this.getClass().getSimpleName() + " " +
			name;
	}
}
