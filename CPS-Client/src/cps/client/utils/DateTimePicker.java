/**
 * 
 */
package cps.client.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

/**
 * Created on: 2018-01-09 10:38:44 PM
 */
/**
 * @author firl
 *
 */
public class DateTimePicker extends DatePicker {
  /**
   * 
   */
  public static final String DefaultFormat = "yyyy-MM-dd HH:mm";

  /**
   * 
   */
  private DateTimeFormatter             formatter;
  /**
   * 
   */
  private ObjectProperty<LocalDateTime> dateTimeValue = new SimpleObjectProperty<>(LocalDateTime.now());
  /**
   * 
   */
  private ObjectProperty<String>        format        = new SimpleObjectProperty<String>() {
                                                        public void set(String newValue) {
                                                          super.set(newValue);
                                                          formatter = DateTimeFormatter.ofPattern(newValue);
                                                        }
                                                      };

  /**
   * 
   */
  public DateTimePicker() {
    getStyleClass().add("datetime-picker");
    setFormat(DefaultFormat);
    setConverter(new InternalConverter());

    valueProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue == null) {
        dateTimeValue.set(null);
      } else {
        if (dateTimeValue.get() == null) {
          dateTimeValue.set(LocalDateTime.of(newValue, LocalTime.now()));
        } else {
          LocalTime time = dateTimeValue.get().toLocalTime();
          dateTimeValue.set(LocalDateTime.of(newValue, time));
        }
      }
    });

    dateTimeValue.addListener((observable, oldValue, newValue) -> {
      setValue(newValue == null ? null : newValue.toLocalDate());
    });

    getEditor().focusedProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue)
        simulateEnterPressed();
    });

  }

  /**
   * 
   */
  private void simulateEnterPressed() {
    getEditor().fireEvent(new KeyEvent(getEditor(), getEditor(), KeyEvent.KEY_PRESSED, null, null, KeyCode.ENTER, false,
        false, false, false));
  }

  /**
   * @return
   */
  public LocalDateTime getDateTimeValue() {
    return dateTimeValue.get();
  }

  /**
   * @param dateTimeValue
   */
  public void setDateTimeValue(LocalDateTime dateTimeValue) {
    if (dateTimeValue.isAfter(LocalDateTime.of(1971, 6, 30, 12, 00)))
      this.dateTimeValue.set(dateTimeValue);
    else
      this.dateTimeValue.set(null);
  }

  /**
   * @return
   */
  public ObjectProperty<LocalDateTime> dateTimeValueProperty() {
    return dateTimeValue;
  }

  /**
   * @return
   */
  public String getFormat() {
    return format.get();
  }

  /**
   * @return
   */
  public ObjectProperty<String> formatProperty() {
    return format;
  }

  /**
   * @param format
   */
  public void setFormat(String format) {
    this.format.set(format);
  }

  /**
   * @author firl
   *
   */
  class InternalConverter extends StringConverter<LocalDate> {
    public String toString(LocalDate object) {

      LocalDateTime value = getDateTimeValue();
      return (value != null) ? value.format(formatter) : "";
    }

    public LocalDate fromString(String value) {
      if (value == null) {
        dateTimeValue.set(null);
        return null;
      }

      dateTimeValue.set(LocalDateTime.parse(value, formatter));
      return dateTimeValue.get().toLocalDate();
    }
  }
}
