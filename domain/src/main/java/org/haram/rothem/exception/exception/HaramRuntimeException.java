package org.haram.rothem.exception.exception;

import lombok.Getter;
import org.haram.rothem.exception.bodycode.BodyCode;

@Getter
public class HaramRuntimeException extends RuntimeException {

  private final BodyCode bodyCode;

  public HaramRuntimeException(BodyCode bodyCode) {
    this.bodyCode = bodyCode;
  }

  public HaramRuntimeException(String message, BodyCode bodyCode) {
    super(message);
    this.bodyCode = bodyCode;
  }

  public HaramRuntimeException(String message, Throwable cause, BodyCode bodyCode) {
    super(message, cause);
    this.bodyCode = bodyCode;
  }

  public HaramRuntimeException(Throwable cause, BodyCode bodyCode) {
    super(cause);
    this.bodyCode = bodyCode;
  }
}
