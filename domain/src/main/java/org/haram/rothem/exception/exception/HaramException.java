package org.haram.rothem.exception.exception;


import org.haram.rothem.exception.bodycode.BodyCode;
import lombok.Getter;

@Getter
public class HaramException extends Exception {

  private final BodyCode bodyCode;

  public HaramException(BodyCode bodyCode) {
    this.bodyCode = bodyCode;
  }

  public HaramException(String message, BodyCode bodyCode) {
    super(message);
    this.bodyCode = bodyCode;
  }

  public HaramException(Throwable cause, BodyCode bodyCode) {
    super(cause);
    this.bodyCode = bodyCode;
  }

  public HaramException(String message, Throwable cause, BodyCode bodyCode) {
    super(message, cause);
    this.bodyCode = bodyCode;
  }
}
