import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepotSystem implements Serializable
{
	private Depot depot;

	private final String PATH = "C:\\Users\\David\\IdeaProjects\\eDepot\\src\\main\\java\\";
	public static final Scanner input = new Scanner(System.in); // This can be accessed from every class Depot.input
	private Driver driver; // This variable lets the system know if the user is a manager or not, the menu
	// shown will depend on this
	private List<Depot> depots = new ArrayList<Depot>();
	private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

	public DepotSystem() throws Exception
	{
		deSerialize();
		setDepot("Liverpool");
		depots.add(new Depot("Liverpool"));
		depots.add(new Depot("Manchester"));
		depots.add(new Depot("Leeds"));

		vehicles.add(new Tanker("Mercedes", "25-18", 15000, "OU04PFN", "Liverpool", 2000, "Oil"));
		vehicles.add(new Tanker("Toyota", "A1", 12000, "AN69HTU", "Leeds", 2000, "Water"));
		vehicles.add(new Truck("Vauxhall", "DS-54", 13000, "UT19PAL", "Manchester", 1500));

	}

	public void entryMenu() throws Exception
	{
		String choice = "";
		do
		{
			System.out.println("TODO CREATE VEHICLES, SAVING AND COMMENTING AND DOCUMENTS, OURS LOOKS REALLY BAD COMPARED TO GLYNS BECAUSE HALF OF OUR CODE IS IN THE WRONG PLACE");
			System.out.println("Entry Menu");
			System.out.println("[1] Logon");
			System.out.println("[2] Quit");
			System.out.print("Pick: ");
			choice = input.nextLine();

			switch (choice)
			{ // this is the way glyn has it setup in his video, so I've replicated it
				case "1":
					run();
					break;
			}
		} while (!choice.equals("2"));
		input.close();
		serialize();
		System.exit(0);
	}

	private void run() throws Exception
	{
		String choice;
		String userName;
		while (true)
		{
			driver = depot.logOn();
			break;
		}

		if (driver.getIsManager())
		{
			do
			{
				System.out.printf(
					"%n[1] View your assigned work schedules %n[2] Create a new work schedule %n[3] Set a work schedule %n[4] View vehicles %n" +
						"[5] Reassign vehicles %n[6] Set a work schedule as complete %n[7] View archived work schedules %n[8] Create Driver NEEDS FIXING %n[9] Exit%n%nSelect your option: ");
				choice = DepotSystem.input.nextLine();
				switch (choice)
				{
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
						createDriver(); // DOESN'T WORK BECAUSE all the drivers are created in depot and not in the DepotSystem password method causes it to break
						break;
					case "9":
						entryMenu();
						break;
					// exit the system
				}

			} while (true);
		}
		else
		{
			do
			{
				System.out.printf("%n[1] View your assigned work schedule %n[2] Exit %n%nSelect your option: ");
				choice = input.nextLine();

				switch (choice)
				{
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

	public Depot getDepot()
	{
		return depot;
	}

	public void setDepot(String s) throws Exception
	{
		depot = new Depot(s);
	}


	public void listDepots()
	{
		for (Depot depot : depots)
		{
			System.out.println(depot);
		}

	}

	public void listVehicles()
	{
		System.out.printf("%-10s %-10s %-12s %6s %n", "Make", "Model", "Registration", "Depot");
		for (Vehicle vehicle : vehicles)
		{
			System.out.printf("%-10s %-10s %-10s %12s %n", vehicle.make, vehicle.model,
				vehicle.regNo, vehicle.depot);
		}
	}

	public void reAssignVehicles()
	{
		String vehicleSelection;
		String depotSelection;
		boolean exit = false, validReg = false;
		do
		{
			System.out.println("Depots");
			listVehicles();
			System.out.printf("%nSelect the registration number of the vehicle you wish to reassign: %n");
			vehicleSelection = DepotSystem.input.nextLine();
			for (Vehicle vehicle : vehicles)
			{

				if (vehicleSelection.equals(vehicle.getRegNo()))
				{
					validReg = true;
					System.out.printf("%nYou have selected vehicle: %s%n", vehicleSelection);
					listDepots();
					System.out.printf("%nPlease select the depot you want to reassign this vehicle to: %n");
					depotSelection = DepotSystem.input.nextLine();
					if ((depotSelection.equals("Liverpool") || depotSelection.equals("Leeds")
						|| depotSelection.equals("Manchester")) && !depotSelection.equals(vehicle.getDepot()))
					{
						System.out.printf("%nVehicle %s is now assigned to %s%n", vehicleSelection, depotSelection);
						vehicle.setDepot(depotSelection);
						exit = true;
						break;

					}
					else
					{
						System.out.printf("%nThe vehicle is either already assigned to the depot, or the depot does not exist%n%n");
					}

				}
				else
				{
					validReg = false;
				}

			}
			if (!validReg)
			{
				System.out.printf("Please select a valid registration%n%n");
			}

		} while (!exit);
	}

	public void createDriver()
	{
		String driverName, driverPassword, depotLocation, yesNo;
		boolean isManager = false, exit = false, validLocation = true;
		do
		{
			System.out.printf("%nEnter the drivers name: ");
			driverName = DepotSystem.input.nextLine().trim();
			if (!driverName.matches(".*\\d.*")) { // prevents entering numbers as a name

				System.out.printf("%nEnter the drivers password: ");
				driverPassword = DepotSystem.input.nextLine().trim();

				System.out.printf("%nIs the driver a manager: i.e [yes or no]: ");
				yesNo = DepotSystem.input.nextLine().toLowerCase();
				if (yesNo.contains("yes")) {
					isManager = true;
				} else if (yesNo.contains("no")) {
					isManager = false;
				}

				System.out.printf("%nDepots%n%n");
				listDepots();
				System.out.printf("%nEnter the depots Location: ");
				depotLocation = DepotSystem.input.nextLine();
				for (Depot depot : depots)
				{
					if (depotLocation.equals(depot.getDepotLocation()))
					{
						validLocation = true;
						getDepot(depotLocation).addDriver(new Driver(driverName, driverPassword, false, isManager, getDepot(depotLocation))); // assigned is always false
						exit = true;
						break;
					} else {
						validLocation = false;
					}
				}
			} else {
				System.out.println("Please Enter a name without Numbers.");
			}
			if (!validLocation) {
				System.out.println("Please Enter a valid location.");
		}

	} while (!exit);

}

	public Depot getDepot(String location) {
		for (Depot depot : depots) {
			if (location.equals(depot.getDepotLocation())) {
				return depot;
			}
		}
		return null;
	}

	private void serialize() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(PATH + "depots.ser"));
			oos.writeObject(depots);
			oos.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void deSerialize() {
		ObjectInputStream ois;

		try {
			ois = new ObjectInputStream(new FileInputStream(PATH + "depots.ser"));

			depots = (List<Depot>)ois.readObject(); // this error is normal don't try fix it
			ois.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
