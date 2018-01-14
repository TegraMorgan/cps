package cps.client.controller.service;

import cps.client.controller.ClientControllerBase;
import cps.client.controller.ControllersClientAdapter;
import cps.client.controller.ControllerConstants.SceneCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ServiceActionControllerBase extends ClientControllerBase {

  @FXML
  void handleBackButton(ActionEvent event) {
    ControllersClientAdapter.setStage(SceneCode.SERVICE_ACTION_MENU);
  }

  @FXML
  void handleCancelButton(ActionEvent event) {
    ControllersClientAdapter.setStage(SceneCode.SERVICE_ACTION_MENU);
  }

}
