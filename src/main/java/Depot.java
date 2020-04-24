import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Depot implements Serializable {
	private Vehicle vehicle;
	private Vehicle[] vehicles = new Vehicle[5];
	private Driver driver;
	private ArrayList<Driver> drivers = new ArrayList<Driver>();
	private WorkSchedule workSchedule;
	private List<WorkSchedule> workSchedules = Collections.synchronizedList(new ArrayList<WorkSchedule>());
	private List<WorkSchedule> completedWorkSchedules = Collections.synchronizedList(new ArrayList<WorkSchedule>());

	private String depotLocation;

	public Depot(String depotLocation) throws Exception {
		this.depotLocation = depotLocation;

		drivers.add(new Driver("Glyn", "_Glyn", false, true));
		drivers.add(new Driver("Sorren", "_Sorren", false, true));
		drivers.add(new Driver("Kirsty", "_Kirsty", false, true));
		drivers.add(new Driver("Homer", "_Homer", false, false));
		drivers.add(new Driver("Bart", "_Bart", false, false));

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

		} while (!authenticate(uName.trim(), pWord.trim())); // Allows the system to ask for a user's info again if they
																// entered it incorrectly
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
			if (workSchedules.size() != 0) { // Make sure there is still active work schedules
				listWorkSchedulue();
				System.out.printf("%nEnter the name of the client's schedue you wish to set as complete: ");
				String choice = DepotSystem.input.nextLine();
				WorkSchedule completedSchedule = new WorkSchedule(null, LocalDate.parse("2020-05-07"),
						LocalDate.parse("2020-05-08")); // Used as a place holder that we set equal to an existing
														// schedule in line 120
				for (WorkSchedule schedule : workSchedules) {
					if (choice.equals(schedule.client) && schedule.getDriverAssigned() != null) {
						completedSchedule = schedule;
						found = true;
						assigned = true;
					} else {
						assigned = false;
					}
				}
				if (!assigned) {
					System.out.printf("%nPlease enter a schedule that has a Driver assigned to it.%n");
					found = true; // This is so if there is no schedules with a driver assigned to it, it will
									// break the loop.
				}
				if (found) {
					completedWorkSchedules.add(completedSchedule);
					workSchedules.remove(completedSchedule);
					for (Driver driver : drivers) {
						if (driver.getSchedule() != null) { // Checks if a driver has an assigned schedule
							WorkSchedule ws = driver.getSchedule();
							String client = ws.getClient();
							if (client.equals(completedSchedule.getClient())) { // Checks the assigned schedule matches
																				// the one we want to delete
								driver.setSchedule(null); // Deletes schedule from driver
								driver.setAssigned(false);// Sets the drivers assigned attribute to false
							}
						}

					}
				}
			} else {
				System.out.printf("%nThere are currently no active work schedules%n");
			}
		} while (!found);
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
		System.out.printf("%-10s %-10s %10s %17s%n", "Client", "Start Date", "End Date", "Assigned to"); // Used to
																											// format
																											// the print
																											// in a
																											// table
																											// like
																											// structure
		for (WorkSchedule workSchedule : workSchedules) {
			System.out.println(workSchedule.toString());

		}
	}

	public void listCompletedWorkSchedulue() {
		System.out.printf("%-10s %-10s %10s %17s%n", "Client", "Start Date", "End Date", "Assigned to"); // Used to
																											// format
																											// the print
																											// in a
																											// table
																											// like
																											// structure
		sortWorkSchedule();
		for (WorkSchedule workSchedule : completedWorkSchedules) {
			System.out.println(workSchedule.toString());
		}
	}

	@Override
	public String toString() {
		return depotLocation;
	}

	public List<WorkSchedule> getWorkSchedules() {
		return workSchedules;
	}

	public void sortWorkSchedule() { // sorts both the workSchedule and Completed workSchedule arrayLists by start
										// date
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

	public String getDepotLocation() {
		return depotLocation;
	}
}
