import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 * @author Kyle Ellison-Beattie
 * @author David Lomath Connis
 * This class allows us to define a work schedule
 *
 */
public class WorkSchedule implements Comparable<WorkSchedule>, Serializable
{
	protected String client;
	private LocalDate startDate;
	private LocalDate endDate;
	private Driver driverAssigned;
	/**
	 * This is the constrctor for creating a work schedule object
	 * @param client The name of the client
	 * @param startDate The date we want to start
	 * @param endDate The date delivery is due
	 * @throws Exception
	 */

	public WorkSchedule(String client, LocalDate startDate, LocalDate endDate) throws Exception {
		this.client = client;
		setStartDate(startDate);
		setEndDate(endDate);
		this.driverAssigned = null;
	}

	public WorkSchedule(String client) throws Exception {
		this.client = client;
		this.startDate = null;
		this.endDate = null;
	}

/**
 * This method sets the start date and checks it is not in the past.
 * @param startDate The date we want to start on
 * @throws Exception
 */
	public void setStartDate(LocalDate startDate) throws Exception {
		if (startDate.isAfter(LocalDate.now())) { // Checks to see if the date inputed is after todays date.
			this.startDate = startDate;
		}
		else {
			throw new Exception("Attempt to set start date (" + startDate.toString() + " which is in the past)");
		}
	}
/**
 * This method sets the end date and checks it is a valid date
 * no more than 72 hours in the future from the start date.
 * @param endDate The date delivery is due
 * @throws Exception
 */
	public void setEndDate(LocalDate endDate) throws Exception {
		if (endDate.isAfter(LocalDate.now().plusDays(1)) && endDate.isBefore(startDate.plusDays(4))) {
			this.endDate = endDate; // Checks if the date is 72 hours within the start date.
		}
		else {
			throw new Exception("Attempt to set the end date (" + endDate.toString() + " which is too far in the future)");
		}
	}

	public void setStartDate(String startDate) throws Exception {
		setStartDate(LocalDate.parse(startDate));
	}

	public void setEndDate(String endDate) throws Exception {
		setEndDate(LocalDate.parse(endDate));
	}

	@Override
	public String toString() {
		return String.format("%-10s %-10s %12s %8s", client, startDate, endDate, driverAssigned); // Formatted as a table
	}
/**
 * Sets the driver who is assigned to the schedule
 * @param driver The name of the driver
 */
	public void setDriverAssigned(Driver driver) {
		this.driverAssigned = driver;
	}
/**
* Gets the driver assigned to the schedule
* @return The name of the driver.
*/

	public Driver getDriverAssigned() {
		return driverAssigned;
	}
/**
 * Gets the client for the work schedule
 * @return the client's name
 */
	public String getClient()
	{
		return client;
	}
/**
 * Get the start date of the schedule
 * @return The date we want to start
 */
	public LocalDate getStartDate()
	{
		return startDate;
	}
/**
 * Get the end date of the schedule
 * @return the date delivery is due
 */
	public LocalDate getEndDate()
	{
		return endDate;
	}

	public Object getStartDate(WorkSchedule schedule)
	{
		return schedule.startDate;
	}

	@Override
	public int compareTo(WorkSchedule o)
	{
		return 0;
	}
}
