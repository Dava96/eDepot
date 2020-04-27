import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author Kyle Ellison-Beattie
 * @author David Lomath-Connis
 * This is the class through which contains the menus required to navigate the program
 * and a number of methods which are used within the menus.
 *
 *
 */
public class DepotSystem
{
	private Depot depot;

	private final String PATH = "C:\\Users\\David\\IdeaProjects\\eDepot\\src\\main\\java\\"; // Directory will need to be changed in order for the program to read from the file
	public final Scanner input = new Scanner(System.in); // This can be accessed from every class Depot.input
	private Driver driver; // This variable lets the system know if the user is a manager or not, the menu
	// shown will depend on this
	private List<Depot> depots = new ArrayList<Depot>();
	private int depotNo = 0;
	//private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

	public DepotSystem() throws Exception
	{
		deSerialize();
		/*
		//setDepot("Liverpool");
		depots.add(new Depot("Lpool")); // 0
		depots.add(new Depot("Leeds")); // 1
		depots.add(new Depot("MChester")); // 2
		//depots.add(new Depot("London")); // 3

		/* Note Debugging data
		depots.get(0).addDriver(new Driver("Glyn", "_Glyn", false, true, getDepot("Lpool")));
		depots.get(2).addDriver(new Driver("Sorren", "_Sorren", false, true, getDepot("MChester")));
		depots.get(1).addDriver(new Driver("Kirsty", "_Kirsty", false, true, getDepot("Leeds")));
		depots.get(0).addDriver(new Driver("Homer", "_Homer", false, false, getDepot("Lpool")));
		depots.get(0).addDriver(new Driver("Bart", "_Bart", false, false, getDepot("MChester")));

		WorkSchedule bob = new WorkSchedule("Bob", LocalDate.parse("2020-05-05"), LocalDate.parse("2020-05-06"));
		WorkSchedule Gary = new WorkSchedule("Gary", LocalDate.parse("2020-04-25"), LocalDate.parse("2020-04-27"));
		depots.get(1).addWorkSchedule(bob);
		depots.get(1).addWorkSchedule(Gary);

		//depots.get(0).addDriver((new Driver("Spongebob", "Garysnail1", false, false)));
		//depots.get(0).addDriver((new Driver("Homer", "Donutdonut1", false, true)));
		Vehicle v1 = new Tanker("Mercedes", "25-18", 15000, "OU04PFN", getDepot("Liverpool"), 2000, "Oil");
		Vehicle v2 = new Tanker("Toyota", "A1", 12000, "AN69HTU", getDepot("Leeds"), 2000, "Water");
		Vehicle v3 = new Truck("Vauxhall", "DS-54", 13000, "UT19PAL", getDepot("Manchester"), 1500);
		depots.get(0).makeVehicle(v1);
		depots.get(1).makeVehicle(v2);
		depots.get(2).makeVehicle(v3);

		//vehicles.add(new Tanker("Mercedes", "25-18", 15000, "OU04PFN", "Liverpool", 2000, "Oil"));
		//vehicles.add(new Tanker("Toyota", "A1", 12000, "AN69HTU", "Leeds", 2000, "Water"));
		//vehicles.add(new Truck("Vauxhall", "DS-54", 13000, "UT19PAL", "Manchester", 1500));

		 */
	}

	/**
	 * This method is used to call our entry menu. The entry menu will give the user
	 * the option to log in or exit the system. It is called upon execution and again
	 * later when a user logs out.
	 * @throws Exception
	 */
	public void entryMenu() throws Exception
	{
		depotNo = 0;
		String choice;
		do
		{
			System.out.println("Entry Menu");
			System.out.println("[1] Logon");
			System.out.println("[2] Quit");
			System.out.print("Pick: ");
			choice = input.nextLine();

			switch (choice)
			{
				case "1":
					logOn();
					break;
			}
		} while (!choice.equals("2"));
		input.close();
		serialize();
		System.exit(0); // To exit System
	}

	private void logOn() throws Exception
	{
		driver = null;
		String uName;
		String pWord;
		Boolean exit = false;

		do {
			System.out.println("Please enter your username: ");
			uName = input.nextLine();

			System.out.println("Please enter your password: ");
			pWord = input.nextLine();
			for (depotNo = 0; depotNo < depots.size(); depotNo++) {
				depot = depots.get(depotNo); // Assigns the depot number to depot
				if (depot.authenticate(uName.trim(), pWord.trim())) { // users the assigned depot to check if there is an existing account
					// with that combination of username and password
					driver = depot.getDriver(uName); // if the depot has the combination of password and username set driver
					exit = true;
					break;
				}
			}
		} while (!exit);
		depotMenu();
	}

	/**
	 * The depotMenu method shows the user 1 of 2 menus (depending on if they are a manager).
	 * The menus contain all the options they need and it is used to call the other methods
	 * we have written.
	 * @throws Exception
	 */
	private void depotMenu() throws Exception
	{
		String choice;
		/*while (true)
		{
			driver = depot.logOn();
			break;
		}*/

		if (driver.getIsManager()) // checks if the driver is a manager
		{
			do
			{
				System.out.printf(
					"%n[1] View your assigned work schedules %n[2] Create a new work schedule %n[3] Set a work schedule %n[4] View vehicles %n" +
						"[5] Reassign vehicles %n[6] Set a work schedule as complete %n[7] View archived work schedules %n[8] Create Driver" +
						"%n[9] Create Vehicle %n[10] Exit%n%nSelect your option: ");
				choice = input.nextLine();
				switch (choice)
				{
					case "1":
						System.out.println(driver.getSchedule());
						break;
					case "2":
						// Create a work schedule
						createWorkSchedule();
						break;
					case "3":
						assignSchedule();
						break;
					case "4":
						depots.get(depotNo).listVehicles();
						break;
					case "5":
						reAssignVehicles();
						break;
					case "6":
						completeWorkSchedule();
						break;
					case "7":
						depots.get(depotNo).listCompletedWorkSchedulue();
						break;
					case "8":
						createDriver();
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
		else // If the driver is not a manager, they will be shown a more limited menu
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
						depotMenu();
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


	public void listDepots()
	{
		for (Depot depot : depots)
		{
			System.out.println(depot);
		}

	}

	public void assignSchedule() {
		String client, driver;
		Boolean exit = false, validClient = false, validDriver = false;

		if(depots.get(depotNo).getWorkSchedules().size() != 0) {
			do {
				depots.get(depotNo).listWorkSchedulue();
				System.out.print("Enter the client name for the schedule you wish to assign: ");
				client = input.nextLine();

				for (WorkSchedule ws : depots.get(depotNo).getWorkSchedules()) {
					if (client.equals(ws.getClient())) {
						validClient = true;
						System.out.printf("%n%20s%n", "Drivers");
						depots.get(depotNo).listDrivers();
						System.out.print("Which Driver do you want to assign this to: ");
						driver = input.nextLine();

						for (Driver drivers : depots.get(depotNo).getDrivers()) {
							if (driver.equals(drivers.userName) && drivers.getSchedule() == null) {
								validDriver = true;
								System.out.printf("%nThe work schedule for client %s is now assigned to %s.%n ", client,
									driver);
								depots.get(depotNo).getDriver(driver).setSchedule(depots.get(depotNo).getWorkSchedule(client));
								drivers.setAssigned(true);
								ws.setDriverAssigned(depots.get(depotNo).getDriver(driver));
								exit = true;
								break;
							} else if ((!driver.equals(drivers.userName) && drivers.getSchedule() != null)) {
								validDriver = false;
							}
						}
						break;
					} else {
						validClient = false;
					}
				}
				if (!validClient) {
					System.out.printf("%nPlease enter a valid client.%n");
				} else if (!validDriver) {
					System.out.printf("%nEither not a valid driver or Driver is already assigned a Job.%n");
				}
			} while (!exit);
		}
		else {
			System.out.printf("%nThere are currently no unassinged work schedules%n");
		}

	}

	public void createWorkSchedule() {

		do { // Loop is necessary to allow re entry of data should any erroneous dates be
			// inputed
			System.out.print("Please enter a client name: ");
			String client = input.nextLine();

			System.out.print("Please enter a start date [i.e 2020-05-15]: ");
			String startDate = input.nextLine();

			System.out.print("Please enter an end date [i.e 2020-05-17]: ");
			String endDate = input.nextLine();
			try {
				// Try and create an object of the work schedule class with the user defined
				// parameters
				depots.get(depotNo).addWorkSchedule( new WorkSchedule(client, LocalDate.parse(startDate), LocalDate.parse(endDate)));
				//WorkSchedule schedule = new WorkSchedule(client, LocalDate.parse(startDate), LocalDate.parse(endDate));
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

	public void sortWorkSchedule() { // sorts both the workSchedule and Completed workSchedule arrayLists by start date
		depots.get(depotNo).getWorkSchedules().sort(Comparator.comparing(WorkSchedule::getStartDate));
		depots.get(depotNo).getCompletedWorkSchedules().sort(Comparator.comparing(WorkSchedule::getStartDate));
		//completedWorkSchedules.sort(Comparator.comparing(WorkSchedule::getStartDate));
	}

	public void completeWorkSchedule() throws Exception
	{
		boolean found = false, assigned = false;
		WorkSchedule ws;
		do {
			if(depots.get(depotNo).getWorkSchedules().size() !=0) { // Make sure there is still active work schedules
				depots.get(depotNo).listWorkSchedulue();
				System.out.printf("%nEnter the name of the client's schedule you wish to set as complete: ");
				String choice = input.nextLine();
					//LocalDate.parse("2020-05-08")); // Used as a place holder that we set equal to an existing schedulue
				// needs fixing
				for (WorkSchedule schedule : depots.get(depotNo).getWorkSchedules()) {
					if (choice.equals(schedule.client) && schedule.getDriverAssigned() != null) {
						found = true; assigned = true;
					} else {
						assigned = false;
					}
				} if (!assigned) {
					System.out.printf("%nPlease enter a schedule that has a Driver assigned to it.%n");
					found = true; // This is so if there is no schedules with a driver assigned to it, it will break the loop.
				}

				ws = depots.get(depotNo).getWorkSchedule(choice);
				depots.get(depotNo).addCompletedWorkSchedule(ws);
				depots.get(depotNo).getWorkSchedules().remove(ws);

				for (Driver driver : depots.get(depotNo).getDrivers()) {
					if (driver.getSchedule() != null) { // Checks if a driver has an assigned schedule
						WorkSchedule ws1 = driver.getSchedule();
						String client = ws1.getClient();
						if (client.equals(ws.getClient())) { // Checks the assigned schedule matches
																			// the one we want to delete
							driver.setSchedule(null); // Deletes schedule from driver
							driver.setAssigned(false); // Sets the drivers assigned attribute to false
							sortWorkSchedule(); // Sorts the workschedulues by Start date
						}
					}

				}
			} else {
				System.out.printf("%nThere are currently no active work schedules%n");
			}
		} while (!found);
	}

	 /*public void listVehicles()
	{
		System.out.printf("%-10s %-10s %-12s %6s %10s%n", "Make", "Model", "Registration", "Depot", "Type"); // Used to format the print in a table like structure
		for (Vehicle vehicle : vehicles)
		{
			System.out.printf("%-10s %-10s %-10s %12s %8s %n", vehicle.make, vehicle.model,
				vehicle.regNo, vehicle.depot, vehicle.getClass().getName());
		}
	}
*/

	/**
	 * This method allows a user with manager permissions to reassign vehicles.
	 * They will select a vehicle from the menu and then select the new depot
	 * that they want to send the selected vehicle to.
	 */
	public void reAssignVehicles()
	{
		String vehicleSelection;
		String depotSelection;
		boolean exit = false, validReg = false, validLocation = false, removeMan = false, removeLee = false, removeLiv = false;
		if (!depots.get(depotNo).getVehicles().isEmpty())
		{
			do
			{
				System.out.println("Depots");
				depots.get(depotNo).listVehicles();
				System.out.printf("%nSelect the registration number of the vehicle you wish to reassign: %n");
				vehicleSelection = input.nextLine();
				for (Vehicle vehicle : depots.get(depotNo).getVehicles())
				{

					if (vehicleSelection.equals(vehicle.getRegNo()))
					{
						validReg = true;
						System.out.printf("%nYou have selected vehicle: %s%n", vehicleSelection);
						listDepots();
						System.out.printf("%nPlease select the depot you want to reassign this vehicle to: %n");
						depotSelection = input.nextLine();

						for (Depot d : depots)
						{
							if (depotSelection.equals(d.getDepotLocation()) && !depotSelection.equals(vehicle.getDepot()))
							{
								validLocation = true;
								System.out.printf("%nVehicle %s is now assigned to %s%n", vehicleSelection, depotSelection);
								vehicle.setDepot(getDepot(depotSelection));
							/*	if (depotSelection.equals("Lpool")) { // invalid code, doesn't work But I was trying to remove the vehicle from one depo and move it to the next
									removeLiv = true;
									Vehicle v = depots.get(depotNo).getVehiclebyReg(vehicleSelection);
									depots.get(0).makeVehicle(v);
									depots.get(depotNo).removeByVehicleReg(vehicleSelection);
									exit = true;
									break;
								}
								if (depotSelection.equals("Leeds")) {
								Vehicle v = depots.get(depotNo).getVehiclebyReg(vehicleSelection);
								depots.get(1).makeVehicle(v);
								depots.get(depotNo).removeByVehicleReg(vehicleSelection);
								exit = true;
								break;
								}
								if (depotSelection.equals("MChester")) {

									Vehicle v = depots.get(depotNo).getVehiclebyReg(vehicleSelection);
									depots.get(2).makeVehicle(v);
									depots.get(depotNo).removeByVehicleReg(vehicleSelection);
									exit = true;
									break;
								} */
								exit = true;
								break;

							}
							else
							{
								validLocation = false;
							}
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
				if (!validLocation)
				{
					System.out.printf("%nThe vehicle is either already assigned to the depot, or the depot does not exist%n%n");
				}

			} while (!exit);
		}
		System.out.println("There are no vehicles in the Depot to reassign");
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
			vMake = input.nextLine().trim();
			if (!vMake.matches(".*\\d.*")) { // This prevents the user from entering numbers as a name.

				System.out.printf("%nEnter the Vehicles Model i.e [DS-12]: ");
				vModel = input.nextLine().trim().toUpperCase();

				System.out.printf("%nEnter the Vehicles RegNo: i.e [UT19PAL]: ");
				vRegNo = input.nextLine().toUpperCase();

				try {
					System.out.printf("%nEnter the Vehicles capacity: i.e [2500]: ");
					capacity = Integer.parseInt(input.nextLine()); // I have parsed a int from a string because using nextInt alone was causing errors later in the method.

					System.out.printf("%nEnter the Vehicles weight: i.e [12000]: ");
					weight = Integer.parseInt(input.nextLine());

				} catch (NumberFormatException e) {
					System.out.println("Please Enter a valid number.");
					//e.printStackTrace(); // debugging code
					break;
				}

				for (Vehicle v : depots.get(depotNo).getVehicles()) {
					if (vRegNo.equals(v.getRegNo())) {
						duplicateReg = false;
					}
				}	if (!duplicateReg) { // If the registration is duplicate, print this line
					System.out.println("You have Entered a duplicate Registration number");
					break;
				}
				System.out.printf("%nIs the vehicle a Truck or Tanker: ");
				yesNo = input.nextLine().toLowerCase();
				if (yesNo.equals("truck")) {
					isTruck = true;
				} if (yesNo.equals("tanker")) {
					isTanker = true;
					System.out.printf("%nEnter the Liquid type i.e [Oil]: ");
					liquidType = input.nextLine();
				} else if (!yesNo.equals("truck")) { // else if it doesn't equal truck it can't equal tanker either.
					System.out.println("Enter a valid vehicle type, either truck or tanker.");
					break;
				}

				System.out.printf("%nDepots%n%n");
				listDepots();
				System.out.printf("%nEnter the depots Location: ");
				vDepo = input.nextLine();
				for (Depot depot : depots)
				{
					if (vDepo.equals(depot.getDepotLocation())) // checks if the depot location that the user entered exists
					{
						validLocation = true;
						depotLocation = getDepot(vDepo);
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
				depots.get(depotNo).makeVehicle(new Tanker(vMake, vModel, weight, vRegNo, depotLocation, capacity, liquidType));
			} if (isTruck) {
			depots.get(depotNo).makeVehicle(new Truck(vMake, vModel, weight, vRegNo, depotLocation, capacity));
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
			driverName = input.nextLine().trim();
			if (!driverName.matches(".*\\d.*")) { // prevents entering numbers as a name

				System.out.printf("%nEnter the drivers password: ");
				driverPassword = input.nextLine().trim();

				System.out.printf("%nIs the driver a manager: i.e [yes or no]: ");
				yesNo = input.nextLine().toLowerCase();
				if (yesNo.contains("yes")) {
					isManager = true;
				} else if (yesNo.contains("no")) {
					isManager = false;
				}

				System.out.printf("%nDepots%n%n");
				listDepots();
				System.out.printf("%nEnter the depots Location: ");
				depotLocation = input.nextLine();
				for (Depot depot : depots)
				{
					if (depotLocation.equals(depot.getDepotLocation()))
					{
						validLocation = true;
						depots.get(depotNo).addDriver(new Driver(driverName, driverPassword, false, isManager, getDepot(depotLocation))); // assigned is always false
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
		for (Vehicle v : depots.get(depotNo).getVehicles()) {
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
