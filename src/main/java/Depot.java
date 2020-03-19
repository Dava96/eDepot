import java.time.LocalDate;

public class Depot
{
	private Vehicle vehicle;
	private Vehicle[] vehicles = new Vehicle[5];
	private Driver driver;
	private Driver[] drivers = new Driver[5];
	private WorkSchedule workSchedule;
	private WorkSchedule[] workSchedules = new WorkSchedule[2];

	private String depotLocation;

	public Depot(String depotLocation) throws Exception
	{
		this.depotLocation = depotLocation;
		for (int i = 0; i < 5; i++) {
			vehicles[i] = new Vehicle(); // creates 5 vehicle & driver instances
			drivers[i] = new Driver();
		}

		workSchedules[0] = new WorkSchedule("Krusty Crab", LocalDate.parse("2020-03-20"), LocalDate.parse("2020-03-22"));
		workSchedules[1] = new WorkSchedule("Chum Bucket", LocalDate.parse("2020-03-20"), LocalDate.parse("2020-03-22"));
	}

	public void logOn() {

	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setUpWorkSchedule() {

	}

	public void listVehicles() {
		for (int i = 0; i < vehicles.length; i++) {
			System.out.println(vehicles[i].toString());
		}
	}

	public void listDrivers() {
		for (int i = 0; i < drivers.length; i++) {
			System.out.println(drivers[i].toString());
		}

	}

	public void listWorkSchedulue() {
		for (int i = 0; i < workSchedules.length; i++) {
			System.out.println(workSchedules[i].toString());
			// this is just a test to see if it works, it's probably not needed
		}
	}

	@Override
	public String toString()
	{
		return depotLocation;
	}
}
