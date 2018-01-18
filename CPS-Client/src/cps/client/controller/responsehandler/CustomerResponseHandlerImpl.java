package cps.client.controller.responsehandler;

import cps.api.response.CancelOnetimeParkingResponse;
import cps.api.response.ComplaintResponse;
import cps.api.response.FullSubscriptionResponse;
import cps.api.response.IncidentalParkingResponse;
import cps.api.response.ListOnetimeEntriesResponse;
import cps.api.response.ListParkingLotsResponse;
import cps.api.response.LoginResponse;
import cps.api.response.ParkingEntryResponse;
import cps.api.response.ParkingExitResponse;
import cps.api.response.RegularSubscriptionResponse;
import cps.api.response.ReservedParkingResponse;
import cps.api.response.ServerResponse;
import cps.client.controller.ControllersClientAdapter;
import cps.client.controller.ParkingLotsController;

class CustomerResponseHandlerImpl implements CustomerResponseHandler {

  // handlers
  @Override
  public ServerResponse handle(CancelOnetimeParkingResponse response) {
    return ControllersClientAdapter.getCurrentCtrl().handle(response);
  }

  @Override
  public ServerResponse handle(ComplaintResponse response) {
    return ControllersClientAdapter.getCurrentCtrl().handle(response);
  }

  @Override
  public ServerResponse handle(IncidentalParkingResponse response) {
    return ControllersClientAdapter.getCurrentCtrl().handle(response);
  }

  @Override
  public ServerResponse handle(ListOnetimeEntriesResponse response) {
    return ControllersClientAdapter.getCurrentCtrl().handle(response);
  }

  @Override
  public ServerResponse handle(LoginResponse response) {
    return ControllersClientAdapter.getCurrentCtrl().handle(response);
  }

  @Override
  public ServerResponse handle(ParkingEntryResponse response) {
    return ControllersClientAdapter.getCurrentCtrl().handle(response);
  }

  @Override
  public ServerResponse handle(ParkingExitResponse response) {
    return ControllersClientAdapter.getCurrentCtrl().handle(response);
  }

  @Override
  public ServerResponse handle(ListParkingLotsResponse response) {
    ParkingLotsController ctrl = (ParkingLotsController) ControllersClientAdapter.getCurrentCtrl();
    ctrl.setParkingLots(response.getData());
    ctrl.turnProcessingStateOff();
    return response;
  }

  @Override
  public ServerResponse handle(ReservedParkingResponse response) {
    return ControllersClientAdapter.getCurrentCtrl().handle(response);
  }

  @Override
  public ServerResponse handle(RegularSubscriptionResponse response) {
    return ControllersClientAdapter.getCurrentCtrl().handle(response);
  }

  @Override
  public ServerResponse handle(FullSubscriptionResponse response) {
    return ControllersClientAdapter.getCurrentCtrl().handle(response);
  }

}
