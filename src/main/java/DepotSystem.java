public class DepotSystem
{
	private Depot depot;

	public DepotSystem() throws Exception
	{
		setDepot("Liverpool");
	}

	public void run() {
		while (true) {
			System.out.println(getDepot().toString());
			depot.listVehicles();
			depot.listDrivers();
			depot.listWorkSchedulue();
			break;
		}
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(String s) throws Exception
	{
		depot = new Depot(s);
	}
}
