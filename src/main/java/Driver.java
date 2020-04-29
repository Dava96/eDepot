import java.io.Serializable;

/**
 *
 * @author Kyle Ellison-Beattie
 * @author David Lomath-Connis
 * This class is used to create drivers. It also holds methods
 * that are relevant to drivers.
 *
 */
public class Driver implements Serializable
{
	protected String userName;
	protected String passWord;
	private boolean assigned; // not sure if this is private or protected yet
	private boolean isManager; // to check if a driver is a manager or not
	private String location;
	private Depot depot;
	private WorkSchedule schedule;

	/**
	 * This is a constructor to create a new driver in the system.
	 * @param userName The user name of the driver
	 * @param passWord The password of the driver
	 * @param assigned A boolean value which lets us know if the driver
	 * is currently assigned a work schedule
	 * @param isManager A boolean value which lets us know if the driver
	 * is a manager or not.
	 * @throws Exception
	 */
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

	/**
	 * This is a constructor to create a new driver in the system with a depot location.
	 * @param userName The user name of the driver
	 * @param passWord The password of the driver
	 * @param assigned A boolean value which lets us know if the driver
	 * is currently assigned a work schedule
	 * @param isManager A boolean value which lets us know if the driver
	 * is a manager or not.
	 * @param location Allows us to set which depot the driver is assigned to
	 */
	public Driver(String userName, String passWord, boolean assigned, boolean isManager, Depot location) {
		this.userName = userName.trim();
		checkPassword(passWord.trim());
		this.assigned = assigned;
		this.isManager = isManager;
		this.schedule = null;
		this.depot = location;
	}

	/**
	 * This method is used to check the validity of a drivers password
	 * @param passWord The password which the method is checking
	 * @return True if the password is valid, false if the password is invalid.
	 */
	public boolean checkPassword(String passWord) {
		char[] capsCheck;
		// checks if the user entered password is within a certain length
		capsCheck = passWord.toCharArray(); // converts the entered password to a charArray.
		for (char c : capsCheck) {
			if ((passWord.startsWith("_")) && (passWord.contains(userName))) { // checks for a capital letter
				setPassWord(passWord);
				return true;
			} else {
				System.out.println("Password didn't follow the form of _Username");
				return false;
			}
		}
		// check to see if the password is over a length etc...
		//System.out.println("Password is not over 8 characters long");
		return false;
	}

	/**
	 * This method allows us to set a user's password
	 * @param passWord The password we want to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * This method checks if a driver is currently assigned to a work schedule
	 * @return true if the driver is assigned, false if they are not.
	 */
	public boolean isAvaliable() {
		return assigned;// returns false if assigned = true
	}

	@Override
	public String toString() {
		return String.format("%s", userName);
	}

	/**
	 * Gets the isManager boolean
	 * @return true or false depending on value of isManager
	 */
	public boolean getIsManager() {
		return isManager;
	}

	public void display() { //This is just so we can see all the objects in the driver array
		System.out.println("Name: " + this.userName);
		System.out.println("Password: " + this.passWord);
	}

	/**
	 * Gets a driver's work schedule
	 * @return Will return the driver's schedule, or will return null if the driver is not
	 * assigned to a schedule
	 */
	public WorkSchedule getSchedule() {
		return schedule;
	}

	/**
	 * This method allows us to set the drivers work schedule
	 * @param workSchedule The work schedule we want to set
	 * @throws Exception
	 */
	public void setSchedule(WorkSchedule workSchedule)  {
		this.schedule = workSchedule;
	}

	/**
	 * This method lets us set the assigned value to true or false
	 * @param a the value we are setting assigned to.
	 */
	public void setAssigned (boolean a) {
		assigned = a;
	}

	/**
	 * Gets the user's user name
	 * @return the user name
	 */
	public String getUserName()
	{
		return userName;
	}
}
