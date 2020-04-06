import java.util.Scanner;

public class Driver {
	protected String userName;
	protected String passWord;
	private boolean assigned; // not sure if this is private or protected yet
	private boolean isManager; // to check if a driver is a manager or not
	private static Scanner input = new Scanner(System.in);
	private static WorkSchedule schedule;



	public Driver(String userName, String passWord, boolean assigned, boolean isManager) {
		// test
		this.userName = userName.trim();
		checkPassword(passWord.trim());
		this.assigned = assigned;
		this.isManager = isManager;

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

	public static void setSchedulue() {
		String client;
		String driver;
		Boolean exit = false;
		
		do {
			Depot.listWorkSchedulue();
			System.out.printf("%nEnter the client name for the schedule you wish to assign: ");
			client = input.nextLine();
			for (WorkSchedule schedules : Depot.getWorkSchedules()) {
				if (client.equals(WorkSchedule.GetClient())) {
					System.out.printf("%nWhich driver do you want to assign this to?%n");
					Depot.listDrivers();
					driver = input.nextLine();
					for (Driver drivers : Depot.getDrivers()) {
						if (driver.equals(drivers.userName) && drivers.assigned == false) {
							System.out.printf("%nThe work schedule for client %s is now assigned to %s%n", client,
									driver);
							schedule = schedules;
							drivers.setAssigned(true);
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

	private void setAssigned(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public String toString() {
		return String.format("%s %s %s %s", userName, passWord, assigned, isManager);
	}

	public class Manager {

		public Manager() {
			// I need to read the labs on nested classes, but I'm sure this will be one
		}
	}
	
	public boolean getIsManager() {
		return isManager;
	}

	public void display(){ //This is just so we can see all the objects in the driver array
		System.out.println("Name: "+this.userName);
		System.out.println("Password: "+this.passWord);

	}

	public static WorkSchedule getSchedule() {
		return schedule;
	}

}
