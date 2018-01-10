package cps.api.response;

public class ComplaintResponse extends ServerResponse {
	private static final long serialVersionUID = 1L;
	private int complaintID = 0;

	public int getComplaintID() {
		return complaintID;
	}

	public void setComplaintID(int complaintID) {
		this.complaintID = complaintID;
	}

	@Override
	public ServerResponse handle(ResponseHandler handler) {
		return handler.handle(this);
	}
}
