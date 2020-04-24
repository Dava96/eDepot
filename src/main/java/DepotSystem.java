import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * @author Kyle Ellison-Beattie
 * @author David Lomath Connis
 * This is the class through which contains the menus required to navigate the program
 * and a number of methods which are used within the menus.
 * 
 *
 */
public class DepotSystem
{
	private Depot depot;

	private final String PATH = "C:\\Users\\kebea\\";
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
	
	/**
	 * This method is used to call our entry menu. The entry menu will give the user
	 * the option to log in or exit the system. It is called upon execution and again
	 * later when a user logs out.
	 * @throws Exception
	 */

	public void entryMenu() throws Exception
	{
		String choice;
		do
		{
			System.out.println("SAVING AND COMMENTING AND DOCUMENTS, OURS LOOKS REALLY BAD COMPARED TO GLYNS BECAUSE HALF OF OUR CODE IS IN THE WRONG PLACE ");
			System.out.println("Entry Menu");
			System.out.println("[1] Logon");
			System.out.println("[2] Quit");
			System.out.print("Pick: ");
			choice = input.nextLine();

			switch (choice)
			{
				case "1":
					run();
					break;
			}
		} while (!choice.equals("2"));
		input.close();
		serialize();
		System.exit(0); // To exit system
	}
	
	/**
	 * The run method shows the user 1 of 2 menus (depending on if they are a manager).
	 * The menus contain all the options they need and it is used to call the other methods
	 * we have written.
	 * @throws Exception
	 */
	private void run() throws Exception
	{
		String choice;
		while (true)
		{
			driver = depot.logOn(); //logOn returns the driver
			break;
		}

		if (driver.getIsManager()) //Checks if a driver is set as a manager
		{
			do
			{
				System.out.printf(
					"%n[1] View your assigned work schedules %n[2] Create a new work schedule %n[3] Set a work schedule %n[4] View vehicles %n" +
						"[5] Reassign vehicles %n[6] Set a work schedule as complete %n[7] View archived work schedules %n[8] Create Driver NEEDS FIXING " +
						"%n[9] Create Vehicle %n[10] Exit%n%nSelect your option: ");
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
						createDriver(); // DOESN'T WORK BECAUSE all the drivers are created in depot and not in the DepotSystem & password method causes it to break
						break;
					case "9":
						createVehicle();
						break;
					case "10":
						entryMenu();
						break;
					// exit the system
				}

			} while (true);
		}
		else //If driver is not a manager, they will be shown a more limited menu
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
	/**
	 * Gets the value of the depot
	 * @return This is the depot
	 */
	public Depot getDepot()
	{
		return depot;
	}
	
/**
 * Allows us to rename the depot
 * @param s This is the new name for the depot
 * @throws Exception
 */
	public void setDepot(String s) throws Exception
	{
		depot = new Depot(s);
	}

/**
 * This method lists all the depots
 */
	public void listDepots()
	{
		for (Depot depot : depots)
		{
			System.out.println(depot);
		}

	}
	
	/**
	 * This method lists all of the vehicles
	 */

	public void listVehicles()
	{
		System.out.printf("%-10s %-10s %-12s %6s %10s%n", "Make", "Model", "Registration", "Depot", "Type"); // Used to format the print in a table like structure
		for (Vehicle vehicle : vehicles)
		{
			System.out.printf("%-10s %-10s %-10s %12s %8s %n", vehicle.make, vehicle.model,
				vehicle.regNo, vehicle.depot, vehicle.getClass().getName());
		}
	}

	/**
	 * This method allows a user with manager permissions to reassign vehicles.
	 * They will select a vehicle from the menu and then select the new depot
	 * that they want to send the selected vehicle to.
	 */
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
			if (!validReg) //To prompt user to enter correct information
			{
				System.out.printf("Please select a valid registration%n%n");
			}

		} while (!exit);
	}
	
	/**
	 * This method allows a user with manager permission to add a 
	 * new vehicle to the list. They will enter all of the vehicles attributes,
	 * and the vehicle will be added.
	 * 
	 */

	public void createVehicle()
	{
		String vMake, vModel = "", vRegNo = "", vDepo = "", yesNo, liquidType = "";
		int weight = 0, capacity = 0;
		Depot depotLocation = null;
		boolean isTruck = false, isTanker = false, exit = false, validLocation = true, duplicateReg = true;
		do
		{
			System.out.printf("%nEnter the Vehicles Make i.e [Toyota] : ");
			vMake = DepotSystem.input.nextLine().trim();
			if (!vMake.matches(".*\\d.*")) { // This prevents the user from entering numbers as a name.

				System.out.printf("%nEnter the Vehicles Model i.e [DS-12]: ");
				vModel = DepotSystem.input.nextLine().trim().toUpperCase();

				System.out.printf("%nEnter the Vehicles RegNo: i.e [UT19PAL]: ");
				vRegNo = DepotSystem.input.nextLine().toUpperCase();

				try {
					System.out.printf("%nEnter the Vehicles capacity: i.e [2500]: ");
					capacity = Integer.parseInt(DepotSystem.input.nextLine()); // I have parsed a int from a string because using nextInt alone was causing errors later in the method.

					System.out.printf("%nEnter the Vehicles weight: i.e [12000]: ");
					weight = Integer.parseInt(DepotSystem.input.nextLine());

				} catch (NumberFormatException e) {
					System.out.println("Please Enter a valid number.");
					//e.printStackTrace(); // debugging code
					break;
				}

				for (Vehicle v : vehicles) {
					if (vRegNo.equals(v.getRegNo())) {
						duplicateReg = false;
					}
				}	if (!duplicateReg) { // If the registration is duplicate, print this line
					System.out.println("You have Entered a duplicate Registration number");
					break;
				}
				System.out.printf("%nIs the vehicle a Truck or Tanker: ");
				yesNo = DepotSystem.input.nextLine().toLowerCase();
				if (yesNo.equals("truck")) {
					isTruck = true;
				} if (yesNo.equals("tanker")) {
					isTanker = true;
					System.out.printf("%nEnter the Liquid type i.e [Oil]: ");
					liquidType = DepotSystem.input.nextLine();
				} else if (!yesNo.equals("truck")) { // else if it doesn't equal truck it can't equal tanker either.
					System.out.println("Enter a valid vehicle type, either truck or tanker.");
					break;
				}

				System.out.printf("%nDepots%n%n");
				listDepots();
				System.out.printf("%nEnter the depots Location: ");
				vDepo = DepotSystem.input.nextLine();
				for (Depot depot : depots)
				{
					if (vDepo.equals(depot.getDepotLocation())) // checks if the depot location that the user entered exists
					{
						validLocation = true;
						//depotLocation = getDepot(vDepo); assingment of depot Object
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
			if (isTanker) {
				vehicles.add(new Tanker(vMake, vModel, weight, vRegNo, vDepo, capacity, liquidType));
			} if (isTruck) {
			vehicles.add(new Truck(vMake, vModel, weight, vRegNo, vDepo, capacity));
		}
		} while (!exit);

	}
	
	/**
	 * This method allows a user with manager permissions to add a 
	 * new driver to the system. They will add all of the drivers 
	 * attributes and the new driver will be added to the list.
	 */

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
	/**
	 * This methods returns all the data on a depot when you 
	 * input a location.
	 * @param location This is the location of the depot we want 
	 * to find
	 * @return A depot will be returned if the parameter matches 
	 * a depot location. It will return null if not.
	 */

	public Depot getDepot(String location) {
		for (Depot depot : depots) {
			if (location.equals(depot.getDepotLocation())) {
				return depot;
			}
		}
		return null;
	}
	
/**
 * This method returns a specific vehicle that matches
 * the inputed registration
 * @param regNo The method finds a vehicle with this
 * registration.
 * @return Returns the vehicle if the registration is a match, 
 * otherwise it returns null.
 */
	public Vehicle getVehicleReg(String regNo) {
		for (Vehicle v : vehicles) {
			if (regNo.equals(v.regNo)) {
				return v;
			}
		}
		return null;
	}
	
	/**
	 * This method allows for serialisation of objects upon program exit.
	 */

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
/**
 * This method allows for deserialisation of objects upon program start.
 */
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
