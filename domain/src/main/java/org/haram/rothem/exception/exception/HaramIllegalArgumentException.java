package org.haram.rothem.exception.exception;

import org.haram.rothem.exception.bodycode.BodyCode;
import lombok.Getter;

@Getter
public class HaramIllegalArgumentException extends HaramRuntimeException {

    private final String message;

    public HaramIllegalArgumentException(String message, BodyCode bodyCode) {
        super(message, bodyCode);
        this.message = message;
    }

}
