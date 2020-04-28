import java.io.Serializable;

/**
 *
 * @author Kyle Ellison-Beattie
 * @author David Lomath-Connis
 * This is the class that allows us to define a vehicle, it is the super class
 * of truck and tanker.
 */
public abstract class Vehicle implements Serializable
{
	protected String make, model, regNo;
	protected int weight;
	protected boolean assigned;
	protected Depot depot;


	/**
	 * This is the constructor that allows us to define a vehicle object
	 * @param make The vehicles make
	 * @param model The vehicles model
	 * @param weight The vehicles weight
	 * @param regNo The vehicles registration number
	 * @param depot The depot the vehicle is assigned to
	 */
	public Vehicle(String make, String model, int weight, String regNo, Depot depot) {
		this.make = make;
		this.model = model;
		this.weight = weight;
		this.regNo = regNo;
		this.depot = depot;
	}

	/**
	 * This method checks if a vehicle is currently in use
	 * @return true or false based on the status of the vehicle.
	 */
	public boolean isAvaliable() {
		return assigned; // returns false if assigned = true
	}

	@Override
	public String toString() {
		return make + " " + model + " " + regNo + " " + weight + this.getClass().getSimpleName();
	}

	/**
	 * This method allows us to set the depot a vehicle is assigned to
	 * @param d The name of the depot
	 */
	public void setDepot(Depot d) {
		this.depot = d;
	}

	/**
	 * Gets the vehicles registration number
	 * @return the vehicles registration number
	 */
	public String getRegNo() {
		return regNo;
	}

	/**
	 * Gets the name of the depot the vehicle is assigned to
	 * @return The name of the depot.
	 */
	public String getDepot() {
		return depot.toString();
	}
}
