import java.io.Serializable;

public class Driver implements Serializable
{
	protected String userName;
	protected String passWord;
	private boolean assigned; // not sure if this is private or protected yet
	private boolean isManager; // to check if a driver is a manager or not
	private String location;
	private Depot depot;
	private WorkSchedule schedule;


	public Driver(String userName, String passWord, boolean assigned, boolean isManager) throws Exception
	{
		// test
		this.userName = userName.trim();
		checkPassword(passWord.trim());
		this.assigned = assigned;
		this.isManager = isManager;
		this.schedule = null;
		this.depot = null;
	}

	public Driver(String userName, String passWord, boolean assigned, boolean isManager, Depot location) {
		this.userName = userName.trim();
		checkPassword(passWord.trim());
		this.assigned = assigned;
		this.isManager = isManager;
		this.schedule = null;
		this.depot = location;
	}

	public boolean checkPassword(String passWord) {
		char[] capsCheck;
		if (passWord.length() >= 8) { // checks if the user entered password is within a certain length
			capsCheck = passWord.toCharArray(); // converts the entered password to a charArray.
			for (char c : capsCheck) {
				if (Character.isUpperCase(c)) { // checks for a capital letter
					setPassWord(passWord);
					return true;
				} else {
					System.out.println("Password didn't contain a capital letter");
					return false;
				}
			}
			System.out.println("Password is not over 8 characters long");
			return true;
		}
		// check to see if the password is over a length etc...
		System.out.println("Password is not over 8 characters long");
		return false;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public boolean isAvaliable() {
		if (assigned) return true;
		return false; // returns false if assigned = true
	}

	@Override
	public String toString() {
		return String.format("%s", userName);
	}
	
	public boolean getIsManager() {
		return isManager;
	}

	public void display() { //This is just so we can see all the objects in the driver array
		System.out.println("Name: "+this.userName);
		System.out.println("Password: "+this.passWord);

	}

	public WorkSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(WorkSchedule workSchedule) throws Exception {
		this.schedule = workSchedule;
	}

	public void assignSchedule(Depot depotName) throws Exception {
		String client, driver;
		Boolean exit = false, validClient = false, validDriver = false;
		
		if(depotName.getWorkSchedules().size() != 0) {
			do {
				depotName.listWorkSchedulue();
				System.out.print("Enter the client name for the schedule you wish to assign: ");
				client = DepotSystem.input.nextLine();

				for (WorkSchedule ws : depotName.getWorkSchedules()) {
					if (client.equals(ws.getClient())) {
						validClient = true;
						System.out.printf("%n%20s%n", "Drivers");
						depotName.listDrivers();
						System.out.print("Which Driver do you want to assign this to: ");
						driver = DepotSystem.input.nextLine();

						for (Driver drivers : depotName.getDrivers()) {
							if (driver.equals(drivers.userName) && !drivers.assigned) {
								validDriver = true;
								System.out.printf("%nThe work schedule for client %s is now assigned to %s.%n ", client,
									driver);
								depotName.getDriver(driver).setSchedule(depotName.getWorkSchedule(client));
								drivers.setAssigned(true);
								ws.setDriverAssigned(depotName.getDriver(driver));
								exit = true;
								break;
							} else if ((!driver.equals(drivers.userName) && drivers.assigned)) {
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
	
	public void setAssigned (boolean a) {
		assigned = a;
	}

	public String getUserName()
	{
		return userName;
	}
}
