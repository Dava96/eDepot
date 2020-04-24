import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Depot implements Serializable
{
	private Vehicle vehicle;
	private Driver driver;
	private WorkSchedule workSchedule;

	private ArrayList<Driver> drivers = new ArrayList<Driver>();
	private List<WorkSchedule> workSchedules = Collections.synchronizedList(new ArrayList<WorkSchedule>());
	private List<WorkSchedule> completedWorkSchedules = Collections.synchronizedList(new ArrayList<WorkSchedule>());
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	private String depotLocation;

	public Depot(String depotLocation) throws Exception {
		this.depotLocation = depotLocation;

		/*drivers.add(new Driver("Spongebob", "Garysnail1", false, false));
		drivers.add(new Driver("Homer", "Donutdonut1", false, true));
		drivers.add(new Driver("Bart", "Shortsshort1", false, false));
*/
		//workSchedules.add(new WorkSchedule("Bob", LocalDate.parse("2020-05-05"), LocalDate.parse("2020-05-06")));
		//workSchedules.add(new WorkSchedule("Gary", LocalDate.parse("2020-04-25"), LocalDate.parse("2020-04-27")));
		//System.out.println(drivers.toString());
	}


	public boolean authenticate(String uName, String pWord) {
		for (Driver driver : drivers) {
			if (uName.equals(driver.userName) && pWord.equals(driver.passWord)) {
				System.out.printf("%nWelcome back %s 	Depot: %s%n", uName, depotLocation);
				getDriver();
				return true;
			}
		}
		System.out.printf("Incorrect login credentials please try again %n%n");
		return false;
	}

	public Vehicle getVehiclebyReg(String regNo) {
		for (Vehicle v : vehicles) {
			if (v.getRegNo().equals(regNo));
			return v;
		}
		return null;
	}

	/**
	 * This method First checks if the vehicles arraylist is empty
	 * If it is not empty, it will print out a table like structure
	 * Then it will list all the vehicles belonging to that depot.
	 */
	 public void listVehicles()
	{
		if (!vehicles.isEmpty())
		{
			System.out.printf("%-10s %-10s %-12s %6s %10s%n", "Make", "Model", "Registration", "Depot", "Type"); // Used to format the print in a table like structure
			{
				for (Vehicle vehicle : vehicles)
				{
					System.out.printf("%-10s %-10s %-10s %12s %8s %n", vehicle.make, vehicle.model,
						vehicle.regNo, vehicle.depot, vehicle.getClass().getName());
				}
			}
		}
		System.out.println("There is no vehicles currently at this depot");
	}

	public Driver getDriver() {
		return driver;
	}

	public void makeVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}

	public Driver getDriver(String username) {
		for (Driver driver : drivers) {
			if (username.equals(driver.userName)) {
				return driver;
			}
		}
		return null;
	}


	public void listDrivers() {
		for (Driver driver : drivers) {
			System.out.println(driver.toString());
		}

	}

	public void listWorkSchedulue() {
		System.out.printf("%-10s %-10s %10s %17s%n", "Client", "Start Date", "End Date", "Assigned to"); // Used to format the print in a table like structure
		for (WorkSchedule workSchedule : workSchedules) {
			System.out.println(workSchedule.toString());

		}
	}

	public void listCompletedWorkSchedulue() {
		System.out.printf("%-10s %-10s %10s %17s%n", "Client", "Start Date", "End Date", "Assigned to"); // Used to format the print in a table like structure
		for (WorkSchedule workSchedule : completedWorkSchedules) {
			System.out.println(workSchedule.toString());
		}
	}

	@Override
	public String toString() {
		return depotLocation;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public List<WorkSchedule> getWorkSchedules() {
		return workSchedules;
	}

	public List<WorkSchedule> getCompletedWorkSchedules() {
		return completedWorkSchedules;
	}

	public ArrayList<Driver> getDrivers() {
		return drivers;
	}

	public void addDriver(Driver driver) {
		drivers.add(driver);
	}

	public void addWorkSchedule(WorkSchedule ws) {
		workSchedules.add(ws);

		// might need to add something here to link it to vehicle/driver
	}

	public void addCompletedWorkSchedule(WorkSchedule ws) {
		completedWorkSchedules.add(ws);
	}

	public WorkSchedule getWorkSchedule(String clientName) {
		for (WorkSchedule workSchedule : workSchedules) {
			if (clientName.equals(workSchedule.client)) {
				return workSchedule;
			}
		}
		return null;
	}

	public String getDepotLocation()
	{
		return depotLocation;
	}
}
