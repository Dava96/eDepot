import java.io.Serializable;

public abstract class Vehicle implements Serializable
{
	protected String make, model, regNo, depot;
	protected int weight;
	protected boolean assigned; // might not be protected

	public Vehicle(String make, String model, int weight, String regNo, String depot) {
		this.make = make;
		this.model = model;
		this.weight = weight;
		this.regNo = regNo;
		this.depot = depot;
	}

	public Vehicle() {
		//test
		
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
	
	public void setDepot(String d) {
		this.depot = d;
	}
	
	public String getRegNo() {
		return regNo;
	}
	
	public String getDepot() {
		return depot;
	}
}
