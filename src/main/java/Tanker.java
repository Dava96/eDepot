public class Tanker extends Vehicle
{
	private int liquidCapacity;
	private String liquidType;

	public Tanker(String make, String model, Integer weight, String regNo, Integer liquidCapacity, String liquidType) {
		super(make, model, weight, regNo);

		this.liquidType = liquidType;
		this.liquidCapacity = liquidCapacity;
	}
}
