public class Driver
{
	protected String userName;
	protected String passWord;
	private boolean assigned; // not sure if this is private or protected yet

	public Driver() {
		// test
		this.userName = "Spongebob";
		this.passWord = "Gary1";
		this.assigned = false;
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

}
