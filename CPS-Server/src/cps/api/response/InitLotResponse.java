package cps.api.response;

/** Is sent in response to an InitLot action. */
public class InitLotResponse extends ServerResponse {
  private static final long serialVersionUID = 1L;
  private int               lotID            = 0;

  public int getLotID() {
    return lotID;
  }

  public void setLotID(int lotID) {
    this.lotID = lotID;
  }

  /* (non-Javadoc)
   * @see cps.api.response.Response#handle(cps.api.response.ResponseHandler)
   */
  @Override
  public void handle(ResponseHandler handler) {
    handler.handle(this);
  }
}
