import java.util.ArrayList;
import java.util.Scanner;

public class DepotSystem {
	private Depot depot;
	private boolean isManager;
	public static final Scanner input = new Scanner(System.in); // This can be accessed from every class Depot.input
	private Driver driver; // This variable lets the system know if the user is a manager or not, the menu
							// shown will depend on this
	private ArrayList<Depot> depots = new ArrayList<Depot>();
	private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

	public DepotSystem() throws Exception {
		setDepot("Liverpool");
		depots.add(new Depot("Liverpool"));
		depots.add(new Depot("Manchester"));
		depots.add(new Depot("Leeds"));

		vehicles.add(new Tanker("Mercedes", "25-18", 15000, "OU04PFN", "Liverpool", 2000, "Oil"));
		vehicles.add(new Tanker("Toyota", "A1", 12000, "AN69HTU", "Leeds", 2000, "Water"));
		vehicles.add(new Truck("Vauxhall", "DS-54", 13000, "UT19PAL", "Manchester", 1500));

	}

	public void entryMenu() throws Exception {
		String choice = "";
		do {
			System.out.println("Entry Menu");
			System.out.println("[1] Logon");
			System.out.println("[2] Quit");
			System.out.print("Pick: ");
			choice = input.nextLine();

			switch (choice) { // this is the way glyn has it setup in his video, so I've replicated it
			case "1":
				run();
				break;
			}
		} while (!choice.equals("2"));
		input.close();
		System.exit(0);
	}

	private void run() throws Exception {
		String choice;
		String userName;
		while (true) {
			driver = depot.logOn();
			break;
		}

		if (driver.getIsManager()) {
			do {
				System.out.printf(
						"%n[1] View your assigned work schedules %n[2] Create a new work schedule %n[3] Set a work schedule %n[4] View vehicles %n[5] Reassign vehicles %n[6] Set a work schedule as complete %n[7] View archived work schedules %n[8] Exit %n%nSelect your option: ");
				choice = DepotSystem.input.nextLine();
				switch (choice) {
				case "1":
					System.out.println(driver.getSchedule());
					break;
				case "2":
					// Create a work schedule
					depot.createWorkSchedule();
					break;
				case "3":
					driver.assignSchedule(depot);
					break;
				case "4":
					listVehicles();
					break;
				case "5":
					reAssignVehicles();
					break;
				case "6":
					depot.completeWorkSchedule();
					break;
				case "7":
					depot.listCompletedWorkSchedulue();
					break;
				case "8":
					entryMenu();
					break;
				// exit the system
				}

			} while (true);
		} else {
			do {
				System.out.printf("%n[1] View your assigned work schedule %n[2] Exit %n%nSelect your option: ");
				choice = input.nextLine();

				switch (choice) {
				case "1":
					System.out.println(driver.getSchedule());
					break;
				case "2":
					entryMenu();
					break;
				}
			} while (true);
		}
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(String s) throws Exception {
		depot = new Depot(s);
	}

	public void setIsManager(boolean b) {
		isManager = b;
	}

	public void listDepots() {
		for (Depot depot : depots) {
			System.out.println(depot.toString());
		}

	}

	public void listVehicles() {
		System.out.printf("%-10s %-10s %-12s %6s %n", "Make", "Model", "Registration", "Depot");
		for (Vehicle vehicle : vehicles) {
			System.out.printf("%-10s %-10s %-10s %12s %n", vehicle.make, vehicle.model,
					vehicle.regNo, vehicle.depot);
		}
	}

	public void reAssignVehicles() {
		String vehicleSelection;
		String depotSelection;
		boolean exit = false, validReg = false;
		do {

			listVehicles();
			System.out.printf("%nSelect the registration number of the vehicle you wish to reassign: %n");
			vehicleSelection = DepotSystem.input.next();
			for (Vehicle vehicle : vehicles) {

				if (vehicleSelection.equals(vehicle.getRegNo())) {
					validReg = true;
					System.out.printf("%nYou have selected vehicle: %s%n", vehicleSelection);
					listDepots();
					System.out.printf("%nPlease select the depot you want to reassign this vehicle to: %n");
					depotSelection = DepotSystem.input.next();
					if ((depotSelection.equals("Liverpool") || depotSelection.equals("Leeds")
							|| depotSelection.equals("Manchester")) && !depotSelection.equals(vehicle.getDepot())) {
						System.out.printf("%nVehicle %s is now assigned to %s%n", vehicleSelection, depotSelection);
						vehicle.setDepot(depotSelection);
						exit = true;
						break;

					} else {
						System.out.printf(
								"%nThe vehicle is either already assigned to the depot, or the depot does not exist%n%n");
					}

				} else {
				validReg = false;
				}

			} if (!validReg) {
				System.out.printf("Please select a valid registration%n%n");
			}

		} while (!exit);

	}
}
