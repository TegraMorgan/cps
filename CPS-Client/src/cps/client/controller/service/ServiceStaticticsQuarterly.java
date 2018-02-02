package cps.client.controller.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


import cps.api.action.GetQuarterlyReportAction;
import cps.api.request.ListParkingLotsRequest;
import cps.api.response.ListParkingLotsResponse;
import cps.api.response.QuarterlyReportResponse;
import cps.api.response.ServerResponse;
import cps.client.controller.ControllerConstants.SceneCode;
import cps.client.controller.ControllersClientAdapter;
import cps.client.controller.ParkingLotsController;
import cps.client.controller.ReportsController;
import cps.common.Constants;
import cps.entities.models.MonthlyReport;
import cps.entities.models.ParkingLot;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

// TODO extract controller for each tab to a separate class

public class ServiceStaticticsQuarterly extends ServiceStatitisticsBase
    implements ParkingLotsController, ReportsController {

  //@FXML
  //private TableColumn<TableQuarterlyEntry, String> complaintsColLotID;//NOT needed because user already chose a lot by street address

  @FXML
  private TableColumn<TableQuarterlyEntry, String> complaintsColMonth;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> complaintsColQuarter;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> complaintsColOpenedClaims;
  
  @FXML
  private TableColumn<TableQuarterlyEntry, String> complaintsColClosedClaims;
  
  @FXML
  private TableColumn<TableQuarterlyEntry, String> complaintsColRefundedClaims;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> complaintsColRejectedClaims;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> complaintsColYear;

  @FXML
  private TableView<TableQuarterlyEntry> complaintsTableView;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> disabledCellsColLotID;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> disabledCellsColMonth;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> disabledCellsColNumberOfDisabledCells;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> disabledCellsColQuarter;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> disabledCellsColYear;

  @FXML
  private TableView<TableQuarterlyEntry> disabledCellsTableView;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> ordersColLotID;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> ordersColMonth;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> ordersColQuarter;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> ordersColReservedParkings;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> ordersColIncidentalParkings;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> ordersColRegularSubscriptions;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> ordersColFullSubscriptions;

  @FXML
  private TableColumn<TableQuarterlyEntry, String> ordersColYear;

  @FXML
  private TableView<TableQuarterlyEntry> ordersTableView;

  @FXML
  private DatePicker startDatePicker;
  
  @FXML
  private DatePicker endDatePicker;
  
  @FXML
  private ComboBox<String> parkingLotsList;

  private HashMap<String, ParkingLot> parkingLotsMap = new HashMap<String, ParkingLot>();

  /** List holding the entries */
  private ObservableList<TableQuarterlyEntry> obsEntriesList;

  //private Collection<Complaint> complaints;

  /* (non-Javadoc)
   * @see cps.client.controller.service.ServiceActionControllerBase#handleBackButton(javafx.event.ActionEvent) */
  @FXML
  void handleBackButton(ActionEvent event) {
    ControllersClientAdapter.setStage(SceneCode.SERVICE_STATISTICS_CHOICE);
  }

  /** Handle start date choice.
   * @param event the event */
  @FXML
  void handleStartDateChoice(ActionEvent event) {
    if (processing) {
      return;
    }
    endDatePicker.setDisable(false);
  }
  
  /** Handle end date choice.
   * @param event the event */
  @FXML
  void handleEndDateChoice(ActionEvent event) {
    if (processing) {
      return;
    }
    parkingLotsList.setDisable(false);

    if (!parkingLotsList.getItems().isEmpty()) {
      parkingLotsList.getItems().clear();
      parkingLotsMap.clear();
    }
    // Get the list of parking lots
    turnProcessingStateOn();
    sendRequest(new ListParkingLotsRequest());
  }

  /** Handle lot choice.
   * @param event the event */
  @FXML
  void handleLotChoice(ActionEvent event) {
    if (processing) {
      return;
    }
    int userChosenLotID = parkingLotsMap.get(parkingLotsList.getValue()).getId();
    ControllersClientAdapter.getEmployeeContext().setChosenLotID(userChosenLotID);
    validateAndSend();
  }

  @FXML
  void initialize() {
    super.baseInitialize();
    assert complaintsColMonth != null : "fx:id=\"complaintsColMonth\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert complaintsColOpenedClaims != null : "fx:id=\"complaintsColOpenedClaims\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert complaintsColQuarter != null : "fx:id=\"complaintsColQuarter\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert complaintsColRefundedClaims != null : "fx:id=\"complaintsColRefundedClaims\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert complaintsColRejectedClaims != null : "fx:id=\"complaintsColRejectedClaims\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert complaintsColYear != null : "fx:id=\"complaintsColYear\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert complaintsTableView != null : "fx:id=\"complaintsTableView\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert disabledCellsColLotID != null : "fx:id=\"disabledCellsColLotID\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert disabledCellsColMonth != null : "fx:id=\"disabledCellsColMonth\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert disabledCellsColNumberOfDisabledCells != null : "fx:id=\"disabledCellsColNumberOfDisabledCells\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert disabledCellsColQuarter != null : "fx:id=\"disabledCellsColQuarter\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert disabledCellsColYear != null : "fx:id=\"disabledCellsColYear\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert disabledCellsTableView != null : "fx:id=\"disabledCellsTableView\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert endDatePicker != null : "fx:id=\"endDatePicker\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert ordersColLotID != null : "fx:id=\"ordersColLotID\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert ordersColMonth != null : "fx:id=\"ordersColMonth\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert ordersColQuarter != null : "fx:id=\"ordersColQuarter\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert ordersColFullSubscriptions != null : "fx:id=\"ordersColFullSubscriptions\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert ordersColRegularSubscriptions != null : "fx:id=\"ordersColRegularSubscriptions\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert ordersColIncidentalParkings != null : "fx:id=\"ordersColIncidentalParkings\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert ordersColReservedParkings != null : "fx:id=\"ordersColReservedParkings\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert ordersColYear != null : "fx:id=\"ordersColYear\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert ordersTableView != null : "fx:id=\"ordersTableView\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert parkingLotsList != null : "fx:id=\"parkingLotsList\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";
    assert startDatePicker != null : "fx:id=\"startDatePicker\" was not injected: check your FXML file 'ServiceStaticticsQuarterly.fxml'.";

    // binding to list of monthly reports
    obsEntriesList = FXCollections.observableArrayList();
    this.obsEntriesList = FXCollections.observableArrayList();
    complaintsTableView.setItems(this.obsEntriesList);
    disabledCellsTableView.setItems(this.obsEntriesList);
    ordersTableView.setItems(this.obsEntriesList);

    complaintsColYear.setCellValueFactory(cellData -> Bindings.select(cellData.getValue().getYear(), "Year"));//TODO trying to bind 
    
    ControllersClientAdapter.registerCtrl(this, SceneCode.SERVICE_STATISTICS_QUARTERLY);
  }

  @Override
  public void cleanCtrl() {
    super.cleanCtrl();
    startDatePicker.setValue(null);
    endDatePicker.setValue(null);
    endDatePicker.setDisable(true);
    parkingLotsList.setDisable(true);
    parkingLotsMap.clear();
    obsEntriesList.clear();
    //refresh();
  }
  
  /** Validates that the fields and Sends API request to the server. */
  @Override
  void validateAndSend() {
    // validation in same order as order in the form
    LocalDate reportStartDate = getReportStartDate();
    if (reportStartDate == null) {
      displayError("Start date invalid");
      return;
    }
    LocalDate reportEndDate = getReportEndDate();
    if (reportEndDate == null) {
      displayError("End date invalid");
      return;
    }
    if (reportStartDate.compareTo(reportEndDate) >= 0) {
      displayError("End date must be greater than start date");
      return;
    }
    int lotID = ControllersClientAdapter.getEmployeeContext().getChosenLotID();
    int userID = ControllersClientAdapter.getEmployeeContext().getCompanyPerson().getId();
    GetQuarterlyReportAction request = new GetQuarterlyReportAction(userID, Constants.REPORT_TYPE_QUARTERLY, reportStartDate, reportEndDate, lotID);
    turnProcessingStateOn();
    ControllersClientAdapter.getClient().sendRequest(request);
  }
  
  /** @return report start date or null if empty */
  private LocalDate getReportStartDate() {
    return getDate(startDatePicker);
  }

  /** @return report end date or null if empty */
  private LocalDate getReportEndDate() {
    return getDate(endDatePicker);
  }

  private LocalDate getDate(DatePicker picker) {
    if (picker.getValue() == null) {
      return null;
    }
    try {
      return picker.getValue();
    } catch (DateTimeException e) {
      return null;
    }
  }

  /*private void refresh() {
      if (parkingLotsMap.isEmpty()) {
        ListParkingLotsRequest request = new ListParkingLotsRequest();
        // Toggle processing state on
        turnProcessingStateOn();
        ControllersClientAdapter.getClient().sendRequest(request);
      } else if (obsEntriesList.isEmpty()) {
        validateAndSend();
      }//FIXME check this function logic and when it's being called
  }*/

  @Override
  public ServerResponse handle(ListParkingLotsResponse response) {
    if (response.success()) {
      setParkingLots(response.getData());
      displayInfo("Parking lots list retrieved successfully");
    } else {
      displayError("Can't retrieve parking lots");
    }
    turnProcessingStateOff();
    return response;
  }

  @Override
  public ServerResponse handle(QuarterlyReportResponse response) {
    if (response.success()) {
      fillReportTable(response.getData());
      displayInfo("Reports retrieved successfully");
    } else {
      displayError("Can't retrieve quarterly report");
    }
    turnProcessingStateOff();
    return response;
  }

  /*
   * (non-Javadoc)
   * @see cps.client.controller.ParkingLotsController#setParkingLots(java.util.
   * Collection)
   */
  @Override
  public void setParkingLots(Collection<ParkingLot> list) {

    List<String> tmp = new ArrayList<String>();
    parkingLotsMap = new HashMap<String, ParkingLot>();

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
  public void fillReportTable(Collection<MonthlyReport> list) {
    List<TableQuarterlyEntry> newEntriesList = new LinkedList<TableQuarterlyEntry>();
    list.forEach(monthlyReport -> {

      int year = monthlyReport.getYear();
      int quarter = monthlyReport.getMonth() / 4;
      int month = monthlyReport.getMonth();
      int reservedParkings = monthlyReport.getOrdReserved();
      int incidentalParkings = monthlyReport.getOrdIncidental();
      int regularSubscriptions = monthlyReport.getOrdRegular();
      int fullSubscriptions = monthlyReport.getOrdFull();
      int openedClaims = monthlyReport.getComplaintsCount();
      int closedClaims = monthlyReport.getComplaintsClosedCount();
      int refunded = monthlyReport.getComplaintsRefundedCount();
      int rejectedClaims = closedClaims - refunded;
      int numberOfDisabledCells = monthlyReport.getDisabledSlots();

      TableQuarterlyEntry toAdd = new TableQuarterlyEntry(year, quarter, month, reservedParkings,
          incidentalParkings, regularSubscriptions, fullSubscriptions, openedClaims, closedClaims, rejectedClaims, refunded,
          numberOfDisabledCells);
      newEntriesList.add(toAdd);
    });
    
//    obsEntriesList.setAll(newEntriesList); // TODO uncomment when the binding works
  }

  public class TableQuarterlyEntry {

    private SimpleStringProperty year;
    private SimpleStringProperty quarter;
    private SimpleStringProperty month;
    private SimpleStringProperty reservedParkings;
    private SimpleStringProperty incidentalParkings;
    private SimpleStringProperty regularSubscriptions;
    private SimpleStringProperty fullSubscriptions;
    private SimpleStringProperty openedClaims;
    private SimpleStringProperty closedClaims;
    private SimpleStringProperty rejectedClaims;
    private SimpleStringProperty refunded;
    private SimpleStringProperty numberOfDisabledCells;

    public TableQuarterlyEntry(int year, int quarter, int month, int reservedParkings,
        int incidentalParkings, int regularSubscriptions, int fullSubscriptions, int openedClaims, int closedClaims, int rejectedClaims,
        int refunded, int numberOfDisabledCells) {
      super();
      this.year = new SimpleStringProperty(Integer.toString(year));
      this.quarter = new SimpleStringProperty(Integer.toString(quarter));
      this.month = new SimpleStringProperty(Integer.toString(month));
      this.reservedParkings = new SimpleStringProperty(Integer.toString(reservedParkings));
      this.incidentalParkings = new SimpleStringProperty(Integer.toString(incidentalParkings));
      this.regularSubscriptions = new SimpleStringProperty(Integer.toString(regularSubscriptions));
      this.fullSubscriptions = new SimpleStringProperty(Integer.toString(fullSubscriptions));
      this.openedClaims = new SimpleStringProperty(Integer.toString(openedClaims));
      this.closedClaims = new SimpleStringProperty(Integer.toString(closedClaims));
      this.rejectedClaims = new SimpleStringProperty(Integer.toString(rejectedClaims));
      this.refunded = new SimpleStringProperty(Integer.toString(refunded));
      this.numberOfDisabledCells = new SimpleStringProperty(Integer.toString(numberOfDisabledCells));
    }

    public SimpleStringProperty getYear() {
      return year;
    }

    public void setYear(SimpleStringProperty year) {
      this.year = year;
    }

    public SimpleStringProperty getQuarter() {
      return quarter;
    }

    public void setQuarter(SimpleStringProperty quarter) {
      this.quarter = quarter;
    }

    public SimpleStringProperty getMonth() {
      return month;
    }

    public void setMonth(SimpleStringProperty month) {
      this.month = month;
    }

    public SimpleStringProperty getReservedParkings() {
      return reservedParkings;
    }

    public void setReservedParkings(SimpleStringProperty reservedParkings) {
      this.reservedParkings = reservedParkings;
    }

    public SimpleStringProperty getIncidentalParkings() {
      return incidentalParkings;
    }

    public void setIncidentalParkings(SimpleStringProperty incidentalParkings) {
      this.incidentalParkings = incidentalParkings;
    }

    public SimpleStringProperty getRegularSubscriptions() {
      return regularSubscriptions;
    }

    public void setRegularSubscriptions(SimpleStringProperty regularSubscriptions) {
      this.regularSubscriptions = regularSubscriptions;
    }

    public SimpleStringProperty getFullSubscriptions() {
      return fullSubscriptions;
    }

    public void setFullSubscriptions(SimpleStringProperty fullSubscriptions) {
      this.fullSubscriptions = fullSubscriptions;
    }


    public SimpleStringProperty getOpenedClaims() {
      return openedClaims;
    }

    public void setOpenedClaims(SimpleStringProperty openedClaims) {
      this.openedClaims = openedClaims;
    }
    
    public SimpleStringProperty getClosedClaims() {
      return closedClaims;
    }

    public void setClosedClaims(SimpleStringProperty closedClaims) {
      this.closedClaims = closedClaims;
    }

    public SimpleStringProperty getRejectedClaims() {
      return rejectedClaims;
    }

    public void setRejectedClaims(SimpleStringProperty rejectedClaims) {
      this.rejectedClaims = rejectedClaims;
    }

    public SimpleStringProperty getRefunded() {
      return refunded;
    }

    public void setRefunded(SimpleStringProperty refunded) {
      this.refunded = refunded;
    }

    public SimpleStringProperty getNumberOfDisabledCells() {
      return numberOfDisabledCells;
    }

    public void setNumberOfDisabledCells(SimpleStringProperty numberOfDisabledCells) {
      this.numberOfDisabledCells = numberOfDisabledCells;
    }
  }
}