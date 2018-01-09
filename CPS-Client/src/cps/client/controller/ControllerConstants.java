package cps.client.controller;

public class ControllerConstants {

  public enum SceneCode {
  
    MAIN_MENU("../view/AlphaGUI_mainMenu.fxml"),
    LOGIN("../view/LoginScene.fxml"),
    CUSTOMER_INITIAL_MENU("../view/CustomerInitialMenuScene.fxml"),
    CUSTOMER_LIST_SUBSCRIPTIONS("../view/CustomerListSubscriptionsScene.fxml"),
    INCIDENTAL_PARKING("../view/AlphaGUI_2.fxml"),
    VIEW_MY_REQUESTS("../view/AlphaGUI_3.fxml"),
    REQUEST_PARKING_ENTRY("../view/AlphaGUI_4.fxml"),
    INIT_PARKING_LOT("../view/AlphaGUI_5.fxml");
  
    String myRelativePath;
    
    SceneCode(String relativePath){
      this.myRelativePath = relativePath;
    }
    
    String getCode() {
      return this.name();
    }
  
  }

  public enum InputVerification {
    INPUT_OK(0, "The request is valid"), 
    MISSING_USERID(0, "Missing or bad UserID"), 
    MISSING_EMAIL(1, "Missing or bad Email"),
    MISSING_CARID(3, "Missing or bad CarID"),
    MISSING_LOTID(4, "Missing or bad LotID"),
    MISSING_PLANNEDENDTIME(5, "Missing or bad Planned End Time");
  
    private final int    id;
    private final String msg;
  
    InputVerification(int id, String msg) {
      this.id = id;
      this.msg = msg;
    }
  
    public int getId() {
      return this.id;
    }
  
    public String getMsg() {
      return this.msg;
    }
  }
  
  // TODO Title maybe unnecessary in 'fxml' files serviceActionsMenuScene/serviceLoginScene/serviceMainMenuScene

}