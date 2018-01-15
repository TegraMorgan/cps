package cps.client.controller.customer;

import java.util.List;

import cps.api.request.ParkingExitRequest;
import cps.client.controller.ControllerConstants;
import cps.client.controller.ControllersClientAdapter;
import cps.client.utils.FormatValidation.InputFormats;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ExitCarController extends CustomerActionControllerBase {

  @FXML // fx:id="carIdTextField"
  private TextField carIdTextField; // Value injected by FXMLLoader

  @FXML
  void handleSubmitButton(ActionEvent event) {
    if (processing) {
      return;
    }
    validateAndSend();
  }

  @FXML // This method is called by the FXMLLoader when initialization is
        // complete
  void initialize() {
    super.baseInitialize();
    assert carIdTextField != null : "fx:id=\"carIdTextField\" was not injected: check your FXML file 'EnterParkingScene.fxml'.";
    ControllersClientAdapter.registerCtrl(this, ControllerConstants.SceneCode.EXIT_PARKING);
    Platform.runLater(() -> infoBox.requestFocus()); // to unfocus the Text
                                                     // Field
  }

  private void validateAndSend() {
    // validation in same order as order in the form
    // out of form
    // customer validation
    int customerId = getCustomerId();
    if (customerId < 0) {
      displayError("Invalid customer ID");
      return;
    }
    // inside the form
    // car id validation
    String carId = getCarId();
    if (!InputFormats.CARID.validate(carId)) {
      displayError(InputFormats.CARID.errorMsg());
      return;
    }

    // lotid handling from the list instead
    int lotId = getLotId();
    if (lotId < 0) {
      displayError("Invalid lot ID");
      return;
    }

    ParkingExitRequest request = new ParkingExitRequest(getCustomerId(), getLotId(), getCarId());
    turnProcessingStateOn();
    ControllersClientAdapter.getClient().sendRequest(request);
  }

  // returns customer context - >=1 if logged in, 0 otherwise
  private int getCustomerId() {
    int id = ControllersClientAdapter.getCustomerContext().getCustomerId();
    return id;
  }

  // return car id or null if empty
  private String getCarId() {
    return carIdTextField.getText();
  }

  // returns lot id or -1 if empty
  private int getLotId() {
    if (ControllersClientAdapter.getLotID() == 0) {
      return -1;
    }
    return ControllersClientAdapter.getLotID();
  }

  @Override
  public void displayInfo(List<Text> formattedText) {
    infoBox.getStyleClass().clear();
    infoBox.getStyleClass().add("infoLabel");
    infoLabel.getChildren().clear();
    for (Text ft : formattedText) {
      infoLabel.getChildren().add(ft);
    }
  }

  @Override
  public void cleanCtrl() {
    // info box clear
    super.cleanCtrl();
    // input fields clear
    carIdTextField.clear();
  }
}