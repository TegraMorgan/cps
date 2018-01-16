package cps.client.controller.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import cps.api.action.RequestLotStateAction;
import cps.api.request.ListParkingLotsRequest;
import cps.api.response.RequestLotStateResponse;
import cps.api.response.ServerResponse;
import cps.client.controller.ControllerConstants.SceneCode;
import cps.client.controller.ControllersClientAdapter;
import cps.client.controller.ParkingLotsController;
import cps.common.Constants;
import cps.entities.models.ParkingCell;
import cps.entities.models.ParkingLot;
import cps.entities.people.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

public class ServiceActionLotStateController extends ServiceActionControllerBase implements ParkingLotsController {

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="gridAnchor"
  private StackPane gridAnchor; // Value injected by FXMLLoader

  @FXML // fx:id="parkingLotsList"
  private ComboBox<String> parkingLotsList; // Value injected by FXMLLoader

  @FXML // fx:id="levelIndicator"
  private Label levelIndicator; // Value injected by FXMLLoader

  @FXML
  private Button overviewButton;

  private ArrayList<GridPane> carsGrids;

  private HashMap<String, ParkingLot> parkingLotsMap = new HashMap<String, ParkingLot>();

  // matrix representing 3D array
  private ArrayList<ArrayList<ArrayList<ParkingCell>>> parkingCell;

  private ArrayList<Text> overviewInfo;

  private ArrayList<Text> cellInfo;

  private Rectangle selectedCar;

  @FXML
  void addDummyData(ActionEvent event) {

  }

  @FXML
  void upperPressed(ActionEvent event) {
    if (!processing) {
      int levelInt = Integer.parseInt(levelIndicator.getText());
      if (levelInt < 3) {
        levelIndicator.setText(Integer.toString(levelInt + 1));
        updateView();
      }
    }
  }

  @FXML
  void lowerPressed(ActionEvent event) {
    if (!processing) {
      int levelInt = Integer.parseInt(levelIndicator.getText());
      if (levelInt > 1) {
        levelIndicator.setText(Integer.toString(levelInt - 1));
        updateView();
      }
    }
  }

  private void updateView() {
    gridAnchor.getChildren().forEach(carsGrid -> carsGrid.setVisible(false));
    gridAnchor.getChildren().get(getCurrentLevelIndex()).setVisible(true);
  }

  @FXML
  void handleRefreshButton(ActionEvent event) {
    if (!processing) {
      validateAndSend();
    }
  }

  @FXML
  void showOverview(ActionEvent event) {
    if (!processing) {
      displayInfo(overviewInfo);
    }
  }

  @FXML // This method is called by the FXMLLoader when initialization is
        // complete
  void initialize() {
    super.baseInitialize();
    assert parkingLotsList != null : "fx:id=\"parkingLotsList\" was not injected: check your FXML file 'ServiceActionLotState.fxml'.";
    assert levelIndicator != null : "fx:id=\"levelIndicator\" was not injected: check your FXML file 'ServiceActionLotState.fxml'.";
    assert overviewButton != null : "fx:id=\"overviewButton\" was not injected: check your FXML file 'ServiceActionLotState.fxml'.";
    initParkingCells();
    initCarsGrids();
    initOverviewInfo();
    initCellInfo();
    ControllersClientAdapter.registerCtrl(this, SceneCode.SERVICE_ACTION_LOT_STATE);
  }

  @Override
  public void cleanCtrl() {
    super.cleanCtrl();
    clearCarsGrids();
    overviewButton.setDisable(true);
    parkingLotsList.getItems().clear();
    parkingLotsList.setDisable(false);
    parkingLotsMap.clear();
    // Get the list of parking lots
    turnProcessingStateOn();
    sendRequest(new ListParkingLotsRequest());
  }

  private void clearParkingCells() {
    parkingCell.forEach(inner -> {
      inner.forEach(inner2 -> {
        inner2.clear();
      });
      inner.clear();
    });
    parkingCell.clear();
  }

  private void initParkingCells() {
    parkingCell = new ArrayList<ArrayList<ArrayList<ParkingCell>>>(Constants.LOT_HEIGHT);
    for (int i = 0; i < Constants.LOT_HEIGHT; i++) {
      parkingCell.add(i, new ArrayList<ArrayList<ParkingCell>>(0));
      for (int j = 0; j < Constants.LOT_DEPTH; j++) {
        parkingCell.get(i).add(new ArrayList<ParkingCell>());
      }
    }
  }

  private void clearCarsGrids() {
    carsGrids.forEach(carsGrid -> {
      carsGrid.getChildren().clear();
    });
    gridAnchor.getChildren().forEach(carsGrid -> carsGrid.setVisible(false));
  }

  private void initCarsGrids() {
    carsGrids = new ArrayList<GridPane>(Constants.LOT_HEIGHT);
    for (int i = 0; i < Constants.LOT_DEPTH; i++) {
      GridPane carsGrid = new GridPane();
      carsGrid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
      carsGrid.setPrefWidth(Double.MAX_VALUE);
      carsGrid.setPrefHeight(Double.MAX_VALUE);
      carsGrid.setVgap(10);
      carsGrid.setHgap(10);
      carsGrid.setPadding(new Insets(10));
      carsGrid.setVisible(false);
      carsGrids.add(i, carsGrid);
    }
    gridAnchor.getChildren().setAll(carsGrids);
  }

  private void initOverviewInfo() {
    overviewInfo = new ArrayList<Text>(15);

    overviewInfo.add(new Text("Capacity : "));
    overviewInfo.add(new Text(""));
    overviewInfo.add(new Text("\n"));

    overviewInfo.add(new Text("Free : "));
    overviewInfo.add(new Text(""));
    overviewInfo.add(new Text("\n"));

    overviewInfo.add(new Text("Disabled : "));
    overviewInfo.add(new Text(""));
    overviewInfo.add(new Text("\n"));

    overviewInfo.add(new Text("Reserved : "));
    overviewInfo.add(new Text(""));
    overviewInfo.add(new Text("\n"));

    overviewInfo.add(new Text("Occupied : "));
    overviewInfo.add(new Text(""));
    overviewInfo.add(new Text("\n"));
  }

  private void updateOverviewInfo() {
    int capacity = parkingCell.size() * parkingCell.get(0).size() * parkingCell.get(0).get(0).size();
    int disabled = new Integer(0);
    int reserved = 0;
    int free = 0;
    int occupied = 0;

    // ArrayList<ArrayList<ArrayList<ParkingCell>>>
    for (ArrayList<ArrayList<ParkingCell>> level : parkingCell) {
      for (ArrayList<ParkingCell> row : level) {
        for (ParkingCell cell : row) {
          if (cell.isDisabled()) {
            ++disabled;
          } else if (cell.isReserved()) {
            ++reserved;
          } else if (cell.isFree()) {
            ++free;
          } else {
            ++occupied;
          }
        }
      }
    }
    overviewInfo.set(1, new Text(Integer.toString(capacity)));
    overviewInfo.set(4, new Text(Integer.toString(free)));
    overviewInfo.set(7, new Text(Integer.toString(disabled)));
    overviewInfo.set(10, new Text(Integer.toString(reserved)));
    overviewInfo.set(13, new Text(Integer.toString(occupied)));

    // overviewInfo.forEach(text -> {
    // text.getStyleClass().add("textFont");
    // });
  }

  private void initCellInfo() {
    cellInfo = new ArrayList<Text>(15);

    cellInfo.add(new Text("Placement : "));
    cellInfo.add(new Text(""));
    cellInfo.add(new Text("\n"));

    cellInfo.add(new Text("Car ID : "));
    cellInfo.add(new Text(""));
    cellInfo.add(new Text("\n"));

    cellInfo.add(new Text("Planned exit : "));
    cellInfo.add(new Text(""));
    cellInfo.add(new Text("\n"));

    cellInfo.add(new Text("Is reserved : "));
    cellInfo.add(new Text(""));
    cellInfo.add(new Text("\n"));

    cellInfo.add(new Text("Is disabled : "));
    cellInfo.add(new Text(""));
    cellInfo.add(new Text("\n"));

    // cellInfo.forEach(text -> {
    // text.getStyleClass().add("textFont");
    // });
  }

  private void updateCellInfo(ParkingCell cell) {

    cellInfo.set(1, new Text("[" + (cell.height + 1) + "," + (cell.depth + 1) + "," + (cell.width + 1) + "]"));
    if (cell.getCarID() != null) {
      cellInfo.set(4, new Text(cell.getCarID()));
    } else {
      cellInfo.set(4, new Text("-"));
    }
    if (cell.getPlannedExitTime() != null) {
      cellInfo.set(7, new Text(cell.getPlannedExitTime().toString()));
    } else {
      cellInfo.set(7, new Text("-"));
    }
    cellInfo.set(10, new Text("" + cell.isReserved()));
    cellInfo.set(13, new Text("" + cell.isDisabled()));

  }

  private int getCurrentLevelIndex() {
    return Integer.parseInt(levelIndicator.getText()) - 1;
  }

  @Override
  void validateAndSend() {
    try {
      User user = requireLoggedInUser();
      ParkingLot lot = parkingLotsMap.get(parkingLotsList.getValue());
      errorIfNull(lot, "Please choose a parking lot");
      turnProcessingStateOn();
      sendRequest(new RequestLotStateAction(user.getId(), lot.getId()));
    } catch (Exception e) {
      displayError(e.getMessage());
    }
  }

  @Override
  public void setParkingLots(Collection<ParkingLot> list) {
    List<String> tmp = new ArrayList<String>();

    parkingLotsMap.clear();

    for (ParkingLot i : list) {
      String address = new String(i.getStreetAddress());
      tmp.add(address);
      parkingLotsMap.put(address, i);
    }

    ObservableList<String> addresses = FXCollections.observableList(tmp);
    fillComboBoxItems(addresses);
  }

  private void fillComboBoxItems(ObservableList<String> addresses) {
    parkingLotsList.getItems().addAll(addresses);
    parkingLotsList.setDisable(false);
  }

  @Override
  public ServerResponse handle(RequestLotStateResponse response) {

    if (response.success()) {

      ParkingCell[][][] content = response.getContent();

      clearParkingCells();
      initParkingCells();
      readContent(content);
      updateView();
      updateOverviewInfo();
      overviewButton.setDisable(false);
      parkingLotsList.setDisable(true);
      turnProcessingStateOff();
      displayInfo(overviewInfo);
    } else {
      turnProcessingStateOff();
      displayError(response.getDescription());
    }
    return null;
  }

  private void readContent(ParkingCell[][][] content) {
    double vgap = carsGrids.get(0).getVgap();
    double hgap = carsGrids.get(0).getHgap();

    Rectangle[][][] rects = new Rectangle[content[0].length][content[0][0].length][content.length];

    double rectWidth = (gridAnchor.getWidth() - vgap) / content[0][0].length - vgap;
    double rectHeight = (gridAnchor.getHeight() - hgap) / content.length - hgap;
    Paint rectPaint;

    for (int level = 0; level < content[0].length; level++) {
      for (int depth = 0; depth < content[0][0].length; depth++) {
        for (int width = 0; width < content.length; width++) {
          ParkingCell currentParkingCell = content[width][level][depth];
          parkingCell.get(level).get(depth).add(width, currentParkingCell);
          if (currentParkingCell.isFree() || currentParkingCell == null) {
            rectPaint = (Paint.valueOf("WHITE"));
          } else if (currentParkingCell.isDisabled()) {
            rectPaint = (Paint.valueOf("RED"));
          } else if (currentParkingCell.isReserved()) {
            rectPaint = (Paint.valueOf("GREEN"));
          } else {
            rectPaint = (Paint.valueOf("BLUE"));
          }
          Rectangle currentRectangle = new Rectangle(rectWidth, rectHeight, rectPaint);
          currentRectangle.setArcHeight(rectWidth * 0.2);
          currentRectangle.setArcWidth(rectHeight * 0.2);
          currentRectangle.setStroke(Paint.valueOf("BLACK"));
          currentRectangle.setStrokeType(StrokeType.INSIDE);
          currentRectangle.setStrokeWidth(Math.min(rectWidth, rectHeight) * 0.1);
          currentRectangle.setOnMouseEntered(evt -> {
            if (selectedCar == null) {
              int rowInd = GridPane.getRowIndex(currentRectangle);
              int colInd = GridPane.getColumnIndex(currentRectangle);
              int levelInd = getCurrentLevelIndex();
              ParkingCell correspondingCell = parkingCell.get(levelInd).get(colInd).get(rowInd);
              updateCellInfo(correspondingCell);
              displayInfo(cellInfo);
            }
          });
          currentRectangle.setOnMouseClicked(evt -> {
            int rowInd = GridPane.getRowIndex(currentRectangle);
            int colInd = GridPane.getColumnIndex(currentRectangle);
            int levelInd = getCurrentLevelIndex();
            ParkingCell correspondingCell = parkingCell.get(levelInd).get(colInd).get(rowInd);
            toggleSelectCar(correspondingCell, currentRectangle);
            if (selectedCar != null) {
              updateCellInfo(correspondingCell);
              displayInfo(cellInfo);
            }
          });
          rects[level][depth][width] = currentRectangle;
        }
      }
    }
    for (int level = 0; level < rects.length; level++) {
      GridPane carsGrid = carsGrids.get(level);
      for (int depth = 0; depth < rects[0].length; depth++) {
        for (int width = 0; width < rects[0][0].length; width++) {
          if (rects[level][depth][width] != null) {
            carsGrid.add(rects[level][depth][width], depth, width);
          }
        }
      }
    }
  }

  private void toggleSelectCar(ParkingCell correspondingCell, Rectangle currentRectangle) {
    // if pressed same or another and was assigned
    if (selectedCar != null) {
      selectedCar.setStroke(Paint.valueOf("BLACK"));
      selectedCar.setStrokeWidth(Math.min(selectedCar.getWidth(), selectedCar.getHeight()) * 0.1);
      selectedCar.getStrokeDashArray().clear();
      if (selectedCar != currentRectangle) {
        selectedCar = currentRectangle;
        currentRectangle.setStroke(Paint.valueOf("GOLDENROD"));
        currentRectangle.setStrokeWidth(Math.min(selectedCar.getWidth(), selectedCar.getHeight()) * 0.2);
      } else { // selectedCar == currentRectangle
        selectedCar = null;
      }
    } else { // nothing was selected
      selectedCar = currentRectangle;
      currentRectangle.setStroke(Paint.valueOf("GOLDENROD"));
      currentRectangle.setStrokeWidth(Math.min(selectedCar.getWidth(), selectedCar.getHeight()) * 0.2);
    }
  }
}
