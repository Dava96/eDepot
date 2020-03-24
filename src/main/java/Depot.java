import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Depot {
	private Vehicle vehicle;
	private Vehicle[] vehicles = new Vehicle[5];
	private static Driver driver;
	private ArrayList<Driver> drivers = new ArrayList<Driver>();
	private WorkSchedule workSchedule;
	private WorkSchedule[] workSchedules = new WorkSchedule[2];
	private static Scanner input = new Scanner(System.in);

	private String depotLocation;

	public Depot(String depotLocation) throws Exception {
		this.depotLocation = depotLocation;
		for (int i = 0; i < 5; i++) {
			vehicles[i] = new Vehicle(); // creates 5 vehicle & driver instances

		}
		drivers.add(new Driver("Spongebob", "Gary1", false));
		drivers.add(new Driver("Homer", "Donut1", false));
		drivers.add(new Driver("Bart", "Shorts1", false));
		System.out.println(drivers.toString());


		// Creating 3 unique drivers

		/*
		 * workSchedules[0] = new WorkSchedule("Krusty Crab",
		 * LocalDate.parse("2020-03-31"), LocalDate.parse("2020-04-02"));
		 * workSchedules[1] = new WorkSchedule("Chum Bucket",
		 * LocalDate.parse("2020-03-31"), LocalDate.parse("2020-04-02"));
		 */
	}

	public  void logOn() {
		String uName;
		String pWord;
		do {
			System.out.println("Please enter your username: ");
			uName = input.nextLine();

			System.out.println("Please enter your password: ");
			pWord = input.nextLine();

		} while (!authenticate(uName, pWord));
	}

	public boolean authenticate(String uName, String pWord) {
		for (Driver driver : drivers) {
			if (uName.equals(driver.userName) && pWord.equals(driver.passWord)) {
				System.out.printf("Welcome back %s", uName);
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

	}

	public void listVehicles() {
		for (int i = 0; i < vehicles.length; i++) {
			System.out.println(vehicles[i].toString());
		}
	}

	public void listDrivers() {
		for (Driver driver : drivers) {
			driver.toString();
		}

	}

	public void listWorkSchedulue() {
		for (int i = 0; i < workSchedules.length; i++) {
			System.out.println(workSchedules[i].toString());
			// this is just a test to see if it works, it's probably not needed
		}
	}

	@Override
	public String toString() {
		return depotLocation;
	}
}
