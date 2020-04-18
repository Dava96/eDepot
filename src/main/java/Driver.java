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
		this.schedule = new WorkSchedule("David", LocalDate.parse("2020-04-20"), LocalDate.parse("2020-04-21"));
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

	public void setSchedule() throws Exception {

	}

}
