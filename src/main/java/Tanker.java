/**
 * 
 * @author Kyle Ellison-Beattie
 * @author David Lomath Connis
 * 
 * This class inherits all attributes and methods from vehicle and is used 
 * to define tanker objects.
 *
 */

public class Tanker extends Vehicle
{
	private int liquidCapacity;
	private String liquidType;
	private String name;

	/**
	 * This method is a constructor for a tanker object.
	 * @param make The make of the tanker
	 * @param model The model of the tanker
	 * @param weight The weight of the tanker
	 * @param regNo The registration number of the tanker
	 * @param depot What depot the tanker is assigned to
	 * @param liquidCapacity The capacity of the tanker
	 * @param liquidType The liquid type the tanker holds
	 */
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
