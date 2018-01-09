package cps.api.action;

import cps.api.response.ServerResponse;
import cps.server.RequestHandler;

public class SetFullLotAction extends LotAction {
	private static final long serialVersionUID = 1L;
	int alternativeLotID;
	boolean lotFull;

	public SetFullLotAction(int userID, int lotID, boolean lotfull, int alternativeLotID) {
		super(userID, lotID);
		this.alternativeLotID = alternativeLotID;
		this.lotFull = lotfull;

	}

	public int getAlternativeLotID() {
		return alternativeLotID;
	}

	public void setAlternativeLotID(int alternativeLotID) {
		this.alternativeLotID = alternativeLotID;
	}

	public boolean getLotFull() {
		return lotFull;
	}

	public void setLotFull(boolean lotFull) {
		this.lotFull = lotFull;
	}

	@Override
	public ServerResponse handle(RequestHandler handler) {
		return handler.handle(this);
	}
}
