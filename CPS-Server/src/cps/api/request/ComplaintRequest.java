package cps.api.request;

import cps.api.response.ServerResponse;

public class ComplaintRequest extends CustomerRequest {
	private static final long serialVersionUID = 1L;

	private String content;

	public ComplaintRequest(int customerID, String content) {
		super(customerID);
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public <T> ServerResponse handle(RequestHandler<T> handler, T session) {
		return handler.handle(this, session);
	}
}
