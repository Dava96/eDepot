public class Driver {
	protected String userName;
	protected String passWord;
	private boolean assigned; // not sure if this is private or protected yet
	private boolean isManager; // to check if a driver is a manager or not




	public Driver(String userName, String passWord, boolean assigned, boolean isManager) {
		// test
		this.userName = userName.trim();
		this.passWord = passWord.trim();
		this.assigned = assigned;
		this.isManager = isManager;

	}

	public boolean checkPassword() {
		// check to see if the password is over a length etc...
		return true;
	}

	public boolean isAvaliable() {
		if (assigned) return true;
		return false; // returns false if assigned = true
	}

	public void setSchedulue() {

	}

	public String toString() {
		return userName + " " + passWord;
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


}
