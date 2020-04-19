import java.time.LocalDate;
import java.util.Scanner;

public class Driver {
	protected String userName;
	protected String passWord;
	private boolean assigned; // not sure if this is private or protected yet
	private boolean isManager; // to check if a driver is a manager or not
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
	}

	public boolean checkPassword(String passWord) {
		// need to write the exception for this
		char[] capsCheck;
		if (passWord.length() >= 8) {
			capsCheck = passWord.toCharArray();
			for (char c : capsCheck) {
				if (Character.isUpperCase(c)) {
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

	public String toString() {
		return String.format("%s %s %s %s", userName, passWord, assigned, isManager);
	}
	
	public boolean getIsManager() {
		return isManager;
	}

	public void display() { //This is just so we can see all the objects in the driver array
		System.out.println("Name: "+this.userName);
		System.out.println("Password: "+this.passWord);

	}

	public WorkSchedule getSchedule() throws Exception {
		return schedule;
	}

	public void setSchedule(WorkSchedule workSchedule) throws Exception {
		this.schedule = workSchedule;
	}

	public void assignSchedule(Depot depotName) throws Exception {
		String client;
		String driver;
		WorkSchedule workSchedule;
		Boolean exit = false;

		do {
			depotName.listWorkSchedulue();
			System.out.print("Enter the client name for the schedule you wish to assign: ");
			client = Depot.input.next();
			for (WorkSchedule schedules : depotName.getWorkSchedules()) {
				if (client.equals(schedules.getClient())) {
					System.out.print("Which driver do you want to assign this to?\n");
					depotName.listDrivers();
					driver = Depot.input.next();
					for (Driver drivers : depotName.getDrivers()) {
						if (driver.equals(drivers.userName) && !drivers.assigned) {
							System.out.printf("%nThe work schedule for client %s is now assigned to %s%n ", client,
								driver);
							depotName.getDriver(driver).setSchedule(depotName.getWorkSchedule(client)); // this needs cleaning up badly
							//schedule = schedules;
							//drivers.setAssigned(true);
							exit = true;
							break;

						} else {
							System.out.printf("%nThis driver is either already assigned or does not exist.%n");

						}


					}


				} else {
					System.out.printf("%nPlease select a valid client name%n");

				}
			}
		} while (exit = false);
	}

}
