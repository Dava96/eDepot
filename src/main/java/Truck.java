public class Truck extends Vehicle
{
	private int cargoCapacity;

	public Truck(String make, String model, Integer weight, String regNo, Integer cargoCapacity) {
		super(make, model, weight, regNo);

		this.cargoCapacity = cargoCapacity;
	}
}
