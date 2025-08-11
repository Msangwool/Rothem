package org.haram.rothem.exception.exception;

import org.haram.rothem.exception.bodycode.BodyCode;
import lombok.Getter;

@Getter
public class HaramEntityExistException extends HaramRuntimeException {

    private String message;

    public HaramEntityExistException(String message, BodyCode bodyCode) {
        super(message, bodyCode);
    }

}
