import java.time.LocalDate;

public class WorkSchedule
{
	private String client;
	private LocalDate startDate;
	private LocalDate endDate;

	public WorkSchedule(String client, LocalDate startDate, LocalDate endDate) throws Exception {
		this.client = client;
		setStartDate(startDate);
		setEndDate(endDate);
	}

	public WorkSchedule(String client) throws Exception {
		this.client = client;
		this.startDate = null;
		this.endDate = null;
	}


	public void setStartDate(LocalDate startDate) throws Exception {
		if (startDate.isAfter(LocalDate.now())) {
			this.startDate = startDate;
		}
		else {
			throw new Exception("Attempt to set start date (" + startDate.toString() + " which is in the past)");
		}
	}

	public void setEndDate(LocalDate endDate) throws Exception {
		if (endDate.isAfter(LocalDate.now().plusDays(1)) && endDate.isBefore(LocalDate.now().plusDays(4))) {
			this.endDate = endDate; // i think the spec said it had to be delivered in 72 hours, so this checks for that
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
		return this.client + " " + this.startDate + " " + this.endDate;
	}
}
