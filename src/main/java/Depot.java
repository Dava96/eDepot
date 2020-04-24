import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class Depot implements Serializable
{
	private Vehicle vehicle;
	private Vehicle[] vehicles = new Vehicle[5];
	private Driver driver;
	private ArrayList<Driver> drivers = new ArrayList<Driver>();
	private WorkSchedule workSchedule;
	private ArrayList<WorkSchedule> workSchedules = new ArrayList<WorkSchedule>();
	private ArrayList<WorkSchedule> completedWorkSchedules = new ArrayList<WorkSchedule>();

	private String depotLocation;

	public Depot(String depotLocation) throws Exception {
		this.depotLocation = depotLocation;

		drivers.add(new Driver("Spongebob", "Garysnail1", false, false));
		drivers.add(new Driver("Homer", "Donutdonut1", false, true));
		drivers.add(new Driver("Bart", "Shortsshort1", false, false));

		workSchedules.add(new WorkSchedule("Bob", LocalDate.parse("2020-05-05"), LocalDate.parse("2020-05-06")));
		workSchedules.add(new WorkSchedule("Gary", LocalDate.parse("2020-04-25"), LocalDate.parse("2020-04-27")));
		System.out.println(drivers.toString());
	}

	public Driver logOn() {
		String uName;
		String pWord;
		do {
			System.out.println("Please enter your username: ");
			uName = DepotSystem.input.nextLine();

			System.out.println("Please enter your password: ");
			pWord = DepotSystem.input.nextLine();

		} while (!authenticate(uName.trim(), pWord.trim()));
		return getDriver(uName);
	}

	public boolean authenticate(String uName, String pWord) {
		for (Driver driver : drivers) {
			if (uName.equals(driver.userName) && pWord.equals(driver.passWord)) {
				System.out.printf("%nWelcome back %s 	Depot: %s%n", uName, depotLocation);
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

	public Driver getDriver(String username) {
		for (Driver driver : drivers) {
			if (username.equals(driver.userName)) {
				return driver;
			}
		}
		return null;
	}

	public void createWorkSchedule() {

		do { // Loop is necessary to allow re entry of data should any erroneous dates be
				// inputed
			System.out.print("Please enter a client name: ");
			String client = DepotSystem.input.nextLine();

			System.out.print("Please enter a start date [i.e 2020-05-15]: ");
			String startDate = DepotSystem.input.nextLine();

			System.out.print("Please enter an end date [i.e 2020-05-17]: ");
			String endDate = DepotSystem.input.nextLine();
			try {
				// Try and create an object of the work schedule class with the user defined
				// parameters
				WorkSchedule schedule = new WorkSchedule(client, LocalDate.parse(startDate), LocalDate.parse(endDate));
				workSchedules.add(schedule);
				sortWorkSchedule();
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

	public void completeWorkSchedule() throws Exception {
		boolean found = false, assigned = false;
		do {
			if(workSchedules.size() !=0) {
				listWorkSchedulue();
				System.out.printf("%nEnter the name of the client's schedue you wish to set as complete: ");
				String choice = DepotSystem.input.nextLine();
				WorkSchedule completedSchedule = new WorkSchedule(null, LocalDate.parse("2020-05-07"),
						LocalDate.parse("2020-05-08"));
				for (WorkSchedule schedule : workSchedules) {
					if (choice.equals(schedule.client) && schedule.getDriverAssigned() != null) {
						completedSchedule = schedule;
						found = true;
						assigned = true;
					} else {
						assigned = false;
					}
				} if (!assigned) {
					System.out.printf("%nPlease enter a schedule that has a Driver assigned to it.%n");
					found = true; // This is so if there is no schedules with a driver assigned to it, it will break the loop.
				}
				if (found) {
					completedWorkSchedules.add(completedSchedule);
					workSchedules.remove(completedSchedule);
					for (Driver driver : drivers) {
						if (driver.getSchedule() != null) {
							WorkSchedule ws = driver.getSchedule();
							String client = ws.getClient();
							if (client.equals(completedSchedule.getClient())) {
								driver.setSchedule(null);
								driver.setAssigned(false);
							}
						}

					}
				}
			} else {
				System.out.printf("%nThere are currently no active work schedules%n");
			}
		} while(!found);
	}

	public void listVehicles() {
		for (int i = 0; i < vehicles.length; i++) {
			System.out.println(vehicles[i].toString());
		}
	}

	public void listDrivers() {
		for (Driver driver : drivers) {
			System.out.println(driver.toString());
		}

	}

	public void listWorkSchedulue() {
		System.out.printf("%-10s %-10s %10s %17s%n", "Client", "Start Date", "End Date", "Assigned to");
		for (WorkSchedule workSchedule : workSchedules) {
			System.out.println(workSchedule.toString());

		}
	}

	public void listCompletedWorkSchedulue() {
		System.out.printf("%-10s %-10s %10s %17s%n", "Client", "Start Date", "End Date", "Assigned to");
		sortWorkSchedule();
		for (WorkSchedule workSchedule : completedWorkSchedules) {
			System.out.println(workSchedule.toString());
		}
	}

	@Override
	public String toString() {
		return depotLocation;
	}

	public ArrayList<WorkSchedule> getWorkSchedules() {
		return workSchedules;
	}

	public void sortWorkSchedule() {
		workSchedules.sort(Comparator.comparing(WorkSchedule::getStartDate));
		completedWorkSchedules.sort(Comparator.comparing(WorkSchedule::getStartDate));
	}

	public ArrayList<Driver> getDrivers() {
		return drivers;
	}

	public void addDriver(Driver driver) {
		drivers.add(driver);
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
