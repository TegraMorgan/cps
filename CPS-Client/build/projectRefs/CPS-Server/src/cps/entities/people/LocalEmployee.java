package cps.entities.people;

public abstract class LocalEmployee extends Employee {
  private static final long serialVersionUID = 1L;

  public LocalEmployee(int id, String email, String username, String password, String firstName, String lastName,
      String jobTitle, int managerID, int departmentID) {
    super(id, email, username, password, firstName, lastName, jobTitle, managerID, departmentID);
  }
  
  @Override
  public int getLotID() {
    return getDepartmentID();
  }
}
