import java.util.Scanner;

public class DepotSystem {
	private Depot depot;
	private static Scanner input = new Scanner(System.in);
	private static boolean isManager; // This variable lets the system know if the user is a manager or not, the menu
										// shown will depend on this

	public DepotSystem() throws Exception {
		setDepot("Liverpool");
	}

	public void entryMenu() throws Exception
	{
		String choice = "";
		do
		{
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
		}
		while (!choice.equals("2"));
		input.close();
		depot = null;
	}

	private void run() throws Exception {
		String choice;
		String userName;
		while (true) {
			userName = depot.logOn();

			// System.out.println(getDepot().toString()); depot.listVehicles();
			// depot.listDrivers(); depot.listWorkSchedulue();

			break;
		}

		if (isManager) {
			do {
				System.out.printf(
						"%n[1] View your assigned work schedules %n[2] Create a new work schedule %n[3] Set a work schedule %n[4] Exit %n%nSelect your option: ");
				choice = input.next();

				switch (choice) {
				case "1":
					System.out.println(Driver.getSchedule()); // Just a test to see that the log on method is returning the correct
													// username
					System.out.println(isManager);
					// View work schedule method will appear here
					break;
				case "2":
					// Create a work schedule
					depot.setUpWorkSchedule();
					break;
				case "3":
					// Not sure if needed this is mainly for testing purposes
					Driver.setSchedulue();
					break;
				case "4":
					entryMenu();
					break;
					// exit the system
				}

			} while (true);
		} else {
			do {
				System.out.printf("%n[1] View your assigned work schedule %n[2] Exit %n%nSelect your option: ");
				choice = input.nextLine();
				
				switch(choice) {
				case "1":
					
					System.out.println(Driver.getSchedule());
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

	public static void setIsManager(boolean b) {
		isManager = b;
	}
}
