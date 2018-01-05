package cps.api.action;

import cps.api.response.ServerResponse;
import cps.server.RequestHandler;

public class RequestReportAction extends ServiceAction {
	private static final long serialVersionUID = 1L;
	private String reportType; 

	public RequestReportAction(int userID, String reportType) {
		super(userID);
		this.reportType = reportType;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}	

	@Override
	public ServerResponse handle(RequestHandler handler) {
		return handler.handle(this);
	}
}
