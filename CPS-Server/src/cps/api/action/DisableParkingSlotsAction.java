package cps.api.action;

public class DisableParkingSlotsAction extends LotAction {
	private static final long serialVersionUID = 1L;
	private int locationI;
	private int locationJ;
	private int locationK;

	public DisableParkingSlotsAction(int userID, int lotID, int locationI, int locationJ, int locationK) {
		super(userID, lotID);
		this.locationI = locationI;
		this.locationJ = locationJ;
		this.locationK = locationK;
	}

	public int getLocationI() {
		return locationI;
	}

	public void setLocationI(int locationI) {
		this.locationI = locationI;
	}

	public int getLocationJ() {
		return locationJ;
	}

	public void setLocationJ(int locationJ) {
		this.locationJ = locationJ;
	}

	public int getLocationK() {
		return locationK;
	}

	public void setLocationK(int locationK) {
		this.locationK = locationK;
	}
}