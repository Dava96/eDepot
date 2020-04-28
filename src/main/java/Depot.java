import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Kyle Ellison-Beattie
 * @author David Lomath-Connis
 * This class contains all the attributes and methods needed for a depot
 *
 *
 */
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

	/**
	 * This is a constructor that allows us to create
	 * a depot object
	 * @param depotLocation The location of the depot
	 * @throws Exception
	 */
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

	/**
	 * This method is used to check that correct login details
	 * are being used, i.e. user name and password match
	 * @param uName the user name signed in with
	 * @param pWord the password entered
	 * @return true if info is correct, false otherwise
	 */
	public boolean authenticate(String uName, String pWord) {
		for (Driver driver : drivers) {
			if (uName.equals(driver.userName) && pWord.equals(driver.passWord)) {
				System.out.printf("%nWelcome back %s 	Depot: %s%n", uName, depotLocation);
				//getDriver();
				return true;
			}
		}
		return false;
	}

	/**
	 * This method gets a specific vehicle
	 * @param regNo the registration number of the vehicle we want
	 * @return returns the vehicle if found, null otherwise
	 */
	public Vehicle getVehiclebyReg(String regNo) {
		for (Vehicle v : vehicles) {
			if (regNo.equals(v.regNo)) {
				return v;
			}
		}
		return null;
	}

	/**
	 * Removes a specific vehicle from the array list
	 * @param regNo the registration number of the vehicle we want
	 * to remove
	 */
	public void removeByVehicleReg(String regNo) {
		vehicles.removeIf(v -> v.getRegNo().equals(regNo));
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
			System.out.printf("%-10s %-10s %-15s %8s %10s%n", "Make", "Model", "Registration", "Depot", "Type"); // Used to format the print in a table like structure
			{
				for (Vehicle vehicle : vehicles)
				{
					System.out.printf("%-10s %-10s %-15s %8s %10s %n", vehicle.make, vehicle.model,
						vehicle.regNo, vehicle.depot, vehicle.getClass().getName());
				}
			}
		}else {
			System.out.println("There is no vehicles currently at this depot");
		}
	}

	/**
	 * This method gets a driver1
	 * @return the driver
	 */
	public Driver getDriver() {
		return driver;
	}

	/**
	 * This method allows us to add a vehicle to the array list
	 * @param vehicle the vehicle we want to add
	 */
	public void makeVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}

	/**
	 * This method allows us to get a specific driver
	 * @param username the user name of the driver we want
	 * @return the driver or null if nor found
	 */
	public Driver getDriver(String username) {
		for (Driver driver : drivers) {
			if (username.equals(driver.userName)) {
				return driver;
			}
		}
		return null;
	}

	/**
	 * This method prints out a list of drivers
	 */
	public void listDrivers() {
		if (!drivers.isEmpty())
		{
			for (Driver driver : drivers) {
				System.out.println(driver.toString());
			}
		}
	}

	/**
	 * This method prints out a list of work schedules
	 */
	public void listWorkSchedulue() {
		System.out.printf("%-10s %-10s %10s %17s%n", "Client", "Start Date", "End Date", "Assigned to"); // Used to format the print in a table like structure
		for (WorkSchedule workSchedule : workSchedules) {
			System.out.println(workSchedule.toString());
		}
	}

	/**
	 * This method prints out a list of completed work schedules
	 */
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

	/**
	 * This method gets all the vehicles
	 * @return the list of vehicles
	 */
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	/**
	 * This method gets all the active work schedules
	 * @return the active work schedules
	 */
	public List<WorkSchedule> getWorkSchedules() {
		return workSchedules;
	}

	/**
	 * This method gets all the completed work schedules
	 * @return the completed work schedules
	 */
	public List<WorkSchedule> getCompletedWorkSchedules() {
		return completedWorkSchedules;
	}

	/**
	 * This method gets all the drivers
	 * @return the drivers
	 */
	public ArrayList<Driver> getDrivers() {
		return drivers;
	}

	/**
	 * This method allows us to add a driver to the array list
	 * @param driver the driver we want to add
	 */
	public void addDriver(Driver driver) {
		drivers.add(driver);
	}

	/**
	 * This method allows us to add a work schedule to the
	 * array list
	 * @param ws the work schedule we wish to add
	 */
	public void addWorkSchedule(WorkSchedule ws) {
		workSchedules.add(ws);

		// might need to add something here to link it to vehicle/driver
	}

	/**
	 * This method allows us to add a completed work
	 * schedule to the array list
	 * @param ws the work schedule we wish to add
	 */
	public void addCompletedWorkSchedule(WorkSchedule ws) {
		completedWorkSchedules.add(ws);
	}

	/**
	 * This method allows us to get a specific work schedule
	 * from the array list
	 * @param clientName the name of the client associated with
	 * the work schedule we want to get
	 * @return the work schedule we want, or null if not found
	 */
	public WorkSchedule getWorkSchedule(String clientName) {
		for (WorkSchedule workSchedule : workSchedules) {
			if (clientName.equals(workSchedule.client)) {
				return workSchedule;
			}
		}
		return null;
	}

	/**
	 * This method gets a depot location
	 * @return the location of the depot
	 */
	public String getDepotLocation()
	{
		return depotLocation;
	}
}
