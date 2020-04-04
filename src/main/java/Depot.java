import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Depot {
	private Vehicle vehicle;
	private ArrayList<Vehicle> vehicles = new ArrayList<>();
	private static Driver driver;
	private ArrayList<Driver> drivers = new ArrayList<Driver>();
	private WorkSchedule workSchedule;
	private ArrayList<WorkSchedule> workSchedules = new ArrayList<WorkSchedule>();
	private static Scanner input = new Scanner(System.in);

	private String depotLocation;

	public Depot(String depotLocation) throws Exception {
		this.depotLocation = depotLocation;

		drivers.add(new Driver("Spongebob", "Garysnail1", false, false));
		drivers.add(new Driver("Homer", "Donuts20", false, true));
		drivers.add(new Driver("Bart", "Shorts19", false, false));
		System.out.println(drivers.toString());

		// Creating 3 unique drivers

		/*
		 * workSchedules[0] = new WorkSchedule("Krusty Crab",
		 * LocalDate.parse("2020-03-31"), LocalDate.parse("2020-04-02"));
		 * workSchedules[1] = new WorkSchedule("Chum Bucket",
		 * LocalDate.parse("2020-03-31"), LocalDate.parse("2020-04-02"));
		 */
	}

	public String logOn() {
		String uName, pWord;
		do {
			System.out.println("Please enter your username: ");
			uName = input.nextLine();

			System.out.println("Please enter your password: ");
			pWord = input.nextLine();

		} while (!authenticate(uName.trim(), pWord.trim()));
		return uName;
	}

	public boolean authenticate(String uName, String pWord) {
		for (Driver driver : drivers) {
			if (uName.equals(driver.userName) && pWord.equals(driver.passWord)) {
				System.out.printf("%nWelcome back %s%n", uName);
				DepotSystem.setIsManager(driver.getIsManager());
				return true;

			}
		}
		System.out.printf("Incorrect login credentials please try again %n%n");
		return false;

	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setUpWorkSchedule() {

		do { // Loop is necessary to allow re entry of data should any erroneous dates be
				// inputed
			System.out.printf("%nPlease enter a client name: ");
			String client = input.nextLine();

			System.out.printf("%nPlease enter a start date (yyyy-mm-dd): ");
			String startDate = input.nextLine();

			System.out.printf("%nPlease enter an end date (yyyy-mm-dd): ");
			String endDate = input.nextLine();
			try {
				// Try and create an object of the work schedule class with the user defined
				// parameters
				WorkSchedule schedule = new WorkSchedule(client, LocalDate.parse(startDate), LocalDate.parse(endDate));
				workSchedules.add(schedule);
				System.out.printf("%nWork schedule successfully created %n%n");
				break;
			} catch (Exception e) {
				// If there is an issue with the dates the user entered they will be told as
				// such and prompted to re enter with correct parameters
				System.out.println(e.getMessage());
				System.out.printf("%nPlease enter a valid date this time %n%n");

			}
		} while (true);

	}

	public void listVehicles() {
		for (Vehicle vehicle : vehicles) {
			vehicle.toString();
		}
	}

	public void listDrivers() {
		for (Driver driver : drivers) {
			driver.toString();
		}

	}

	public void listWorkSchedulue() {
		for (int i = 0; i < workSchedules.size(); i++) {
			System.out.println(workSchedules.toString());
			// this is just a test to see if it works, it's probably not needed
		}
	}

	@Override
	public String toString() {
		return depotLocation;
	}
}
