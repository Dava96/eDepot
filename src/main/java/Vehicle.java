public abstract class Vehicle
{
	protected String make, model, regNo;
	protected int weight;
	protected boolean assigned; // might not be protected

	public Vehicle(String make, String model, int weight, String regNo) {
		this.make = "Toyota";
		this.model = "CS23";
		this.weight = 4090;
		this.regNo = "454564";
		this.assigned = false;
	}

	public Vehicle() {
		//test
		this("Toyota", "Cs23", 454545, "443456");
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

}
