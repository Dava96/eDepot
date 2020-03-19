public class Vehicle
{
	protected String make, model, regNo;
	protected int weight;
	protected boolean assigned; // might not be protected

	public Vehicle(String make, String model, String regNo, int weight) {
		this.make = "Toyota";
		this.model = "CS23";
		this.regNo = "454564";
		this.weight = 4090;
		this.assigned = false;
	}

	public Vehicle() {
		//test
		this("Toyota", "Cs23", "454545", 443456);
	}

	public boolean isAvaliable() {
		if (assigned) return true;
		return false; // returns false if assigned = true
	}

	public void setSchedulue() {

	}


	@Override
	public String toString() {
		return make + " " + model + " " + regNo + " " + weight;
	}

	public class Truck {
		// I need to read the labs on nested classes, but I'm sure this will be one
		private int cargoCapacity;
	}

	public class Tanker {
		private int liquidCapacity;
		private String liquidType;
	}
}
