package cps.server.testing.tests;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import cps.api.request.Request;
import cps.api.response.ServerResponse;
import cps.entities.models.ParkingLot;
import cps.server.ServerConfig;
import cps.server.ServerController;
import cps.server.ServerException;
import cps.server.session.CompanyPersonService;
import cps.server.session.SessionHolder;
import cps.server.testing.utilities.CustomerActor;
import cps.server.testing.utilities.EmployeeActor;
import cps.server.testing.utilities.ParkingLotData;
import cps.server.testing.utilities.ServerControllerTest;
import cps.server.testing.utilities.World;

public class TestStatistics extends ServerControllerTest implements World {
  EmployeeActor[] employees;
  CustomerActor[] customers;
  int numParkingLots;
  int numEmployees;
  Duration timeSlice;
  
  public TestStatistics() {
    super(ServerConfig.testing());
//    super(ServerConfig.local());
  }

  @Before
  public void setUp() throws Exception {
    super.setUp();
    
    timeSlice = Duration.ofMinutes(10);
    numParkingLots = 5;
    numEmployees = CompanyPersonService.getNumberOfEmployees();
    
    // Create employees
    employees = new EmployeeActor[numEmployees];
    
    for (int i = 0; i < employees.length; i++) {
      employees[i] = new EmployeeActor(this, i + 1);
    }
    
    // Create customers
    customers = new CustomerActor[10];
    
    for (int i = 0; i < customers.length; i++) {
      customers[i] = new CustomerActor(this, i + 1);
    }
  }
  
  void tick() {
    setTime(getTime().plus(timeSlice));
  }
  
  void makeLots() throws ServerException {
    ParkingLotData lotData[] = new ParkingLotData[] {
        new ParkingLotData(0, "Sesame, 1", 4, 5f, 4f, "1.0.0.1"),
        new ParkingLotData(0, "Monaco, 2", 4, 50f, 40f, "1.0.0.1"),
        new ParkingLotData(0, "Dubai, 3", 10, 1000f, 500f, "1.0.0.1"),
        new ParkingLotData(0, "Bangkok, 4", 5, 10f, 7f, "1.0.0.1"),
        new ParkingLotData(0, "Jakarta, 5", 5, 3f, 10f, "1.0.0.1"),
    };
    
    for (int i = 0; i < lotData.length; i++) {
      ParkingLot lot = initParkingLot(lotData[i].streetAddress, lotData[i].width, lotData[i].price1, lotData[i].price2, lotData[i].robotIP); 
      lotData[i].lotID = lot.getId();
    }
  }

  @Test
  public void test() throws ServerException {
    // Enable silent mode
    setSilent(true);
    
    // Initialize parking lots
    makeLots();
    
    // Run in a loop for a specified period    
    LocalDateTime endOfTime = getTime().plusMonths(2);
    
    int epoch = 1;
    int j = 0;
    
    // Make sure that the initial actions are in order, so that the IDs match the tokens    
    for (; getTime().isBefore(endOfTime) && j < customers.length; tick()) {
      System.out.printf("Epoch: %d\n", epoch);
      
      customers[j].act(this);
      if (customers[j].getCustomerID() != 0) {
        j++;
      }
      
      epoch++;
    }
    
    // Do the rest of actions in random order (whoever decides to act first gets to act)
    for (; getTime().isBefore(endOfTime); tick()) {
      System.out.printf("Epoch: %d\n", epoch);
      
      for (int i = 0; i < customers.length; i++) {
        customers[i].act(this);
      }
      
      for (int i = 0; i < employees.length; i++) {
        employees[i].act(this);
      }
      
      epoch++;
    }
  }

  @Override
  public int getNumberOfParkingLots() {
    return numParkingLots;
  }

  @Override
  public Duration getTimeslice() {
    return timeSlice;
  }

  @Override
  public <T extends ServerResponse> T sendRequest(Request request, SessionHolder context, Class<T> type) {
    ServerResponse response = server.dispatch(request, context);
    assertNotNull(response);
    assertThat(response, instanceOf(type));
    return type.cast(response);
  }

  @Override
  public ServerController getServerController() {
    return server;
  }

}
