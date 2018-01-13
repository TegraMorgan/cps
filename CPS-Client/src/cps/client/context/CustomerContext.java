package cps.client.context;

public interface CustomerContext {
  public int getCustomerId();

  public void setCustomerId(int id);

  public String getCustomerEmail();

  public String getPendingEmail();

  public void setPendingEmail(String pendingEmail);

  public void acceptPendingEmail();

  public boolean isLoggedIn();

  public void setLoggedIn(boolean loggedIn);

  public void logContextOut();
}
