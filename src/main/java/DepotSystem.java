import java.util.Scanner;

public class DepotSystem {
	private Depot depot;
	private static Scanner input = new Scanner(System.in);

	public DepotSystem() throws Exception {
		setDepot("Liverpool");
	}

	public void run() {
		String choice;
		while (true) {
			depot.logOn();

			// System.out.println(getDepot().toString()); depot.listVehicles();
			// depot.listDrivers(); depot.listWorkSchedulue();

			break;
		}
		do {
			System.out.printf(
					"%n[1] View your assigned work schedules %n[2] Create a new work schedule %n[3] Exit %n%nSelect your option: ");
			choice = input.next();

			switch (choice) {
			case "1":
				// View work schedule method will appear here
				break;
			case "2":
				// Create a work schedule
				break;
			case "3":
				System.exit(0);
				//exit the system
				//test
			}

		} while (true);
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(String s) throws Exception {
		depot = new Depot(s);
	}
}
