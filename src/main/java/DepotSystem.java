import java.util.Scanner;

public class DepotSystem {
	private Depot depot;
	private static boolean isManager;
	private Driver driver; // This variable lets the system know if the user is a manager or not, the menu
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
			choice = Depot.input.nextLine();

			switch (choice) { // this is the way glyn has it setup in his video, so I've replicated it
				case "1":
					run();
					break;
			}
		}
		while (!choice.equals("2"));
		Depot.input.close();
		System.exit(0);
	}

	private void run() throws Exception {
		String choice;
		String userName;
		while (true) {
			driver = depot.logOn();
			break;
		}

		if (isManager) {
			do {
				System.out.printf(
						"%n[1] View your assigned work schedules %n[2] Create a new work schedule %n[3] Set a work schedule %n[4] Exit %n%nSelect your option: ");
				choice = Depot.input.next();

				switch (choice) {
				case "1":
					System.out.println(driver.getSchedule()); // Just a test to see that the log on method is returning the correct
													// username
				
					// View work schedule method will appear here
					break;
				case "2":
					// Create a work schedule
					depot.createWorkSchedule();
					break;
				case "3":
					// Not sure if needed this is mainly for testing purposes
					driver.assignSchedule(depot);
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
				choice = Depot.input.nextLine();
				
				switch(choice) {
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

	public static void setIsManager(boolean b) {
		isManager = b;
	}
}
