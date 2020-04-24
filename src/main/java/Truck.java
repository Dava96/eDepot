/**
 * 
 * @author Kyle Ellison-Beattie
 * @author David Lomath Connis
 * This class inherits all of the attributes and method of the vehicle class
 * and allows us to define a truck object.
 *
 */
public class Truck extends Vehicle
{
	private int cargoCapacity;
	private String name;
	/**
	 * The constructor for a truck object
	 * @param make The make of the truck
	 * @param model The model of the truck
	 * @param weight The weight of the truck
	 * @param regNo The registration number of the truck
	 * @param depot The depot the truck is assigned to
	 * @param cargoCapacity The capacity of the truck
	 */

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
