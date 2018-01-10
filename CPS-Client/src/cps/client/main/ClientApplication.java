/**
 *
 */
package cps.client.main;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

import cps.api.request.Request;
import cps.api.response.Response;
import cps.api.response.ResponseHandler;
import cps.api.response.ServerResponse;
import cps.client.controller.ControllerConstants;
import cps.client.controller.ControllerConstants.SceneCode;
import cps.client.controller.responsehandler.ResponseHandlerImpl;
import cps.client.controller.ControllersClientAdapter;
import cps.client.network.CPSNetworkClient;
import cps.client.network.INetworkClient;
import cps.client.utils.CmdParser;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ClientApplication extends Application implements INetworkClient {

  private CPSNetworkClient client;

  private Stage primaryStage;

  private ResponseHandler responseHandler = new ResponseHandlerImpl();

  private int lotID; // required : -1 if web-client

  public int getLotID() {
    return lotID;
  }

  private void setLotID(int lotID) {
    this.lotID = lotID;
  }

  /**
   *
   */
  public ClientApplication() {
  }

  private void loadKiosk() throws IOException {
    try {
      Scene scene = ControllersClientAdapter.registerScene(SceneCode.CUSTOMER_INITIAL_MENU);

      initializeStage(scene, "CPS Kiosk Client");

      ControllersClientAdapter.registerScene(SceneCode.LOGIN);
      ControllersClientAdapter.registerScene(SceneCode.INCIDENTAL_PARKING);
      ControllersClientAdapter.registerScene(SceneCode.CUSTOMER_LIST_SUBSCRIPTIONS);
      ControllersClientAdapter.registerScene(SceneCode.REQUEST_PARKING_ENTRY);
      ControllersClientAdapter.registerScene(SceneCode.VIEW_MY_REQUESTS);
      ControllersClientAdapter.registerScene(SceneCode.INIT_PARKING_LOT);
      ControllersClientAdapter.registerScene(SceneCode.RESERVE_PARKING);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadService() {
    try {
      Scene scene = ControllersClientAdapter.registerScene(SceneCode.SERVICE_ACTION_MENU);

      initializeStage(scene, "CPS Service Client");

      ControllersClientAdapter.registerScene(SceneCode.SERVICE_ACTION_DISABLE_SLOT);
      ControllersClientAdapter.registerScene(SceneCode.SERVICE_ACTION_INIT_LOT);
      ControllersClientAdapter.registerScene(SceneCode.SERVICE_ACTION_LOGIN);
      ControllersClientAdapter.registerScene(SceneCode.SERVICE_ACTION_LOT_IS_FULL);
      ControllersClientAdapter.registerScene(SceneCode.SERVICE_ACTION_LOT_STATE);
      ControllersClientAdapter.registerScene(SceneCode.SERVICE_ACTION_REFUND);
      ControllersClientAdapter.registerScene(SceneCode.SERVICE_ACTION_RESERVE_SLOT);
      ControllersClientAdapter.registerScene(SceneCode.SERVICE_ACTION_UPDATE_PRICES);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void start(Stage primaryStage) {
    try {

      this.primaryStage = primaryStage;
      CmdParser parser = new CmdParser();
      try {
        parser.extract(getParameters().getRaw().toArray(new String[0]));
      } catch (ParseException e) {
        System.exit(1);
      }

      this.client = new CPSNetworkClient(parser.getHost(), parser.getPort(), this);

      ControllersClientAdapter.registerClient(this);

      switch (parser.getMode()) {
        case "webclient":
          // loadWebclient();
          break;
        case "service":
          loadService();
          break;
        default:
          loadKiosk();
      }

      setLotID(parser.getLotId());

    } catch (IOException e) {
      System.out.println("Error: Can't setup connection! Terminating client.");
      System.exit(1);
    } catch (NumberFormatException e) {
      System.out.println("Error: Wrong port or parking lot id");
      System.exit(1);
    }
  }

  private void initializeStage(Scene scene, String title) {
    primaryStage.setTitle(title);
    primaryStage.setScene(scene);
    primaryStage.setMaximized(true);
    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();
    primaryStage.setX(bounds.getMinX());
    primaryStage.setY(bounds.getMinY());
    primaryStage.setWidth(bounds.getWidth());
    primaryStage.setHeight(bounds.getHeight());
    primaryStage.show();
    primaryStage.setOnCloseRequest(e -> {
      Platform.exit();
      System.exit(0);
    });

  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void sendRequest(Object rqst) {
    client.handleMessageFromClientUI(rqst);
  }

  @Override
  public void receiveResponse(Object resp) {

     // TODO handling goes here
     ServerResponse response = responseHandler.dispatch((Response) resp);
  }

  public Stage getPrimaryStage() {
    return primaryStage;
  }

  public void setPrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }
}
