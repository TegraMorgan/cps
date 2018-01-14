package cps.client.controller.customer;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.javafx.collections.ObservableListWrapper;

import cps.client.controller.ControllerConstants;
import cps.client.controller.ControllersClientAdapter;
import cps.client.controller.OnetimeEntriesController;
import cps.client.controller.ParkingLotsController;
import cps.entities.models.OnetimeService;
import cps.entities.models.ParkingLot;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

public class ViewMyReservationsController implements ParkingLotsController, OnetimeEntriesController {

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="infoLabel"
  private TextFlow infoLabel; // Value injected by FXMLLoader

  @FXML // fx:id="infoProgress"
  private ProgressIndicator infoProgress; // Value injected by FXMLLoader

  @FXML // fx:id="tableView"
  private TableView<TableOnetimeService> tableView; // Value injected by
                                                    // FXMLLoader

  @FXML // fx:id="infoBox"
  private VBox infoBox; // Value injected by FXMLLoader

  @FXML
  private TableColumn<TableOnetimeService, String> colCancel;

  @FXML
  private TableColumn<TableOnetimeService, String> colType;

  @FXML
  private TableColumn<TableOnetimeService, String> colLeave;

  @FXML
  private TableColumn<TableOnetimeService, String> colStart;

  @FXML
  private TableColumn<TableOnetimeService, String> colCarId;

  @FXML
  private TableColumn<TableOnetimeService, String> colLot;

  @FXML
  void handleBackButton(ActionEvent event) {

  }

  @FXML
  void handleRefreshButton(ActionEvent event) {

  }

  @FXML // This method is called by the FXMLLoader when initialization is
        // complete
  void initialize() {
    assert infoLabel != null : "fx:id=\"infoLabel\" was not injected: check your FXML file 'ViewMyReservationsScene.fxml'.";
    assert infoProgress != null : "fx:id=\"infoProgress\" was not injected: check your FXML file 'ViewMyReservationsScene.fxml'.";
    assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'ViewMyReservationsScene.fxml'.";
    tableView = new TableView<TableOnetimeService>();
    assert infoBox != null : "fx:id=\"infoBox\" was not injected: check your FXML file 'ViewMyReservationsScene.fxml'.";
    ControllersClientAdapter.registerCtrl(this, ControllerConstants.SceneCode.CUSTOMER_INITIAL_MENU);
    Platform.runLater(() -> infoBox.requestFocus()); // to unfocus the Text
                                                     // Field

  }

  @Override
  public void displayInfo(List<Text> formattedText) {
    // TODO Auto-generated method stub

  }

  @Override
  public void displayInfo(String simpleInfoMsg) {
    // TODO Auto-generated method stub

  }

  @Override
  public void displayError(List<Text> formettedErrorMsg) {
    // TODO Auto-generated method stub

  }

  @Override
  public void displayError(String simpleErrorMsg) {
    // TODO Auto-generated method stub

  }

  @Override
  public void turnProcessingStateOn() {
    // TODO Auto-generated method stub

  }

  @Override
  public void turnProcessingStateOff() {
    // TODO Auto-generated method stub

  }

  @Override
  public void turnLoggedInStateOn() {
    // TODO Auto-generated method stub

  }

  @Override
  public void turnLoggedInStateOff() {
    // TODO Auto-generated method stub

  }

  @Override
  public void cleanCtrl() {
    // TODO Auto-generated method stub

  }

  @Override
  public void setOnetimeEntries(List<OnetimeService> list) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setParkingLots(List<ParkingLot> list) {
    // TODO Auto-generated method stub

  }

  private static class TableOnetimeService {

    private SimpleStringProperty type;
    private SimpleStringProperty carID;
    private SimpleStringProperty lotName;
    private SimpleStringProperty startDate;
    private SimpleStringProperty leaveDate;

    public TableOnetimeService(String strType, String strCarID, String strLotName, String strStartDate,
        String strLeaveDate) {
      this.type = new SimpleStringProperty(strType);
      this.carID = new SimpleStringProperty(strCarID);
      this.lotName = new SimpleStringProperty(strLotName);
      this.startDate = new SimpleStringProperty(strStartDate);
      this.leaveDate = new SimpleStringProperty(strLeaveDate);
    }

    public StringProperty typeProperty() {
      if (type == null) {
        type = new SimpleStringProperty(this, "type");
      }
      return type;
    }

    public StringProperty carIDProperty() {
      if (carID == null) {
        carID = new SimpleStringProperty(this, "carID");
      }
      return carID;
    }
    
    public StringProperty lotNameProperty() {
      if (lotName == null) {
        lotName = new SimpleStringProperty(this, "lotName");
      }
      return lotName;
    }

    public StringProperty startDateProperty() {
      if (startDate == null) {
        startDate = new SimpleStringProperty(this, "startDate");
      }
      return type;
    }

    public StringProperty leaveDateProperty() {
      if (leaveDate == null) {
        leaveDate = new SimpleStringProperty(this, "leaveDate");
      }
      return leaveDate;
    }
    
    public String getType() {
      return type.get();
    }

    public String getCarID() {
      return carID.get();
    }

    public String getLotName() {
      return lotName.get();
    }

    public String getStartDate() {
      return startDate.get();
    }

    public String getLeaveDate() {
      return leaveDate.get();
    }

    public void setType(String strType) {
      this.type.set(strType);
    }

    public void setCarID(String strCarID) {
      this.carID.set(strCarID);
    }

    public void setLotName(String strLotName) {
      this.lotName.set(strLotName);
    }

    public void setStartDate(String strStartDate) {
      this.startDate.set(strStartDate);
    }

    public void setLeaveDate(String strLeaveDate) {
      this.leaveDate.set(strLeaveDate);
    }
  }

  // TODO testing
  @FXML
  void addDummyData(ActionEvent event) {
    List<OnetimeService> list = new LinkedList<OnetimeService>();
    for (int i = 0; i < 10; i++) {
      list.add((new OnetimeService(0, 1 + (i % 2), 0, "email" + i, "carid" + i, 1,
          Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), false)));
    }

    ObservableList<TableOnetimeService> tableEntries = new ObservableListWrapper<TableOnetimeService>(
        new LinkedList<TableOnetimeService>());

    list.forEach(service -> tableEntries.add(new TableOnetimeService(Integer.toString(service.getParkingType()),
        service.getCarID(), Integer.toString(service.getLotID()), service.getPlannedStartTime().toString(),
        service.getPlannedEndTime().toString())));

    
    colType = new TableColumn<TableOnetimeService,String>("_Type");
    colType.setCellValueFactory(new PropertyValueFactory<TableOnetimeService,String>("type"));

    colCarId = new TableColumn<TableOnetimeService,String>("_Car ID");
    colCarId.setCellValueFactory(new PropertyValueFactory<TableOnetimeService,String>("carID"));
    
    colLot = new TableColumn<TableOnetimeService,String>("_Lot Name");
    colLot.setCellValueFactory(new PropertyValueFactory<TableOnetimeService,String>("lotName"));
    
    colStart = new TableColumn<TableOnetimeService,String>("_Start Date");
    colStart.setCellValueFactory(new PropertyValueFactory<TableOnetimeService,String>("startDate"));
    
    colLeave = new TableColumn<TableOnetimeService,String>("_Leave Date");
    colLeave.setCellValueFactory(new PropertyValueFactory<TableOnetimeService,String>("leaveDate"));
    
    tableView.getColumns().add(colLot);
    tableView.getColumns().add(colType);
    
    tableView.setItems(tableEntries);

  }

}
