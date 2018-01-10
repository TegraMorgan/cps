package cps.api.response;

public class CancelOnetimeParkingResponse extends CustomerResponse {
	private static final long serialVersionUID = 1L;
	private int onetimeServiceID;
	private float refundAmount = 0f;

	public CancelOnetimeParkingResponse(boolean success, String description, int customerID, int onetimeServiceID) {
		super(success, description, customerID);
		this.onetimeServiceID = onetimeServiceID;
	}

	public CancelOnetimeParkingResponse(boolean success, String description) {
		this(success, description, 0, 0);
	}

	public int getOnetimeServiceID() {
		return onetimeServiceID;
	}

	public void setOnetimeServiceID(int onetimeServiceID) {
		this.onetimeServiceID = onetimeServiceID;
	}

	public float getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(float refundAmount) {
		this.refundAmount = refundAmount;
	}

	@Override
	public ServerResponse handle(ResponseHandler handler) {
		return handler.handle(this);
	}
}