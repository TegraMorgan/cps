package cps.client.utils;

import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/** Class containing input pattern matching utilities. */
public class FormatValidation {

  /**
   * Enum containing Regexes.
   */
  /**
   * 
   */
  public enum InputFormats {
    USERNAME("^[a-zA-Z0-9._-]{3,}$", "Bad or Missing Username"),

    CARID("[A-Z0-9\\-]{1,12}", "Bad or Missing Car ID"),

    LOCAL_TIME("([01]?[0-9]|2[0-3]):[0-5][0-9]", "Bad or Missing Local Time"),
    // for local date use build-in format checker

    ;

    public enum InputValidation {
      BAD_EMAIL(0, "Missing or bad Email"), WRONG_DATA(1, "Bad email or password, please try again");

      private final int    id;
      private final String msg;

      InputValidation(int id, String msg) {
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

    Pattern pattern;
    String  errorMsg;

    InputFormats(String regex, String errorMsg) {
      this.pattern = Pattern.compile(regex);
      this.errorMsg = errorMsg;
    }

    
    /**
     * @param input to be validated
     * @return true if matches , false otherwise
     */
    public boolean validate(String input) {
      return this.pattern.matcher(input).matches();
    }

    public String errorMsg() {
      return this.errorMsg;
    }

    
    /**
     * @param email string representing email
     * @return true if matches , false otherwise
     */
    public static boolean isValidEmailAddress(String email) {
      boolean result = true;
      try {
        InternetAddress emailAddr = new InternetAddress(email);
        emailAddr.validate();
      } catch (AddressException ex) {
        result = false;
      }
      return result;
    }
  }
}
