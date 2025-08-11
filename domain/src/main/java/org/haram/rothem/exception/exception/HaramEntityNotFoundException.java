package org.haram.rothem.exception.exception;

import org.haram.rothem.exception.bodycode.BodyCode;
import lombok.Getter;

@Getter
public class HaramEntityNotFoundException extends HaramRuntimeException {

    private String message;

    public HaramEntityNotFoundException(String message, BodyCode bodyCode) {
        super(message, bodyCode);
        this.message = message;
    }

}
